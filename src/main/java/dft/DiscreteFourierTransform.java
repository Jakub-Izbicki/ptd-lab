package dft;

import java.util.List;

import complex.ComplexNumber;

public class DiscreteFourierTransform {

    private final List<ComplexNumber> frequencyDomain;

    public DiscreteFourierTransform(final List<ComplexNumber> frequencyDomain) {
        this.frequencyDomain = frequencyDomain;
    }

    public List<ComplexNumber> getFrequencyDomain() {
        return frequencyDomain;
    }
}
