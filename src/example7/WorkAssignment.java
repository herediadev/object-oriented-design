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

    @Override
    public String toString() {
        Money compensation = this.painter.estimateCompensation(sqMeters);
        Duration totalTime = this.painter.estimateTimeToPaint(sqMeters);
        String formattedTime = TimeUtils.format(totalTime);

        return String.format(
                "Letting %s paint %.2f sq. meters during %s at total cost %s\n",
                this.painter.getName(), this.sqMeters, formattedTime, compensation
        );
    }
}
