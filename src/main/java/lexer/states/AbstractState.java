package lexer.states;

import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractState<N extends Number> implements State<N> {
    protected final @NotNull Tokenizer<N> tokenizer;
    protected final @NotNull List<Token<N>> accumulatedTokens;

    public AbstractState(final @NotNull @NonNull Tokenizer<N> tokenizer, final @NotNull @NonNull List<Token<N>> accumulatedTokens) {
        this.tokenizer = tokenizer;
        this.accumulatedTokens = accumulatedTokens;
    }

    @Override
    public @NotNull List<Token<N>> getAccumulatedTokens() {
        return this.accumulatedTokens;
    }
}
