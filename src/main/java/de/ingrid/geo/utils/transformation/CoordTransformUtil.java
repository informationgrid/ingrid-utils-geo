package de.ingrid.geo.utils.transformation;

import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;


/**
 * CoordTransformUtil is a singleton class to convert
 * coordinates of coordination reference systems Gauss-Krueger(GK) or
 * ETRS89 to coordinates of coordination reference system WGS84
 * @author ktt 
 */
public class CoordTransformUtil {

	private static final String	COORDS_WKT_GK2				= "PROJCS[\"DHDN / Gauss-Kruger zone 2\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]], PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]], UNIT[\"degree\", 0.017453292519943295], AXIS[\"Geodetic longitude\", EAST], AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 6.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 2500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31466\"]]";
	private static final String	COORDS_WKT_GK3				= "PROJCS[\"DHDN / Gauss-Kruger zone 3\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]],    PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],    UNIT[\"degree\", 0.017453292519943295],    AXIS[\"Geodetic longitude\", EAST],    AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 9.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 3500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31467\"]]";
	private static final String	COORDS_WKT_GK4				= "PROJCS[\"DHDN / Gauss-Kruger zone 4\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]],    PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],    UNIT[\"degree\", 0.017453292519943295],    AXIS[\"Geodetic longitude\", EAST],    AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 12.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 4500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31468\"]]";
	private static final String	COORDS_WKT_GK5				= "PROJCS[\"DHDN / Gauss-Kruger zone 5\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]],    PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],    UNIT[\"degree\", 0.017453292519943295],    AXIS[\"Geodetic longitude\", EAST],    AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 15.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 5500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31469\"]]";
	private static final String	COORDS_WKT_ETRS89_UTM31N	= "PROJCS[\"ETRS89 / UTM zone 31N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 3.0], PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25831\"]]";
	private static final String	COORDS_WKT_ETRS89_UTM32N	= "PROJCS[\"ETRS89 / UTM zone 32N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 9.0], PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25832\"]]";
	private static final String	COORDS_WKT_ETRS89_UTM33N	= "PROJCS[\"ETRS89 / UTM zone 33N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 15.0],PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25833\"]]";
	private static final String	COORDS_WKT_ETRS89_UTM34N	= "PROJCS[\"ETRS89 / UTM zone 34N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 21.0],PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25834\"]]";
	private static final String	COORDS_WKT_WGS84			= "GEOGCS[\"WGS 84\", DATUM[\"World Geodetic System 1984\",SPHEROID[\"WGS 84\", 6378137.0, 298.257223563, AUTHORITY[\"EPSG\",\"7030\"]], AUTHORITY[\"EPSG\",\"6326\"]],  PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]], UNIT[\"degree\", 0.017453292519943295], AXIS[\"Geodetic longitude\", EAST], AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4326\"]]";
	
	private static CoordTransformUtil coordTransformUtil;
	
	public enum CoordType {
		COORDS_ETRS89_UTM31N,
		COORDS_ETRS89_UTM32N,
		COORDS_ETRS89_UTM33N,
		COORDS_ETRS89_UTM34N,
		COORDS_GK2,
		COORDS_GK3,
		COORDS_GK4,
		COORDS_GK5
	}
	
	private CoordTransformUtil() {
	}
	
	public static synchronized CoordTransformUtil getInstance() {
		if (coordTransformUtil == null) {
			coordTransformUtil = new CoordTransformUtil();
		}
		return coordTransformUtil;
	}
	
