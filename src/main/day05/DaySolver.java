package main.day05;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    private final Integer ADD = 1;
    private final Integer MUL = 2;
    private final Integer READ = 3;
    private final Integer WRITE = 4;
    private final Integer HALT = 99;

    @Override
    public void solvePart1() {
        List<Integer> input = AoCFileReader.readIntegerLine(new File(this.inputFile1));
        int instructionCnt;
        int argument = 1;
        List<Integer> outputBuffer = new ArrayList<>();
        int[] instruction;
        for (int i = 0; i < input.size(); i+=instructionCnt) {
            instruction = String.format("%05d", input.get(i)).chars().map(c -> c - '0').toArray();
            int op = Integer.parseInt(String.valueOf(instruction[3]) + String.valueOf(instruction[4]));
            if (ADD.equals(op)) {
                int val1 = instruction[2] == 0 ? input.get(input.get(i + 1)) : input.get(i + 1);
                int val2 = instruction[1] == 0 ? input.get(input.get(i + 2)) : input.get(i + 2);
                input.set(input.get(i + 3), val1 + val2);
                instructionCnt = 4;
            } else if (MUL.equals(op)) {
                int val1 = instruction[2] == 0 ? input.get(input.get(i + 1)) : input.get(i + 1);
                int val2 = instruction[1] == 0 ? input.get(input.get(i + 2)) : input.get(i + 2);
                input.set(input.get(i + 3), val1 * val2);
                instructionCnt = 4;
            } else if (READ.equals(op)) {
                input.set(input.get(i + 1), argument);
                instructionCnt = 2;
            } else if (WRITE.equals(op)) {
                outputBuffer.add(instruction[2] == 0 ? input.get(input.get(i + 1)) : input.get(i + 1));
                instructionCnt = 2;
            } else if (HALT.equals(op)) {
                instructionCnt = 1;
                break;
            } else {
                System.out.println("BEEP");
                break;
            }

        }
        System.out.println(outputBuffer.get(outputBuffer.size() - 1));
    }

    private final Integer JIT = 5;
    private final Integer JIF = 6;
    private final Integer LT = 7;
    private final Integer EQ = 8;

    @Override
    public void solvePart2() {
        List<Integer> input = AoCFileReader.readIntegerLine(new File(this.inputFile2));
        int instructionCnt;
        int argument = 5;
        List<Integer> outputBuffer = new ArrayList<>();
        int[] instruction;
        for (int i = 0; i < input.size(); i+=instructionCnt) {
            instruction = String.format("%05d", input.get(i)).chars().map(c -> c - '0').toArray();
            int op = Integer.parseInt(String.valueOf(instruction[3]) + String.valueOf(instruction[4]));
            if (ADD.equals(op)) {
                int val1 = instruction[2] == 0 ? input.get(input.get(i + 1)) : input.get(i + 1);
                int val2 = instruction[1] == 0 ? input.get(input.get(i + 2)) : input.get(i + 2);
                input.set(input.get(i + 3), val1 + val2);
                instructionCnt = 4;
            } else if (MUL.equals(op)) {
                int val1 = instruction[2] == 0 ? input.get(input.get(i + 1)) : input.get(i + 1);
                int val2 = instruction[1] == 0 ? input.get(input.get(i + 2)) : input.get(i + 2);
                input.set(input.get(i + 3), val1 * val2);
                instructionCnt = 4;
            } else if (READ.equals(op)) {
                input.set(input.get(i + 1), argument);
                instructionCnt = 2;
            } else if (WRITE.equals(op)) {
                outputBuffer.add(instruction[2] == 0 ? input.get(input.get(i + 1)) : input.get(i + 1));
                instructionCnt = 2;
            } else if (JIT.equals(op)) {
                int val1 = instruction[2] == 0 ? input.get(input.get(i + 1)) : input.get(i + 1);
                int val2 = instruction[1] == 0 ? input.get(input.get(i + 2)) : input.get(i + 2);
                if (val1 != 0) {
                    i = val2;
                    instructionCnt = 0;
                } else {
                    instructionCnt = 3;
                }
            } else if (JIF.equals(op)) {
                int val1 = instruction[2] == 0 ? input.get(input.get(i + 1)) : input.get(i + 1);
                int val2 = instruction[1] == 0 ? input.get(input.get(i + 2)) : input.get(i + 2);
                if (val1 == 0) {
                    i = val2;
                    instructionCnt = 0;
                } else {
                    instructionCnt = 3;
                }
            } else if (LT.equals(op)) {
                int val1 = instruction[2] == 0 ? input.get(input.get(i + 1)) : input.get(i + 1);
                int val2 = instruction[1] == 0 ? input.get(input.get(i + 2)) : input.get(i + 2);
                input.set(input.get(i + 3), val1 < val2 ? 1 : 0);
                instructionCnt = 4;
            } else if (EQ.equals(op)) {
                int val1 = instruction[2] == 0 ? input.get(input.get(i + 1)) : input.get(i + 1);
                int val2 = instruction[1] == 0 ? input.get(input.get(i + 2)) : input.get(i + 2);
                input.set(input.get(i + 3), val1 == val2 ? 1 : 0);
                instructionCnt = 4;
            } else if (HALT.equals(op)) {
                break;
            } else {
                System.out.println("BEEP");
                break;
            }

        }
        System.out.println(outputBuffer.get(outputBuffer.size() - 1));
    }

}
