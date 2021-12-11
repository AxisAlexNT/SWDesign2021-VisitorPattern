package evaluators;

import lexer.tokens.Operation;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public interface PostfixEvaluator<NT extends Number> {
    void evaluate(final @NotNull @NonNull Operation<NT> op, final @NotNull @NonNull Stack<@NotNull @NonNull Token<NT>> args);
}
