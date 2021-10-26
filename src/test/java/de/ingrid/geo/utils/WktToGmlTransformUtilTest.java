package de.ingrid.geo.utils;

import com.vividsolutions.jts.io.ParseException;
import de.ingrid.geo.utils.transformation.WktToGmlTransformUtil;
import junit.framework.TestCase;

import java.io.IOException;

public class WktToGmlTransformUtilTest extends TestCase {

	public void testPoint() throws ParseException, IOException {
		String wkt = "POINT(30 10)";
		String gml = WktToGmlTransformUtil.wktToGml3(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:Point>" +
				  "<gml:pos>30.0 10.0</gml:pos>" +
				"</gml:Point>";
		assertEquals(expected, actual);
	}

	public void testMultiPoint() throws ParseException, IOException {
		String wkt = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
		String gml = WktToGmlTransformUtil.wktToGml3(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:MultiPoint>" +
				  "<gml:pointMember>" +
				    "<gml:Point srsDimension=\"2\">" +
				      "<gml:pos>10.0 40.0</gml:pos>" +
				    "</gml:Point>" +
				  "</gml:pointMember>" +
				  "<gml:pointMember>" +
				    "<gml:Point srsDimension=\"2\">" +
				      "<gml:pos>40.0 30.0</gml:pos>" +
				    "</gml:Point>" +
				  "</gml:pointMember>" +
				  "<gml:pointMember>" +
				    "<gml:Point srsDimension=\"2\">" +
				      "<gml:pos>20.0 20.0</gml:pos>" +
				    "</gml:Point>" +
				  "</gml:pointMember>" +
				  "<gml:pointMember>" +
				    "<gml:Point srsDimension=\"2\">" +
				      "<gml:pos>30.0 10.0</gml:pos>" +
				    "</gml:Point>" +
				  "</gml:pointMember>" +
				"</gml:MultiPoint>";
		assertEquals(expected, actual);
	}

	public void testLinestring() throws ParseException, IOException {
		String wkt = "LINESTRING (30 10, 10 30, 40 40)";
		String gml = WktToGmlTransformUtil.wktToGml3(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:LineString>" +
				  "<gml:posList>30.0 10.0 10.0 30.0 40.0 40.0</gml:posList>" +
				"</gml:LineString>";
		assertEquals(expected, actual);
	}

	public void testMultiLinestring() throws ParseException, IOException {
		String wkt = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
		String gml = WktToGmlTransformUtil.wktToGml3(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:MultiLineString>" +
				  "<gml:lineStringMember>" +
				    "<gml:LineString srsDimension=\"2\">" +
				      "<gml:posList>10.0 10.0 20.0 20.0 10.0 40.0</gml:posList>" +
				    "</gml:LineString>" +
				  "</gml:lineStringMember>" +
				  "<gml:lineStringMember>" +
				    "<gml:LineString srsDimension=\"2\">" +
				      "<gml:posList>40.0 40.0 30.0 30.0 40.0 20.0 30.0 10.0</gml:posList>" +
				    "</gml:LineString>" +
				  "</gml:lineStringMember>" +
				"</gml:MultiLineString>";
		assertEquals(expected, actual);
	}

	public void testPolygon() throws ParseException, IOException {
		String wkt = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))";
		String gml = WktToGmlTransformUtil.wktToGml3(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:Polygon>" +
				  "<gml:exterior>" +
				    "<gml:LinearRing srsDimension=\"2\">" +
				      "<gml:posList>35.0 10.0 45.0 45.0 15.0 40.0 10.0 20.0 35.0 10.0</gml:posList>" +
				    "</gml:LinearRing>" +
				  "</gml:exterior>" +
				  "<gml:interior>" +
				    "<gml:LinearRing srsDimension=\"2\">" +
				      "<gml:posList>20.0 30.0 35.0 35.0 30.0 20.0 20.0 30.0</gml:posList>" +
				    "</gml:LinearRing>" +
				  "</gml:interior>" +
				"</gml:Polygon>";
		assertEquals(expected, actual);
	}

	public void testMultiPolygon() throws ParseException, IOException {
		String wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))";
		String gml = WktToGmlTransformUtil.wktToGml3(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:MultiPolygon>" +
				  "<gml:polygonMember>" +
				    "<gml:Polygon srsDimension=\"2\">" +
				      "<gml:exterior>" +
				        "<gml:LinearRing srsDimension=\"2\">" +
				          "<gml:posList>40.0 40.0 20.0 45.0 45.0 30.0 40.0 40.0</gml:posList>" +
				        "</gml:LinearRing>" +
				      "</gml:exterior>" +
				    "</gml:Polygon>" +
				  "</gml:polygonMember>" +
				  "<gml:polygonMember>" +
				    "<gml:Polygon srsDimension=\"2\">" +
				      "<gml:exterior>" +
				        "<gml:LinearRing srsDimension=\"2\">" +
				          "<gml:posList>20.0 35.0 10.0 30.0 10.0 10.0 30.0 5.0 45.0 20.0 20.0 35.0</gml:posList>" +
				        "</gml:LinearRing>" +
				      "</gml:exterior>" +
				      "<gml:interior>" +
				        "<gml:LinearRing srsDimension=\"2\">" +
				          "<gml:posList>30.0 20.0 20.0 15.0 20.0 25.0 30.0 20.0</gml:posList>" +
				        "</gml:LinearRing>" +
				      "</gml:interior>" +
				    "</gml:Polygon>" +
				  "</gml:polygonMember>" +
				"</gml:MultiPolygon>";
		assertEquals(expected, actual);
	}

	public void testMultiGeometry() throws ParseException, IOException {
		String wkt = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
		String gml = WktToGmlTransformUtil.wktToGml3(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:MultiGeometry>" +
				  "<gml:geometryMember>" +
				    "<gml:Point srsDimension=\"2\">" +
				      "<gml:pos>40.0 10.0</gml:pos>" +
				    "</gml:Point>" +
				  "</gml:geometryMember>" +
				  "<gml:geometryMember>" +
				    "<gml:LineString srsDimension=\"2\">" +
				      "<gml:posList>10.0 10.0 20.0 20.0 10.0 40.0</gml:posList>" +
				    "</gml:LineString>" +
				  "</gml:geometryMember>" +
				  "<gml:geometryMember>" +
				    "<gml:Polygon srsDimension=\"2\">" +
				      "<gml:exterior>" +
				        "<gml:LinearRing srsDimension=\"2\">" +
				          "<gml:posList>40.0 40.0 20.0 45.0 45.0 30.0 40.0 40.0</gml:posList>" +
				        "</gml:LinearRing>" +
				      "</gml:exterior>" +
				    "</gml:Polygon>" +
				  "</gml:geometryMember>" +
				"</gml:MultiGeometry>";
		assertEquals(expected, actual);
	}

	private String deleteIdAttributes(String gml) {
		return gml.replaceAll(" gml:id=\"[^\"]+\"", "");
	}
}

