package mulproj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class ResizeRectangle extends JPanel {

    private int SIZE = 8;
    public Rectangle2D[] points = new Rectangle2D[2];
    Rectangle2D s = new Rectangle2D.Double();
    ShapeResizeHandler ada = new ShapeResizeHandler();

    public ResizeRectangle(double nwx, double nwy, double sex, double sey) {
        this.points[0] = new Rectangle2D.Double(nwx, nwy, SIZE, SIZE);
        this.points[1] = new Rectangle2D.Double(sex, sey, SIZE, SIZE);
        addMouseListener(ada);
        addMouseMotionListener(ada);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);

        for (int i = 0; i < points.length; i++) {
            g2.fill(points[i]);
        }
        s.setRect(points[0].getCenterX(), points[0].getCenterY(),
                Math.abs(points[1].getCenterX() - points[0].getCenterX()),
                Math.abs(points[1].getCenterY() - points[0].getCenterY()));

        g2.draw(s);
//        System.out.println(points[0].getCenterX()+" "+points[0].getCenterY());
    }

    class ShapeResizeHandler extends MouseAdapter {

        Rectangle2D r = new Rectangle2D.Double(0, 0, SIZE, SIZE);
        private int pos = -1;

        public void mousePressed(MouseEvent event) {
            Point p = event.getPoint();

            for (int i = 0; i < points.length; i++) {
                if (points[i].contains(p)) {
                    pos = i;
                    return;
                }
            }
        }

        public void mouseReleased(MouseEvent event) {
            pos = -1;
        }

        public void mouseDragged(MouseEvent event) {
            if (pos == -1) {
                return;
            }

            points[pos].setRect(event.getPoint().x, event.getPoint().y, points[pos].getWidth(),
                    points[pos].getHeight());
            repaint();
        }
    }

}