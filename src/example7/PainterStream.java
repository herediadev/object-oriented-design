package example7;

import java.time.Duration;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PainterStream implements ForwardingStream<Painter> {

    private final Stream<Painter> stream;

    public PainterStream(Stream<Painter> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<Painter> getStream() {
        return this.stream;
    }

    public PainterStream available() {
        return new PainterStream(
                this.getStream()
                        .map(Painter::available)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
        );
    }

    public Optional<Painter> cheapest(double sqMeters) {
        return this
                .getStream()
                .min(Comparator.comparing(painter -> painter.estimateCompensation(sqMeters)));
    }

    public double estimateSqMeters(Duration time) {
        return this.getStream()
                .mapToDouble(painter -> painter.estimateSqMeters(time))
                .sum();
    }

    public Optional<Painter> workTogether(PaintingScheduler scheduler) {
        return CompositePainter.of(
                this.getStream().collect(Collectors.toList()), scheduler)
                .map(Function.identity());
    }

    public WorkStream assign(Duration totalTime) {
        return WorkAssignment
                .stream(this.getStream().map(painter -> painter.assign(painter.estimateSqMeters(totalTime))));
    }

    public DurationsStream timesToPaint(double sqMeters) {
        return DurationsStream.stream(this.getStream().map(painter -> painter.estimateTimeToPaint(sqMeters)));
    }
}
