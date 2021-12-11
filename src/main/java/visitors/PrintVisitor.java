package visitors;

import ex.ApplicationRuntimeException;
import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class PrintVisitor implements TokenVisitor {
    private final @NotNull @NonNull StringBuilder visitBuilder = new StringBuilder();

    @Override
    public void visit(final @NotNull NumberToken token) {
        visitBuilder.append("NUMBER(").append(token.getValue()).append(")").append(' ');
    }

    @Override
    public void visit(final @NotNull Brace token) {
        if (token instanceof Brace.LeftBrace) {
            visitBuilder.append("LEFT");
        } else if (token instanceof Brace.RightBrace) {
            visitBuilder.append("RIGHT");
        } else {
            throw new ApplicationRuntimeException("Unknown bracket type");
        }
        visitBuilder.append(' ');
    }

    @Override
    public void visit(final @NotNull Operation token) {
        visitBuilder.append(token.getClass().getSimpleName().toUpperCase(Locale.US)).append(' ');
    }

    public String getVisitResult() {
        return visitBuilder.toString();
    }
}
