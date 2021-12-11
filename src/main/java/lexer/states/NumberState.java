package lexer.states;

import evaluators.PostfixEvaluator;
import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.PushbackReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberState<NT extends PostfixEvaluator<NT>, T extends Token<NT>> extends AbstractState<NT, T> {
    private final @NotNull Queue<Character> digitsAccumulator;

    public NumberState(final @NotNull @NonNull Tokenizer<NT, T> tokenizer, final @NotNull @NonNull List<T> accumulatedTokens) {
        super(tokenizer, accumulatedTokens);
        this.digitsAccumulator = new LinkedList<>();
    }

    @Override
    public @NotNull State<NT, T> handle() {
        final @NotNull @NonNull PushbackReader reader = tokenizer.getInputReader();
        final @NotNull State<NT, T> newState;

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
                    accumulatedTokens.add(tokenizer.getTokenConstructor().apply(numberBuilder.toString()));
                }
                newState = new StartState<NT, T>(tokenizer, accumulatedTokens);
            }
        } catch (final Exception e) {
            return new ErrorState<>(tokenizer, accumulatedTokens, e);
        }

        return newState;
    }

    @Override
    public boolean isTerminalState() {
        return false;
    }

}
