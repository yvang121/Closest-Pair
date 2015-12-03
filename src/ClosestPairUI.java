import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Ye on 12/2/2015.
 */
public class ClosestPairUI extends GraphicsProgram {
    private ArrayList<GPoint> coordinates = new ArrayList<GPoint>();
    private static final int CIRCLE_RADIUS = 15;

    public void init() {
        addMouseListeners();
        addKeyListeners();
    }

    public void run() {
        while (true) {
            if (coordinates.size() > 1 ) {
                ClosestPair closestPair = new ClosestPair(coordinates);
                System.out.println(closestPair.
                        findClosestDist(closestPair.getSortedByXCoords(), closestPair.getSortedByYCoords())
                .getValue2());
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pause(250);
            System.exit(0);
        }
    }

    public void mouseClicked(MouseEvent e) {
        GPoint point = new GPoint(e.getX(), e.getY());
        coordinates.add(point);
        GOval circle = new GOval(CIRCLE_RADIUS, CIRCLE_RADIUS);
        add(circle, e.getX(), e.getY());
    }
}
