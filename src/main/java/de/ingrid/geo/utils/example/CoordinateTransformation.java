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

import de.ingrid.geo.utils.transformation.CoordTransformUtil;
import de.ingrid.geo.utils.transformation.CoordTransformUtil.CoordType;





public class CoordinateTransformation {
	
	public static void main(String[] args) throws Exception {
		
		double dXCoord;
		double dYCoord;
        double dXCoordWGS84;
        double dYCoordWGS84;
		
		float fXCoord;
		float fYCoord;
		
		// ETRS89 UTM Zone 31N to WGS84
		dXCoordWGS84 = CoordTransformUtil.getInstance().transformToWGS84(2380000.0, 5180000.0, CoordType.COORDS_ETRS89_UTM31N)[0];
		dYCoordWGS84 = CoordTransformUtil.getInstance().transformToWGS84(2380000.0, 5180000.0, CoordType.COORDS_ETRS89_UTM31N)[1];
		fXCoord = CoordTransformUtil.getInstance().transformToWGS84((float)2380000.0, (float)5180000.0, CoordType.COORDS_ETRS89_UTM31N)[0];
		fYCoord = CoordTransformUtil.getInstance().transformToWGS84((float)2380000.0, (float)5180000.0, CoordType.COORDS_ETRS89_UTM31N)[1];
		System.out.println("\nETRS89 UTM Zone 31N to WGS84");
        System.out.println("  2380000.0, 5180000.0 ->");
		System.out.println("dx:" + dXCoordWGS84 + "\ndy:" + dYCoordWGS84);
		System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);
		
