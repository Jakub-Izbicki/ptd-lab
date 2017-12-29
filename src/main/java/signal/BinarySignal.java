package signal;

import java.util.ArrayList;
import java.util.List;

public class BinarySignal extends Signal {

    private static final double LOW_FREQ_MODIFIER = 1d;
    private static final double HIGH_FREQ_MODIFIER = 2d;
    private final double byteLifetime;

    public BinarySignal(final List<Point> points, final double byteLifetime) {
        super(points);
        this.byteLifetime = byteLifetime;
    }

    public Signal amplitudeShiftKeying(final SimpleTone signal) {
        final List<Point> keyingPoints = new ArrayList<>();

        for (int i = 0; i < getPoints().size(); i++) {
            final double amp = getPoints().get(i).getAmplitude() * signal.getPoints().get(i).getAmplitude();

            keyingPoints.add(new Point(getPoints().get(i).getTime(), amp));
        }

        return new Signal(keyingPoints);
    }

    public Signal frequencyShiftKeying() {
        final List<Point> keyingPoints = new ArrayList<>();
        final int sampleNaturalNumber = getPoints().size();
        final double lowFreq = (sampleNaturalNumber + LOW_FREQ_MODIFIER) / byteLifetime;
        final double highFreq = (sampleNaturalNumber + HIGH_FREQ_MODIFIER) / byteLifetime;

        for (int i = 0; i < getPoints().size(); i++) {
            final double freq = getPoints().get(i).getAmplitude() == 0 ? lowFreq : highFreq;
            final double time = getPoints().get(i).getTime();

            keyingPoints.add(new Point(time, Math.sin(2d * Math.PI * freq * time)));
        }

        return new Signal(keyingPoints);
    }

    public Signal frequencyShiftKeying(final SimpleTone lowFreqSignal, final SimpleTone highFreqSignal) {
        checkAmplitudesEqual(lowFreqSignal, highFreqSignal);
        final List<Point> keyingPoints = new ArrayList<>();

        for (int i = 0; i < getPoints().size(); i++) {
            final double amplitude = getPoints().get(i).getAmplitude() == 0 ? //
                    lowFreqSignal.getPoints().get(i).getAmplitude() : //
                    highFreqSignal.getPoints().get(i).getAmplitude();
            keyingPoints.add(new Point(getPoints().get(i).getTime(), amplitude));
        }

        return new Signal(keyingPoints);
    }

    public Signal phaseShiftKeying(final SimpleTone signal) {
        final List<Point> keyingPoints = new ArrayList<>();

        for (int i = 0; i < getPoints().size(); i++) {
            final double time = getPoints().get(i).getTime();
            final double phaseOffset = getPoints().get(i).getAmplitude() == 0 ? 0 : Math.PI;

            keyingPoints.add(new Point(time, Math.sin(signal.getPulsation() * time + phaseOffset)));
        }

        return new Signal(keyingPoints);
    }

    private void checkAmplitudesEqual(final SimpleTone simpleTone1, final SimpleTone simpleTone2) {
        if (simpleTone1.getAmplitude() != simpleTone2.getAmplitude()) {
            throw new IllegalStateException("To do FSK, both signals must have equal amplitude!");
        }
    }
}
