import evaluators.PostfixEvaluator;
import ex.ApplicationRuntimeException;
import lexer.Tokenizer;
import lexer.tokens.NumberToken;
import lexer.tokens.Token;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import visitors.CalcVisitor;
import visitors.ParserVisitor;
import visitors.PrintVisitor;
import visitors.TokenVisitor;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

public class InfixToPostfixCalculator<N extends Number> implements Runnable {
    private final @NotNull @NonNull PostfixEvaluator<N> operationEvaluator;
    private final @NotNull @NonNull Tokenizer<N> tokenizer;
    private final @NotNull @NonNull TokenVisitor<N> parserVisitor, printVisitor, calcVisitor;

    public InfixToPostfixCalculator(final @NotNull @NonNull InputStream inputStream, final @NotNull @NonNull Function<String, NumberToken<N>> numberTokenConstructor, final @NotNull @NonNull PostfixEvaluator<N> operationEvaluator) {
        this.operationEvaluator = operationEvaluator;
        tokenizer = new Tokenizer<>(inputStream, numberTokenConstructor);
        parserVisitor = new ParserVisitor<>();
        printVisitor = new PrintVisitor<>();
        calcVisitor = new CalcVisitor<>(operationEvaluator);
    }

    @Override
    public void run() {
        try {
            // Parse:
            final List<Token<N>> sourceTokens = tokenizer.getTokens();
            final ParserVisitor<N> parserVisitor = new ParserVisitor<>();
            sourceTokens.forEach(parserVisitor::visit);
            final List<Token<N>> postfixTokens = parserVisitor.getOutputExpression();
            // Print postfix expression:
            final PrintVisitor<N> printVisitor = new PrintVisitor<>();
            postfixTokens.forEach(printVisitor::visit);
            System.out.println(printVisitor.getVisitResult());
            // Evaluate:
            final CalcVisitor<N> calcVisitor = new CalcVisitor<>(operationEvaluator);
            postfixTokens.forEach(calcVisitor::visit);
            final NumberToken<N> calcResult = calcVisitor.getResult();
            // Print expression result:
            System.out.println(calcResult.getValue());
        } catch (final ApplicationRuntimeException e) {
            System.out.printf("An exception occurred during expression evaluation: %s", e.getMessage());
            e.printStackTrace();
        }
    }
}