        // WGS84 to ETRS89 UTM Zone 31N 
        dXCoord = CoordTransformUtil.getInstance().transform(dXCoordWGS84, dYCoordWGS84, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM31N)[0];
        dYCoord = CoordTransformUtil.getInstance().transform(dXCoordWGS84, dYCoordWGS84, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM31N)[1];
        fXCoord = CoordTransformUtil.getInstance().transform((float)dXCoordWGS84, (float)dYCoordWGS84, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM31N)[0];
        fYCoord = CoordTransformUtil.getInstance().transform((float)dXCoordWGS84, (float)dYCoordWGS84, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM31N)[1];
        System.out.println("\nWGS84 to ETRS89 UTM Zone 31N");
        System.out.println("  " + dXCoordWGS84 +", " + dYCoordWGS84 + " ->");
        System.out.println("dx:" + dXCoord + "\ndy:" + dYCoord);
        System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);

        System.out.println("\n-------------------------------");

		// ETRS89 UTM Zone 32N to WGS84
        dXCoordWGS84 = CoordTransformUtil.getInstance().transformToWGS84(75000.0, 5070000.0, CoordType.COORDS_ETRS89_UTM32N)[0];
        dYCoordWGS84 = CoordTransformUtil.getInstance().transformToWGS84(75000.0, 5070000.0, CoordType.COORDS_ETRS89_UTM32N)[1];
		fXCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 75000.0, (float)5070000.0, CoordType.COORDS_ETRS89_UTM32N)[0];
		fYCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 75000.0, (float)5070000.0, CoordType.COORDS_ETRS89_UTM32N)[1];
		System.out.println("\nETRS89 UTM Zone 32N to WGS84");
        System.out.println("  75000.0, 5070000.0 ->");
		System.out.println("dx:" + dXCoordWGS84 + "\ndy:" + dYCoordWGS84);
		System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);
		
        // WGS84 to ETRS89 UTM Zone 32N
        dXCoord = CoordTransformUtil.getInstance().transform(dXCoordWGS84, dYCoordWGS84, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM32N)[0];
        dYCoord = CoordTransformUtil.getInstance().transform(dXCoordWGS84, dYCoordWGS84, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM32N)[1];
        fXCoord = CoordTransformUtil.getInstance().transform((float) dXCoordWGS84, (float)dYCoordWGS84, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM32N)[0];
        fYCoord = CoordTransformUtil.getInstance().transform((float) dXCoordWGS84, (float)dYCoordWGS84, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM32N)[1];
        System.out.println("\nWGS84 to ETRS89 UTM Zone 32N");
        System.out.println("  " + dXCoordWGS84 +", " + dYCoordWGS84 + " ->");
        System.out.println("dx:" + dXCoord + "\ndy:" + dYCoord);
        System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);
        
        System.out.println("\n-------------------------------");

		// ETRS89 UTM Zone 33N
		dXCoord = CoordTransformUtil.getInstance().transformToWGS84(-425000.0, 5020000.0, CoordType.COORDS_ETRS89_UTM33N)[0];
		dYCoord = CoordTransformUtil.getInstance().transformToWGS84(-425000.0, 5020000.0, CoordType.COORDS_ETRS89_UTM33N)[1];
		fXCoord = CoordTransformUtil.getInstance().transformToWGS84((float) -425000.0, (float)5020000.0, CoordType.COORDS_ETRS89_UTM33N)[0];
		fYCoord = CoordTransformUtil.getInstance().transformToWGS84((float) -425000.0, (float)5020000.0, CoordType.COORDS_ETRS89_UTM33N)[1];
		System.out.println("\nETRS89 UTM Zone 33N");
		System.out.println("dx:" + dXCoord + "\ndy:" + dYCoord);
		System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);
		
		// ETRS89 UTM Zone 34N
		dXCoord = CoordTransformUtil.getInstance().transformToWGS84(2380000.0, 5180000.0, CoordType.COORDS_ETRS89_UTM34N)[0];
		dYCoord = CoordTransformUtil.getInstance().transformToWGS84(2380000.0, 5180000.0, CoordType.COORDS_ETRS89_UTM34N)[1];
		fXCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 2380000.0, (float)5180000.0, CoordType.COORDS_ETRS89_UTM34N)[0];
		fYCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 2380000.0, (float)5180000.0, CoordType.COORDS_ETRS89_UTM34N)[1];
		System.out.println("\nETRS89 UTM Zone 34N");
		System.out.println("dx:" + dXCoord + "\ndy:" + dYCoord);
		System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);
		
		// GK2
		dXCoord = CoordTransformUtil.getInstance().transformToWGS84(2280000.0, 5080000.0, CoordType.COORDS_GK2)[0];
		dYCoord = CoordTransformUtil.getInstance().transformToWGS84(2280000.0, 5080000.0, CoordType.COORDS_GK2)[1];
		fXCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 2280000.0, (float)5080000.0, CoordType.COORDS_GK2)[0];
		fYCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 2280000.0, (float)5080000.0, CoordType.COORDS_GK2)[1];
		System.out.println("\nGK2");
		System.out.println("dx:" + dXCoord + "\ndy:" + dYCoord);
		System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);
		
		// GK3
		dXCoord = CoordTransformUtil.getInstance().transformToWGS84(3365804.6893, 5876051.2873, CoordType.COORDS_GK3)[0];
		dYCoord = CoordTransformUtil.getInstance().transformToWGS84(3365804.6893, 5876051.2873, CoordType.COORDS_GK3)[1];
		fXCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 3365804.6893, (float)5876051.2873, CoordType.COORDS_GK3)[0];
		fYCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 3365804.6893, (float)5876051.2873, CoordType.COORDS_GK3)[1];
		System.out.println("\nGK3");
		System.out.println("dx:" + dXCoord + "\ndy:" + dYCoord);
		System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);
		
		// GK4
		dXCoord = CoordTransformUtil.getInstance().transformToWGS84(3877000.0, 5080000.0, CoordType.COORDS_GK4)[0];
		dYCoord = CoordTransformUtil.getInstance().transformToWGS84(3877000.0, 5080000.0, CoordType.COORDS_GK4)[1];
		fXCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 3877000.0, (float)5080000.0, CoordType.COORDS_GK4)[0];
		fYCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 3877000.0, (float)5080000.0, CoordType.COORDS_GK4)[1];
		System.out.println("\nGK4");
		System.out.println("dx:" + dXCoord + "\ndy:" + dYCoord);
		System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);
		
		// GK5
		dXCoord = CoordTransformUtil.getInstance().transformToWGS84(5000000.0, 6000000.0, CoordType.COORDS_GK5)[0];
		dYCoord = CoordTransformUtil.getInstance().transformToWGS84(5000000.0, 6000000.0, CoordType.COORDS_GK5)[1];
		fXCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 5000000.0, (float)6000000.0, CoordType.COORDS_GK5)[0];
		fYCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 5000000.0, (float)6000000.0, CoordType.COORDS_GK5)[1];
		System.out.println("\nGK5");
		System.out.println("dx:" + dXCoord + "\ndy:" + dYCoord);
		System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);
		
		// WGS84
		dXCoord = CoordTransformUtil.getInstance().transformToWGS84(4.015799478962402, 45.57944882444718, CoordType.COORDS_WGS84)[0];
		dYCoord = CoordTransformUtil.getInstance().transformToWGS84(4.015799478962402, 45.57944882444718, CoordType.COORDS_WGS84)[1];
		fXCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 4.0157995, (float)45.57945, CoordType.COORDS_WGS84)[0];
		fYCoord = CoordTransformUtil.getInstance().transformToWGS84((float) 4.0157995, (float)45.57945, CoordType.COORDS_WGS84)[1];
		System.out.println("\nWGS84");
		System.out.println("dx:" + dXCoord + "\ndy:" + dYCoord);
		System.out.println("fx:" + fXCoord + "\nfy:" + fYCoord);
	}
	
}
