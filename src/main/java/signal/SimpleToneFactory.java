package signal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleToneFactory {

    private static final double SIMULATION_TIME = 1d;
    private static final double INTERVAL_MODIFIER = 0.01d;

    public static SimpleTone get(final double amplitude, final double frequency, final double offset) {
        return generateSimpleSignal(amplitude, calculatePulsation(frequency), offset, Optional.empty());
    }

    public static double calculateInterval(final double pulsation) {
        final double frequency = calculateFrequency(pulsation);
        return (SIMULATION_TIME / (frequency * 2d * SIMULATION_TIME)) * INTERVAL_MODIFIER;
    }

    SimpleTone get(final double amplitude, final double pulsation, final double offset, final double interval) {
        return generateSimpleSignal(amplitude, pulsation, offset, Optional.of(interval));
    }

    private static SimpleTone generateSimpleSignal(final double amplitude,
            final double pulsation,
            final double offset,
            final Optional<Double> interval) {
        if (interval.isPresent()) {
            return generateSimpleSignal(amplitude, pulsation, offset, interval.get());
        }
        return generateSimpleSignal(amplitude, pulsation, offset, calculateInterval(pulsation));
    }

    private static SimpleTone generateSimpleSignal(final double amplitude, final double pulsation, final double offset, double interval) {
        final List<Point> signal = new ArrayList<Point>();

        for (double time = 0d; time < SIMULATION_TIME; time += interval) {
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

    private static double calculateFrequency(final double pulsation) {
        return pulsation / (2 * Math.PI);
    }

    private static double calculatePeriod(final double pulsation) {
        return 2 * Math.PI / pulsation;
    }
}
