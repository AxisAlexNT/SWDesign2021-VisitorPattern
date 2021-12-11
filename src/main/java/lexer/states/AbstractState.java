package lexer.states;

import evaluators.PostfixEvaluator;
import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractState<NT extends PostfixEvaluator<NT>> implements State<NT> {
    protected final @NotNull Tokenizer<NT> tokenizer;
    protected final @NotNull List<Token<NT>> accumulatedTokens;

    public AbstractState(final @NotNull @NonNull Tokenizer<NT> tokenizer, final @NotNull @NonNull List<Token<NT>> accumulatedTokens) {
        this.tokenizer = tokenizer;
        this.accumulatedTokens = accumulatedTokens;
    }

    @Override
    public @NotNull List<Token<NT>> getAccumulatedTokens() {
        return this.accumulatedTokens;
    }
}
