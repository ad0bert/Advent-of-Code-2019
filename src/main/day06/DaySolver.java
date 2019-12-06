package main.day06;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    private final String COM = "COM";

    @Override
    public void solvePart1() {
        List<String> input = AoCFileReader.readMulitpleLines(new File(this.inputFile1));
        String first = input.stream().filter(s -> s.startsWith(COM)).findFirst().orElse(COM);
        input.remove(first);
        OrbitMap om = new OrbitMap(COM, 0, null);
        String[] pair = first.split("\\)");
        om.addOrbiter(pair[0], pair[1]);
        while (input.size() > 0) {
            for (String s : input) {
                pair = s.split("\\)");
                if (om.addOrbiter(pair[0], pair[1])) {
                    input.remove(s);
                    break;
                }
            }
        }
        System.out.println(om.cntOrbits());
    }

    @Override
    public void solvePart2() {
        List<String> input = AoCFileReader.readMulitpleLines(new File(this.inputFile2));
        String first = input.stream().filter(s -> s.startsWith(COM)).findFirst().orElse(COM);
        input.remove(first);
        OrbitMap om = new OrbitMap(COM, 0, null);
        String[] pair = first.split("\\)");
        om.addOrbiter(pair[0], pair[1]);
        while (input.size() > 0) {
            for (String s : input) {
                pair = s.split("\\)");
                if (om.addOrbiter(pair[0], pair[1])) {
                    input.remove(s);
                    break;
                }
            }
        }

        List<String> pathToYou = new ArrayList<>();
        List<String> pathToSan = new ArrayList<>();
        om.getPathTo(pathToYou, "YOU");
        om.getPathTo(pathToSan, "SAN");
        Collections.reverse(pathToYou);
        Collections.reverse(pathToSan);
        List<String> pathToYouCpy = new ArrayList<>(pathToYou);
        List<String> pathToSanCpy = new ArrayList<>(pathToSan);
        pathToSanCpy.removeAll(pathToYou);
        pathToYouCpy.removeAll(pathToSan);
        System.out.println(pathToYouCpy.size() + pathToSanCpy.size() - 2);
    }

}
