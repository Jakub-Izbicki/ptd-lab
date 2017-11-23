import java.io.IOException;

import dft.DFT;
import dft.FrequencyDomain;
import signal.Signal;
import signal.SimpleTone;
import signal.SimpleToneFactory;

public class App {

    public static void main(String[] args) throws IOException {

        final SimpleTone signal1 = SimpleToneFactory.get(1d, 45d + 1/3d, 0d);
        final SimpleTone signal2 = SimpleToneFactory.get(1d, 33d + 1/2d, 0d);

        final Signal sum = signal1.sumWith(signal2);
        sum.plot("Sum");

        final FrequencyDomain frequencyDomain = DFT.calculate(sum);

        frequencyDomain.plotAmplitudeSpectrum("Amplitude spectrum");
    }
}
