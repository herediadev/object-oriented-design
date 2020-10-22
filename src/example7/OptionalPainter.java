package example7;

import java.util.Optional;
import java.util.function.Function;

public class OptionalPainter {

    private final Optional<Painter> content;

    private OptionalPainter(Optional<Painter> content) {
        this.content = content;
    }

    public static OptionalPainter of(Painter painter) {
        return new OptionalPainter(Optional.of(painter));
    }

    public Optional<Painter> asOptional(){
        return this.content;
    }

    public static OptionalPainter empty(){
        return new OptionalPainter(Optional.empty());
    }

    public OptionalPainter available(){
        return this.content.map(Painter::available)
                .orElse(OptionalPainter.empty());
    }

    public OptionalPainter mapPainter(Function<Painter,Painter> mapping){
        return new OptionalPainter(this.content.map(mapping));
    }

    public <TResult> Optional<TResult> map(Function<Painter,TResult> mapping){
        return this.content.map(mapping);
    }

    public OptionalAssignment assign(double sqMeters){
        return this.content.map(painter -> painter.assign(sqMeters))
                .map(OptionalAssignment::of)
                .orElse(OptionalAssignment.empty());
    }
}
