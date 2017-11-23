package signal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

import plot.Plot;

public class Signal {

    private static final String AXIS_X = "Time [s]";
    private static final String AXIS_Y = "Amplitude";
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

    private Signal combineWith(final Signal signalToCombine, final BiFunction<Point, Point, Point> operation) {
        final List<Point> addedPoints = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            addedPoints.add(operation.apply(points.get(i), signalToCombine.points.get(i)));
        }

        return new Signal(addedPoints);
    }

    private BiFunction<Point, Point, Point> addPoints() {
        return (point1, point2) -> new Point(point1.getX(), point1.getY() + point2.getY());
    }

    private BiFunction<Point, Point, Point> multiplyPoints() {
        return (point1, point2) -> new Point(point1.getX(), point1.getY() * point2.getY());
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
            xySeries.add(point.getX(), point.getY());
        });
        return xySeries;
    }
}
