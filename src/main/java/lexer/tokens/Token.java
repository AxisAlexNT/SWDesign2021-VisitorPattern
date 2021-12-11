package lexer.tokens;

import visitors.TokenVisitor;

public interface Token {
    void accept(final TokenVisitor visitor);
}
