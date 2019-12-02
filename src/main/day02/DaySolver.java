package main.day02;

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
    private final Integer HALT = 99;

    private final Integer RES = 19690720;

    @Override
    public void solvePart1() {
        List<Integer> input = AoCFileReader.readIntegerLine(new File(this.inputFile1));
        for (int i = 0; i < input.size() - 4; i += 4) {
            if (input.get(i).equals(ADD)) {
                input.set(input.get(i + 3), input.get(input.get(i + 1)) + input.get(input.get(i + 2)));
            } else if (input.get(i).equals(MUL)) {
                input.set(input.get(i + 3), input.get(input.get(i + 1)) * input.get(input.get(i + 2)));
            } else if (input.get(i).equals(HALT)) {
                break;
            } else {
                System.out.println("BEEP");
            }
        }
        System.out.println(input.get(0));
    }

    @Override
    public void solvePart2() {
        final int NON_BORDER = 100;
        final int PAR_BORDER = 100;
        int iCnt = 4;
        List<Integer> input = AoCFileReader.readIntegerLine(new File(this.inputFile1));
        for (int nCnt = 0; nCnt < NON_BORDER; ++nCnt) {
            for (int pCnt = 0; pCnt < PAR_BORDER; ++pCnt) {
                List<Integer> cpy = new ArrayList<>(input);
                cpy.set(1, nCnt);
                cpy.set(2, pCnt);
                for (int i = 0; i < cpy.size(); i += iCnt) {
                    if (cpy.get(i).equals(ADD)) {
                        cpy.set(cpy.get(i + 3), cpy.get(cpy.get(i + 1)) + cpy.get(cpy.get(i + 2)));
                        iCnt = 4;
                    } else if (cpy.get(i).equals(MUL)) {
                        cpy.set(cpy.get(i + 3), cpy.get(cpy.get(i + 1)) * cpy.get(cpy.get(i + 2)));
                        iCnt = 4;
                    } else if (cpy.get(i).equals(HALT)) {
                        iCnt = 1;
                        break;
                    } else {
                        break;
                    }
                }
                if (cpy.get(0).equals(RES)) {
                    System.out.println(100 * nCnt + pCnt);
                }
            }
        }
    }
}
