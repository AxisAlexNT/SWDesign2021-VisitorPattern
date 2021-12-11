package visitors;

import ex.ParserException;
import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lexer.tokens.Token;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {
    private final Stack<Token> processingStack = new Stack<>();

    private final List<Token> outputExpression = new ArrayList<>();

    @Override
    public void visit(final @NotNull NumberToken token) {
        outputExpression.add(token);
    }

    @Override
    public void visit(final @NotNull Brace token) {
        if (token instanceof Brace.LeftBrace) {
            processingStack.push(token);
            return;
        } else if (token instanceof Brace.RightBrace) {
            while (!processingStack.isEmpty()) {
                final @NotNull Token stackToken = processingStack.pop();
                if (stackToken instanceof Brace.LeftBrace) {
                    return;
                }
                outputExpression.add(stackToken);
            }
            throw new ParserException("Processing stack underflow: misaligned brackets detected");
        }
        throw new ParserException("Unknown bracket type");
    }

    @Override
    public void visit(final @NotNull Operation token) {
        while (!processingStack.isEmpty()) {
            final @NotNull Token topToken = processingStack.peek();
            if ((token.isPrefix()) || !(topToken instanceof Operation)) {
                break;
            }
            final @NotNull Operation topOp = (Operation) topToken;
            if (
                    (
                            topOp.isPrefix()
                    ) || (
                            Operation.BY_PRIORITY_COMPARATOR.compare(topOp, token) < 0
                    ) || (
                            topOp.isLeftAssociative() && (Operation.BY_PRIORITY_COMPARATOR.compare(topOp, token) == 0)
                    )

            ) {
                outputExpression.add(processingStack.pop());
            } else {
                break;
            }
        }
        processingStack.push(token);
    }

    public List<Token> getOutputExpression() {
        while (!processingStack.isEmpty()) {
            final @NotNull Token topToken = processingStack.pop();
            if (topToken instanceof Operation) {
                outputExpression.add(topToken);
            } else {
                throw new ParserException("Expression has misaligned brackets: not only operation symbols were left at processing stack");
            }
        }

        return outputExpression;
    }
}
