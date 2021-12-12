package lexer.tokens;

import ex.WrongTokenException;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import visitors.TokenVisitor;

public abstract sealed class Brace<N extends Number> implements Token<N> permits Brace.LeftBrace, Brace.RightBrace {

    public static boolean isBraceCharacter(final char ch) {
        return (ch == '(') || (ch == ')');
    }

    public static <N extends Number> Brace<N> getBraceByCharacter(final char ch) throws WrongTokenException {
        assert isBraceCharacter(ch) : "Non-brace character supplied to the getBraceByCharacter";
        return switch (ch) {
            case '(' -> new LeftBrace<>();
            case ')' -> new RightBrace<>();
            default -> throw new WrongTokenException("Cannot construct a brace from nob-brace character");
        };
    }

    @Override
    public void accept(final @NotNull @NonNull TokenVisitor<N> visitor) {
        visitor.visit(this);
    }

    public static final class LeftBrace<N extends Number> extends Brace<N> {
    }

    public static final class RightBrace<N extends Number> extends Brace<N> {
    }
}
