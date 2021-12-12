package lexer.states;

import ex.WrongTokenException;
import lexer.tokens.Token;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface State<N extends Number> {
    @NotNull State<N> handle() throws WrongTokenException;

    @NotNull List<Token<N>> getAccumulatedTokens();

    boolean isTerminalState();
}
