package lexer.tokens;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import visitors.TokenVisitor;

public abstract sealed class NumberToken<N extends Number> implements Token<N> {
    @Getter(value = AccessLevel.PUBLIC)
    protected final @NotNull N value;

    protected NumberToken(final @NotNull @NonNull N value) {
        this.value = value;
    }

    @Override
    public void accept(final @NotNull @NonNull TokenVisitor visitor) {
        visitor.visit(this);
    }

    public static final class LongToken extends NumberToken<Long> {

        @Getter(value = AccessLevel.PUBLIC)
        private final long longValue;

        public LongToken(final long value) {
            super(value);
            this.longValue = value;
        }

        public LongToken(final @NotNull @NonNull String stringValue) {
            this(Long.parseLong(stringValue));
        }


    }
}
