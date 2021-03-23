/*
 * **************************************************-
 * ingrid-utils-geo
 * ==================================================
 * Copyright (C) 2014 - 2018 wemove digital solutions GmbH
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

import junit.framework.TestCase;

public class BoundingBoxTest extends TestCase {

    double[] coord1 = { 8.22971710656067, 53.3197061130618};
    double[] coord2 = { 10.5035995762954, 54.1281513544974};
    double[] coord3 = { 6.92009188388852, 52.9777914432664}; 
    double[] coord4 = { 9.07744917466639, 54.407905432226};

    public void testDistance() {
        assertNotNull(Utils.getDistance(coord1, coord2));
        assertNotNull(BoundingBoxUtils.getSquareBoxOfBoundingBox(coord1, coord2));
        assertNotNull(BoundingBoxUtils.getSquareBoxOfBoundingBox(coord3, coord4));
        assertNotNull(BoundingBoxUtils.getSquareBoxOfPoint( coord1[0], coord1[1], 100));
    }
    
}
