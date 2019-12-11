package main.day09;

import java.io.File;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;
import utils.IntCode;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<Integer> input = AoCFileReader.readIntegerLine(new File(this.inputFile1));
        IntCode program = new IntCode(input);
        System.out.println(program.runFor9(1).get(0));
    }

    @Override
    public void solvePart2() {
        List<Integer> input = AoCFileReader.readIntegerLine(new File(this.inputFile2));
        IntCode program = new IntCode(input);
        System.out.println(program.runFor9(2).get(0));
    }

}
