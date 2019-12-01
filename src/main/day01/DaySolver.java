package main.day01;

import java.io.File;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<Integer> input = AoCFileReader.readIntegerLineVertical(new File(this.inputFile1));
        int res = 0;
        for (int i : input) {
            res += calcFuelFor(i);
        }
        System.out.println(res);
    }

    @Override
    public void solvePart2() {
        List<Integer> input = AoCFileReader.readIntegerLineVertical(new File(this.inputFile2));
        int res = 0;
        for (int i : input) {
            res += calcAllFuelFor(i);
        }
        System.out.println(res);
    }

    private int calcFuelFor(final int weight) {
        return weight/3-2;
    }

    private int calcAllFuelFor(final int weight) {
        int res = 0;
        int curr = calcFuelFor(weight);
        while (curr > 0) {
            res += curr;
            curr = calcFuelFor(curr);
        }
        return res;
    }

}
