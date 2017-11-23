package plot;

import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class Plot extends ApplicationFrame {

    private static final Dimension DIMENSION = new Dimension(500, 270);

    public Plot(final String title, final XYSeries points, final String axisX, final String axisY) {
        super(title);
        final JFreeChart chart = createChart(points, title, axisX, axisY);
        createPanel(chart);
    }

    private JFreeChart createChart(final XYSeries points, final String title, final String axisX, final String axisY) {
        final XYSeriesCollection pointsCollection = new XYSeriesCollection(points);
        return ChartFactory.createXYLineChart( //
                title, //
                axisX, //
                axisY, //
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
