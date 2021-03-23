package de.ingrid.geo.utils;

import java.awt.geom.Point2D;

import org.geotools.referencing.GeodeticCalculator;
import org.geotools.referencing.crs.DefaultGeographicCRS;

public class BoundingBoxUtils {

    private static BoundingBoxUtils    boundingBoxUtils;

    private BoundingBoxUtils() {
    }

    public static synchronized BoundingBoxUtils getInstance() {
        if (boundingBoxUtils == null) {
            boundingBoxUtils = new BoundingBoxUtils();
        }
        return boundingBoxUtils;
    }

    /**
     * 
     * Get a bounding box (square) by a point in WGS84 with a distance
     * 
     * @param longitude in WGS84
     * @param latitude in WGS84
     * @param distance in meters
     * @return Bounding Box [[minx, miny] [maxx, maxy]] in WGS84
     */
    public static double[][] getSquareBoxOfPoint( double longitude, double latitude, double distance ) {
        
        GeodeticCalculator calc = new GeodeticCalculator();
        calc.setStartingGeographicPoint( longitude, latitude );

        calc.setDirection( 0, distance );
        Point2D north = calc.getDestinationGeographicPoint();

        calc.setDirection( 90, distance );
        Point2D east = calc.getDestinationGeographicPoint();

        calc.setDirection( 180, distance );
        Point2D south = calc.getDestinationGeographicPoint();

        calc.setDirection( -90, distance );
        Point2D west = calc.getDestinationGeographicPoint();

        double[][] box = new double[2][2];
        double[] coord1 = { west.getX(), south.getY() };
        double[] coord2 = { east.getX(), north.getY() };
        box[0] = coord1;
        box[1] = coord2;
        return box;
    }

    /**
     * Get a bounding box (square) by two point in WGS84 base on first coordinate (c1)
     *
     * @param c1 longitude and latitude [minx, miny] in WGS84
     * @param c2 longitude and latitude [maxx, maxy] in WGS84
     * @return Bounding Box [[minx, miny] [maxx, maxy]] in WGS84
     */
    public static double[][] getSquareBoxOfBoundingBox( double[] c1, double[] c2) {

        double[][] box = new double[2][2];
        double distance = 0;

        double longitude1 = c1[0];
        double latitude1 = c1[1];
        
        double longitude2 = c2[0];
        double latitude2 = c2[1];
        
        double[] c1Yc2X = { longitude2, latitude1 };
        double distance0 = Utils.getDistance(c1, c1Yc2X);

        double[] c1Xc2Y = { longitude1, latitude2};
        double distance90 = Utils.getDistance(c1, c1Xc2Y);

        double[] newC2 = new double[2];
        GeodeticCalculator calc = new GeodeticCalculator();
        calc.setStartingGeographicPoint( longitude1, latitude1 );

        if (distance0 > distance90) {
            distance = distance0;
            calc.setDirection( 0, distance );
            Point2D north = calc.getDestinationGeographicPoint();
            newC2[0]= longitude2;
            newC2[1]= north.getY();
        } else if (distance0 < distance90){
            distance = distance90;
            calc.setDirection( 90, distance );
            Point2D east = calc.getDestinationGeographicPoint();
            newC2[0]= east.getX();
            newC2[1]= latitude2;
        } else {
            box[0] = c1;
            box[1] = c2;
            return box;
        }

        box[0] = c1;
        box[1] = newC2;
        return box;
    }
}
