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
package de.ingrid.geo.utils.example;

import de.ingrid.geo.utils.Utils;
import de.ingrid.geo.utils.boundingbox.BoundingBoxUtils;

public class BoundingBox {
    
    public static void main(String[] args) {

        double coord1x = 8.22971710656067;
        double coord1y = 53.3197061130618;

        double coord2x = 10.5035995762954;
        double coord2y = 54.1281513544974;

        double coord3x = 6.92009188388852;
        double coord3y = 52.9777914432664;

        double coord4x = 9.07744917466639;
        double coord4y = 54.407905432226;

        double[] coord1 = { coord1x, coord1y};
        double[] coord2 = { coord2x, coord2y};
        double[] coord3 = { coord3x, coord3y}; 
        double[] coord4 = { coord4x, coord4y};

        float[] coord5 = { (float) coord1x, (float) coord1y};
        float[] coord6 = { (float) coord2x, (float) coord2y};
        float[] coord7 = { (float) coord3x, (float) coord3y}; 
        float[] coord8 = { (float) coord4x, (float) coord4y};

        double[] bbox1 = BoundingBoxUtils.getInstance().getSquareBoxOfBoundingBox(coord1, coord2);

        System.out.println(bbox1[0]);
        System.out.println(bbox1[1]);
        System.out.println(bbox1[2]);
        System.out.println(bbox1[3]);

        double[] bbox2 = BoundingBoxUtils.getInstance().getSquareBoxOfBoundingBox(coord3, coord4);
        
        System.out.println(bbox2[0]);
        System.out.println(bbox2[1]);
        System.out.println(bbox2[2]);
        System.out.println(bbox2[3]);

        double[] bbox3 = BoundingBoxUtils.getInstance().getSquareBoxOfPoint( coord1[0], coord1[1], 100.0 );

        System.out.println(bbox3[0]);
        System.out.println(bbox3[1]);
        System.out.println(bbox3[2]);
        System.out.println(bbox3[3]);

        double distance1 = Utils.getInstance().getDistance(coord1, coord2);

        System.out.println("Double distance: " + distance1);

        double distance2 = Utils.getInstance().getDistance(coord1x, coord1y, coord2x, coord2y);

        System.out.println("Double distance: " + distance2);

        float distance3 = Utils.getInstance().getDistance(coord5, coord6);

        System.out.println("Float distance: " + distance3);

        float distance4 = Utils.getInstance().getDistance((float) coord1x, (float) coord1y, (float) coord2x, (float) coord2y);

        System.out.println("Float distance: " + distance4);

    }
    
}
