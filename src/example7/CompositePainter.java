package example7;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompositePainter implements Painter {

    private final List<Painter> subordinatePainters ;
    private final PaintingScheduler scheduler;

    private CompositePainter(List<Painter> subordinatePainters, PaintingScheduler scheduler) {
        this.subordinatePainters = subordinatePainters;
        this.scheduler = scheduler;
    }

    public static OptionalPainter of(List<Painter> subordinatePainters, PaintingScheduler scheduler) {
        return subordinatePainters.isEmpty()
                ? OptionalPainter.empty()
                : OptionalPainter.of(new CompositePainter(subordinatePainters, scheduler));
    }

    @Override
    public OptionalPainter available() {
        return this.painters().available().workTogether(this.scheduler);
    }

    @Override
    public Duration estimateTimeToPaint(double sqMeters) {
        return schedule(sqMeters)
                .timesToPaint()
                .maxOfMany();
    }

    @Override
    public Money estimateCompensation(double sqMeters) {
        return schedule(sqMeters)
                .compensations()
                .sum();
    }

    private WorkStream schedule(double sqMeters) {
        return scheduler.schedule(subordinatePainters, sqMeters);
    }

    @Override
    public String getName() {
        return this
                .getPaintersName()
                .collect(Collectors.joining(", ", "{ ", " }"));
    }

    @Override
    public double estimateSqMeters(Duration time) {
        return painters().estimateSqMeters(time);
    }

    private Stream<String> getPaintersName() {
        return painters().map(Painter::getName);
    }

    private PainterStream painters() {
        return Painter.stream(subordinatePainters);
    }
}
