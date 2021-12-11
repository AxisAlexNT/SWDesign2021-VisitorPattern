package lexer.states;

import evaluators.PostfixEvaluator;
import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EndState<NT extends PostfixEvaluator<NT>, T extends Token<NT>> extends AbstractState<NT, T> {
    @Getter
    private final List<T> accumulatedTokens;

    public EndState(final @NotNull @NonNull Tokenizer<NT, T> tokenizer, final @NotNull @NonNull List<T> accumulatedTokens) {
        super(tokenizer, accumulatedTokens);
        this.accumulatedTokens = accumulatedTokens;
    }

    @Override
    public boolean isTerminalState() {
        return true;
    }

    @Override
    public @NotNull State<NT, T> handle() {
        return this;
    }

}
