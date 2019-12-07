package main.day07;

import java.io.File;
import java.util.*;

import main.AbstractSolver;
import utils.AoCFileReader;
import utils.IntCode;

public class DaySolver extends AbstractSolver {

    private Map<Integer, Map<Integer, Integer>> resultMap;

    private List<int[]> configs;

    public DaySolver(String day) {
        super(day);
    }

    private void init(int[] configSettings) {
        resultMap = new HashMap<>();
        resultMap.put(0, new HashMap<>());
        resultMap.put(1, new HashMap<>());
        resultMap.put(2, new HashMap<>());
        resultMap.put(3, new HashMap<>());
        resultMap.put(4, new HashMap<>());
        configs = new ArrayList<>();
        this.calcConfig(configSettings.length, configSettings);
    }

    @Override
    public void solvePart1() {
        List<Integer> input = AoCFileReader.readIntegerLine(new File(this.inputFile1));
        IntCode program = new IntCode(input);
        int[] phaseSettings = {0, 1, 2, 3, 4};
        this.init(phaseSettings);

        List<Integer> outputs = new ArrayList<>();
        for (int[] config : this.configs) {
            int res = 0;
            for (int i : config) {
                int[] args = {i, res};
                if (resultMap.get(args[0]) != null && resultMap.get(args[0]).get(args[1]) != null) {
                    res = resultMap.get(args[0]).get(args[1]);
                } else {
                    res = program.run(args);
                    resultMap.get(args[0]).put(args[1], res);
                }
            }
            outputs.add(res);
        }
        System.out.println(Collections.max(outputs));
    }

    @Override
    public void solvePart2() {
        List<Integer> input = AoCFileReader.readIntegerLine(new File(this.inputFile2));


        int[] phaseSettings = {5, 6, 7, 8, 9};
        this.init(phaseSettings);

        List<Integer> outputs = new ArrayList<>();
        for (int[] config : this.configs) {
            IntCode[] amps = {
                    new IntCode(input),
                    new IntCode(input),
                    new IntCode(input),
                    new IntCode(input),
                    new IntCode(input)
            };
            // init with config
            for (int i = 0; i < amps.length; i++) {
                amps[i].code = config[i];
            }
            int res = 0;
            int curr = 0;
            while (Arrays.stream(amps).anyMatch(amp -> amp.running)) {
                curr = curr % 5;
                res = amps[curr].runPersist(res);
                if (curr == 4) {
                    outputs.add(res);
                }
                curr++;
            }
        }
        System.out.println(Collections.max(outputs));
    }

    public void calcConfig(int pos, int[] elements) {
        if (pos == 1) {
            this.configs.add(Arrays.copyOf(elements, 5));
        } else {
            for (int i = 0; i < pos - 1; i++) {
                calcConfig(pos - 1, elements);
                if (pos % 2 == 0) {
                    swap(elements, i, pos - 1);
                } else {
                    swap(elements, 0, pos - 1);
                }
            }
            calcConfig(pos - 1, elements);
        }
    }

    private void swap(int[] toSwap, int a, int b) {
        int tmp = toSwap[a];
        toSwap[a] = toSwap[b];
        toSwap[b] = tmp;
    }

}
