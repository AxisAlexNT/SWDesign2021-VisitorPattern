package lexer.tokens;

import evaluators.PostfixEvaluator;
import visitors.TokenVisitor;

public interface Token<NT extends NumberToken & PostfixEvaluator<NT>> {
    void accept(final TokenVisitor<NT> visitor);
}
