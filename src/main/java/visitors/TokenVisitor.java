package visitors;

import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public interface TokenVisitor {
    void visit(final @NotNull @NonNull NumberToken token);

    void visit(final @NotNull @NonNull Brace token);

    void visit(final @NotNull @NonNull Operation token);

    default void visit(final @NotNull @NonNull Token token) {
        if (token instanceof NumberToken) {
            visit((NumberToken) token);
        } else if (token instanceof Brace) {
            visit((Brace) token);
        } else if (token instanceof Operation) {
            visit((Operation) token);
        }
    }
}
