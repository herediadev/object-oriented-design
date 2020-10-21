package example7;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

public class Money implements Comparable<Money> {
    public static Money ZERO = new Money(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static MoneysStream stream(Stream<Money> moneys){
        return new MoneysStream(moneys);
    }

    public Money scale(long multiply, long divide) {
        return this.scale(new BigDecimal(multiply), new BigDecimal(divide));
    }

    public Money scale(double factor) {
        BigDecimal newAmount = this.getAmount().multiply(new BigDecimal(factor));
        return new Money(newAmount);
    }

    public Money add(Money other) {
        return new Money(this.getAmount().add(other.getAmount()));
    }

    @Override
    public int compareTo(Money other) {
        return this.getAmount().compareTo(other.getAmount());
    }

    private BigDecimal getAmount() {
        return amount;
    }

    private Money scale(BigDecimal multiply, BigDecimal divide) {
        return new Money(this.getAmount().multiply(multiply).divide(divide, 2, RoundingMode.HALF_UP));
    }

    @Override
    public String toString() {
        return "$" + getAmount();
    }
}
