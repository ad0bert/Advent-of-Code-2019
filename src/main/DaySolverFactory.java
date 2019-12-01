package main;

import java.lang.reflect.Constructor;

public class DaySolverFactory {

    public static AbstractSolver getSolver(int i) {
        String day = String.format("%02d", i);
        try {
            Class<?> clazz = Class.forName("main.day" + day + ".DaySolver");
            Constructor<?> ctor = clazz.getConstructor(String.class);
            return (AbstractSolver) ctor.newInstance(new Object[] { day });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
