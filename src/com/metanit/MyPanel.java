package com.metanit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    private ArrayList<Point> points;

    public void addPolygon(Polygon p) {
        points = p.getPoints();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {

            for (int i = 0; i < points.size() - 1; i++) {
                int x1 = points.get(i).x;
                int y1 = points.get(i).y;
                int x2 = points.get(i + 1).x;
                int y2 = points.get(i + 1).y;
                g.drawLine(x1, y1, x2, y2);
            }

            Point First = points.get(0);
            Point Last = points.get(points.size() - 1);

            int x1 = Last.x;
            int y1 = Last.y;
            int x2 = First.x;
            int y2 = First.y;
            g.drawLine(x1, y1, x2, y2);


        } catch (NullPointerException e) {
            System.out.println("Прямоугольник не создан");
        }
    }
}
