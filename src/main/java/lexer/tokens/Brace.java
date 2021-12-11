package lexer.tokens;

import visitors.TokenVisitor;

public class Brace implements Token {
    @Override
    public void accept(TokenVisitor visitor) {
        throw new UnsupportedOperationException("TODO");
    }
}
