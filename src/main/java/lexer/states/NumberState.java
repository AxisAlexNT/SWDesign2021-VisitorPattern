package lexer.states;

import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberState extends AbstractState {
    private final @NotNull Queue<Character> digitsAccumulator;


    public NumberState(final @NotNull @NonNull Tokenizer tokenizer, final @NotNull @NonNull List<Token> accumulatedTokens, final char digit) {
        super(tokenizer, accumulatedTokens);
        this.digitsAccumulator = new LinkedList<>();
        assert Character.isDigit(digit) : "First symbol in NumberState must be a digit";
        digitsAccumulator.add(digit);
    }

    @Override
    public @NotNull State handle() {
        throw new UnsupportedOperationException("TODO");
    }

}
