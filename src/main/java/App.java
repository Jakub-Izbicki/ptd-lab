import java.io.IOException;

import signal.BinarySignal;
import signal.SignalFactory;
import signal.SimpleTone;

public class App {

    public static void main(String[] args) throws IOException {

        final SimpleTone highFreq = SignalFactory.simpleTone(1d, 20d, 0d);
        highFreq.plot("High freq");

        final SimpleTone lowFreq = SignalFactory.simpleTone(1d, 4d, 0d);
        highFreq.plot("Low freq");

        final BinarySignal binary = SignalFactory.sampleBinary();
        binary.plot("Binary signal");

        binary.phaseShiftKeying(highFreq).plot("PSK");
    }
}
