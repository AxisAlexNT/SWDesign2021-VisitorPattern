package lexer.states;

import lexer.Tokenizer;
import lexer.tokens.Token;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EndState extends AbstractState {
    @Getter
    private final List<Token> accumulatedTokens;

    public EndState(final @NotNull @NonNull Tokenizer tokenizer, final @NotNull @NonNull List<Token> accumulatedTokens) {
        super(tokenizer, accumulatedTokens);
        this.accumulatedTokens = accumulatedTokens;
    }

    @Override
    public boolean isTerminalState() {
        return true;
    }

    @Override
    public @NotNull State handle() {
        return this;
    }

}
