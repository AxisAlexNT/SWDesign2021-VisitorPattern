package lexer.tokens;

import visitors.TokenVisitor;

public class Operation implements Token {
    @Override
    public void accept(TokenVisitor visitor) {
        throw new UnsupportedOperationException("TODO");
    }
}
