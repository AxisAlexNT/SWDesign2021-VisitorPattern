package lexer.states;

import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EndState<N extends Number> extends AbstractState<N> {
    @Getter
    private final List<Token<N>> accumulatedTokens;

    public EndState(final @NotNull @NonNull Tokenizer<N> tokenizer, final @NotNull @NonNull List<Token<N>> accumulatedTokens) {
        super(tokenizer, accumulatedTokens);
        this.accumulatedTokens = accumulatedTokens;
    }

    @Override
    public boolean isTerminalState() {
        return true;
    }

    @Override
    public @NotNull State<N> handle() {
        return this;
    }

}
