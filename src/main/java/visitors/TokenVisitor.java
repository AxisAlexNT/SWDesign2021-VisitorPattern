package visitors;

import lexer.tokens.Brace;
import lexer.tokens.NumberToken;
import lexer.tokens.Operation;

public interface TokenVisitor {
    void visit(NumberToken token);

    void visit(Brace token);

    void visit(Operation token);
}
