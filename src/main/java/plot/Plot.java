package plot;

import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import signal.Signal;

public class Plot extends ApplicationFrame {

    private static final String X_AXIS_LABEL = "time";
    private static final String Y_AXIS_LABEL = "amplitude";
    private static final Dimension DIMENSION = new Dimension(500, 270);

    public Plot(final String title, final Signal simpleSignal) {
        super(title);

        final XYSeries points = createPoints(simpleSignal, title);
        final JFreeChart chart = createChart(points, title);
        createPanel(chart);
    }

    private XYSeries createPoints(final Signal simpleSignal, final String title) {
        final XYSeries points = new XYSeries(title);
        simpleSignal.getPoints().forEach(point -> {
            points.add(point.getX(), point.getY());
        });
        return points;
    }

    private JFreeChart createChart(final XYSeries points, final String title) {
        final XYSeriesCollection pointsCollection = new XYSeriesCollection(points);
        return ChartFactory.createXYLineChart( //
                title, //
                X_AXIS_LABEL, //
                Y_AXIS_LABEL, //
                pointsCollection, //
                PlotOrientation.VERTICAL, //
                true, //
                true, //
                false);
    }

    private void createPanel(final JFreeChart chart) {
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(DIMENSION);
        setContentPane(chartPanel);
    }
}
