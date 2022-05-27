package com.metanit;

import java.util.ArrayList;

public class Polygon {

    private ArrayList<Point> points = new ArrayList<>();

    public ArrayList<Point> getPoints() {
        return points;
    }

    private boolean arePointsEqual(Point newPoint, Point oldPoint) {

        int newPointX = newPoint.x;
        int newPointY = newPoint.y;

        int oldPointX = oldPoint.x;
        int oldPointY = oldPoint.y;

        return newPointX == oldPointX && newPointY == oldPointY;
    }

    private boolean isPointAlreadyAdded(Point newPoint) {
        for (int i = 0; i < points.size(); i++) {
            if (arePointsEqual(newPoint, points.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void addPoint(int x, int y) throws Exception {
        Point newPoint = new Point(x, y);

        if (!isPointAlreadyAdded(newPoint)) {
            points.add(newPoint);
        }else {
            throw new Exception("Точка уже была добавлена.");
        }
    }

    // Находим площадь по формуле Гаусса.
    private double square() {

        double S = 0;
        int sum1 = 0, sum2 = 0;

        for (int i = 0; i < points.size() - 1; i++) {
            int X1 = points.get(i).x;
            int Y1 = points.get(i).y;
            int X2 = points.get(i + 1).x;
            int Y2 = points.get(i + 1).y;
            sum1 += X1 * Y2;
            sum2 += X2 * Y1;
        }

        int lastPointX = points.get(points.size() - 1).x;
        int lastPointY = points.get(points.size() - 1).y;
        int firstPointX = points.get(0).x;
        int firstPointY = points.get(0).y;

        S = (double) 1 / 2 * (sum1 - sum2 + lastPointX * firstPointY - firstPointX * lastPointY);
        return S;
    }

    public double getSquare() {
        return Math.abs(square());
    }

    public double getPerimeter() {
        int p = 0;
        for (int i = 0; i < points.size() - 1; i++) {

            p += distanceBetween(points.get(i), points.get(i + 1));
        }
        return p + distanceBetween(points.get(0), points.get(points.size() - 1));
    }

    // Нахождение дистанции между двумя точками.
    private double distanceBetween(Point p1, Point p2) {
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    // Смещение на заданные координаты
    public void moving(int mx, int my) {
        for (int i = 0; i < points.size(); i++) {
            points.get(i).setX(points.get(i).x + mx);
            points.get(i).setY(points.get(i).y + my);
        }
    }

    public Polygon findRectangle() {

        int maxX = points.get(0).x, minX = points.get(0).x;
        int maxY = points.get(0).y, minY = points.get(0).y;

        for (int i = 0; i < points.size() - 1; i++) {
            if (maxX < points.get(i + 1).x) {
                maxX = points.get(i + 1).x;
            }
            if (minX > points.get(i + 1).x) {
                minX = points.get(i + 1).x;
            }
            if (maxY < points.get(i + 1).y) {
                maxY = points.get(i + 1).y;
            }
            if (minY > points.get(i + 1).y) {
                minY = points.get(i + 1).y;
            }
        }

        Polygon rectangle = new Polygon();

        try {
            rectangle.addPoint(maxX, maxY);
            rectangle.addPoint(maxX, minY);
            rectangle.addPoint(minX, minY);
            rectangle.addPoint(minX, maxY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rectangle;
    }

    public void scale(double m) {

        if (m < 0) {
            return;
        }

         Point center = findCenter();
         moving(-center.x, -center.y);

        for (int i = 0; i < points.size(); i++) {
            int newX = (int) (points.get(i).x * m);
            points.get(i).setX(newX);
            int newY = (int) (points.get(i).y * m);
            points.get(i).setY(newY);
        }

        moving(center.x, center.y);
    }

    private Point findCenter() {

        int sumX = 0;
        int sumY = 0;

        for (int i = 0; i < points.size() - 1; i++) {
            int x1 = points.get(i).x;
            int y1 = points.get(i).y;
            int x2 = points.get(i + 1).x;
            int y2 = points.get(i + 1).y;
            sumX += (x1 + x2) * (x1 * y2 - x2 * y1);
            sumY += (y1 + y2) * (x1 * y2 - x2 * y1);
        }
        int lastPointX = points.get(points.size() - 1).x;
        int lastPointY = points.get(points.size() - 1).y;
        int firstPointX = points.get(0).x;
        int firstPointY = points.get(0).y;
        int centerX = (int) ((sumX + (lastPointX + firstPointX) * (lastPointX * firstPointY - firstPointX * lastPointY))
                / (6 * square()));
        int centerY = (int) ((sumY + (lastPointY + firstPointY) * (lastPointX * firstPointY - firstPointX * lastPointY))
                / (6 * square()));
        return new Point(centerX, centerY);
    }




}
