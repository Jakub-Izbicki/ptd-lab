import java.io.IOException;

import dft.DFT;
import dft.FrequencyDomain;
import signal.Signal;
import signal.SignalFactory;
import signal.SimpleTone;

public class App {

    public static void main(String[] args) throws IOException {

        final SimpleTone signal1 = SignalFactory.simpleTone(1d, 19d, Math.PI / 2d);
        signal1.plot("Signal 1");

        final Signal modifier = SignalFactory
                .signal((t) -> Math.sin(8d * Math.PI * t + 0.3d * Math.sin(14d * Math.PI * t + (Math.PI / 2d))));
        modifier.plot("Modifier");

        Signal phaseModulationSignal = signal1.modulatePhaseWith(modifier);
        phaseModulationSignal.plot("Phase modulation.");

        final FrequencyDomain frequencyDomain = DFT.calculate(phaseModulationSignal);
        frequencyDomain.plotAmplitudeSpectrum("Amplitude spectrum");
    }
}
