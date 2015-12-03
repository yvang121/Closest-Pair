import acm.graphics.GPoint;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Ye on 11/16/2015.
 */
public class ClosestPair {
    private ArrayList<GPoint> coordinates;
    private ArrayList<GPoint> xCoordSort = new ArrayList<GPoint>();
    private ArrayList<GPoint> yCoordSort = new ArrayList<GPoint>();
    private double closestPairDist;

    /**
     * Constructor method
     * @param coordList that's not necessarily sorted, but a list of GPoints
     */
    public ClosestPair(ArrayList<GPoint> coordList) {
        this.coordinates = coordList;
        this.xCoordSort = sortByX(coordList);
        this.yCoordSort = sortByY(coordList);
        this.closestPairDist = findClosestDist(xCoordSort, yCoordSort).getValue2();
    }
    //Hello
    String name = "Mitchell";
    
    /**
     * Finds the closest pair of GPoints within the GUI
     * @param sortedByX sorted list by x-coordinate
     * @param sortedByY sorted list by y-coordinate
     * @return a triplet containing the two points closest to each other, and the distance between them
     */
    public Triplet<GPoint, GPoint, Double> findClosestDist(ArrayList<GPoint> sortedByX, ArrayList<GPoint> sortedByY) {
        double minDist = Double.POSITIVE_INFINITY;
        if (sortedByX.size() <= 3) {   // if less than or equal to 3 GPoints left
            return bruteForce(this.coordinates);  // brute force it
        } else {
            int mid = (int)Math.floor(sortedByX.size()/2);
            ArrayList<GPoint> half1X = new ArrayList<GPoint>(mid);
            ArrayList<GPoint> half1Y = new ArrayList<GPoint>(mid);
            ArrayList<GPoint> half2X = new ArrayList<GPoint>(sortedByX.size() - mid);
            ArrayList<GPoint> half2Y = new ArrayList<GPoint>(sortedByY.size() - mid);
            for (int i = 0; i <= mid; i++) {
                half1X.add(sortedByX.get(i));
                half1Y.add(sortedByY.get(i));
            }
            for (int i = mid + 1; i < sortedByX.size(); i++) {
                half2X.add(sortedByX.get(i));
                half2Y.add(sortedByY.get(i));
            }

            Triplet<GPoint, GPoint, Double> triplet1 = findClosestDist(half1X, half1Y);
            Triplet<GPoint, GPoint, Double> triplet2 = findClosestDist(half2X, half2Y);
            double minDistOfHalves = Math.min(triplet1.getValue2(), triplet2.getValue2());
            GPoint midPoint = coordinates.get(mid);
        }
        return bruteForce(this.coordinates);
    }

    /**
     * Finds the minimum distance between all points in the input list
     * @param coordList unsorted GPoint list that's less than or equal to 3
     * @return a 3-tuple of the two points being the closest to each other, with the distance between them as the last
     * value.
     */
    public Triplet<GPoint, GPoint, Double> bruteForce(ArrayList<GPoint> coordList) {
        double minDist = Double.POSITIVE_INFINITY;
        GPoint minPoint1 = null;
        GPoint minPoint2 = null;
        for (int i = 0; i < coordList.size()-1; i++) { // if size = 3, i goes from 0 to 1
            for (int j = i+1; j < coordList.size(); j++) { // if size = 3, j goes from 1 to 2
                double tempDist = calcDistance(coordList.get(i), coordList.get(j));
                if (tempDist < minDist) {
                    minDist = tempDist;
                    minPoint1 = coordList.get(i);
                    minPoint2 = coordList.get(j);
                }
            }
        }
        return Triplet.with(minPoint1, minPoint2, minDist);
    }

    /**
     * Calculates the distance between two points. Distance formula
     * @param pt1 GPoint 1
     * @param pt2 GPoint 2
     * @return double primitive number type
     */
    public double calcDistance(GPoint pt1, GPoint pt2) {
        return Math.sqrt( Math.pow((pt2.getX()-pt1.getX()),2) + Math.pow((pt2.getY()-pt1.getY()), 2) );
    }

    /**
     * Sorts a list of GPoints by the x-coordinate
     * @param listOfCoords unsorted list of GPoints
     * @return sorted cloned list by x-coordinates
     */
    public ArrayList<GPoint> sortByX(ArrayList<GPoint> listOfCoords) {
        ArrayList<GPoint> sortedList = new ArrayList<GPoint>(listOfCoords.size());
        for (GPoint pt : listOfCoords) {
            GPoint newPt = new GPoint(pt.getX(), pt.getY());
            sortedList.add(newPt);
        }
        Collections.sort(sortedList, X_NATURAL_ORDER);
        return sortedList;
    }

    /**
     * Sorts a list of GPoints by the y-coordinate
     * @param listOfCoords unsorted list of GPoints
     * @return sorted cloned list by y-coordinates
     */
    public ArrayList<GPoint> sortByY(ArrayList<GPoint> listOfCoords) {
        ArrayList<GPoint> sortedList = new ArrayList<GPoint>(listOfCoords.size());
        for (GPoint pt : listOfCoords) {
            GPoint newPt = new GPoint(pt.getX(), pt.getY());
            sortedList.add(newPt);
        }
        Collections.sort(sortedList, Y_NATURAL_ORDER);
        return sortedList;
    }

    /**
     * Comparator that sorts natural order taking a GPoint's x-coordinate
     */
    static final Comparator<GPoint> X_NATURAL_ORDER = new Comparator<GPoint>() {
        @Override
        public int compare(GPoint o1, GPoint o2) {
            return Double.compare(o1.getX(), o2.getX());
        }
    };

    /**
     * Comparator that sorts natural order taking a GPoint's y-coordinate
     */
    static final Comparator<GPoint> Y_NATURAL_ORDER =  new Comparator<GPoint>() {
        @Override
        public int compare(GPoint o1, GPoint o2) {
            return Double.compare(o1.getY(), o2.getY());
        }
    };


    // Getters and setters -----------------------------------------------------------------
    public ArrayList<GPoint> getCoordinates() {
        return coordinates;
    }

    public ArrayList<GPoint> getSortedByXCoords() {
        return xCoordSort;
    }

    public ArrayList<GPoint> getSortedByYCoords() {
        return yCoordSort;
    }

    public double getClosestPairDist() {
        return closestPairDist;
    }

    public void setClosestPairDist(double closestPairDist) {
        this.closestPairDist = closestPairDist;
    }

}
