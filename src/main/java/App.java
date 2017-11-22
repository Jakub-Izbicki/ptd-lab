import java.io.IOException;

import dft.DiscreteFourierTransformFactory;
import signal.SimpleTone;
import signal.SimpleToneFactory;

public class App {

    public static void main(String[] args) throws IOException {

        final SimpleTone signal1 = SimpleToneFactory.get(0.2d, 2d, 0d);
        final SimpleTone signal2 = SimpleToneFactory.get(0.2d, 10d, 0d);

        signal1.plot("signal1");
        signal2.plot("signal2");
        signal1.sumWith(signal2).plot("sum");

        DiscreteFourierTransformFactory.from(signal1);
    }
}
