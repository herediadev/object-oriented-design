package example7;

import java.util.stream.Stream;

public class MoneysStream implements ForwardingStream<Money>{
    private final Stream<Money> stream;

    public MoneysStream(Stream<Money> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<Money> getStream() {
        return this.stream;
    }

    public Money sum() {
        return this.getStream().reduce(Money::add).orElse(Money.ZERO);
    }
}
