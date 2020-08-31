package example3;

import java.math.BigDecimal;

public final class Currency implements Comparable<Currency> {
    private String symbol;

    public Currency(String symbol) {
        this.symbol = symbol;
    }

    public Money zero() {
        return new Money(BigDecimal.ZERO, this);
    }

    @Override
    public int compareTo(Currency other) {
        return this.symbol.compareTo(other.symbol);
    }

    @Override
    public String toString() {
        return this.symbol;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Currency && this.equals((Currency) other);
    }

    private boolean equals(Currency other) {
        return this.symbol.equals(other.symbol);
    }
}
