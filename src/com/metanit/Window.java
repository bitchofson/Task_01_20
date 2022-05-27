package com.metanit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
20. Более простой вариант предыдущей задачи – все то же самое, но для прямоугольника.
    Непонятные вам моменты по заданию уточнить у преподавателя.
 */

public class Window {

    private JPanel mainPanel;
    private static JFrame frame;
    private Polygon polygon = new Polygon();
    private MyPanel paint = new MyPanel();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        frame = new JFrame("Task_01_20");
        frame.setContentPane(new Window().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public Window() {
        mainPanel.setLayout(new GridLayout());

        try {

            polygon.addPoint(0, 0);
            polygon.addPoint(50, 0);
            polygon.addPoint(50, 50);
            polygon.addPoint(0, 50);
            polygon.addPoint(0, 0);

        }catch (Exception exc){
            System.out.println(exc.getMessage());
        }

        mainPanel.add(paint);
        paint.addPolygon(polygon);
        frame.repaint();

        System.out.println("Изначальный периметр: " + polygon.getPerimeter());
        System.out.println("Изначальная площадь: " + polygon.getSquare());

          polygon.moving(200, 200);
          polygon.scale(2);
          frame.repaint();


        System.out.println("Периметр после масштабирования: " + polygon.getPerimeter());
        System.out.println("Площадь после масштабирования: " + polygon.getSquare());

        Polygon rectangle = polygon.findRectangle();
        ArrayList<Point> points = rectangle.getPoints();
        System.out.print("Координаты точек прямоугольника, описывающего многоугольник: ");
        for (Point point : points) {
            System.out.print("(" + point.x + "," + point.y + ") ");
        }

    }
}
