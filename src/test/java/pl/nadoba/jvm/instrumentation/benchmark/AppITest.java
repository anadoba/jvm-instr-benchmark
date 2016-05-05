package pl.nadoba.jvm.instrumentation.benchmark;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppITest extends TestCase {

    public void testIT() {
        int COUNT = 1000;
        InstrumentationMeasurement measurement = new InstrumentationMeasurement(COUNT);
        measurement.callCountOnAll();
    }
}