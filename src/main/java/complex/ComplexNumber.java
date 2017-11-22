package complex;

public class ComplexNumber {

    private final double real;
    private final double imaginary;

    public ComplexNumber(final double real, final double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    @Override
    public String toString() {
        return "ComplexNumber: " + real + (imaginary < 0 ? ", " : ", +") + imaginary + 'i';
    }
}
