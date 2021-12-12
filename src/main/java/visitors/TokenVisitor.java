package visitors;

import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public interface TokenVisitor<N extends Number> {
    void visit(final @NotNull @NonNull NumberToken<N> token);

    void visit(final @NotNull @NonNull Brace<N> token);

    void visit(final @NotNull @NonNull Operation<N> token);

    default void visit(final @NotNull @NonNull Token<N> token) {
        if (token instanceof NumberToken) {
            visit((NumberToken<N>) token);
        } else if (token instanceof Brace) {
            visit((Brace<N>) token);
        } else if (token instanceof Operation) {
            visit((Operation<N>) token);
        }
    }
}
