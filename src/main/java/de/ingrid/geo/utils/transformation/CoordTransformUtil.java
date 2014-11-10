/*
 * **************************************************-
 * ingrid-utils-geo
 * ==================================================
 * Copyright (C) 2014 wemove digital solutions GmbH
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
package de.ingrid.geo.utils.transformation;

import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

/**
 * CoordTransformUtil is a singleton class to convert coordinates of
 * coordination reference systems Gauss-Krueger(GK) or ETRS89 to coordinates of
 * coordination reference system WGS84
 * 
 * @author ktt
 */
public class CoordTransformUtil {
	
	private static final String			COORDS_WKT_GK2				= "PROJCS[\"DHDN / Gauss-Kruger zone 2\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]], PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]], UNIT[\"degree\", 0.017453292519943295], AXIS[\"Geodetic longitude\", EAST], AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 6.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 2500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31466\"]]";
	private static final String			COORDS_WKT_GK3				= "PROJCS[\"DHDN / Gauss-Kruger zone 3\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]],    PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],    UNIT[\"degree\", 0.017453292519943295],    AXIS[\"Geodetic longitude\", EAST],    AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 9.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 3500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31467\"]]";
	private static final String			COORDS_WKT_GK4				= "PROJCS[\"DHDN / Gauss-Kruger zone 4\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]],    PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],    UNIT[\"degree\", 0.017453292519943295],    AXIS[\"Geodetic longitude\", EAST],    AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 12.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 4500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31468\"]]";
	private static final String			COORDS_WKT_GK5				= "PROJCS[\"DHDN / Gauss-Kruger zone 5\",GEOGCS[\"DHDN\",DATUM[\"Deutsches Hauptdreiecksnetz\", SPHEROID[\"Bessel 1841\", 6377397.155, 299.1528128, AUTHORITY[\"EPSG\",\"7004\"]], TOWGS84[612.4, 77.0, 440.2, -0.054, 0.057, -2.797, 0.5259752559300956], AUTHORITY[\"EPSG\",\"6314\"]],    PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],    UNIT[\"degree\", 0.017453292519943295],    AXIS[\"Geodetic longitude\", EAST],    AXIS[\"Geodetic latitude\", NORTH],    AUTHORITY[\"EPSG\",\"4314\"]],  PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],  PARAMETER[\"central_meridian\", 15.0],  PARAMETER[\"latitude_of_origin\", 0.0],  PARAMETER[\"scale_factor\", 1.0],  PARAMETER[\"false_easting\", 5500000.0],  PARAMETER[\"false_northing\", 0.0],  UNIT[\"m\", 1.0],  AXIS[\"Easting\", EAST],  AXIS[\"Northing\", NORTH],  AUTHORITY[\"EPSG\",\"31469\"]]";
	private static final String			COORDS_WKT_ETRS89_UTM31N	= "PROJCS[\"ETRS89 / UTM zone 31N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 3.0], PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25831\"]]";
	private static final String			COORDS_WKT_ETRS89_UTM32N	= "PROJCS[\"ETRS89 / UTM zone 32N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 9.0], PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25832\"]]";
	private static final String			COORDS_WKT_ETRS89_UTM33N	= "PROJCS[\"ETRS89 / UTM zone 33N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 15.0],PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25833\"]]";
	private static final String			COORDS_WKT_ETRS89_UTM34N	= "PROJCS[\"ETRS89 / UTM zone 34N\",GEOGCS[\"ETRS89\",DATUM[\"European Terrestrial Reference System 1989\",SPHEROID[\"GRS 1980\", 6378137.0, 298.257222101, AUTHORITY[\"EPSG\",\"7019\"]],TOWGS84[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],AUTHORITY[\"EPSG\",\"6258\"]],PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\", 0.017453292519943295],AXIS[\"Geodetic longitude\", EAST],AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4258\"]],PROJECTION[\"Transverse Mercator\", AUTHORITY[\"EPSG\",\"9807\"]],PARAMETER[\"central_meridian\", 21.0],PARAMETER[\"latitude_of_origin\", 0.0], PARAMETER[\"scale_factor\", 0.9996], PARAMETER[\"false_easting\", 500000.0],PARAMETER[\"false_northing\", 0.0],UNIT[\"m\", 1.0],AXIS[\"Easting\", EAST],AXIS[\"Northing\", NORTH],AUTHORITY[\"EPSG\",\"25834\"]]";
	private static final String			COORDS_WKT_WGS84			= "GEOGCS[\"WGS 84\", DATUM[\"World Geodetic System 1984\",SPHEROID[\"WGS 84\", 6378137.0, 298.257223563, AUTHORITY[\"EPSG\",\"7030\"]], AUTHORITY[\"EPSG\",\"6326\"]],  PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]], UNIT[\"degree\", 0.017453292519943295], AXIS[\"Geodetic longitude\", EAST], AXIS[\"Geodetic latitude\", NORTH],AUTHORITY[\"EPSG\",\"4326\"]]";
	
