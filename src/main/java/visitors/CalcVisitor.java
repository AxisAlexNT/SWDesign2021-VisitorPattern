package visitors;

import evaluators.PostfixEvaluator;
import ex.CalculationException;
import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class CalcVisitor<NT extends NumberToken & PostfixEvaluator<NT>> implements TokenVisitor<NT> {
    private final @NonNull PostfixEvaluator<NT> evaluator;
    private final @NotNull Stack<NT> calcStack = new Stack<>();

    public CalcVisitor(final @NotNull @NonNull PostfixEvaluator<NT> evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public void visit(final @NotNull NT token) {
        calcStack.push(token);
    }

    @Override
    public void visit(final @NotNull Brace token) {
        assert false : "Expression in postfix form must not contain any brackets";
        throw new CalculationException("Expression in postfix form must not contain any brackets");
    }

    @Override
    public void visit(final @NotNull Operation token) {
        evaluator.evaluate(token, calcStack);
    }
}
