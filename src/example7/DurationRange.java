package example7;

import java.time.Duration;
import java.util.function.Function;

public class DurationRange {

    private final Duration low;
    private final Duration high;

    private DurationRange(Duration low, Duration high) {
        this.low = low;
        this.high = high;
    }

    public static DurationRange zeroTo(Duration high) {
        return new DurationRange(Duration.ZERO, high);
    }

    public Duration middle() {
        return this.low.plus(this.high).dividedBy(2);
    }

    public Duration range() {
        return this.high.minus(this.low);
    }

    public DurationRange loweHalf() {
        return new DurationRange(this.low, this.middle());
    }

    public DurationRange upperHalf() {
        return new DurationRange(this.middle(), this.high);
    }

    public <TCriterion extends Comparable<TCriterion>> DurationBisection<TCriterion> bisect(Function<Duration, TCriterion> criterionFunction) {
        return new DurationBisection<>(this, criterionFunction);
    }
}
