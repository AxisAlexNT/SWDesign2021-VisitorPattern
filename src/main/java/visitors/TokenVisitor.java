package visitors;

import evaluators.PostfixEvaluator;
import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public interface TokenVisitor<NT extends NumberToken & PostfixEvaluator<NT>> {
    void visit(final @NotNull @NonNull NT token);

    void visit(final @NotNull @NonNull Brace token);

    void visit(final @NotNull @NonNull Operation token);


    default void visit(final @NotNull @NonNull Token token, final @NotNull @NonNull PostfixEvaluator<NT> evaluator) {
        if (token instanceof NumberToken) {
            visit((NT) token);
        } else if (token instanceof Brace) {
            visit((Brace) token);
        } else if (token instanceof Operation) {
            visit((Operation) token);
        }
    }
}
