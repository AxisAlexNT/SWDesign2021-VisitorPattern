package lexer.tokens;

import evaluators.PostfixEvaluator;
import visitors.TokenVisitor;

public interface Token<T extends PostfixEvaluator<T>> {
    void accept(final TokenVisitor<T> visitor);
}
