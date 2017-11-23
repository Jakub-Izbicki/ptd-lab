package signal;

import java.util.List;

public class SimpleTone extends Signal {

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
}
