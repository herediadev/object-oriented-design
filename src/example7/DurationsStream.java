package example7;

import java.time.Duration;
import java.util.stream.Stream;

public class DurationsStream implements ForwardingStream<Duration> {
    private final Stream<Duration> stream;

    private DurationsStream(Stream<Duration> stream) {
        this.stream = stream;
    }

    public static DurationsStream stream(Stream<Duration> stream) {
        return new DurationsStream(stream);
    }

    @Override
    public Stream<Duration> getStream() {
        return this.stream;
    }

    public Duration maxOfMany() {
        return this.getStream().max(Duration::compareTo).get();
    }
}
