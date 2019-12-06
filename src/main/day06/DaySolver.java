package main.day06;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.util.Pair;
import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    private OrbitMap world;

    public DaySolver(String day) {
        super(day);
        this.fillWorld();
    }

    private final String COM = "COM";

    @Override
    public void solvePart1() {
        System.out.println(world.cntOrbits());
    }

    @Override
    public void solvePart2() {
        List<String> pathToYou = new ArrayList<>();
        List<String> pathToSan = new ArrayList<>();
        world.getPathTo(pathToYou, "YOU");
        world.getPathTo(pathToSan, "SAN");
        List<String> pathToYouCpy = new ArrayList<>(pathToYou);
        List<String> pathToSanCpy = new ArrayList<>(pathToSan);
        pathToSanCpy.removeAll(pathToYou);
        pathToYouCpy.removeAll(pathToSan);
        System.out.println(pathToYouCpy.size() + pathToSanCpy.size() - 2);
    }

    private void fillWorld() {
        List<Pair<String, String>> input = AoCFileReader.readMulitpleLines(new File(this.inputFile1)).stream().map(s -> {
            String[] pair = s.split("\\)");
            return new Pair<>(pair[0], pair[1]);
        }).collect(Collectors.toList());
        Pair<String, String> first = input.stream().filter(s -> s.getKey().startsWith(COM)).findFirst().orElse(new Pair<>(COM, ""));
        input.remove(first);
        world = new OrbitMap(COM, 0);
        world.addOrbiter(first.getKey(), first.getValue());
        int cnt = input.size();
        while (cnt > 0) {
            for (int i = 0; i < input.size(); i++) {
                if (input.get(i) == null) continue;
                if (world.addOrbiter(input.get(i).getKey(), input.get(i).getValue())) {
                    input.set(i, null);
                    cnt--;
                }
            }
        }
    }
}
