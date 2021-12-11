package lexer.states;

import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractState implements State {
    protected final @NotNull Tokenizer tokenizer;
    protected final @NotNull List<Token> accumulatedTokens;

    public AbstractState(final @NotNull @NonNull Tokenizer tokenizer, final @NotNull @NonNull List<Token> accumulatedTokens) {
        this.tokenizer = tokenizer;
        this.accumulatedTokens = accumulatedTokens;
    }

    @Override
    public @NotNull List<Token> getAccumulatedTokens() {
        return this.accumulatedTokens;
    }
}
