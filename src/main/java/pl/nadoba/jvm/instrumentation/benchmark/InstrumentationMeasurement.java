package pl.nadoba.jvm.instrumentation.benchmark;

import java.util.LinkedList;
import java.util.List;

public class InstrumentationMeasurement {

    private final String CLASS_NAME = this.getClass().getPackage().getName() + ".generated.GeneratedClass";

    private List<Countable> countables = new LinkedList<>();

    public InstrumentationMeasurement(int numberOfClasses) {
        for (int i = 1; i <= numberOfClasses; i++) {
            String targetClassName = CLASS_NAME + String.valueOf(i);
            try {
                Class clazz = Class.forName(targetClassName);
                Countable countable = (Countable) clazz.newInstance();
                countables.add(countable);
            } catch (Exception e) {
                System.out.println("Couldn't find/instantiate class with name " + targetClassName);
                e.printStackTrace();
            }
        }
    }

    public void callCountOnAll() {
        for (Countable countable : countables) {
            String className = countable.getClass().getName();
            int result = countable.count();
            System.out.println(className + ": " + result);
        }
    }
}
