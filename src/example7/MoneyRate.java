package example7;

import java.time.Duration;

public class MoneyRate {
    private final Money intervalAmount;
    private final Duration interval;

    public MoneyRate(Money intervalAmount, Duration interval) {
        this.intervalAmount = intervalAmount;
        this.interval = interval;
    }

    private Duration getInterval() {
        return this.interval;
    }

    private long getSeconds() {
        return this.getInterval().getSeconds();
    }

    public static MoneyRate hourly(Money amount) {
        return new MoneyRate(amount, Duration.ofHours(1));
    }

    public Money getTotalPerHour() {
        return this.getTotalFor(Duration.ofHours(1));
    }

    public Money getTotalFor(Duration interval) {
        return this.intervalAmount.scale(interval.getSeconds(), this.getSeconds());
    }

    @Override
    public String toString() {
        return this.getTotalPerHour() + "hr.";
    }
}
