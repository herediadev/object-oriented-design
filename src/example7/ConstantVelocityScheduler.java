package example7;

import java.util.List;
import java.util.stream.Stream;

class ConstantVelocityScheduler implements PainterScheduler {

    @Override
    public Stream<WorkAssignment> schedule(List<Painter> painters, double sqMeters) {
        return this.schedule(painters, sqMeters, this.estimateTotalVelocity(sqMeters, painters));
    }

    private Stream<WorkAssignment> schedule(List<Painter> painters, double sqMeters, Velocity totalVelocity) {
        return Painter.stream(painters)
                .map(painter -> painter.assign(sqMeters * painter.estimateVelocity(sqMeters).divideBy(totalVelocity)));
    }

    private Velocity estimateTotalVelocity(double sqMeters, List<Painter> painters) {
        return Painter.stream(painters)
                .map(painter -> painter.estimateVelocity(sqMeters))
                .reduce(Velocity::add)
                .orElse(Velocity.ZERO);
    }
}
