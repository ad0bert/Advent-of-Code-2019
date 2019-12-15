package main.day11;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;
import main.AbstractSolver;
import utils.AoCFileReader;
import utils.IntCode;

import javax.swing.*;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<Long> input = AoCFileReader.readLongLine(new File(this.inputFile1));
        IntCode program = new IntCode();
        program.initLong(input);
        long paint = 0;
        long direc = 0;
        int up = 0;
        int down = 1;
        int left = 2;
        int right  = 3;
        int posX = 0;
        int posY = 0;

        Map<Point, Pair<Long, Long>> panels = new HashMap<>();

        panels.put(new Point(posX, posY), new Pair<>(0L, 0L));
        while (program.running) {
            Point point = new Point(posX, posY);
            Pair<Long, Long> pair = panels.get(point);
            if (pair == null) {
                panels.put(point, new Pair<>(0L, 0L));
            }
            pair = panels.get(point);
            paint = program.runFor10(pair.getKey());
            panels.put(point, new Pair<>(paint, pair.getValue() + 1L));
            if (program.runFor10(pair.getKey()) == 1) {
                if (direc == up) {
                    posX++;
                    direc = right;
                    continue;
                }
                if (direc == down) {
                    posX--;
                    direc = left;
                    continue;
                }
                if (direc == left) {
                    posY--;
                    direc = up;
                    continue;
                }
                if (direc == right) {
                    posY++;
                    direc = down;
                    continue;
                }
            } else {
                if (direc == up) {
                    posX--;
                    direc = left;
                    continue;
                }
                if (direc == down) {
                    posX++;
                    direc = right;
                    continue;
                }
                if (direc == left) {
                    posY++;
                    direc = down;
                    continue;
                }
                if (direc == right) {
                    posY--;
                    direc = up;
                    continue;
                }
            }
        }
        System.out.println(panels.size());
    }

    @Override
    public void solvePart2() {
        List<Long> input = AoCFileReader.readLongLine(new File(this.inputFile2));
        IntCode program = new IntCode();
        program.initLong(input);
        long paint = 0;
        long direc = 0;
        int up = 0;
        int down = 1;
        int left = 2;
        int right  = 3;
        int posX = 0;
        int posY = 0;

        Map<Point, Pair<Long, Long>> panels = new HashMap<>();

        panels.put(new Point(posX, posY), new Pair<>(1L, 0L));
        while (program.running) {
            Point point = new Point(posX, posY);
            Pair<Long, Long> pair = panels.get(point);
            if (pair == null) {
                panels.put(point, new Pair<>(0L, 0L));
            }
            pair = panels.get(point);
            paint = program.runFor10(pair.getKey());
            panels.put(point, new Pair<>(paint, pair.getValue() + 1L));
            if (program.runFor10(pair.getKey()) == 1) {
                if (direc == up) {
                    posX++;
                    direc = right;
                    continue;
                }
                if (direc == down) {
                    posX--;
                    direc = left;
                    continue;
                }
                if (direc == left) {
                    posY--;
                    direc = up;
                    continue;
                }
                if (direc == right) {
                    posY++;
                    direc = down;
                    continue;
                }
            } else {
                if (direc == up) {
                    posX--;
                    direc = left;
                    continue;
                }
                if (direc == down) {
                    posX++;
                    direc = right;
                    continue;
                }
                if (direc == left) {
                    posY++;
                    direc = down;
                    continue;
                }
                if (direc == right) {
                    posY--;
                    direc = up;
                    continue;
                }
            }
        }
        Drawer drawer = new Drawer();
        drawer.toPaint = panels;
        drawer.setVisible(true);
        System.out.println("JFBERBUH");
    }

}
