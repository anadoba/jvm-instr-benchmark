package pl.nadoba.jvm.instrumentation.benchmark;

public class CustomTimer {

    private static long startTime = 0;

    public static void start() {
        System.out.println("Starting measurements...");
        startTime = System.currentTimeMillis();
    }

    public static void stop() {
        if (startTime == 0)
            throw new RuntimeException("CustomTimer has not been started yet");

        long endTime = System.currentTimeMillis();
        long outcome = endTime - startTime;
        System.out.println("Measurement stopped with " + outcome + " ms");
    }
}
