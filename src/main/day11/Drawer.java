package main.day11;

import javafx.util.Pair;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

public class Drawer extends Frame {

    public Map<Point, Pair<Long, Long>> toPaint;

    public Drawer() {
        super("Java AWT Examples");
        prepareGUI();
    }

    private void prepareGUI() {
        setSize(400, 400);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        toPaint.forEach((point, longLongPair) -> {
            if (longLongPair.getKey() == 1) {
                g2.fillOval(point.x+100, point.y+100, 2, 2);
            }
        });
    }
}
