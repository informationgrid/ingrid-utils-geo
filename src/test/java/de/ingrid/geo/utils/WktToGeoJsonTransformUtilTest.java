package de.ingrid.geo.utils;

import java.io.IOException;

import org.locationtech.jts.io.ParseException;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.TransformException;

import de.ingrid.geo.utils.transformation.WktToGeoJsonTransformUtil;
import junit.framework.TestCase;

public class WktToGeoJsonTransformUtilTest extends TestCase {

	public void testPoint() throws ParseException, IOException {
		String wkt = "POINT(30 10)";
		String geojson = WktToGeoJsonTransformUtil.wktToGeoJson(wkt);

		String expected = "{\"type\":\"Point\",\"coordinates\":[30,10]}";
		assertEquals(expected, geojson);
	}

	public void testMultiPoint() throws ParseException, IOException {
		String wkt = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
		String geojson = WktToGeoJsonTransformUtil.wktToGeoJson(wkt);

		String expected = "{\"type\":\"MultiPoint\",\"coordinates\":[[10,40],[40,30],[20,20],[30,10]]}";
		assertEquals(expected, geojson);
	}

	public void testLinestring() throws ParseException, IOException {
		String wkt = "LINESTRING (30 10, 10 30, 40 40)";
		String geojson = WktToGeoJsonTransformUtil.wktToGeoJson(wkt);

		String expected = "{\"type\":\"LineString\",\"coordinates\":[[30,10],[10,30],[40,40]]}";
		assertEquals(expected, geojson);
	}

	public void testMultiLinestring() throws ParseException, IOException {
		String wkt = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
		String geojson = WktToGeoJsonTransformUtil.wktToGeoJson(wkt);

		String expected = "{\"type\":\"MultiLineString\",\"coordinates\":[[[10,10],[20,20],[10,40]],[[40,40],[30,30],[40,20],[30,10]]]}";
		assertEquals(expected, geojson);
	}

	public void testPolygon() throws ParseException, IOException {
		String wkt = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))";
		String geojson = WktToGeoJsonTransformUtil.wktToGeoJson(wkt);

		String expected = "{\"type\":\"Polygon\",\"coordinates\":[[[35,10],[45,45],[15,40],[10,20],[35,10]],[[20,30],[35,35],[30,20],[20,30]]]}";
		assertEquals(expected, geojson);
	}

	public void testMultiPolygon() throws ParseException, IOException {
		String wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))";
		String geojson = WktToGeoJsonTransformUtil.wktToGeoJson(wkt);

		String expected = "{\"type\":\"MultiPolygon\",\"coordinates\":[[[[40,40],[20,45],[45,30],[40,40]]],[[[20,35],[10,30],[10,10],[30,5],[45,20],[20,35]],[[30,20],[20,15],[20,25],[30,20]]]]}";
		assertEquals(expected, geojson);
	}

	public void testMultiGeometry() throws ParseException, IOException {
		String wkt = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
		String geojson = WktToGeoJsonTransformUtil.wktToGeoJson(wkt);

		String expected = "{\"type\":\"GeometryCollection\",\"geometries\":[" +
				"{\"type\":\"Point\",\"coordinates\":[40,10]}," +
				"{\"type\":\"LineString\",\"coordinates\":[[10,10],[20,20],[10,40]]}," +
				"{\"type\":\"Polygon\",\"coordinates\":[[[40,40],[20,45],[45,30],[40,40]]]}]}";
		assertEquals(expected, geojson);
	}

   public void testPointTransform() throws ParseException, IOException, MismatchedDimensionException, NoSuchAuthorityCodeException, FactoryException, TransformException {
        String wkt = "POINT(30 10)";
        String geojson = WktToGeoJsonTransformUtil.wktToGeoJsonTransform(wkt, "4326", "3857");

        String expected = "{\"type\":\"Point\",\"coordinates\":[3339584.723798207,1118889.9748579597]}";
        assertEquals(expected, geojson);
    }

   public void testMultiGeometryTransform() throws ParseException, IOException, MismatchedDimensionException, NoSuchAuthorityCodeException, FactoryException, TransformException {
       String wkt = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
       String geojson = WktToGeoJsonTransformUtil.wktToGeoJsonTransform(wkt, "4326", "3857");

       String expected = "{\"type\":\"GeometryCollection\",\"geometries\":[{\"type\":\"Point\",\"coordinates\":[4452779.631730943,1118889.9748579597]}," +
			   "{\"type\":\"LineString\",\"coordinates\":[[1113194.9079327357,1118889.9748579597],[2226389.8158654715,2273030.926987689],[1113194.9079327357,4865942.279503176]]}," +
			   "{\"type\":\"Polygon\",\"coordinates\":[[[4452779.631730943,4865942.279503176],[2226389.8158654715,5621521.486192066],[5009377.085697311,3503549.843504374],[4452779.631730943,4865942.279503176]]]}]}";
       assertEquals(expected, geojson);
   }
}

