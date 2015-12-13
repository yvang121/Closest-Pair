import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import org.javatuples.Triplet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Ye on 12/2/2015.
 * Closest-pair Graphical User-Interface implementation.
 */
public class ClosestPairUI extends GraphicsProgram {
    private ArrayList<GPoint> coordinates = new ArrayList<GPoint>(); // ArrayList containing all GPoints on canvas.
    private GPoint[] closestPoints = new GPoint[2];  // Array containing the two closest GPoints.
    private GLabel distLabel;  // The label containing the string type of the distance. This has to be dynamic
    private static final int CIRCLE_RADIUS = 10; // Radius of all circle points.
    private static final Color defaultC = new Color(0, 76, 153); // Color for default non-closest pair points.
    private static final Color closePtC = new Color(250, 160, 24); // Color for the two closest pair points.
    private static final GOval cpOverlay1 = new GOval(CIRCLE_RADIUS, CIRCLE_RADIUS); // Overlay closest pt1 with orange
    private static final GOval cpOverlay2 = new GOval(CIRCLE_RADIUS, CIRCLE_RADIUS); // Overlay closest pt2 with orange

    public void init() {
        addMouseListeners();
        addKeyListeners();
    }

    public void run() {
        GLabel minDistance = new GLabel("Minimum distance:"); // Label stating the minimum distance
        add(minDistance, 10, 10);
        ClosestPair cp = new ClosestPair(coordinates);  // Calculate initial null distance (infinity)
        ArrayList<GPoint> x = cp.getSortedByXCoords();
        ArrayList<GPoint> y = cp.getSortedByYCoords();
        double dist = cp.findClosestDist(x, y).getValue2();
        distLabel = new GLabel(Double.toString(dist) + " pixels");
        add(distLabel, 10, 30);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pause(250);
            System.out.println(coordinates.size());
            System.exit(0);
        }
    }

    /**
     * Every time the mouse is clicked, it adds a new default point (blue) and calculates for a new closest-pair
     * if there is one. If so, store these points into the closestPoints array and set the distance label to the
     * new calculated distance. Move overlay orange points to designate which two points are now the closest-pair.
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
        GPoint point = new GPoint(e.getX(), e.getY());
        coordinates.add(point);

        ClosestPair cp = new ClosestPair(coordinates);
        ArrayList<GPoint> x = cp.getSortedByXCoords();
        ArrayList<GPoint> y = cp.getSortedByYCoords();
        Triplet<GPoint, GPoint, Double> triplet = cp.findClosestDist(x, y);
        if (!distLabel.getLabel().equals(Double.toString(triplet.getValue2()))) {
            closestPoints[0] = triplet.getValue0();
            closestPoints[1] = triplet.getValue1();
        }
        double dist = cp.findClosestDist(x, y).getValue2();
        distLabel.setLabel(Double.toString(dist) + " pixels");

        GOval circle = new GOval(CIRCLE_RADIUS, CIRCLE_RADIUS);
        circle.setFilled(true);
        circle.setFillColor(defaultC);

        cpOverlay1.setFilled(true);
        cpOverlay1.setColor(closePtC);
        cpOverlay2.setFilled(true);
        cpOverlay2.setColor(closePtC);

        add(circle, e.getX(), e.getY());
        if (closestPoints[0] != null && closestPoints[1] != null) {
            add(cpOverlay1, closestPoints[0].getX(), closestPoints[0].getY());
            add(cpOverlay2, closestPoints[1].getX(), closestPoints[1].getY());
        }
        System.out.println(dist);
    }

    public static void main(String[] args) {
        new ClosestPairUI().start(args);
    }
}
