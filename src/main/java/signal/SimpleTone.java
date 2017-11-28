package signal;

import java.util.List;
import java.util.function.BiFunction;

public class SimpleTone extends Signal {

    private static final double PM_FACTOR = 2d;
    //
    private final double amplitude;
    private final double pulsation;
    private final double offset;
    private final double period;

    SimpleTone(final List<Point> points, final double amplitude, final double pulsation, final double offset, final double period) {
        super(points);
        this.amplitude = amplitude;
        this.pulsation = pulsation;
        this.offset = offset;
        this.period = period;
    }

    public Signal modulatePhaseWith(final Signal signal) {
        return combineWith(signal, phaseModulation());
    }

    private BiFunction<Point, Point, Point> phaseModulation() {
        return (point1, point2) -> new Point(point1.getTime(),
                amplitude * Math.cos(pulsation * point1.getTime() + PM_FACTOR * point2.getAmplitude()));
    }
}
