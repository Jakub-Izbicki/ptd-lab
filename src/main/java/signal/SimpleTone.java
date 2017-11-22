package signal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

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

    public Signal sumWith(final SimpleTone simpleTone) {
        return combineWith(simpleTone, addPoints());
    }

    public Signal multiplyWith(final SimpleTone simpleTone) {
        return combineWith(simpleTone, multiplyPoints());
    }

    private Signal combineWith(final SimpleTone simpleTone, final BiFunction<Point, Point, Point> operation) {
        final double interval = getSmallerInterval(simpleTone);

        final SimpleTone tone1 = new SimpleToneFactory().get( //
                this.amplitude, this.pulsation, this.offset, interval);
        final SimpleTone tone2 = new SimpleToneFactory().get( //
                simpleTone.amplitude, simpleTone.pulsation, simpleTone.offset, interval);

        final List<Point> addedPoints = new ArrayList<>();
        for (int i = 0; i < tone1.points.size(); i++) {
            addedPoints.add(operation.apply(tone1.points.get(i), tone2.points.get(i)));
        }

        return new Signal(addedPoints);
    }

    private double getSmallerInterval(final SimpleTone simpleTone) {
        double pulsation = //
                this.pulsation < simpleTone.pulsation ? this.pulsation : simpleTone.pulsation;
        return SimpleToneFactory.calculateInterval(pulsation);
    }

    private BiFunction<Point, Point, Point> addPoints() {
        return (point1, point2) -> new Point(point1.getX(), point1.getY() + point2.getY());
    }

    private BiFunction<Point, Point, Point> multiplyPoints() {
        return (point1, point2) -> new Point(point1.getX(), point1.getY() * point2.getY());
    }
}
