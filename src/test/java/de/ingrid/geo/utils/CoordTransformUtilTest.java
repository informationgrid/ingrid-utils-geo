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
package de.ingrid.geo.utils;

import org.geotools.referencing.operation.projection.MapProjection;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;

import de.ingrid.geo.utils.transformation.CoordTransformUtil;
import de.ingrid.geo.utils.transformation.CoordTransformUtil.CoordType;
import junit.framework.TestCase;

public class CoordTransformUtilTest extends TestCase {

	public void testTransformToWGS84() throws FactoryException, TransformException {
		/*
		 * 	Not in use because geotools throw exception
		 *  org.geotools.factory.FactoryRegistry scanForPlugins
		 *  WARNUNG: Can't load a service for category "MathTransformProvider". Cause is "NoClassDefFoundError: javax/media/jai/WarpAffine".
		 * 	Unresolved by geotools.
		 * */
	    
	    // disable assertion due to a but in geotools
	    // see: http://stackoverflow.com/questions/16525478/testing-a-geo-transformation-fails-in-maven-works-in-eclipse
	    MapProjection.class.getClassLoader().setClassAssertionStatus(MapProjection.class.getName(), false);
	    
	    // Testdata can be generated at https://epsg.io
		assertEquals(26.555676, CoordTransformUtil.getInstance().transformToWGS84((float) 2380000.0, (float) 5180000.0, CoordType.COORDS_ETRS89_UTM31N)[0], 0.0002);
		assertEquals(44.273464, CoordTransformUtil.getInstance().transformToWGS84((float) 2380000.0, (float) 5180000.0, CoordType.COORDS_ETRS89_UTM31N)[1], 0.0002);
        assertEquals(2380000.0, CoordTransformUtil.getInstance().transform((float) 26.555676, (float) 44.273464, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM31N)[0], 100.00);
        assertEquals(5180000.0, CoordTransformUtil.getInstance().transform((float) 26.555676, (float) 44.273464, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM31N)[1], 100.00);

		assertEquals(3.5451324, CoordTransformUtil.getInstance().transformToWGS84((float) 75000.0, (float) 5070000.0, CoordType.COORDS_ETRS89_UTM32N)[0], 0.0002);
		assertEquals(45.653145, CoordTransformUtil.getInstance().transformToWGS84((float) 75000.0, (float) 5070000.0, CoordType.COORDS_ETRS89_UTM32N)[1], 0.0002);
        assertEquals(75000.0, CoordTransformUtil.getInstance().transform((float) 3.5451324, (float) 45.653145, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM32N)[0], 0.02);
        assertEquals(5070000.0, CoordTransformUtil.getInstance().transform((float) 3.5451324, (float) 45.653145, CoordType.COORDS_WGS84, CoordType.COORDS_ETRS89_UTM32N)[1], 0.02);

		assertEquals(3.3184357, CoordTransformUtil.getInstance().transformToWGS84((float) -425000.0, (float) 5020000.0, CoordType.COORDS_ETRS89_UTM33N)[0], 0.0002);
		assertEquals(44.731895, CoordTransformUtil.getInstance().transformToWGS84((float) -425000.0, (float) 5020000.0, CoordType.COORDS_ETRS89_UTM33N)[1], 0.0002);
		assertEquals(10.7116561, CoordTransformUtil.getInstance().transformToWGS84((float) 615142.0, (float) 5862034.0, CoordType.COORDS_ETRS89_UTM32N_NE)[0], 0.0002);
		assertEquals(52.8956464, CoordTransformUtil.getInstance().transformToWGS84((float) 615142.0, (float) 5862034.0, CoordType.COORDS_ETRS89_UTM32N_NE)[1], 0.0002);
		assertEquals(16.7116562, CoordTransformUtil.getInstance().transformToWGS84((float) 615142.0, (float) 5862034.0, CoordType.COORDS_ETRS89_UTM33N_NE)[0], 0.0002);
		assertEquals(52.8956463, CoordTransformUtil.getInstance().transformToWGS84((float) 615142.0, (float) 5862034.0, CoordType.COORDS_ETRS89_UTM33N_NE)[1], 0.0002);
		assertEquals(8.7231355, CoordTransformUtil.getInstance().transformToWGS84((float) 32480196.0, (float) 5548940.0, CoordType.COORDS_ETRS89_UTM32N_ZE)[0], 0.0002);
		assertEquals(50.0923915, CoordTransformUtil.getInstance().transformToWGS84((float) 32480196.0, (float) 5548940.0, CoordType.COORDS_ETRS89_UTM32N_ZE)[1], 0.0002);
		assertEquals(7.6433596, CoordTransformUtil.getInstance().transformToWGS84((float) 33018256.0, (float) 6009262.0, CoordType.COORDS_ETRS89_UTM33N_ZE)[0], 0.0002);
		assertEquals(54.0059252, CoordTransformUtil.getInstance().transformToWGS84((float) 33018256.0, (float) 6009262.0, CoordType.COORDS_ETRS89_UTM33N_ZE)[1], 0.0002);
		assertEquals(44.555676, CoordTransformUtil.getInstance().transformToWGS84((float) 2380000.0, (float) 5180000.0, CoordType.COORDS_ETRS89_UTM34N)[0], 0.0002);
		assertEquals(44.273464, CoordTransformUtil.getInstance().transformToWGS84((float) 2380000.0, (float) 5180000.0, CoordType.COORDS_ETRS89_UTM34N)[1], 0.0002);

		assertEquals(3.168283, CoordTransformUtil.getInstance().transformToWGS84((float) 2280000.0, (float) 5080000.0, CoordType.COORDS_GK2)[0], 0.0002);
		assertEquals(45.82419, CoordTransformUtil.getInstance().transformToWGS84((float) 2280000.0, (float) 5080000.0, CoordType.COORDS_GK2)[1], 0.0002);
		assertEquals(7.000021, CoordTransformUtil.getInstance().transformToWGS84((float) 3365804.6893, (float) 5876051.2873, CoordType.COORDS_GK3)[0], 0.0002);
		assertEquals(53.000004, CoordTransformUtil.getInstance().transformToWGS84((float) 3365804.6893, (float) 5876051.2873, CoordType.COORDS_GK3)[1], 0.0002);
		assertEquals(4.0157995, CoordTransformUtil.getInstance().transformToWGS84((float) 3877000.0, (float) 5080000.0, CoordType.COORDS_GK4)[0], 0.0002);
		assertEquals(45.57945, CoordTransformUtil.getInstance().transformToWGS84((float) 3877000.0, (float) 5080000.0, CoordType.COORDS_GK4)[1], 0.0002);
		assertEquals(7.3865886, CoordTransformUtil.getInstance().transformToWGS84((float) 5000000.0, (float) 6000000.0, CoordType.COORDS_GK5)[0], 0.0002);
		assertEquals(53.88878, CoordTransformUtil.getInstance().transformToWGS84((float) 5000000.0, (float) 6000000.0, CoordType.COORDS_GK5)[1], 0.0002);
		assertEquals(3.168283, CoordTransformUtil.getInstance().transformToWGS84((float) 2280000.0, (float) 5080000.0, CoordType.COORDS_GK2_EN)[0], 0.0002);
		assertEquals(45.82419, CoordTransformUtil.getInstance().transformToWGS84((float) 2280000.0, (float) 5080000.0, CoordType.COORDS_GK2_EN)[1], 0.0002);
		assertEquals(7.000021, CoordTransformUtil.getInstance().transformToWGS84((float) 3365804.6893, (float) 5876051.2873, CoordType.COORDS_GK3_EN)[0], 0.0002);
		assertEquals(53.000004, CoordTransformUtil.getInstance().transformToWGS84((float) 3365804.6893, (float) 5876051.2873, CoordType.COORDS_GK3_EN)[1], 0.0002);
		assertEquals(4.0157995, CoordTransformUtil.getInstance().transformToWGS84((float) 3877000.0, (float) 5080000.0, CoordType.COORDS_GK4_EN)[0], 0.0002);
		assertEquals(45.57945, CoordTransformUtil.getInstance().transformToWGS84((float) 3877000.0, (float) 5080000.0, CoordType.COORDS_GK4_EN)[1], 0.0002);
		assertEquals(7.3865886, CoordTransformUtil.getInstance().transformToWGS84((float) 5000000.0, (float) 6000000.0, CoordType.COORDS_GK5_EN)[0], 0.0002);
		assertEquals(53.88878, CoordTransformUtil.getInstance().transformToWGS84((float) 5000000.0, (float) 6000000.0, CoordType.COORDS_GK5_EN)[1], 0.0002);

		assertEquals(-69.2895396, CoordTransformUtil.getInstance().transformToWGS84((float) 615142.0, (float) 5862034.0, CoordType.COORDS_ETRS89_LCC)[0], 0.0002);
		assertEquals(61.6865639, CoordTransformUtil.getInstance().transformToWGS84((float) 615142.0, (float) 5862034.0, CoordType.COORDS_ETRS89_LCC)[1], 0.0002);
		assertEquals(-171.2369156, CoordTransformUtil.getInstance().transformToWGS84((float) 615142.0, (float) 5862034.0, CoordType.COORDS_ETRS89_LCC_DE)[0], 0.0002);
		assertEquals(85.4679938, CoordTransformUtil.getInstance().transformToWGS84((float) 615142.0, (float) 5862034.0, CoordType.COORDS_ETRS89_LCC_DE)[1], 0.0002);
		assertEquals(-64.2103372, CoordTransformUtil.getInstance().transformToWGS84((float) 615142.0, (float) 5862034.0, CoordType.COORDS_ETRS89_LAEA)[0], 0.0002);
		assertEquals(55.7674705, CoordTransformUtil.getInstance().transformToWGS84((float) 615142.0, (float) 5862034.0, CoordType.COORDS_ETRS89_LAEA)[1], 0.0002);
		assertEquals(25.1367122, CoordTransformUtil.getInstance().transformToWGS84((float) 2798206.0, (float) 2504688.0, CoordType.COORDS_WGS84_PM)[0], 0.0002);
		assertEquals(21.9430412, CoordTransformUtil.getInstance().transformToWGS84((float) 2798206.0, (float) 2504688.0, CoordType.COORDS_WGS84_PM)[1], 0.0002);


		assertEquals(10.0, CoordTransformUtil.getInstance().transformToWGS84((float) 50.0, (float) 10.0, CoordType.COORDS_CRS84)[0], 0.0002);
		assertEquals(50.0, CoordTransformUtil.getInstance().transformToWGS84((float) 50.0, (float) 10.0, CoordType.COORDS_CRS84)[1], 0.0002);
		assertEquals(50.0, CoordTransformUtil.getInstance().transformToWGS84((float) 50.0, (float) 10.0, CoordType.COORDS_WGS84)[0], 0.0002);
		assertEquals(10.0, CoordTransformUtil.getInstance().transformToWGS84((float) 50.0, (float) 10.0, CoordType.COORDS_WGS84)[1], 0.0002);

	}
}
