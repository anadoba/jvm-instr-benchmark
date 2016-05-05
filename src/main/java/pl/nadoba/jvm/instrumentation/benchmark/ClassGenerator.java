package pl.nadoba.jvm.instrumentation.benchmark;

import com.sun.codemodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ClassGenerator {

    private final String PACKAGE = this.getClass().getPackage().getName() + ".generated";
    private static final String PREFIX = "src/main/java";
    private Random random = new Random();

    public void generateMultiple(int amount) {
        for (int i = 1; i <= amount; i++) {
            try {
                generate(i);
            } catch (JClassAlreadyExistsException e) {
                System.out.println("Tried to generate a class, which already exists!");
                e.printStackTrace();
            }
        }
    }

    private boolean generate(int number) throws JClassAlreadyExistsException {
        JCodeModel cm = new JCodeModel();
        String targetClassName = PACKAGE + ".GeneratedClass" + number;
        JDefinedClass dc = cm._class(targetClassName)._implements(Countable.class);
        JMethod m = dc.method(JMod.PUBLIC, int.class, "count");
        m.body()._return(JExpr.lit(random.nextInt(1000)));

        File file = new File(PREFIX);
        file.mkdirs();
        try {
            cm.build(file);
            //System.out.println("Successfully generated class " + targetClassName);
            return true;
        } catch (IOException ex) {
            System.out.println("Error during saving new class [" + file + "]");
            ex.printStackTrace();
            return false;
        }
    }
}
