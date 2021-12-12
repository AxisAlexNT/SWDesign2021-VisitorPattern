package lexer.states;

import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ErrorState<N extends Number> extends AbstractState<N> {
    @Getter
    private final @NotNull @NonNull Throwable cause;

    public ErrorState(final @NotNull @NonNull Tokenizer<N> tokenizer, final @NotNull @NonNull List<Token<N>> accumulatedTokens, final @NotNull @NonNull Throwable cause) {
        super(tokenizer, accumulatedTokens);
        this.cause = cause;
    }

    @Override
    public @NotNull State<N> handle() {
        return this;
    }

    @Override
    public boolean isTerminalState() {
        return true;
    }

}
