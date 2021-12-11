package lexer.tokens;

import visitors.TokenVisitor;

public class NumberToken implements Token {
    @Override
    public void accept(TokenVisitor visitor) {
        throw new UnsupportedOperationException("TODO");
    }
}
