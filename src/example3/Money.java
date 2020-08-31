package example3;

import java.math.BigDecimal;

public class Money implements Comparable<Money> {

    private BigDecimal amount;
    private Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money scale(double factor) {
        return new Money(this.amount.multiply(new BigDecimal(factor)), this.currency);
    }

    @Override
    public int compareTo(Money other) {
        return this.compareAmountTo(this.currency.compareTo(other.currency), other);
    }

    private int compareAmountTo(int currencyCompare, Money other) {
        return currencyCompare == 0 ? this.amount.compareTo(other.amount)
                : currencyCompare;
    }

    @Override
    public String toString() {
        return this.amount + " " + this.currency;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == this.getClass() && this.equals((Money) other);
    }

    @Override
    public int hashCode() {
        return this.amount.hashCode() * 17 + this.currency.hashCode();
    }

    private boolean equals(Money other) {
        return this.amount.equals(other.amount) && this.currency.equals(other.currency);
    }

    public Money add(Money other) {
        if (other.currency.compareTo(this.currency) != 0)
            throw new IllegalArgumentException("using different currency");

        return new Money(this.amount.add(other.amount), this.currency);
    }
}
