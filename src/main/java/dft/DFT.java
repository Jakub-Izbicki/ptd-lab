package dft;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import complex.ComplexNumber;
import signal.Point;
import signal.Signal;

public class DFT {

    public static FrequencyDomain calculate(final Signal signal) {
        final List<Double> timeDomain = signal.getPoints().stream() //
                .map(Point::getAmplitude) //
                .collect(Collectors.toList());

        return calculate(timeDomain);
    }

    private static FrequencyDomain calculate(final List<Double> timeDomain) {
        final List<ComplexNumber> frequencyDomain = new ArrayList<>();

        for (int i = 0; i < timeDomain.size(); i++) {
            frequencyDomain.add(calculateIndex(i, timeDomain));
        }

        return new FrequencyDomain(frequencyDomain);
    }

    private static ComplexNumber calculateIndex(final int index, final List<Double> timeDomain) {
        return new ComplexNumber(calculateReal(index, timeDomain), calculateImaginary(index, timeDomain));
    }

    private static double calculateReal(final int index, final List<Double> timeDomain) {
        double real = 0d;

        for (int i = 0; i < timeDomain.size(); i++) {
            real += timeDomain.get(i) * Math.cos((2d * Math.PI * (double) i * (double) index) / timeDomain.size());
        }

        return real;
    }

    private static double calculateImaginary(final int index, final List<Double> timeDomain) {
        double imaginary = 0d;

        for (int i = 0; i < timeDomain.size(); i++) {
            imaginary += (double) -1 * timeDomain.get(i) * Math.sin((2d * Math.PI * (double) i * (double) index) / timeDomain.size());
        }

        return imaginary;
    }
}
