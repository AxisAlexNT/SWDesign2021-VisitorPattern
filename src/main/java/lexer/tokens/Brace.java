package lexer.tokens;

import evaluators.PostfixEvaluator;
import ex.WrongTokenException;
import visitors.TokenVisitor;

public abstract sealed class Brace<NT extends PostfixEvaluator<NT>> implements Token<NT> permits Brace.LeftBrace, Brace.RightBrace {

    public static boolean isBraceCharacter(final char ch) {
        return (ch == '(') || (ch == ')');
    }

    public static <T extends PostfixEvaluator<T>> Brace<T> getBraceByCharacter(final char ch) throws WrongTokenException {
        assert isBraceCharacter(ch) : "Non-brace character supplied to the getBraceByCharacter";
        return switch (ch) {
            case '(' -> new LeftBrace<>();
            case ')' -> new RightBrace<>();
            default -> throw new WrongTokenException("Cannot construct a brace from nob-brace character");
        };
    }

    @Override
    public void accept(TokenVisitor<NT> visitor) {
        visitor.visit(this);
    }


    public static final class LeftBrace<NT extends PostfixEvaluator<NT>> extends Brace<NT> {
    }

    public static final class RightBrace<NT extends PostfixEvaluator<NT>> extends Brace<NT> {
    }
}
