package pl.nadoba.jvm.instrumentation.benchmark;

import pl.nadoba.jvm.instrumentation.benchmark.transformers.ASMTransformer;
import pl.nadoba.jvm.instrumentation.benchmark.transformers.JavassistTransformer;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        CustomTimer.start();

inst.addTransformer(new JavassistTransformer());
    }

}
