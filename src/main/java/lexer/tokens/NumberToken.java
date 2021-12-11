package lexer.tokens;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import visitors.TokenVisitor;

public abstract sealed class NumberToken implements Token permits NumberToken.LongToken {

    protected NumberToken() {
    }

    public static NumberToken valueOf(final @NotNull @NonNull String stringValue) {
        return new LongToken(Long.parseLong(stringValue));
    }

    public static final class LongToken extends NumberToken {
        @Getter(value = AccessLevel.PUBLIC)
        private final long value;

        public LongToken(final long value) {
            this.value = value;
        }

        @Override
        public void accept(TokenVisitor visitor) {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
