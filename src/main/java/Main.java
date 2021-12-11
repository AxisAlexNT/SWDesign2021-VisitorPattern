import lexer.Tokenizer;
import lexer.tokens.NumberToken;
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
        final @NotNull Tokenizer<NumberToken.LongToken> tokenizer = new Tokenizer<>(new ByteArrayInputStream(expressionString.getBytes(StandardCharsets.UTF_8)), NumberToken.LongToken::valueOf);
        final List<NumberToken.LongToken> sourceTokens = tokenizer.getTokens();
        final ParserVisitor<NumberToken.LongToken> parserVisitor = new ParserVisitor<>();
        sourceTokens.forEach(parserVisitor::visit);
        final List<NumberToken.LongToken> postfixTokens = parserVisitor.getOutputExpression();
    }
}
