package pl.nadoba.jvm.instrumentation.benchmark;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testInstrumentation() {
        assertTrue(true);
        //ClassGenerator generator = new ClassGenerator();
        //generator.generateMultiple(1000);
        //CustomTimer.stop();
    }
}
