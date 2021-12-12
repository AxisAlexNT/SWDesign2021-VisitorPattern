package evaluators;

import ex.CalculationException;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class LongEvaluator implements PostfixEvaluator<Long> {
    private static final @NotNull @NonNull PostfixEvaluator<Long> INSTANCE = new LongEvaluator();
    private static final @NotNull @NonNull Map<Class<?>, BinaryOperator<@NotNull @NonNull Long>> binaryOperatorMap = Map.of(
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

    public static @NotNull @NonNull PostfixEvaluator<Long> getInstance() {
        return INSTANCE;
    }

    @Override
    public void evaluate(final @NotNull @NonNull Operation<Long> op, final @NotNull @NonNull Stack<@NotNull @NonNull NumberToken<Long>> argStack) {
        final long result;
        if (op instanceof Operation.UnaryMinus) {
            if (argStack.empty()) {
                throw new CalculationException("Stack underflow during evaluation of UnaryMinus");
            }
            final @NotNull @NonNull NumberToken<Long> arg = argStack.pop();
            result = -(arg.getValue());
        } else if (binaryOperatorMap.containsKey(op.getClass())) {
            if (argStack.size() < 2) {
                throw new CalculationException("Stack Underflow during evaluation of binary operator");
            }
            final @NotNull @NonNull NumberToken<Long> op1 = argStack.pop();
            final @NotNull @NonNull NumberToken<Long> op2 = argStack.pop();

            result = Stream.of(op2, op1).
                    map(NumberToken::getValue).
                    reduce(binaryOperatorMap.get(op.getClass())).
                    orElseThrow(
                            () -> new CalculationException("Stack Underflow during evaluation of binary operator")
                    );
        } else {
            throw new CalculationException("Cannot evaluate operator");
        }

        argStack.push(new NumberToken.LongToken(result));
    }
}
