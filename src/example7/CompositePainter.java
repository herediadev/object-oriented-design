package example7;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompositePainter implements Painter {

    private final List<Painter> painters;
    private final PaintingScheduler scheduler;

    private CompositePainter(List<Painter> painters, PaintingScheduler scheduler) {
        this.painters = painters;
        this.scheduler = scheduler;
    }

    public static Optional<CompositePainter> of(List<Painter> painters, PaintingScheduler scheduler) {
        return painters.isEmpty()
                ? Optional.empty()
                : Optional.of(new CompositePainter(painters, scheduler));
    }

    @Override
    public Optional<Painter> available() {
        return CompositePainter.of(
                Painter
                        .stream(this.painters)
                        .available()
                        .collect(Collectors.toList()), new ConstantVelocityScheduler()
        ).map(Function.identity());
    }

    @Override
    public Duration estimateTimeToPaint(double sqMeters) {
        return scheduler.schedule(painters, sqMeters)
                .map(WorkAssignment::estimateTimeToPaint)
                .max(Duration::compareTo)
                .get();
    }

    @Override
    public Money estimateCompensation(double sqMeters) {
        return scheduler.schedule(painters, sqMeters)
                .map(WorkAssignment::estimateCompensation)
                .reduce(Money::add)
                .orElse(Money.ZERO);
    }

    @Override
    public String getName() {
        return this
                .getPaintersName()
                .collect(Collectors.joining(", ", "{ ", " }"));
    }

    @Override
    public double estimateSqMeters(Duration time) {
        return Painter.stream(painters)
                .mapToDouble(painter -> painter.estimateSqMeters(time))
                .sum();
    }

    private Stream<String> getPaintersName() {
        return Painter.stream(this.painters).map(Painter::getName);
    }
}
