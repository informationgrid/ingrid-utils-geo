/*
 * **************************************************-
 * ingrid-utils-geo
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.geo.utils.boundingbox;

import java.awt.geom.Point2D;

import org.geotools.referencing.GeodeticCalculator;

import de.ingrid.geo.utils.Utils;

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

    public float[] getSquareBoxOfPoint( float longitude, float latitude, double distance ) {
        
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

        float[] box = new float[4];
        box[0] = (float) west.getX();
        box[1] = (float) south.getY();
        box[2] = (float) east.getX();
        box[3] = (float) north.getY();
        return box;
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
    public double[] getSquareBoxOfPoint( double longitude, double latitude, double distance ) {
        
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

        double[] box = new double[4];
        box[0] = west.getX();
        box[1] = south.getY();
        box[2] = east.getX();
        box[3] = north.getY();
        return box;
    }

    public float[] getSquareBoxOfBoundingBox(float y1, float x1, float y2, float x2) {
        float[] c1 = {y1, x1};
        float[] c2 = {y2, x2};
        return getSquareBoxOfBoundingBox(c1, c2);
    }

    /**
     * Get a bounding box (square) by two point in WGS84 base on first coordinate (c1)
     *
     * @param c1 longitude and latitude [minx, miny] in WGS84
     * @param c2 longitude and latitude [maxx, maxy] in WGS84
     * @return Bounding Box [[minx, miny] [maxx, maxy]] in WGS84
     */
    public float[] getSquareBoxOfBoundingBox(float[] c1, float[] c2) {

        double distance = 0;

        float longitude1 = c1[0];
        float latitude1 = c1[1];
        
        float longitude2 = c2[0];
        float latitude2 = c2[1];
        
        float[] c1Yc2X = { longitude2, latitude1 };
        double distance0 = Utils.getInstance().getDistance(c1, c1Yc2X);

        float[] c1Xc2Y = { longitude1, latitude2};
        double distance90 = Utils.getInstance().getDistance(c1, c1Xc2Y);

        GeodeticCalculator calc = new GeodeticCalculator();
        calc.setStartingGeographicPoint( longitude1, latitude1 );

        float[] box = new float[4];
        box[0] = longitude1;
        box[1] = latitude1;

        if (distance0 > distance90) {
            distance = distance0;
            calc.setDirection( 0, distance );
            Point2D north = calc.getDestinationGeographicPoint();
            box[2]= longitude2;
            box[3]= (float) north.getY();
        } else if (distance0 < distance90){
            distance = distance90;
            calc.setDirection( 90, distance );
            Point2D east = calc.getDestinationGeographicPoint();
            box[2]= (float) east.getX();
            box[3]= latitude2;
        } else {
            box[2] = longitude2;
            box[3] = latitude2;
            return box;
        }

        return box;
    }

    public double[] getSquareBoxOfBoundingBox(double y1, double x1, double y2, double x2) {
        double[] c1 = {y1, x1};
        double[] c2 = {y2, x2};
        return getSquareBoxOfBoundingBox(c1, c2);
    }
    /**
     * Get a bounding box (square) by two point in WGS84 base on first coordinate (c1)
     *
     * @param c1 longitude and latitude [minx, miny] in WGS84
     * @param c2 longitude and latitude [maxx, maxy] in WGS84
     * @return Bounding Box [[minx, miny] [maxx, maxy]] in WGS84
     */
    public double[] getSquareBoxOfBoundingBox(double[] c1, double[] c2) {

        double distance = 0;

        double longitude1 = c1[0];
        double latitude1 = c1[1];
        
        double longitude2 = c2[0];
        double latitude2 = c2[1];
        
        double[] c1Yc2X = { longitude2, latitude1 };
        double distance0 = Utils.getDistance(c1, c1Yc2X);

        double[] c1Xc2Y = { longitude1, latitude2};
        double distance90 = Utils.getDistance(c1, c1Xc2Y);

        GeodeticCalculator calc = new GeodeticCalculator();
        calc.setStartingGeographicPoint( longitude1, latitude1 );

        double[] box = new double[4];
        box[0] = longitude1;
        box[1] = latitude1;

        if (distance0 > distance90) {
            distance = distance0;
            calc.setDirection( 0, distance );
            Point2D north = calc.getDestinationGeographicPoint();
            box[2]= longitude2;
            box[3]= north.getY();
        } else if (distance0 < distance90){
            distance = distance90;
            calc.setDirection( 90, distance );
            Point2D east = calc.getDestinationGeographicPoint();
            box[2]= east.getX();
            box[3]= latitude2;
        } else {
            box[2] = longitude2;
            box[3] = latitude2;
            return box;
        }

        return box;
    }
}
