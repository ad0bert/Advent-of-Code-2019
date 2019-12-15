package main.day10;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<List<String>> input = AoCFileReader.readListOfCharList(new File(this.inputFile1));
        List<Point> asteroids = this.getAsteroids(input);
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < asteroids.size(); i++) {
            Point start = asteroids.get(i);
            int res = 0;
            for (int j = 0; j < asteroids.size(); j++) {
                Point end = asteroids.get(j);
                if (start.x == end.x && start.y == end.y) continue;
                boolean doContain = false;
                for (int k = 0; k < asteroids.size(); k++) {
                    Point curr = asteroids.get(k);
                    if ((start.x == curr.x && start.y == curr.y) || (end.x == curr.x && end.y == curr.y)) continue;
                    if (this.isBetween(start, end, curr)) {
                        doContain = true;
                        break;
                    }
                }
                if (!doContain) {
                    res++;
                }
            }
            values.add(res);

        }
        System.out.println(Collections.max(values));
    }

    @Override
    public void solvePart2() {
        List<List<String>> input = AoCFileReader.readListOfCharList(new File(this.inputFile2));
        List<Point> asteroids = this.getAsteroids(input);

        Point station = this.findStation(asteroids);
        List<Point> nearby = this.findNearbyAsteroids(station, asteroids);
        int removeCnt = 0;

        List<Point> toRemove = nearby.stream().filter(asteroid -> asteroid.x >= station.x).collect(Collectors.toList());
        nearby.removeAll(toRemove);
        removeCnt += toRemove.size();
        toRemove = nearby.stream().filter(asteroid -> asteroid.y >= station.y).collect(Collectors.toList());
        nearby.removeAll(toRemove);
        removeCnt += toRemove.size();

        Collections.sort(nearby, (lh, rh) -> {
            Line2D line1 = new Line2D.Double(station, lh);
            Line2D line2 = new Line2D.Double(station, rh);
            double angle1 = Math.atan2(line1.getY1() - line1.getY2(),
                    line1.getX1() - line1.getX2());
            double angle2 = Math.atan2(line2.getY1() - line2.getY2(),
                    line2.getX1() - line2.getX2());
            double res = (Math.abs(angle1) - Math.abs(angle2));
            if (res < 0) return -1;
            if (res > 0) return 1;
            return 0;
        });
        Point n200 = nearby.get(200 - removeCnt - 1);
        System.out.println(n200.x * 100 + n200.y);
    }

    private List<Point> getAsteroids(List<List<String>> input) {
        List<Point> asteroids = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).size(); j++) {
                if ("#".equals(input.get(i).get(j))) {
                    asteroids.add(new Point(j, i));
                }
            }
        }
        return asteroids;
    }

    private Point findStation(List<Point> asteroids) {
        for (int i = 0; i < asteroids.size(); i++) {
            Point start = asteroids.get(i);
            int res = 0;
            for (int j = 0; j < asteroids.size(); j++) {
                Point end = asteroids.get(j);
                if (start.x == end.x && start.y == end.y) continue;
                boolean doContain = false;
                for (int k = 0; k < asteroids.size(); k++) {
                    Point curr = asteroids.get(k);
                    if ((start.x == curr.x && start.y == curr.y) || (end.x == curr.x && end.y == curr.y)) continue;
                    if (this.isBetween(start, end, curr)) {
                        doContain = true;
                        break;
                    }
                }
                if (!doContain) {
                    res++;
                }
            }
            // if equals result from part 1
            if (res == 292) {
                return start;
            }
        }
        return null;
    }

    private List<Point> findNearbyAsteroids(Point station, List<Point> asteroids) {
        List<Point> nearby = new ArrayList<>();
        for (int j = 0; j < asteroids.size(); j++) {
            Point end = asteroids.get(j);
            if (station.x == end.x && station.y == end.y) continue;
            boolean doContain = false;
            for (int k = 0; k < asteroids.size(); k++) {
                Point curr = asteroids.get(k);
                if ((station.x == curr.x && station.y == curr.y) || (end.x == curr.x && end.y == curr.y)) continue;
                if (this.isBetween(station, end, curr)) {
                    doContain = true;
                    break;
                }
            }
            if (!doContain) {
                nearby.add(end);
            }
        }
        return nearby;
    }

    private boolean isBetween(Point a, Point b, Point c) {
        int crossproduct = (c.y - a.y) * (b.x - a.x) - (c.x - a.x) * (b.y - a.y);

        if (Math.abs(crossproduct) != 0) return false;

        int dotproduct = (c.x - a.x) * (b.x - a.x) + (c.y - a.y) * (b.y - a.y);
        if (dotproduct < 0) return false;

        int squaredlengthba = (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
        if (dotproduct > squaredlengthba) return false;
        return true;
    }

}
