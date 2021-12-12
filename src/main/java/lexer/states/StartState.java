package lexer.states;

import ex.WrongTokenException;
import lexer.Tokenizer;
import lexer.tokens.Brace;
import lexer.tokens.Operation;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.PushbackReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class StartState<N extends Number> extends AbstractState<N> {
    private final Map<Character, Callable<Operation<N>>> charToOp = Map.of(
            '+', Operation.Plus::new,
            '-', Operation.BinaryMinus::new,
            '*', Operation.Multiply::new,
            '/', Operation.Divide::new
    );

    public StartState(final @NotNull @NonNull Tokenizer<N> tokenizer, final @NotNull @NonNull List<Token<N>> accumulatedTokens) {
        super(tokenizer, accumulatedTokens);
    }

    @Override
    public boolean isTerminalState() {
        return false;
    }

    @Override
    public @NotNull State<N> handle() {
        final @NotNull @NonNull PushbackReader reader = tokenizer.getInputReader();
        final @NotNull State<N> newState;

        // Global try-catch block allows for only-return flow
        try {
            final char inputCharacter = (char) (reader.ready() ? reader.read() : -1);
            if (inputCharacter == (char) (-1)) {
                reader.close();
                newState = new EndState<>(tokenizer, accumulatedTokens);
            } else if (Character.isWhitespace(inputCharacter)) {
                newState = this;
            } else if (Character.isDigit(inputCharacter)) {
                reader.unread(inputCharacter);
                newState = new NumberState<>(tokenizer, accumulatedTokens);
            } else if (inputCharacter == '-') {
                // Process unary/binary minus case:
                final @NotNull @NonNull Token<N> opToken;
                if (accumulatedTokens.isEmpty()) {
                    opToken = new Operation.UnaryMinus<>();
                } else {
                    final @NotNull @NonNull Token<N> previousToken = accumulatedTokens.get(accumulatedTokens.size() - 1);
                    if ((previousToken instanceof Brace.LeftBrace) || (previousToken instanceof Operation)) {
                        opToken = new Operation.UnaryMinus<>();
                    } else {
                        opToken = new Operation.BinaryMinus<>();
                    }
                }
                accumulatedTokens.add(opToken);
                newState = this;
            } else if (charToOp.containsKey(inputCharacter)) {
                final @NotNull @NonNull Token<N> opToken;
                opToken = charToOp.get(inputCharacter).call();
                accumulatedTokens.add(opToken);
                newState = this;
            } else if (Brace.isBraceCharacter(inputCharacter)) {
                accumulatedTokens.add(Brace.getBraceByCharacter(inputCharacter));
                newState = this;
            } else {
                throw new WrongTokenException("Unexpected character in input: " + inputCharacter);
            }
        } catch (final Exception error) {
            return new ErrorState<>(tokenizer, accumulatedTokens, error);
        }

        return newState;
    }
}
