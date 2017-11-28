package signal;

public class Point {

    private double time;
    private double amplitude;

    Point(final double time, final double amplitude) {
        this.time = time;
        this.amplitude = amplitude;
    }

    public double getTime() {
        return time;
    }

    public double getAmplitude() {
        return amplitude;
    }
}
