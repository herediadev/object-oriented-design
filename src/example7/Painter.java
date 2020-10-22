package example7;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface Painter {
    Duration estimateTimeToPaint(double sqMeters);

    OptionalPainter available();

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

    double estimateSqMeters(Duration time);

    default PainterStream with(Painter other) {
        return new PainterStream(Stream.of(this, other));
    }

    default PainterStream with(Optional<Painter> other){
        return other
                .map(this::with)
                .orElse(new PainterStream(Stream.of(this)));
    }

    default PainterStream with(OptionalPainter other){
        return this.with(other.asOptional());
    }
}
