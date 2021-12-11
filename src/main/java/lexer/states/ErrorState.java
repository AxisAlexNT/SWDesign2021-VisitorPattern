package lexer.states;

import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ErrorState extends AbstractState {
    @Getter
    private final @NotNull @NonNull Throwable cause;

    public ErrorState(final @NotNull @NonNull Tokenizer tokenizer, final @NotNull @NonNull List<Token> accumulatedTokens, final @NotNull @NonNull Throwable cause) {
        super(tokenizer, accumulatedTokens);
        this.cause = cause;
    }

    @Override
    public @NotNull State handle() {
        throw new UnsupportedOperationException("TODO");
    }

}
