import ex.ApplicationRuntimeException;
import lexer.Tokenizer;
import lexer.tokens.NumberToken;
import lexer.tokens.Token;
import org.jetbrains.annotations.NotNull;
import visitors.CalcVisitor;
import visitors.ParserVisitor;
import visitors.PrintVisitor;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final @NotNull String expressionString;
        try (final @NotNull Scanner inputScanner = new Scanner(System.in)) {
            expressionString = inputScanner.nextLine();
        }
        try {
            final @NotNull Tokenizer tokenizer = new Tokenizer(new ByteArrayInputStream(expressionString.getBytes(StandardCharsets.UTF_8)));
            final List<Token> sourceTokens = tokenizer.getTokens();
            final ParserVisitor parserVisitor = new ParserVisitor();
            sourceTokens.forEach(parserVisitor::visit);
            final List<Token> postfixTokens = parserVisitor.getOutputExpression();
            final PrintVisitor printVisitor = new PrintVisitor();
            postfixTokens.forEach(printVisitor::visit);
            System.out.println(printVisitor.getVisitResult());
            final CalcVisitor calcVisitor = new CalcVisitor();
            postfixTokens.forEach(calcVisitor::visit);
            final NumberToken calcResult = calcVisitor.getResult();
            System.out.println(calcResult.getValue());
        } catch (final ApplicationRuntimeException e) {
            System.out.printf("An exception occurred during expression evaluation: %s", e.getMessage());
            e.printStackTrace();
        }
    }
}
