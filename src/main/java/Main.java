import evaluators.LongEvaluator;
import lexer.tokens.NumberToken;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final @NotNull String expressionString;
        try (final @NotNull Scanner inputScanner = new Scanner(System.in)) {
            expressionString = inputScanner.nextLine();
        }
        final @NotNull @NonNull InputStream inputStream = new ByteArrayInputStream(expressionString.getBytes(StandardCharsets.UTF_8));

        final InfixToPostfixCalculator<Long> calculator = new InfixToPostfixCalculator<>(inputStream, NumberToken.LongToken::new, LongEvaluator.getInstance());
        calculator.run();
    }
}