	private static final String			COORDS_EPSG_GK2				= "31466";
	private static final String			COORDS_EPSG_GK3				= "31467";
	private static final String			COORDS_EPSG_GK4				= "31468";
	private static final String			COORDS_EPSG_GK5				= "31469";
	private static final String			COORDS_EPSG_ETRS89_UTM31N	= "25831";
	private static final String			COORDS_EPSG_ETRS89_UTM32N	= "25832";
	private static final String			COORDS_EPSG_ETRS89_UTM33N	= "25833";
	private static final String			COORDS_EPSG_ETRS89_UTM34N	= "25834";
	private static final String			COORDS_EPSG_WGS84			= "4326";
	
	private static final String			COORDS_SYSTEM_GK2			= "Gauss-Kr&uuml;ger Zone 2";
	private static final String			COORDS_SYSTEM_GK3			= "Gauss-Kr&uuml;ger Zone 3";
	private static final String			COORDS_SYSTEM_GK4			= "Gauss-Kr&uuml;ger Zone 4";
	private static final String			COORDS_SYSTEM_GK5			= "Gauss-Kr&uuml;ger Zone 5";
	private static final String			COORDS_SYSTEM_ETRS89_UTM31N	= "UTM Zone 31N";
	private static final String			COORDS_SYSTEM_ETRS89_UTM32N	= "UTM Zone 32N";
	private static final String			COORDS_SYSTEM_ETRS89_UTM33N	= "UTM Zone 33N";
	private static final String			COORDS_SYSTEM_ETRS89_UTM34N	= "UTM Zone 34N";
	private static final String			COORDS_SYSTEM_WGS84			= "WGS 84";
	
	private static CoordTransformUtil	coordTransformUtil;
	
	public enum CoordType {
		COORDS_ETRS89_UTM31N, COORDS_ETRS89_UTM32N, COORDS_ETRS89_UTM33N, COORDS_ETRS89_UTM34N, COORDS_GK2, COORDS_GK3, COORDS_GK4, COORDS_GK5, COORDS_WGS84
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
	public float[] transformToWGS84(float coordsX, float coordsY, CoordType coordsType) throws FactoryException, TransformException {
		
		float[] src = new float[] { coordsX, coordsY };
		float[] dst = new float[2];
		
		CoordinateReferenceSystem inCRS = CRS.parseWKT(getWKTString(coordsType));
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
	public double[] transformToWGS84(double coordsX, double coordsY, CoordType coordsType) throws FactoryException, TransformException {
		
		double[] src = new double[] { coordsX, coordsY };
		double[] dst = new double[2];
		
		CoordinateReferenceSystem inCRS = CRS.parseWKT(getWKTString(coordsType));
		CoordinateReferenceSystem outCRS = CRS.parseWKT(COORDS_WKT_WGS84);
		
		MathTransform tf = CRS.findMathTransform(inCRS, outCRS);
		tf.transform(src, 0, dst, 0, 1);
		
		return dst;
	}
	
	/**
	 * Get the WKT String of a coordinate reference system
	 * 
	 * @param coordsType
	 *            Define existing coordinate reference system
	 * @return WKT String of an EPSG
	 */
	public String getWKTString(CoordType coordsType) {
		
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
			// WGS84
			default:
				stringCoordsType = COORDS_WKT_WGS84;
				break;
			
		}
		
		return stringCoordsType;
	}
	
	/**
	 * Get EPSG Code of CoordType
	 * 
	 * @param coordsType
	 * @return EPSG Code as String
	 */
	public String getEPSG(CoordType coordsType) {
		String espgCode;
		
		switch (coordsType) {
			
			// ETRS89 UTM Zone 31N
			case COORDS_ETRS89_UTM31N:
				espgCode = COORDS_EPSG_ETRS89_UTM31N;
				break;
			// ETRS89 UTM Zone 32N
			case COORDS_ETRS89_UTM32N:
				espgCode = COORDS_EPSG_ETRS89_UTM32N;
				break;
			// ETRS89 UTM Zone 33N
			case COORDS_ETRS89_UTM33N:
				espgCode = COORDS_EPSG_ETRS89_UTM33N;
				break;
			// ETRS89 UTM Zone 34N
			case COORDS_ETRS89_UTM34N:
				espgCode = COORDS_EPSG_ETRS89_UTM34N;
				break;
			// GK2
			case COORDS_GK2:
				espgCode = COORDS_EPSG_GK2;
				break;
			// GK3
			case COORDS_GK3:
				espgCode = COORDS_EPSG_GK3;
				break;
			// GK4
			case COORDS_GK4:
				espgCode = COORDS_EPSG_GK4;
				break;
			// GK5
			case COORDS_GK5:
				espgCode = COORDS_EPSG_GK5;
				break;
			default:
				espgCode = COORDS_EPSG_WGS84;
				break;
		}
		return espgCode;
	}
	
