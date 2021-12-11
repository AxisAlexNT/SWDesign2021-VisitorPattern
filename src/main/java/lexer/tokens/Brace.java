package lexer.tokens;

import ex.WrongTokenException;
import visitors.TokenVisitor;

public abstract sealed class Brace implements Token permits Brace.LeftBrace, Brace.RightBrace {

    public static boolean isBraceCharacter(final char ch){
        return (ch == '(') || (ch == ')');
    }

    public static Brace getBraceByCharacter(final char ch) throws WrongTokenException{
        assert isBraceCharacter(ch) : "Non-brace character supplied to the getBraceByCharacter";
        return switch (ch) {
            case '(' -> new LeftBrace();
            case ')' -> new RightBrace();
            default -> throw new WrongTokenException("Cannot construct a brace from nob-brace character");
        };
    }

    public static final class LeftBrace extends Brace {
        @Override
        public void accept(TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static final class RightBrace extends Brace {
        @Override
        public void accept(TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
