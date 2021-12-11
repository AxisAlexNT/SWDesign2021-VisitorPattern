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

public abstract sealed class NumberToken implements Token permits NumberToken.DoubleToken, NumberToken.LongToken {
    @Getter(value = AccessLevel.PUBLIC)
    protected final @NotNull Number value;

    protected NumberToken(final @NotNull Number value) {
        this.value = value;
    }

    public static NumberToken valueOf(final @NotNull @NonNull String stringValue) {
        return new LongToken(stringValue);
    }

    @Override
    public void accept(TokenVisitor visitor) {
        throw new UnsupportedOperationException("TODO");
    }

    public static final class LongToken extends NumberToken implements PostfixEvaluator<LongToken> {
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

        public LongToken(final @NotNull @NonNull String value) {
            super(Long.parseLong(value));
        }

        public LongToken(final long value) {
            super(value);
        }

        @Override
        public void evaluate(final @NotNull @NonNull Operation op, final @NotNull @NonNull Stack<@NotNull @NonNull LongToken> argStack) {
            final long result;
            if (op instanceof Operation.UnaryMinus) {
                if (argStack.empty()) {
                    throw new CalculationException("Stack underflow during evaluation of UnaryMinus");
                }
                result = -((long) argStack.pop().getValue());
            } else if (binaryOperatorMap.containsKey(op.getClass())) {
                if (argStack.size() < 2) {
                    throw new CalculationException("Stack Underflow during evaluation of binary operator");
                }
                result = argStack.
                        stream().
                        limit(2L).
                        map(lt -> (long) lt.getValue()).
                        reduce(binaryOperatorMap.get(op.getClass())).
                        orElseThrow(
                                () -> new CalculationException("Stack Underflow during evaluation of binary operator")
                        );
            } else {
                throw new CalculationException("Cannot evaluate operator");
            }

            argStack.push(new LongToken(result));
        }
    }

    public static final class DoubleToken extends NumberToken {
        public DoubleToken(final @NotNull @NonNull String value) {
            super(Double.parseDouble(value));
        }
    }
}
