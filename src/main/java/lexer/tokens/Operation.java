package lexer.tokens;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import visitors.TokenVisitor;

public abstract sealed class Operation implements Token permits Operation.Divide, Operation.Minus, Operation.Multiply, Operation.Plus {
    public static final class Plus extends Operation {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static final class Multiply extends Operation {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static final class Minus extends Operation {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
        }
    }

    public static final class Divide extends Operation {
        @Override
        public void accept(final @NotNull @NonNull TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
