package lexer.states;

import lexer.Tokenizer;
import lexer.tokens.NumberToken;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.PushbackReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberState extends AbstractState {
    private final @NotNull Queue<Character> digitsAccumulator;


    public NumberState(final @NotNull @NonNull Tokenizer tokenizer, final @NotNull @NonNull List<Token> accumulatedTokens) {
        super(tokenizer, accumulatedTokens);
        this.digitsAccumulator = new LinkedList<>();
    }

    @Override
    public @NotNull State handle() {
        final @NotNull @NonNull PushbackReader reader = tokenizer.getInputReader();
        final @NotNull State newState;

        final int inputChar;

        try {
            inputChar = reader.read();
            if (Character.isDigit(inputChar)) {
                digitsAccumulator.add((char) inputChar);
                newState = this;
            } else {
                reader.unread(inputChar);

                // Pop all digits and go to start state
                if (!digitsAccumulator.isEmpty()) {
                    final @NotNull StringBuilder numberBuilder = new StringBuilder();
                    digitsAccumulator.forEach(numberBuilder::append);
                    accumulatedTokens.add(NumberToken.valueOf(numberBuilder.toString()));
                }
                newState = new StartState(tokenizer, accumulatedTokens);
            }
        } catch (final Exception e) {
            return new ErrorState(tokenizer, accumulatedTokens, e);
        }

        return newState;
    }

    @Override
    public boolean isTerminalState() {
        return false;
    }

}
