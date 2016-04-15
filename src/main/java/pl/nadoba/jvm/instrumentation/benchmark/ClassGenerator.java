package pl.nadoba.jvm.instrumentation.benchmark;

import com.sun.codemodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ClassGenerator {

    private final String PACKAGE = this.getClass().getPackage().getName();
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

    private void generate(int number) throws JClassAlreadyExistsException {
        JCodeModel cm = new JCodeModel();
        JDefinedClass dc = cm._class(PACKAGE + ".GeneratedClass" + number);
        JMethod m = dc.method(0, int.class, "print");
        m.body()._return(JExpr.lit(random.nextInt(1000)));

        File file = new File(".");
        file.mkdirs();
        try {
            cm.build(file);
        } catch (IOException ex) {
            System.out.println("Error during saving new class [" + file + "]");
            ex.printStackTrace();
        }
    }
}
