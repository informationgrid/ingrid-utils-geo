package de.ingrid.geo.utils;

import junit.framework.TestCase;

import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;

import de.ingrid.geo.utils.transformation.CoordTransformUtil;
import de.ingrid.geo.utils.transformation.CoordTransformUtil.CoordinationType;

public class CoordTransformUtilTest extends TestCase {
	
	public void testTransformToWGS84() throws FactoryException, TransformException {
		
		assertEquals(26.555676, CoordTransformUtil.getInstance().transformToWGS84((float) 2380000.0, (float) 5180000.0, CoordinationType.COORDS_ETRS89_UTM31N)[0], 0.0002);
		assertEquals(44.273464, CoordTransformUtil.getInstance().transformToWGS84((float) 2380000.0, (float) 5180000.0, CoordinationType.COORDS_ETRS89_UTM31N)[1], 0.0002);
		assertEquals(3.5451324, CoordTransformUtil.getInstance().transformToWGS84((float) 75000.0, (float) 5070000.0, CoordinationType.COORDS_ETRS89_UTM32N)[0], 0.0002);
		assertEquals(45.653145, CoordTransformUtil.getInstance().transformToWGS84((float) 75000.0, (float) 5070000.0, CoordinationType.COORDS_ETRS89_UTM32N)[1], 0.0002);
		assertEquals(3.3184357, CoordTransformUtil.getInstance().transformToWGS84((float) -425000.0, (float) 5020000.0, CoordinationType.COORDS_ETRS89_UTM33N)[0], 0.0002);
		assertEquals(44.731895, CoordTransformUtil.getInstance().transformToWGS84((float) -425000.0, (float) 5020000.0, CoordinationType.COORDS_ETRS89_UTM33N)[1], 0.0002);
		assertEquals(44.555676, CoordTransformUtil.getInstance().transformToWGS84((float) 2380000.0, (float) 5180000.0, CoordinationType.COORDS_ETRS89_UTM34N)[0], 0.0002);
		assertEquals(44.273464, CoordTransformUtil.getInstance().transformToWGS84((float) 2380000.0, (float) 5180000.0, CoordinationType.COORDS_ETRS89_UTM34N)[1], 0.0002);
		
		assertEquals(3.168283, CoordTransformUtil.getInstance().transformToWGS84((float) 2280000.0, (float) 5080000.0, CoordinationType.COORDS_GK2)[0], 0.0002);
		assertEquals(45.82419, CoordTransformUtil.getInstance().transformToWGS84((float) 2280000.0, (float) 5080000.0, CoordinationType.COORDS_GK2)[1], 0.0002);
		assertEquals(7.000021, CoordTransformUtil.getInstance().transformToWGS84((float) 3365804.6893, (float) 5876051.2873, CoordinationType.COORDS_GK3)[0], 0.0002);
		assertEquals(53.000004, CoordTransformUtil.getInstance().transformToWGS84((float) 3365804.6893, (float) 5876051.2873, CoordinationType.COORDS_GK3)[1], 0.0002);
		assertEquals(4.0157995, CoordTransformUtil.getInstance().transformToWGS84((float) 3877000.0, (float) 5080000.0, CoordinationType.COORDS_GK4)[0], 0.0002);
		assertEquals(45.57945, CoordTransformUtil.getInstance().transformToWGS84((float) 3877000.0, (float) 5080000.0, CoordinationType.COORDS_GK4)[1], 0.0002);
		assertEquals(7.3865886, CoordTransformUtil.getInstance().transformToWGS84((float) 5000000.0, (float) 6000000.0, CoordinationType.COORDS_GK5)[0], 0.0002);
		assertEquals(53.88878, CoordTransformUtil.getInstance().transformToWGS84((float) 5000000.0, (float) 6000000.0, CoordinationType.COORDS_GK5)[1], 0.0002);
	
	}
}
