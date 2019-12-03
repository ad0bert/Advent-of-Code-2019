package main.day03;

public class Day3Instruction {

    public char direction;
    public int steps;

    public Day3Instruction(String input) {
        direction = input.charAt(0);
        steps = Integer.parseInt(input.substring(1));
    }

    @Override
    public String toString() {
        return direction + String.valueOf(steps);
    }
}
