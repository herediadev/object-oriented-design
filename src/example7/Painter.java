package example7;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public interface Painter {
    Duration estimateTimeToPaint(double sqMeters);

    Optional<Painter> available();

    Money estimateCompensation(double sqMeters);

    String getName();

    default Velocity estimateVelocity(double sqMeters) {
        return new Velocity(sqMeters, this.estimateTimeToPaint(sqMeters));
    }

    default WorkAssignment assign(double sqMeters) {
        return new WorkAssignment(this, sqMeters);
    }

    static PainterStream stream(List<Painter> painters) {
        return new PainterStream(painters.stream());
    }
}
