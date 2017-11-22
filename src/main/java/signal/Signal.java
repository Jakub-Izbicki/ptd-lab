package signal;

import java.util.List;

import org.jfree.ui.RefineryUtilities;

import plot.Plot;

public class Signal {

    protected final List<Point> points;

    protected Signal(final List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void plot(final String title) {
        final Plot plot = new Plot(title, this);
        plot.pack();
        RefineryUtilities.centerFrameOnScreen(plot);
        plot.setVisible(true);
    }
}
