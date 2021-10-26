package de.ingrid.geo.utils;

import com.vividsolutions.jts.io.ParseException;
import de.ingrid.geo.utils.transformation.WktToGeoJsonTransformUtil;
import junit.framework.TestCase;

import java.io.IOException;

public class WktToGeoJsonTransformUtilTest extends TestCase {

	public void testPoint() throws ParseException, IOException {
		String wkt = "POINT(30 10)";
		String gml = WktToGeoJsonTransformUtil.wktToKml(wkt);

		String actual = deleteIdAttributes(gml);
		String expected = "{\"type\":\"Point\",\"coordinates\":[30,10]}";
		assertEquals(expected, actual);
	}

	public void testMultiPoint() throws ParseException, IOException {
		String wkt = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
		String gml = WktToGeoJsonTransformUtil.wktToKml(wkt);

		String actual = deleteIdAttributes(gml);
		String expected = "{\"type\":\"MultiPoint\",\"coordinates\":[[10,40],[40,30],[20,20],[30,10]]}";
		assertEquals(expected, actual);
	}

	public void testLinestring() throws ParseException, IOException {
		String wkt = "LINESTRING (30 10, 10 30, 40 40)";
		String gml = WktToGeoJsonTransformUtil.wktToKml(wkt);

		String actual = deleteIdAttributes(gml);
		String expected = "{\"type\":\"LineString\",\"coordinates\":[[30,10],[10,30],[40,40]]}";
		assertEquals(expected, actual);
	}

	public void testMultiLinestring() throws ParseException, IOException {
		String wkt = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
		String gml = WktToGeoJsonTransformUtil.wktToKml(wkt);

		String actual = deleteIdAttributes(gml);
		String expected = "{\"type\":\"MultiLineString\",\"coordinates\":[[[10,10],[20,20],[10,40]],[[40,40],[30,30],[40,20],[30,10]]]}";
		assertEquals(expected, actual);
	}

	public void testPolygon() throws ParseException, IOException {
		String wkt = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))";
		String gml = WktToGeoJsonTransformUtil.wktToKml(wkt);

		String actual = deleteIdAttributes(gml);
		String expected = "{\"type\":\"Polygon\",\"coordinates\":[[[35,10],[45,45],[15,40],[10,20],[35,10]],[[20,30],[35,35],[30,20],[20,30]]]}";
		assertEquals(expected, actual);
	}

	public void testMultiPolygon() throws ParseException, IOException {
		String wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))";
		String gml = WktToGeoJsonTransformUtil.wktToKml(wkt);

		String actual = deleteIdAttributes(gml);
		String expected = "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[40,40],[20,45],[45,30],[40,40]]],[[[20,35],[10,30],[10,10],[30,5],[45,20],[20,35]],[[30,20],[20,15],[20,25],[30,20]]]]}";
		assertEquals(expected, actual);
	}

	public void testMultiGeometry() throws ParseException, IOException {
		String wkt = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
		String gml = WktToGeoJsonTransformUtil.wktToKml(wkt);

		String actual = deleteIdAttributes(gml);
		String expected = "{\"type\":\"GeometryCollection\",\"geometries\":[" +
				"{\"type\":\"Point\",\"coordinates\":[40,10]}," +
				"{\"type\":\"LineString\",\"coordinates\":[[10,10],[20,20],[10,40]]}," +
				"{\"type\":\"Polygon\",\"coordinates\":[[[40,40],[20,45],[45,30],[40,40]]]}]}";
		assertEquals(expected, actual);
	}

	private String deleteIdAttributes(String gml) {
		return gml.replaceAll(" gml:id=\"[^\"]+\"", "");
	}
}

