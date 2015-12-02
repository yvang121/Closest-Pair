import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.util.ArrayList;

/**
 * Created by Ye on 12/2/2015.
 */
public class ClosestPairUI extends GraphicsProgram {
    ArrayList<GPoint> coordinates;

    public void init() {
        addMouseListeners();
    }

    public void run() {
        coordinates = new ArrayList<GPoint>();
        while (true) {
            if (coordinates.size() > 1 ) {
                ClosestPair closestPair = new ClosestPair(coordinates);
//                ArrayList<Double> sortedX = closestPair.getSortedXCoords();
//                ArrayList<Double> sortedY = closestPair.getSortedYCoords();
//                closestPair.findClosestPair(sortedX, sortedY);
            }
        }
    }
}
