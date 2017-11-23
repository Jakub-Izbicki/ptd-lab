package signal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleToneFactory {

    public static final double PROBING_FREQUENCY = 1000d;
    private static final double SIMULATION_TIME = 1d;

    public static SimpleTone get(final double amplitude, final double frequency, final double offset) {
        return generateSimpleSignal(amplitude, calculatePulsation(frequency), offset);
    }

    private static SimpleTone generateSimpleSignal(final double amplitude, final double pulsation, final double offset) {
        final List<Point> signal = new ArrayList<Point>();

        for (double time = 0d; time < SIMULATION_TIME; time += 1d / PROBING_FREQUENCY) {
            signal.add(new Point(time, calculateFunction(time, amplitude, pulsation, offset)));
        }

        return new SimpleTone(signal, amplitude, pulsation, offset, calculatePeriod(pulsation));
    }

    private static double calculateFunction(final double time, final double amplitude, final double pulsation, final double offset) {
        final double v = amplitude * Math.sin(time * pulsation + offset);
        return v;
    }

    private static double calculatePulsation(final double frequency) {
        return 2 * Math.PI * frequency;
    }

    private static double calculatePeriod(final double pulsation) {
        return 2 * Math.PI / pulsation;
    }
}
