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
import java.util.function.Function;

public class NumberState<N extends Number> extends AbstractState<N> {
    private final @NotNull Queue<Character> digitsAccumulator;

    private final @NotNull Function<String, NumberToken<N>> numberTokenCreator;


    public NumberState(final @NotNull @NonNull Tokenizer<N> tokenizer, final @NotNull @NonNull List<Token<N>> accumulatedTokens) {
        super(tokenizer, accumulatedTokens);
        this.digitsAccumulator = new LinkedList<>();
        this.numberTokenCreator = tokenizer.getNumberTokenCreator();
    }

    @Override
    public @NotNull State<N> handle() {
        final @NotNull @NonNull PushbackReader reader = tokenizer.getInputReader();
        final @NotNull State<N> newState;

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
                    accumulatedTokens.add(numberTokenCreator.apply(numberBuilder.toString()));
                }
                newState = new StartState<>(tokenizer, accumulatedTokens);
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
