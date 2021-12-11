package lexer.states;

import ex.ApplicationRuntimeException;
import ex.WrongTokenException;
import lexer.Tokenizer;
import lexer.tokens.Brace;
import lexer.tokens.Operation;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class StartState extends AbstractState {
    private static final Map<Character, Callable<Operation>> charToOp = Map.of(
            '+', Operation.Plus::new,
            '-', Operation.Minus::new,
            '*', Operation.Multiply::new,
            '/', Operation.Divide::new
    );

    public StartState(final @NotNull @NonNull Tokenizer tokenizer, final @NotNull @NonNull List<Token> accumulatedTokens) {
        super(tokenizer, accumulatedTokens);
    }

    @Override
    public @NotNull State handle() {
        final @NotNull @NonNull InputStreamReader reader = tokenizer.getInputReader();
        final @NotNull State newState;

        final int inputChar;

        try {
            inputChar = reader.read();
            if (inputChar == -1) {
                reader.close();
            }
        } catch (final Exception e) {
            newState = new ErrorState(tokenizer, accumulatedTokens, e);
            return newState;
        }


        if (inputChar == -1) {
            newState = new EndState(tokenizer, accumulatedTokens);
        } else {
            char ch = (char) inputChar;
            if (Character.isWhitespace(ch)) {
                newState = this;
            } else if (Character.isDigit(ch)) {
                newState = new NumberState(tokenizer, accumulatedTokens, ch);
            } else if (charToOp.containsKey(ch)) {
                final @NotNull @NonNull Token opToken;
                try {
                    opToken = charToOp.get(ch).call();
                } catch (final Exception e) {
                    assert false : "Constructor of Operation must not throw an exception";
                    final Throwable error = new ApplicationRuntimeException("Constructor of Operation must not throw an exception", e);
                    newState = new ErrorState(tokenizer, accumulatedTokens, error);
                    return newState;
                }
                accumulatedTokens.add(opToken);
                newState = this;
            } else if (Brace.isBraceCharacter(ch)) {
                accumulatedTokens.add(Brace.getBraceByCharacter(ch));
                newState = this;
            } else {
                final Throwable error = new WrongTokenException("Unexpected character in input: " + ch);
                newState = new ErrorState(tokenizer, accumulatedTokens, error);
            }
        }

        return newState;
    }
}
