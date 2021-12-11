package visitors;

import ex.CalculationException;
import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class CalcVisitor implements TokenVisitor {
    private final @NotNull Stack<NumberToken> calcStack = new Stack<>();

    @Override
    public void visit(final @NotNull NumberToken token) {
        calcStack.push(token);
    }

    @Override
    public void visit(final @NotNull Brace token) {
        assert false : "Expression in postfix form must not contain any brackets";
        throw new CalculationException("Expression in postfix form must not contain any brackets");
    }

    @Override
    public void visit(final @NotNull Operation token) {
        NumberToken.getEVALUATOR().evaluate(token, calcStack);
    }

    public @NotNull @NonNull NumberToken getResult() {
        if (calcStack.empty()) {
            throw new CalculationException("Stack underflow: no result was calculated");
        } else if (calcStack.size() > 1) {
            throw new CalculationException("Stack overflow: more than one value is present on the stack");
        } else {
            return calcStack.peek();
        }
    }
}
