package example7;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money implements Comparable<Money> {
    public static Money ZERO = new Money(BigDecimal.ZERO);
    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money scale(long multiply, long divide) {
        return new Money(
                this.value
                        .multiply(new BigDecimal(multiply))
                        .divide(new BigDecimal(divide), RoundingMode.CEILING));
    }

    public Money scale(double factor) {
        return new Money(this.value.multiply(new BigDecimal(factor)));
    }

    public Money add(Money other) {
        return new Money(this.value.add(other.value));
    }

    @Override
    public int compareTo(Money other) {
        return this.value.compareTo(other.value);
    }
}
