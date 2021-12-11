import lexer.Tokenizer;
import lexer.tokens.Token;
import org.jetbrains.annotations.NotNull;
import visitors.ParserVisitor;

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
        final @NotNull Tokenizer tokenizer = new Tokenizer(new ByteArrayInputStream(expressionString.getBytes(StandardCharsets.UTF_8)));
        final List<Token> sourceTokens = tokenizer.getTokens();
        final ParserVisitor parserVisitor = new ParserVisitor();
        sourceTokens.forEach(parserVisitor::visit);
        final List<Token> postfixTokens = parserVisitor.getOutputExpression();
    }
}
