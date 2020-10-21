package example7;

import java.time.Duration;
import java.util.stream.Stream;

public class WorkAssignment {

    private final Painter painter;
    private final double sqMeters;

    public WorkAssignment(Painter painter, double sqMeters) {
        this.painter = painter;
        this.sqMeters = sqMeters;
    }

    public static WorkStream stream(Stream<WorkAssignment> stream) {
        return new WorkStream(stream);
    }

    public Money estimateCompensation() {
        return this.painter.estimateCompensation(this.sqMeters);
    }

    public Duration estimateTimeToPaint() {
        return this.painter.estimateTimeToPaint(this.sqMeters);
    }
}
