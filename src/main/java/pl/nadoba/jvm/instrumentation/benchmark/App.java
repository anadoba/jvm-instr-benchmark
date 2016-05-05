package pl.nadoba.jvm.instrumentation.benchmark;


public class App {
    public static void main( String[] args ) {
        ClassGenerator generator = new ClassGenerator();
        int count = Integer.valueOf(args[0]);
        System.out.println("Generating " + count + " classes that implement Countable interface...");
        generator.generateMultiple(count);
        CustomTimer.stop();
    }
}
