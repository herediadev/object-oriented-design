package example7;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalAssignment {

    private final Optional<WorkAssignment> content;

    public OptionalAssignment(Optional<WorkAssignment> content) {
        this.content = content;
    }

    public static OptionalAssignment of(WorkAssignment assignment) {
        return new OptionalAssignment(Optional.of(assignment));
    }

    public static OptionalAssignment empty() {
        return new OptionalAssignment(Optional.empty());
    }

    public WorkStream stream() {
        return WorkAssignment.stream(this.content.map(Stream::of).orElse(Stream.empty()));
    }

    @Override
    public String toString() {
        return this.content.map(WorkAssignment::toString).orElse("");
    }
}
