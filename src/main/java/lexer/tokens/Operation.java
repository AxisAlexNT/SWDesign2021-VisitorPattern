package lexer.tokens;

import evaluators.PostfixEvaluator;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import visitors.TokenVisitor;

import java.util.Comparator;

public abstract sealed class Operation<NT extends PostfixEvaluator<NT>> implements Token<NT> permits Operation.BinaryMinus, Operation.Divide, Operation.Multiply, Operation.Plus, Operation.UnaryMinus {
    public final Comparator<Operation<NT>> BY_PRIORITY_COMPARATOR = Comparator.comparingInt(Operation::getPriority);

    public abstract int getPriority();

    public abstract boolean isPrefix();

    public abstract boolean isLeftAssociative();

    public static final class Plus<NT extends PostfixEvaluator<NT>> extends Operation<NT> {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor<NT> visitor) {
            visitor.visit(this);
        }

        @Override
        public int getPriority() {
            return 3;
        }

        @Override
        public boolean isPrefix() {
            return false;
        }

        @Override
        public boolean isLeftAssociative() {
            return true;
        }
    }

    public static final class Multiply<NT extends PostfixEvaluator<NT>> extends Operation<NT> {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor<NT> visitor) {
            visitor.visit(this);
        }

        @Override
        public int getPriority() {
            return 2;
        }

        @Override
        public boolean isPrefix() {
            return false;
        }

        @Override
        public boolean isLeftAssociative() {
            return true;
        }
    }

    public static final class BinaryMinus<NT extends PostfixEvaluator<NT>> extends Operation<NT> {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor<NT> visitor) {
            visitor.visit(this);
        }

        @Override
        public int getPriority() {
            return 3;
        }

        @Override
        public boolean isPrefix() {
            return false;
        }

        @Override
        public boolean isLeftAssociative() {
            return true;
        }
    }

    public static final class UnaryMinus<NT extends PostfixEvaluator<NT>> extends Operation<NT> {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor<NT> visitor) {
            visitor.visit(this);
        }

        @Override
        public int getPriority() {
            return 1;
        }

        @Override
        public boolean isPrefix() {
            return true;
        }

        @Override
        public boolean isLeftAssociative() {
            return false;
        }
    }

    public static final class Divide<NT extends PostfixEvaluator<NT>> extends Operation<NT> {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor<NT> visitor) {
            visitor.visit(this);
        }

        @Override
        public int getPriority() {
            return 2;
        }

        @Override
        public boolean isPrefix() {
            return false;
        }

        @Override
        public boolean isLeftAssociative() {
            return true;
        }
    }
}
