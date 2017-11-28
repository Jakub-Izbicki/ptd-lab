package signal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SignalFactory {

    private static final double PROBING_FREQUENCY = 1000d;
    private static final double SIMULATION_TIME = 1d;

    public static SimpleTone simpleTone(final double amplitude, final double frequency, final double offset) {
        return generateSignal(amplitude, calculatePulsation(frequency), offset);
    }

    private static SimpleTone generateSignal(final double amplitude, final double pulsation, final double offset) {
        final List<Point> signal = new ArrayList<>();

        for (double time = 0d; time < SIMULATION_TIME; time += 1d / PROBING_FREQUENCY) {
            signal.add(new Point(time, simpleToneEquation(time, amplitude, pulsation, offset)));
        }

        return new SimpleTone(signal, amplitude, pulsation, offset, calculatePeriod(pulsation));
    }

    private static double simpleToneEquation(final double time, final double amplitude, final double pulsation, final double offset) {
        return amplitude * Math.sin(time * pulsation + offset);
    }

    private static double calculatePulsation(final double frequency) {
        return 2 * Math.PI * frequency;
    }

    private static double calculatePeriod(final double pulsation) {
        return 2 * Math.PI / pulsation;
    }

    public static Signal signal(final Function<Double, Double> signalEquation) {
        return generateSignal(signalEquation);
    }

    private static Signal generateSignal(final Function<Double, Double> signalEquation) {
        final List<Point> signal = new ArrayList<>();

        for (double time = 0d; time < SIMULATION_TIME; time += 1d / PROBING_FREQUENCY) {
            signal.add(new Point(time, signalEquation.apply(time)));
        }

        return new Signal(signal);
    }
}
