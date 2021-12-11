package lexer.tokens;

import evaluators.PostfixEvaluator;
import ex.CalculationException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import visitors.TokenVisitor;

import java.util.Map;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class NumberToken implements Token {
    @Getter(value = AccessLevel.PUBLIC)
    protected static final PostfixEvaluator EVALUATOR = new Evaluator();

    @Getter(value = AccessLevel.PUBLIC)
    protected final long value;

    protected NumberToken(final long value) {
        this.value = value;
    }

    protected NumberToken(final @NotNull @NonNull String stringValue) {
        this.value = Long.parseLong(stringValue);
    }

    public static NumberToken valueOf(final @NotNull @NonNull String stringValue) {
        return new NumberToken(stringValue);
    }

    @Override
    public void accept(final @NotNull @NonNull TokenVisitor visitor) {
        visitor.visit(this);
    }


    private static class Evaluator implements PostfixEvaluator {
        private static final @NotNull @NonNull Map<Class<? extends Operation>, BinaryOperator<@NotNull @NonNull Long>> binaryOperatorMap = Map.of(
                Operation.Plus.class, Long::sum,
                Operation.BinaryMinus.class, (final @NotNull @NonNull Long a, final @NotNull @NonNull Long b) -> a - b,
                Operation.Multiply.class, (final @NotNull @NonNull Long a, final @NotNull @NonNull Long b) -> a * b,
                Operation.Divide.class, (final @NotNull @NonNull Long a, final @NotNull @NonNull Long b) -> {
                    if (b == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    return a / b;
                }
        );

        @Override
        public void evaluate(final @NotNull @NonNull Operation op, final @NotNull @NonNull Stack<@NotNull @NonNull NumberToken> argStack) {
            final long result;
            if (op instanceof Operation.UnaryMinus) {
                if (argStack.empty()) {
                    throw new CalculationException("Stack underflow during evaluation of UnaryMinus");
                }
                final @NotNull @NonNull NumberToken arg = argStack.pop();
                result = -(arg.getValue());
            } else if (binaryOperatorMap.containsKey(op.getClass())) {
                if (argStack.size() < 2) {
                    throw new CalculationException("Stack Underflow during evaluation of binary operator");
                }
                final @NotNull @NonNull NumberToken op1 = argStack.pop();
                final @NotNull @NonNull NumberToken op2 = argStack.pop();

                result = Stream.of(op2, op1).
                        map(NumberToken::getValue).
                        reduce(binaryOperatorMap.get(op.getClass())).
                        orElseThrow(
                                () -> new CalculationException("Stack Underflow during evaluation of binary operator")
                        );
            } else {
                throw new CalculationException("Cannot evaluate operator");
            }

            argStack.push(new NumberToken(result));
        }
    }
}
