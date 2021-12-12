package visitors;

import evaluators.PostfixEvaluator;
import ex.CalculationException;
import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class CalcVisitor<N extends Number> implements TokenVisitor<N> {
    private final @NotNull Stack<NumberToken<N>> calcStack = new Stack<>();

    private final @NotNull PostfixEvaluator<N> evaluator;

    public CalcVisitor(final @NotNull @NonNull PostfixEvaluator<N> evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public void visit(final @NotNull NumberToken<N> token) {
        calcStack.push(token);
    }

    @Override
    public void visit(final @NotNull Brace<N> token) {
        assert false : "Expression in postfix form must not contain any brackets";
        throw new CalculationException("Expression in postfix form must not contain any brackets");
    }

    @Override
    public void visit(final @NotNull Operation<N> token) {
        evaluator.evaluate(token, calcStack);
    }

    public @NotNull @NonNull NumberToken<N> getResult() {
        if (calcStack.empty()) {
            throw new CalculationException("Stack underflow: no result was calculated");
        } else if (calcStack.size() > 1) {
            throw new CalculationException("Stack overflow: more than one value is present on the stack");
        } else {
            return calcStack.peek();
        }
    }
}
