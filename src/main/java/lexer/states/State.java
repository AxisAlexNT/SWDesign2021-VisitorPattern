package lexer.states;

import ex.WrongTokenException;
import lexer.tokens.Token;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface State {
    @NotNull State handle() throws WrongTokenException;

    @NotNull List<Token> getAccumulatedTokens();
}
