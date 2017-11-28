package signal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

import plot.Plot;

public class Signal {

    private static final double AM_FACTOR = 0.5d;
    private static final String AXIS_X = "Time [s]";
    private static final String AXIS_Y = "Amplitude";
    //
    private final List<Point> points;

    protected Signal(final List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public Signal sumWith(final Signal simpleTone) {
        return combineWith(simpleTone, addPoints());
    }

    public Signal multiplyWith(final Signal simpleTone) {
        return combineWith(simpleTone, multiplyPoints());
    }

    protected Signal combineWith(final Signal signalToCombine, final BiFunction<Point, Point, Point> operation) {
        final List<Point> addedPoints = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            addedPoints.add(operation.apply(points.get(i), signalToCombine.points.get(i)));
        }

        return new Signal(addedPoints);
    }

    private BiFunction<Point, Point, Point> addPoints() {
        return (point1, point2) -> new Point(point1.getTime(), point1.getAmplitude() + point2.getAmplitude());
    }

    private BiFunction<Point, Point, Point> multiplyPoints() {
        return (point1, point2) -> new Point(point1.getTime(), point1.getAmplitude() * point2.getAmplitude());
    }

    public void plot(final String title) {
        final Plot plot = new Plot(title, createPlotPoints(title), AXIS_X, AXIS_Y);
        plot.pack();
        RefineryUtilities.centerFrameOnScreen(plot);
        plot.setVisible(true);
    }

    private XYSeries createPlotPoints(final String title) {
        final XYSeries xySeries = new XYSeries(title);
        points.forEach(point -> {
            xySeries.add(point.getTime(), point.getAmplitude());
        });
        return xySeries;
    }

    public Signal modulateAmplitudeWith(final Signal signal) {
        return combineWith(signal, amplitudeModulation());
    }

    private BiFunction<Point, Point, Point> amplitudeModulation() {

        return (point1, point2) -> {
            verifyAmplitudeModulationPrecondition(point2.getAmplitude());
            return new Point(point1.getTime(), point1.getAmplitude() * (AM_FACTOR * point2.getAmplitude() + 1d));
        };
    }

    private void verifyAmplitudeModulationPrecondition(final double amplitude) {
        if (Math.abs(AM_FACTOR * amplitude) >= 1d) {
            throw new IllegalStateException("aa");
        }
    }
}
