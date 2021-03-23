package de.ingrid.geo.utils;

import org.geotools.referencing.GeodeticCalculator;
import org.geotools.referencing.crs.DefaultGeographicCRS;

public class Utils {

    private static Utils    utils;

    private Utils() {
    }

    public static synchronized Utils getInstance() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    /**
     * Get distance in meters between to points
     * 
     * @param c1 longitude and latitude [minx, miny] in WGS84
     * @param c2 longitude and latitude [maxx, maxy] in WGS84
     * 
     * @return Distance in meters
     */
    public static double getDistance(double[] c1, double[] c2) {
        GeodeticCalculator gc = new GeodeticCalculator(DefaultGeographicCRS.WGS84);
        gc.setStartingGeographicPoint(c1[0], c1[1]);
        gc.setDestinationGeographicPoint(c2[0], c2[1]);
        return gc.getOrthodromicDistance();
    }
}
