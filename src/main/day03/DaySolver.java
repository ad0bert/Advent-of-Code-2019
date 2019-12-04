package main.day03;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<List<Day3Instruction>> input = AoCFileReader.readDay3Instructions(new File(this.inputFile1));
        Map<Point, Boolean> movement1 = this.createMovement(input.get(0));
        Map<Point, Boolean> movement2 = this.createMovement(input.get(1));
        List<Point> intersections = new ArrayList<>();

        for (Map.Entry entry : movement1.entrySet()) {
            if (movement2.containsKey(entry.getKey())) {
                intersections.add((Point)entry.getKey());
            }
        }
        int minDist = Integer.MAX_VALUE;

        for (Point entry : intersections) {
            int dist = Math.abs(entry.x) + Math.abs(entry.y);
            if (dist < minDist) {
                minDist = dist;
            }
        }

        System.out.println(minDist);
    }

    @Override
    public void solvePart2() {
        List<List<Day3Instruction>> input = AoCFileReader.readDay3Instructions(new File(this.inputFile2));
        Map<Point, Boolean> movement1 = this.createMovement(input.get(0));
        Map<Point, Boolean> movement2 = this.createMovement(input.get(1));
        List<Point> intersections = new ArrayList<>();

        for (Map.Entry entry : movement1.entrySet()) {
            if (movement2.containsKey(entry.getKey())) {
                intersections.add((Point)entry.getKey());
            }
        }
        int minDist = Integer.MAX_VALUE;

        for (Point entry : intersections) {
            int dist = calcDistUntilPoint(entry, input.get(0)) + calcDistUntilPoint(entry, input.get(1));
            if (dist < minDist) {
                minDist = dist;
            }
        }

        System.out.println(minDist);
    }

    private Map<Point, Boolean> createMovement(List<Day3Instruction> input) {
        Map<Point, Boolean> movement = new HashMap<>();
        int x = 0;
        int y = 0;

        for (Day3Instruction day3Instruction : input) {
            for (int i = 0; i < day3Instruction.steps; ++i) {
                if (day3Instruction.direction == 'U') {
                    y--;
                } else if (day3Instruction.direction == 'D') {
                    y++;
                } else if (day3Instruction.direction == 'R') {
                    x++;
                } else if (day3Instruction.direction == 'L') {
                    x--;
                } else {
                    System.out.println("ERROR");
                }
                Point p = new Point(x, y);
                if (movement.containsKey(p)) {
                    movement.put(p, true);
                } else {
                    movement.put(p, false);
                }
            }
        }
        return movement;
    }

    private int calcDistUntilPoint(Point intersection, List<Day3Instruction> input) {
        int x = 0;
        int y = 0;
        int cnt = 1;
        for (Day3Instruction day3Instruction : input) {
            for (int i = 0; i < day3Instruction.steps; ++i, ++cnt) {
                if (day3Instruction.direction == 'U') {
                    y--;
                } else if (day3Instruction.direction == 'D') {
                    y++;
                } else if (day3Instruction.direction == 'R') {
                    x++;
                } else if (day3Instruction.direction == 'L') {
                    x--;
                } else {
                    System.out.println("ERROR");
                }
                if (intersection.x == x && intersection.y == y) {
                    return cnt;
                }
            }
        }
        return -1;
    }

}
