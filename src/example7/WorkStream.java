package example7;

import java.util.stream.Stream;

public class WorkStream implements ForwardingStream<WorkAssignment> {

    private final Stream<WorkAssignment> stream;

    public WorkStream(Stream<WorkAssignment> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<WorkAssignment> getStream() {
        return this.stream;
    }

    public MoneysStream compensations() {
        return Money.stream(this.getStream().map(WorkAssignment::estimateCompensation));
    }

    public DurationsStream timesToPaint() {
        return DurationsStream.stream(this.getStream().map(WorkAssignment::estimateTimeToPaint));
    }
}
