package visitors;

import evaluators.PostfixEvaluator;
import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public interface TokenVisitor<NT extends PostfixEvaluator<NT>> {
    void visit(final @NotNull @NonNull NumberToken<NT> token);

    void visit(final @NotNull @NonNull Brace<NT> token);

    void visit(final @NotNull @NonNull Operation<NT> token);


    default void visit(final @NotNull @NonNull Token<NT> token, final @NotNull @NonNull PostfixEvaluator<NT> evaluator) {
        if (token instanceof NumberToken) {
            visit((NumberToken<NT>) token);
        } else if (token instanceof Brace) {
            visit((Brace<NT>) token);
        } else if (token instanceof Operation) {
            visit((Operation<NT>) token);
        }
    }
}
