package dft;

import java.util.List;

import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

import complex.ComplexNumber;
import plot.Plot;

public class FrequencyDomain {

    private static final double MAX_HZ_RANGE = 2000d;
    private static final String AXIS_X = "Frequencey [Hz]";
    private static final String AXIS_Y = "Amplitude";
    //
    private final List<ComplexNumber> frequency;

    FrequencyDomain(final List<ComplexNumber> frequency) {
        this.frequency = frequency;
    }

    public void plotAmplitudeSpectrum(final String title) {
        final Plot plot = new Plot(title, createPlotPoints(title), AXIS_X, AXIS_Y);
        plot.pack();
        RefineryUtilities.centerFrameOnScreen(plot);
        plot.setVisible(true);
    }

    private XYSeries createPlotPoints(final String title) {
        final XYSeries xySeries = new XYSeries(title);

        int i = 0;
        for (ComplexNumber frequency : frequency) {
            xySeries.add(i, frequency.modulus());
            i++;
        }

        return xySeries;
    }

    public List<ComplexNumber> getFrequency() {
        return frequency;
    }
}
