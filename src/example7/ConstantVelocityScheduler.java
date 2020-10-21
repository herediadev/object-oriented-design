package example7;

import java.util.List;

class ConstantVelocityScheduler implements PaintingScheduler {

    @Override
    public WorkStream schedule(List<Painter> painters, double sqMeters) {
        return this.schedule(painters, sqMeters, this.estimateTotalVelocity(sqMeters, painters));
    }

    private WorkStream schedule(List<Painter> painters, double sqMeters, Velocity totalVelocity) {
        return WorkAssignment.stream(Painter.stream(painters)
                .map(painter -> painter.assign(sqMeters * painter.estimateVelocity(sqMeters).divideBy(totalVelocity))));
    }

    private Velocity estimateTotalVelocity(double sqMeters, List<Painter> painters) {
        return Painter.stream(painters)
                .map(painter -> painter.estimateVelocity(sqMeters))
                .reduce(Velocity::add)
                .orElse(Velocity.ZERO);
    }
}
