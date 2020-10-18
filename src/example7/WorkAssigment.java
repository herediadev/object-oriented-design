package example7;

import java.time.Duration;

public class WorkAssigment {

    private final Painter painter;
    private final double sqMeters;

    public WorkAssigment(Painter painter, double sqMeters) {
        this.painter = painter;
        this.sqMeters = sqMeters;
    }

    public Money estimateCompensation() {
        return this.painter.estimateCompensation(this.sqMeters);
    }

    public Duration estimateTimeToPaint() {
        return this.painter.estimateTimeToPaint(this.sqMeters);
    }
}
