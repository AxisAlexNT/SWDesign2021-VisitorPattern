package lexer.states;

import evaluators.PostfixEvaluator;
import ex.WrongTokenException;
import lexer.tokens.Token;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface State<NT extends PostfixEvaluator<NT>> {
    @NotNull State<NT> handle() throws WrongTokenException;

    @NotNull List<Token<NT>> getAccumulatedTokens();

    boolean isTerminalState();
}
