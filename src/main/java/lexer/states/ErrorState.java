package lexer.states;

import evaluators.PostfixEvaluator;
import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ErrorState<NT extends PostfixEvaluator<NT>, T extends Token<NT>> extends AbstractState<NT, T> {
    @Getter
    private final @NotNull @NonNull Throwable cause;

    public ErrorState(final @NotNull @NonNull Tokenizer<NT, T> tokenizer, final @NotNull @NonNull List<T> accumulatedTokens, final @NotNull @NonNull Throwable cause) {
        super(tokenizer, accumulatedTokens);
        this.cause = cause;
    }

    @Override
    public @NotNull State<NT, T> handle() {
        return this;
    }

    @Override
    public boolean isTerminalState() {
        return true;
    }

}
