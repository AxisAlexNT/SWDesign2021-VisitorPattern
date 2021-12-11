package visitors;

import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;

public class PrintVisitor implements TokenVisitor {
    @Override
    public void visit(NumberToken token) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void visit(Brace token) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void visit(Operation token) {
        throw new UnsupportedOperationException("TODO");
    }
}
