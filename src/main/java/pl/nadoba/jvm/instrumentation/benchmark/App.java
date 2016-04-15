package pl.nadoba.jvm.instrumentation.benchmark;


public class App {
    public static void main( String[] args ) {
        ClassGenerator generator = new ClassGenerator();
        generator.generateMultiple(1);
    }
}
