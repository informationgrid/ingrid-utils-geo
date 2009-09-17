package de.ingrid.geo.utils.example;

import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;





public class CoordinateTransformation {
	
	private static final String	COORDS_WKT_GK2				= "PROJCS[\"DHDN / Gauss-Kruger zone 2\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]], PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]], UNIT[\"degree\", 0.017453292519943295], AXIS[\"Geodetic longitude\", EAST], AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 6.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 2500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31466\"]]";
	private static final String	COORDS_WKT_GK3				= "PROJCS[\"DHDN / Gauss-Kruger zone 3\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]],    PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],    UNIT[\"degree\", 0.017453292519943295],    AXIS[\"Geodetic longitude\", EAST],    AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 9.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 3500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31467\"]]";
	private static final String	COORDS_WKT_GK4				= "PROJCS[\"DHDN / Gauss-Kruger zone 4\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]],    PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],    UNIT[\"degree\", 0.017453292519943295],    AXIS[\"Geodetic longitude\", EAST],    AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 12.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 4500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31468\"]]";
	private static final String	COORDS_WKT_GK5				= "PROJCS[\"DHDN / Gauss-Kruger zone 5\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]],    PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],    UNIT[\"degree\", 0.017453292519943295],    AXIS[\"Geodetic longitude\", EAST],    AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 15.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 5500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31469\"]]";
	private static final String	COORDS_WKT_ETRS89_UTM31N	= "PROJCS[\"ETRS89 / UTM zone 31N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 3.0], PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25831\"]]";
	private static final String	COORDS_WKT_ETRS89_UTM32N	= "PROJCS[\"ETRS89 / UTM zone 32N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 9.0], PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25832\"]]";
	private static final String	COORDS_WKT_ETRS89_UTM33N	= "PROJCS[\"ETRS89 / UTM zone 33N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 15.0],PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25833\"]]";
	private static final String	COORDS_WKT_ETRS89_UTM34N	= "PROJCS[\"ETRS89 / UTM zone 34N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 21.0],PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25834\"]]";
	private static final String	COORDS_WKT_WGS84			= "GEOGCS[\"WGS 84\", DATUM[\"World Geodetic System 1984\",SPHEROID[\"WGS 84\", 6378137.0, 298.257223563, AUTHORITY[\"EPSG\",\"7030\"]], AUTHORITY[\"EPSG\",\"6326\"]],  PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]], UNIT[\"degree\", 0.017453292519943295], AXIS[\"Geodetic longitude\", EAST], AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4326\"]]";
	
	/** Creates a new instance of TransformData 
	 * @throws TransformException 
	 * @throws FactoryException */
	public CoordinateTransformation(float data1, float data2, int inEPSG) throws TransformException, FactoryException {
		

	CoordinateReferenceSystem inCRS;
			CoordinateReferenceSystem outCRS = CRS.parseWKT(COORDS_WKT_WGS84);; // WGS84
			
			float[] src = new float[] { data1, data2 };
			float[] dst = new float[2];
			
			switch (inEPSG) {
				
				/** ETRS89 UTM Zone 31N (min: max: - min: max:) */
				case 0:
					inCRS = CRS.parseWKT(COORDS_WKT_ETRS89_UTM31N);
					break;
				/**
				 * ETRS89 UTM Zone 32N (min:75000.0 max:1550000.0 -
				 * min:5070000.0 max:6310000.0)
				 */
				case 1:
					inCRS = CRS.parseWKT(COORDS_WKT_ETRS89_UTM32N);
					break;
				/**
				 * ETRS89 UTM Zone 33N (min:-425000 max:700000 - min:5020000
				 * max:6340000)
				 */
				case 2:
					inCRS = CRS.parseWKT(COORDS_WKT_ETRS89_UTM33N);
					break;
				/** ETRS89 UTM Zone 34N (min: max: - min: max:) */
				case 3:
					inCRS = CRS.parseWKT(COORDS_WKT_ETRS89_UTM34N);
					break;
				/** GK2 (min:2280000 max:3360000 - min:5080000 max:6320000) */
				case 4:
					inCRS = CRS.parseWKT(COORDS_WKT_GK2);
					break;
				/** GK3 (min:3040000 max:4120000 - min:5070000 max:6310000) */
				case 5:
					inCRS = CRS.parseWKT(COORDS_WKT_GK3);
					break;
				/** GK4 (min:3877000 max:4957000 - min:5080000 max:6320000) */
				case 6:
					inCRS = CRS.parseWKT(COORDS_WKT_GK4);
					break;
				/** GK5 (min:4551000 max:5711000 - min:5015000 max:6335000) */
				case 7:
					inCRS = CRS.parseWKT(COORDS_WKT_GK5);
					break;
				/** ETRS89 UTM Zone 31N (min: max: - min: max:) */
				default:
					inCRS = CRS.parseWKT(COORDS_WKT_ETRS89_UTM31N);
					break;
				
			}
			
			
			MathTransform transform = CRS.findMathTransform(inCRS, outCRS);
			transform.transform(src, 0, dst, 0, 1);
			
			System.out.println("source CRS: " + inCRS.getName().getCode());
			System.out.println("target CRS: " + outCRS.getName().getCode());
			System.out.println("Transformation: " + dst[0] + ", " + dst[1]);
			
	}
	
	public static void main(String[] args) throws Exception {
		
		// ETRS89 UTM Zone 31N
		new CoordinateTransformation((float) 2380000.0, (float) 5180000.0, 0);
		// ETRS89 UTM Zone 32N
		new CoordinateTransformation((float) 75000.0, (float) 5070000.0, 1);
		// ETRS89 UTM Zone 33N
		new CoordinateTransformation((float) -425000.0, (float) 5020000.0, 2);
		// ETRS89 UTM Zone 34N
		new CoordinateTransformation((float) 2380000.0, (float) 5180000.0, 3);
		// GK2
		new CoordinateTransformation((float) 2280000.0, (float) 5080000.0, 4);
		// GK3
		new CoordinateTransformation((float) 3365804.6893, (float) 5876051.2873, 5);
		// GK4
		new CoordinateTransformation((float) 3877000.0, (float) 5080000.0, 6);
		// GK5
		new CoordinateTransformation((float) 5000000.0, (float) 6000000.0, 7);
	}
	
}
