package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IntCode {

    private final Integer ADD = 1;
    private final Integer MUL = 2;
    private final Integer READ = 3;
    private final Integer WRITE = 4;
    private final Integer JIT = 5;
    private final Integer JIF = 6;
    private final Integer LT = 7;
    private final Integer EQ = 8;
    private final Integer REL = 9;
    private final Integer HALT = 99;

    private int relBase = 0;

    private List<Integer> program;

    public IntCode(List<Integer> program) {
        this.program = new ArrayList<>(program);
    }

    public int run(int[] args) {
        int instructionCnt;
        int argument = 0;
        List<Integer> outputBuffer = new ArrayList<>();
        List<Integer> input = new ArrayList<>(this.program);
        int[] instruction;
        for (int i = 0; i < input.size(); i += instructionCnt) {
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
                input.set(input.get(i + 1), args[argument++]);
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
        return outputBuffer.get(outputBuffer.size() - 1);
    }

    private int ip = 0;
    public boolean running = true;
    private boolean init = false;
    public int code = 0;

    public int runPersist(int param) {
        int instructionCnt;
        int[] instruction;
        for (int i = ip; i < program.size(); i += instructionCnt) {
            instruction = String.format("%05d", program.get(i)).chars().map(c -> c - '0').toArray();
            int op = Integer.parseInt(String.valueOf(instruction[3]) + String.valueOf(instruction[4]));
            if (ADD.equals(op)) {
                int val1 = instruction[2] == 0 ? program.get(program.get(i + 1)) : program.get(i + 1);
                int val2 = instruction[1] == 0 ? program.get(program.get(i + 2)) : program.get(i + 2);
                program.set(program.get(i + 3), val1 + val2);
                instructionCnt = 4;
            } else if (MUL.equals(op)) {
                int val1 = instruction[2] == 0 ? program.get(program.get(i + 1)) : program.get(i + 1);
                int val2 = instruction[1] == 0 ? program.get(program.get(i + 2)) : program.get(i + 2);
                program.set(program.get(i + 3), val1 * val2);
                instructionCnt = 4;
            } else if (READ.equals(op)) {
                if (!init) {
                    program.set(program.get(i + 1), code);
                    init = true;
                } else {
                    program.set(program.get(i + 1), param);
                }
                instructionCnt = 2;
            } else if (WRITE.equals(op)) {
                int out = (instruction[2] == 0 ? program.get(program.get(i + 1)) : program.get(i + 1));
//                instructionCnt = 2;
                ip = i + 2;
                return out;
            } else if (JIT.equals(op)) {
                int val1 = instruction[2] == 0 ? program.get(program.get(i + 1)) : program.get(i + 1);
                int val2 = instruction[1] == 0 ? program.get(program.get(i + 2)) : program.get(i + 2);
                if (val1 != 0) {
                    i = val2;
                    instructionCnt = 0;
                } else {
                    instructionCnt = 3;
                }
            } else if (JIF.equals(op)) {
                int val1 = instruction[2] == 0 ? program.get(program.get(i + 1)) : program.get(i + 1);
                int val2 = instruction[1] == 0 ? program.get(program.get(i + 2)) : program.get(i + 2);
                if (val1 == 0) {
                    i = val2;
                    instructionCnt = 0;
                } else {
                    instructionCnt = 3;
                }
            } else if (LT.equals(op)) {
                int val1 = instruction[2] == 0 ? program.get(program.get(i + 1)) : program.get(i + 1);
                int val2 = instruction[1] == 0 ? program.get(program.get(i + 2)) : program.get(i + 2);
                program.set(program.get(i + 3), val1 < val2 ? 1 : 0);
                instructionCnt = 4;
            } else if (EQ.equals(op)) {
                int val1 = instruction[2] == 0 ? program.get(program.get(i + 1)) : program.get(i + 1);
                int val2 = instruction[1] == 0 ? program.get(program.get(i + 2)) : program.get(i + 2);
                program.set(program.get(i + 3), val1 == val2 ? 1 : 0);
                instructionCnt = 4;
            } else if (HALT.equals(op)) {
                break;
            } else {
                System.out.println("BEEP");
                break;
            }
        }
        running = false;
        return -1;
    }

    public List<Long> runFor9(long arg) {
        long instructionCnt;
        List<Long> outputBuffer = new ArrayList<>();
        List<Long> input = new ArrayList<>(this.program.stream().map(Integer::longValue).collect(Collectors.toList()));
        int additionalMemory = 1028;
        while (additionalMemory-- > 0) input.add(0L);
        int[] instruction;
        for (int i = 0; i < input.size(); i += instructionCnt) {
            instruction = String.format("%05d", input.get(i)).chars().map(c -> c - '0').toArray();
            int op = Integer.parseInt(String.valueOf(instruction[3]) + String.valueOf(instruction[4]));
            if (ADD.equals(op)) {
                Long val1 = instruction[2] == 0 ? input.get(input.get(i + 1).intValue()) : instruction[2] == 1 ? input.get(i + 1) : input.get(this.relBase + input.get(i + 1).intValue());
                Long val2 = instruction[1] == 0 ? input.get(input.get(i + 2).intValue()) : instruction[1] == 1 ? input.get(i + 2) : input.get(this.relBase + input.get(i + 2).intValue());
                Long val3 = instruction[0] == 0 ? input.get(i + 3).intValue() : this.relBase + input.get(i + 3);
                input.set(val3.intValue(), val1 + val2);
                instructionCnt = 4;
            } else if (MUL.equals(op)) {
                Long val1 = instruction[2] == 0 ? input.get(input.get(i + 1).intValue()) : instruction[2] == 1 ? input.get(i + 1) : input.get(this.relBase + input.get(i + 1).intValue());
                Long val2 = instruction[1] == 0 ? input.get(input.get(i + 2).intValue()) : instruction[1] == 1 ? input.get(i + 2) : input.get(this.relBase + input.get(i + 2).intValue());
                Long val3 = instruction[0] == 0 ? input.get(i + 3).intValue() : this.relBase + input.get(i + 3);
                input.set(val3.intValue(), val1 * val2);
                instructionCnt = 4;
            } else if (READ.equals(op)) {
                Long val1 = instruction[2] == 0 ? input.get(i + 1).intValue() : this.relBase + input.get(i + 1);
                input.set(val1.intValue(), arg);
                instructionCnt = 2;
            } else if (WRITE.equals(op)) {
                outputBuffer.add(instruction[2] == 0 ? input.get(input.get(i + 1).intValue()) : instruction[2] == 1 ? input.get(i + 1) : input.get(this.relBase + input.get(i + 1).intValue()));
                instructionCnt = 2;
            } else if (JIT.equals(op)) {
                Long val1 = instruction[2] == 0 ? input.get(input.get(i + 1).intValue()) : instruction[2] == 1 ? input.get(i + 1) : input.get(this.relBase + input.get(i + 1).intValue());
                Long val2 = instruction[1] == 0 ? input.get(input.get(i + 2).intValue()) : instruction[1] == 1 ? input.get(i + 2) : input.get(this.relBase + input.get(i + 2).intValue());
                if (val1 != 0) {
                    i = val2.intValue();
                    instructionCnt = 0;
                } else {
                    instructionCnt = 3;
                }
            } else if (JIF.equals(op)) {
                Long val1 = instruction[2] == 0 ? input.get(input.get(i + 1).intValue()) : instruction[2] == 1 ? input.get(i + 1) : input.get(this.relBase + input.get(i + 1).intValue());
                Long val2 = instruction[1] == 0 ? input.get(input.get(i + 2).intValue()) : instruction[1] == 1 ? input.get(i + 2) : input.get(this.relBase + input.get(i + 2).intValue());
                if (val1 == 0) {
                    i = val2.intValue();
                    instructionCnt = 0;
                } else {
                    instructionCnt = 3;
                }
            } else if (LT.equals(op)) {
                Long val1 = instruction[2] == 0 ? input.get(input.get(i + 1).intValue()) : instruction[2] == 1 ? input.get(i + 1) : input.get(this.relBase + input.get(i + 1).intValue());
                Long val2 = instruction[1] == 0 ? input.get(input.get(i + 2).intValue()) : instruction[1] == 1 ? input.get(i + 2) : input.get(this.relBase + input.get(i + 2).intValue());
                Long val3 = instruction[0] == 0 ? input.get(i + 3).intValue() : this.relBase + input.get(i + 3);
                input.set(val3.intValue(), val1 < val2 ? 1L : 0L);
                instructionCnt = 4;
            } else if (EQ.equals(op)) {
                Long val1 = instruction[2] == 0 ? input.get(input.get(i + 1).intValue()) : instruction[2] == 1 ? input.get(i + 1) : input.get(this.relBase + input.get(i + 1).intValue());
                Long val2 = instruction[1] == 0 ? input.get(input.get(i + 2).intValue()) : instruction[1] == 1 ? input.get(i + 2) : input.get(this.relBase + input.get(i + 2).intValue());
                Long val3 = instruction[0] == 0 ? input.get(i + 3).intValue() : this.relBase + input.get(i + 3);
                input.set(val3.intValue(), val1.equals(val2) ? 1L : 0L);
                instructionCnt = 4;
            } else if (REL.equals(op)) {
                Long val1 = instruction[2] == 0 ? input.get(input.get(i + 1).intValue()) : instruction[2] == 1 ? input.get(i + 1) : input.get(this.relBase + input.get(i + 1).intValue());
                this.relBase += val1;
                instructionCnt = 2;
            } else if (HALT.equals(op)) {
                break;
            } else {
                System.out.println("BEEP");
                break;
            }

        }
        return outputBuffer;
    }
}
