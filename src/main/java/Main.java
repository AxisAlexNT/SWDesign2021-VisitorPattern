import lexer.Tokenizer;
import lexer.tokens.Token;
import org.jetbrains.annotations.NotNull;

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
        final List<Token> tokens = tokenizer.getTokens();
    }
}
