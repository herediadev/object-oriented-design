package example7;

import java.time.Duration;
import java.util.Optional;

public class ProportionalPainter implements Painter {

    private final String name;
    private final double sqMetersPerHour;
    private final MoneyRate rate;

    public ProportionalPainter(String name, double sqMetersPerHour, MoneyRate rate) {
        this.name = name;
        this.sqMetersPerHour = sqMetersPerHour;
        this.rate = rate;
    }

    @Override
    public OptionalPainter available() {
        return OptionalPainter.of(this);
    }

    @Override
    public Duration estimateTimeToPaint(double sqMeters) {
        return Duration.ofSeconds(this.getSecondsToPaint(sqMeters));
    }

    private int getSecondsToPaint(double sqMeters) {
        return (int) (sqMeters / this.sqMetersPerHour * 3600);
    }

    @Override
    public Money estimateCompensation(double sqMeters) {
        return this.rate.getTotalFor(this.estimateTimeToPaint(sqMeters));
    }

    @Override
    public String toString() {
        return String.format("%s painting %.2f sq. meters per hour at rate %s",
                this.getName(), this.sqMetersPerHour, this.rate);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double estimateSqMeters(Duration time) {
        return this.sqMetersPerHour * (time.getSeconds() / (double) 3600);
    }
}
