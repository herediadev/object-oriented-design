package example7;

import java.util.List;
import java.util.stream.Stream;

public interface PainterScheduler {
    Stream<WorkAssignment> schedule(List<Painter> painters, double sqMeters);
}
