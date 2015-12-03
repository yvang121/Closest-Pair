import acm.graphics.GPoint;
import org.javatuples.Triplet;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ye on 12/1/2015.
 */
public class ClosestPairTest {
    @Test
    public void testClosestPair() {
        ArrayList<GPoint> coord = new ArrayList<GPoint>(3);
        GPoint pt1 = new GPoint(100, 300);
        GPoint pt2 = new GPoint(50, 150);
        GPoint pt3 = new GPoint(75, 90);
        coord.add(pt1);
        coord.add(pt2);
        coord.add(pt3);

        ClosestPair closestPair = new ClosestPair(coord);
        System.out.println("Pre-sort: " + coord);
        System.out.println("Sorted by x-coordinate: " + closestPair.sortByX(coord));
        System.out.println("Sorted by y-coordinate: " + closestPair.sortByY(coord));
        Triplet<GPoint, GPoint, Double> triplet = closestPair
                .findClosestDist(closestPair.getSortedByXCoords(), closestPair.getSortedByYCoords());
        assertEquals(65.0, triplet.getValue2());
        System.out.println(triplet.getValue0().toString() +","+ triplet.getValue1().toString());
    }
}
