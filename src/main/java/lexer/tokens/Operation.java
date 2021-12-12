package lexer.tokens;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import visitors.TokenVisitor;

import java.util.Comparator;

public abstract sealed class Operation<N extends Number> implements Token<N> permits Operation.BinaryMinus, Operation.Divide, Operation.Multiply, Operation.Plus, Operation.UnaryMinus {
    public static final Comparator<Operation<?>> BY_PRIORITY_COMPARATOR = Comparator.comparingInt(Operation::getPriority);

    public abstract int getPriority();

    public abstract boolean isPrefix();

    public abstract boolean isLeftAssociative();

    public static final class Plus<N extends Number> extends Operation<N> {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
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

    public static final class Multiply<N extends Number> extends Operation<N> {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
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

    public static final class BinaryMinus<N extends Number> extends Operation<N> {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
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

    public static final class UnaryMinus<N extends Number> extends Operation<N> {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
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

    public static final class Divide<N extends Number> extends Operation<N> {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
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
