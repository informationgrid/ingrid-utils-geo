/*
 * **************************************************-
 * ingrid-utils-geo
 * ==================================================
 * Copyright (C) 2014 - 2021 wemove digital solutions GmbH
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

import de.ingrid.geo.utils.BoundingBoxUtils;
import de.ingrid.geo.utils.Utils;

public class BoundingBox {
    
    public static void main(String[] args) {

        double[] coord1 = { 8.22971710656067, 53.3197061130618};
        double[] coord2 = { 10.5035995762954, 54.1281513544974};
        double[] coord3 = { 6.92009188388852, 52.9777914432664}; 
        double[] coord4 = { 9.07744917466639, 54.407905432226};

        double[][] bbox1 = BoundingBoxUtils.getSquareBoxOfBoundingBox(coord1, coord2);
        
        System.out.println(bbox1[0][0]);
        System.out.println(bbox1[0][1]);
        System.out.println(bbox1[1][0]);
        System.out.println(bbox1[1][1]);

        double[][] bbox2 = BoundingBoxUtils.getSquareBoxOfBoundingBox(coord3, coord4);
        
        System.out.println(bbox2[0][0]);
        System.out.println(bbox2[0][1]);
        System.out.println(bbox2[1][0]);
        System.out.println(bbox2[1][1]);

        double[][] bbox3 = BoundingBoxUtils.getSquareBoxOfPoint( coord1[0], coord1[1], 100.0 );

        System.out.println(bbox3[0][0]);
        System.out.println(bbox3[0][1]);
        System.out.println(bbox3[1][0]);
        System.out.println(bbox3[1][1]);

        double distance = Utils.getDistance(coord1, coord2);

        System.out.println(distance);

    }
    
}
