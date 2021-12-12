package lexer;

import ex.TokenizerException;
import lexer.states.ErrorState;
import lexer.states.StartState;
import lexer.states.State;
import lexer.tokens.NumberToken;
import lexer.tokens.Token;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Tokenizer<N extends Number> {
    @Getter(value = AccessLevel.PUBLIC)
    private @NotNull
    final PushbackReader inputReader;
    @Getter(value = AccessLevel.PUBLIC)
    private final @NotNull Function<String, NumberToken<N>> numberTokenCreator;
    private @NotNull State<N> state;

    public Tokenizer(final @NotNull @NonNull InputStream inputStream, final @NotNull @NonNull Function<String, NumberToken<N>> numberTokenCreator) {
        inputReader = new PushbackReader(new InputStreamReader(inputStream));
        this.numberTokenCreator = numberTokenCreator;
        final @NotNull List<Token<N>> tokens = new ArrayList<>();
        this.state = new StartState<>(this, tokens);
    }

    public List<Token<N>> getTokens() {
        assert (state instanceof StartState) : "Attempt to call getTokens() not from the start state?";
        while (!state.isTerminalState()) {
            state = state.handle();
        }

        if (state instanceof ErrorState<N>) {
            throw new TokenizerException("Failed to split input stream into the tokens", ((ErrorState<N>) state).getCause());
        }

        return state.getAccumulatedTokens();
    }
}
