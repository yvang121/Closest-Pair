import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Ye on 12/2/2015.
 */
public class ClosestPairUI extends GraphicsProgram {
    private ArrayList<GPoint> coordinates = new ArrayList<GPoint>();
    private static final int CIRCLE_RADIUS = 10;

    public void init() {
        addMouseListeners();
        addKeyListeners();
    }

    public void run() {
        Label minDistance = new Label("Minimum distance");
        Double distance = 0.0;
        GLabel distLabel = new GLabel(distance.toString());
        add(distLabel, 30, 30);
        //todo: what if for every click, we simply updated it once instead of constantly
//        while (true) {
//            GPoint pt1 = new GPoint();
//            GPoint pt2 = new GPoint();
//            if (coordinates.size() > 1) {
//                ClosestPair closestPair = new ClosestPair(coordinates);
//                Triplet<GPoint, GPoint, Double> triplet = closestPair.findClosestDist(
//                        closestPair.getSortedByXCoords(), closestPair.getSortedByYCoords());
//                distance = triplet.getValue2();
//                pt1 = triplet.getValue0();
//                pt2 = triplet.getValue1();
//            }
//        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pause(250);
            System.out.println(coordinates.size());
            System.exit(0);
        }
    }

    public void mouseClicked(MouseEvent e) {
        GPoint point = new GPoint(e.getX(), e.getY());
        coordinates.add(point);
        GOval circle = new GOval(CIRCLE_RADIUS, CIRCLE_RADIUS);
        circle.setFilled(true);
        circle.setFillColor(Color.BLACK);
        add(circle, e.getX(), e.getY());
    }
}
