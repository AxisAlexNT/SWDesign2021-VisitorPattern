package evaluators;

import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public interface PostfixEvaluator {
    void evaluate(final @NotNull @NonNull Operation op, final @NotNull @NonNull Stack<@NotNull @NonNull NumberToken> args);
}
