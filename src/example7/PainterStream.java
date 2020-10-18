package example7;

import java.util.Comparator;
import java.util.Optional;
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
}
