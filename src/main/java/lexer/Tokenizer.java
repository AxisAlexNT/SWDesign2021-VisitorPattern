package lexer;

import lexer.states.EndState;
import lexer.states.StartState;
import lexer.states.State;
import lexer.tokens.Token;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private @NotNull State state;
    @Getter(value = AccessLevel.PUBLIC)
    private @NotNull InputStreamReader inputReader;

    public Tokenizer(final @NotNull @NonNull InputStream inputStream) {
        inputReader = new InputStreamReader(inputStream);
        final @NotNull List<Token> tokens = new ArrayList<>();
        this.state = new StartState(this, tokens);
    }

    public List<Token> getTokens(){
        assert (this.state instanceof StartState) : "Attempt to call getTokens() not from the start state?";
        while (!(this.state instanceof EndState)){
            this.state = this.state.handle();
        }
        return this.state.getAccumulatedTokens();
    }
}