	/**
	 * Float coordinate transformation to WGS84
	 * 
	 * @param coordsX
	 *            Float x-coordinate of a coordinate reference system
	 * @param coordsY
	 *            Float y-coordinate of a coordinate reference system
	 * @param coordsType
	 *            Define existing coordinate reference system
	 * @return An Array with converted WGS84 values of coordsX and coordsY
	 * @throws FactoryException 
	 * @throws TransformException 
	 */
	public float[] transformToWGS84(float coordsX, float coordsY, CoordType coordsType)
		throws FactoryException, TransformException {
		
		float[] src = new float[] { coordsX, coordsY };
		float[] dst = new float[2];
		
		String stringCoordsType;
		
		switch (coordsType) {
			
			// ETRS89 UTM Zone 31N
			case COORDS_ETRS89_UTM31N:
				stringCoordsType = COORDS_WKT_ETRS89_UTM31N;
				break;
			// ETRS89 UTM Zone 32N
			case COORDS_ETRS89_UTM32N:
				stringCoordsType = COORDS_WKT_ETRS89_UTM32N;
				break;
			// ETRS89 UTM Zone 33N
			case COORDS_ETRS89_UTM33N:
				stringCoordsType = COORDS_WKT_ETRS89_UTM33N;
				break;
			// ETRS89 UTM Zone 34N
			case COORDS_ETRS89_UTM34N:
				stringCoordsType = COORDS_WKT_ETRS89_UTM34N;
				break;
			// GK2
			case COORDS_GK2:
				stringCoordsType = COORDS_WKT_GK2;
				break;
			// GK3
			case COORDS_GK3:
				stringCoordsType = COORDS_WKT_GK3;
				break;
			// GK4
			case COORDS_GK4:
				stringCoordsType = COORDS_WKT_GK4;
				break;
			// GK5
			case COORDS_GK5:
				stringCoordsType = COORDS_WKT_GK5;
				break;
			// ETRS89 UTM Zone 31N
			default:
				stringCoordsType = COORDS_WKT_ETRS89_UTM31N;
				break;
			
		}
		
		CoordinateReferenceSystem inCRS = CRS.parseWKT(stringCoordsType);
		CoordinateReferenceSystem outCRS = CRS.parseWKT(COORDS_WKT_WGS84);

		MathTransform tf = CRS.findMathTransform(inCRS, outCRS);
		tf.transform(src, 0, dst, 0, 1);

		return dst;
	}
	
	/**
	 * Double coordinate transformation to WGS84
	 * 
	 * @param coordsX
	 *            double x-coordinate of a coordinate reference system
	 * @param coordsY
	 *            double y-coordinate of a coordinate reference system
	 * @param coordsType
	 *            Define existing coordinate reference system
	 * @return An Array with converted WGS84 values of coordsX and coordsY
	 * @throws FactoryException 
	 * @throws TransformException 
	 */
	public double[] transformToWGS84(double coordsX, double coordsY, CoordType coordsType)
		throws FactoryException, TransformException {
		
		double[] src = new double[] { coordsX, coordsY };
		double[] dst = new double[2];
		
		String stringCoordsType;
		
		switch (coordsType) {
			
			// ETRS89 UTM Zone 31N
			case COORDS_ETRS89_UTM31N:
				stringCoordsType = COORDS_WKT_ETRS89_UTM31N;
				break;
			// ETRS89 UTM Zone 32N
			case COORDS_ETRS89_UTM32N:
				stringCoordsType = COORDS_WKT_ETRS89_UTM32N;
				break;
			// ETRS89 UTM Zone 33N
			case COORDS_ETRS89_UTM33N:
				stringCoordsType = COORDS_WKT_ETRS89_UTM33N;
				break;
			// ETRS89 UTM Zone 34N
			case COORDS_ETRS89_UTM34N:
				stringCoordsType = COORDS_WKT_ETRS89_UTM34N;
				break;
			// GK2
			case COORDS_GK2:
				stringCoordsType = COORDS_WKT_GK2;
				break;
			// GK3
			case COORDS_GK3:
				stringCoordsType = COORDS_WKT_GK3;
				break;
			// GK4
			case COORDS_GK4:
				stringCoordsType = COORDS_WKT_GK4;
				break;
			// GK5
			case COORDS_GK5:
				stringCoordsType = COORDS_WKT_GK5;
				break;
			// ETRS89 UTM Zone 31N
			default:
				stringCoordsType = COORDS_WKT_ETRS89_UTM31N;
				break;
			
		}
		
		CoordinateReferenceSystem inCRS = CRS.parseWKT(stringCoordsType);
		CoordinateReferenceSystem outCRS = CRS.parseWKT(COORDS_WKT_WGS84);

		MathTransform tf = CRS.findMathTransform(inCRS, outCRS);
		tf.transform(src, 0, dst, 0, 1);

		return dst;
	}
	
}