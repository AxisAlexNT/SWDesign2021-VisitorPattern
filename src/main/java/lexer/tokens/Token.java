package lexer.tokens;

import visitors.TokenVisitor;

public interface Token<N extends Number> {
    void accept(final TokenVisitor<N> visitor);
}
