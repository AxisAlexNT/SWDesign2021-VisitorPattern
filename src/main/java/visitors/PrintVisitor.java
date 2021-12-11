package visitors;

import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;
import org.jetbrains.annotations.NotNull;

public class PrintVisitor implements TokenVisitor {
    @Override
    public void visit(@NotNull NumberToken token) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void visit(@NotNull Brace token) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void visit(@NotNull Operation token) {
        throw new UnsupportedOperationException("TODO");
    }
}