	/**
	 * Get coordinate system name of CoordType
	 * 
	 * @param coordsType
	 * @return coordinate system name as String
	 */
	public String getCoordinateSystemName(CoordType coordsType) {
		String coordinateName;
		
		switch (coordsType) {
			
			// ETRS89 UTM Zone 31N
			case COORDS_ETRS89_UTM31N:
				coordinateName = COORDS_SYSTEM_ETRS89_UTM31N;
				break;
			// ETRS89 UTM Zone 32N
			case COORDS_ETRS89_UTM32N:
				coordinateName = COORDS_SYSTEM_ETRS89_UTM32N;
				break;
			// ETRS89 UTM Zone 33N
			case COORDS_ETRS89_UTM33N:
				coordinateName = COORDS_SYSTEM_ETRS89_UTM33N;
				break;
			// ETRS89 UTM Zone 34N
			case COORDS_ETRS89_UTM34N:
				coordinateName = COORDS_SYSTEM_ETRS89_UTM34N;
				break;
			// GK2
			case COORDS_GK2:
				coordinateName = COORDS_SYSTEM_GK2;
				break;
			// GK3
			case COORDS_GK3:
				coordinateName = COORDS_SYSTEM_GK3;
				break;
			// GK4
			case COORDS_GK4:
				coordinateName = COORDS_SYSTEM_GK4;
				break;
			// GK5
			case COORDS_GK5:
				coordinateName = COORDS_SYSTEM_GK5;
				break;
			default:
				coordinateName = COORDS_SYSTEM_WGS84;
				break;
		}
		return coordinateName;
	}
	
	
		
	/**
	 * Get name of an EPSG code.
	 * 
	 * @param epsgCode
	 * @return EPSG name
	 * 
	 */
	public String getNameOfEPSG(String epsgCode){
		String epsgName = "";
		if(epsgCode.equals(COORDS_EPSG_WGS84)){
			epsgName = COORDS_SYSTEM_WGS84;
		}else if(epsgCode.equals(COORDS_EPSG_ETRS89_UTM31N)){
			epsgName = COORDS_SYSTEM_ETRS89_UTM31N;
		}else if(epsgCode.equals(COORDS_EPSG_ETRS89_UTM32N)){
			epsgName = COORDS_SYSTEM_ETRS89_UTM32N;
		}else if(epsgCode.equals(COORDS_EPSG_ETRS89_UTM33N)){
			epsgName = COORDS_SYSTEM_ETRS89_UTM33N;
		}else if(epsgCode.equals(COORDS_EPSG_ETRS89_UTM34N)){
			epsgName = COORDS_SYSTEM_ETRS89_UTM34N;
		}else if(epsgCode.equals(COORDS_EPSG_GK2)){
			epsgName = COORDS_SYSTEM_GK2;
		}else if(epsgCode.equals(COORDS_EPSG_GK3)){
			epsgName = COORDS_SYSTEM_GK3;
		}else if(epsgCode.equals(COORDS_EPSG_GK4)){
			epsgName = COORDS_SYSTEM_GK4;
		}else if(epsgCode.equals(COORDS_EPSG_GK5)){
			epsgName = COORDS_SYSTEM_GK5;
		}
		return epsgName;
	}
	
	/**
	 * Get CoordType by EPSG Code
	 * 
	 * @param epsgCode
	 * @return CoordType
	 * 
	 */
	public CoordType getCoordTypeByEPSGCode(String epsgCode){
		if(epsgCode.equals(COORDS_EPSG_WGS84)){
			return CoordType.COORDS_WGS84;
		}else if(epsgCode.equals(COORDS_EPSG_ETRS89_UTM31N)){
			return CoordType.COORDS_ETRS89_UTM31N;
		}else if(epsgCode.equals(COORDS_EPSG_ETRS89_UTM32N)){
			return CoordType.COORDS_ETRS89_UTM32N;
		}else if(epsgCode.equals(COORDS_EPSG_ETRS89_UTM33N)){
			return CoordType.COORDS_ETRS89_UTM33N;
		}else if(epsgCode.equals(COORDS_EPSG_ETRS89_UTM34N)){
			return CoordType.COORDS_ETRS89_UTM34N;
		}else if(epsgCode.equals(COORDS_EPSG_GK2)){
			return CoordType.COORDS_GK2;
		}else if(epsgCode.equals(COORDS_EPSG_GK3)){
			return CoordType.COORDS_GK3;
		}else if(epsgCode.equals(COORDS_EPSG_GK4)){
			return CoordType.COORDS_GK4;
		}else if(epsgCode.equals(COORDS_EPSG_GK5)){
			return CoordType.COORDS_GK5;
		}else{
			return null;
		}
	}
}