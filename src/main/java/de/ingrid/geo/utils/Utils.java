/*
 * **************************************************-
 * ingrid-utils-geo
 * ==================================================
 * Copyright (C) 2014 - 2023 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
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

    public float getDistance(float y1, float x1, float y2, float x2) {
        float[] c1 = {y1, x1};
        float[] c2 = {y2, x2};
        return getDistance(c1 , c2);
    }

    /**
     * Get distance in meters between to points
     * 
     * @param c1 longitude and latitude [minx, miny] in WGS84
     * @param c2 longitude and latitude [maxx, maxy] in WGS84
     * 
     * @return Distance in meters
     */
    public float getDistance(float[] c1, float[] c2) {
        GeodeticCalculator gc = new GeodeticCalculator(DefaultGeographicCRS.WGS84);
        gc.setStartingGeographicPoint(c1[0], c1[1]);
        gc.setDestinationGeographicPoint(c2[0], c2[1]);
        return (float) gc.getOrthodromicDistance();
    }

    public double getDistance(double y1, double x1, double y2, double x2) {
        double[] c1 = {y1, x1};
        double[] c2 = {y2, x2};
        return getDistance(c1 , c2);
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
