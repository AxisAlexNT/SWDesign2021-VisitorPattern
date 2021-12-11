package lexer;

import evaluators.PostfixEvaluator;
import ex.TokenizerException;
import lexer.states.ErrorState;
import lexer.states.StartState;
import lexer.states.State;
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

public class Tokenizer<NT extends PostfixEvaluator<NT>> {
    @Getter(value = AccessLevel.PUBLIC)
    private @NotNull
    final PushbackReader inputReader;
    @Getter(value = AccessLevel.PUBLIC)
    private @NotNull
    final Function<String, Token<NT>> tokenConstructor;
    private @NotNull State<NT, T> state;

    public Tokenizer(final @NotNull @NonNull InputStream inputStream, final @NotNull @NonNull Function<String, T> tokenConstructor) {
        inputReader = new PushbackReader(new InputStreamReader(inputStream));
        this.tokenConstructor = tokenConstructor;
        final @NotNull List<T> tokens = new ArrayList<>();
        this.state = new StartState<NT, T>(this, tokens);
    }

    public List<T> getTokens() {
        assert (state instanceof StartState) : "Attempt to call getTokens() not from the start state?";
        while (!state.isTerminalState()) {
            state = state.handle();
        }

        if (state instanceof ErrorState) {
            throw new TokenizerException("Failed to split input stream into the tokens", ((ErrorState<NT, T>) state).getCause());
        }

        return state.getAccumulatedTokens();
    }
}
