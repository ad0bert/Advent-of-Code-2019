package main;

import java.time.LocalDateTime;

public class MainApplication {

    public static void main(String[] args) {
        int dayOfMonth = LocalDateTime.now().getDayOfMonth();
        int day = dayOfMonth > 25 ? (dayOfMonth % 26) + 1 : dayOfMonth;
        if ((args.length == 1) && args[0].matches("(0?[1-9]|1[0-9]|2[0-5])")) {
            day = Integer.parseInt(args[0]);
        } else {
            System.out.println("No or wrong input. Enter a number between 1 and 25.");
        }
        System.out.println("Executing program for day: " + day);
        AbstractSolver as = DaySolverFactory.getSolver(day);
        System.out.print("Result Part 1: ");
        as.solvePart1();
        System.out.print("Result Part 2: ");
        as.solvePart2();
    }

}
