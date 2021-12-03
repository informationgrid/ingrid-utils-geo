package de.ingrid.geo.utils;

import com.vividsolutions.jts.io.ParseException;
import de.ingrid.geo.utils.transformation.WktToGmlTransformUtil;
import junit.framework.TestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class WktToGmlTransformUtilTest extends TestCase {

	public void testPointString() throws ParseException, IOException {
		String wkt = "POINT(30 10)";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:Point>" +
				  "<gml:pos>30.0 10.0</gml:pos>" +
				"</gml:Point>";
		assertEquals(expected, actual);
	}

	public void testPointDom() throws ParseException, IOException, TransformerException, SAXException {
		String wkt = "POINT(30 10)";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:Point", root.getTagName());

		Element pos = getFirstElementChild(root);
		assertEquals("gml:pos", pos.getTagName());
		assertEquals("30.0 10.0", pos.getTextContent());
	}

	public void testMultiPointString() throws ParseException, IOException {
		String wkt = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

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

	public void testMultiPointDom() throws ParseException, IOException, TransformerException, SAXException {
		String wkt = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:MultiPoint", root.getTagName());

		Element pointMember = getFirstElementChild(root);
		assertEquals("gml:pointMember", pointMember.getTagName());
		Element point = getFirstElementChild(pointMember);
		assertEquals("gml:Point", point.getTagName());
		Element pos = getFirstElementChild(point);
		assertEquals("10.0 40.0", pos.getTextContent());

		pointMember = getNextElementSibling(pointMember);
		assertEquals("gml:pointMember", pointMember.getTagName());
		point = getFirstElementChild(pointMember);
		assertEquals("gml:Point", point.getTagName());
		pos = getFirstElementChild(point);
		assertEquals("40.0 30.0", pos.getTextContent());

		pointMember = getNextElementSibling(pointMember);
		assertEquals("gml:pointMember", pointMember.getTagName());
		point = getFirstElementChild(pointMember);
		assertEquals("gml:Point", point.getTagName());
		pos = getFirstElementChild(point);
		assertEquals("20.0 20.0", pos.getTextContent());

		pointMember = getNextElementSibling(pointMember);
		assertEquals("gml:pointMember", pointMember.getTagName());
		point = getFirstElementChild(pointMember);
		assertEquals("gml:Point", point.getTagName());
		pos = getFirstElementChild(point);
		assertEquals("30.0 10.0", pos.getTextContent());
	}

	public void testLinestringString() throws ParseException, IOException {
		String wkt = "LINESTRING (30 10, 10 30, 40 40)";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:LineString>" +
				  "<gml:posList>30.0 10.0 10.0 30.0 40.0 40.0</gml:posList>" +
				"</gml:LineString>";
		assertEquals(expected, actual);
	}

	public void testLinstringDom() throws ParseException, IOException, TransformerException, SAXException {
		String wkt = "LINESTRING (30 10, 10 30, 40 40)";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:LineString", root.getTagName());

		Element posList = getFirstElementChild(root);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("30.0 10.0 10.0 30.0 40.0 40.0", posList.getTextContent());
	}

	public void testMultiLinestringString() throws ParseException, IOException {
		String wkt = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:MultiCurve>" +
				  "<gml:curveMember>" +
				    "<gml:LineString srsDimension=\"2\">" +
				      "<gml:posList>10.0 10.0 20.0 20.0 10.0 40.0</gml:posList>" +
				    "</gml:LineString>" +
				  "</gml:curveMember>" +
				  "<gml:curveMember>" +
				    "<gml:LineString srsDimension=\"2\">" +
				      "<gml:posList>40.0 40.0 30.0 30.0 40.0 20.0 30.0 10.0</gml:posList>" +
				    "</gml:LineString>" +
				  "</gml:curveMember>" +
				"</gml:MultiCurve>";
		assertEquals(expected, actual);
	}

	public void testMultiLinstringDom() throws ParseException, IOException, TransformerException, SAXException {
		String wkt = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:MultiCurve", root.getTagName());

		Element member = getFirstElementChild(root);
		assertEquals("gml:curveMember", member.getTagName());
		Element lineString = getFirstElementChild(member);
		assertEquals("gml:LineString", lineString.getTagName());
		Element posList = getFirstElementChild(lineString);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("10.0 10.0 20.0 20.0 10.0 40.0", posList.getTextContent());

		member = getNextElementSibling(member);
		assertEquals("gml:curveMember", member.getTagName());
		lineString = getFirstElementChild(member);
		assertEquals("gml:LineString", lineString.getTagName());
		posList = getFirstElementChild(lineString);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("40.0 40.0 30.0 30.0 40.0 20.0 30.0 10.0", posList.getTextContent());
	}

	public void testPolygonString() throws ParseException, IOException {
		String wkt = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

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

	public void testPolygonDom() throws ParseException, IOException, TransformerException, SAXException {
		String wkt = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:Polygon", root.getTagName());

		Element exterior = getFirstElementChild(root);
		assertEquals("gml:exterior", exterior.getTagName());
		Element linearRing = getFirstElementChild(exterior);
		assertEquals("gml:LinearRing", linearRing.getTagName());
		Element posList = getFirstElementChild(linearRing);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("35.0 10.0 45.0 45.0 15.0 40.0 10.0 20.0 35.0 10.0", posList.getTextContent());

		Element interior = getNextElementSibling(exterior);
		assertEquals("gml:interior", interior.getTagName());
		linearRing = getFirstElementChild(interior);
		assertEquals("gml:LinearRing", linearRing.getTagName());
		posList = getFirstElementChild(linearRing);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("20.0 30.0 35.0 35.0 30.0 20.0 20.0 30.0", posList.getTextContent());
	}

	public void testMultiPolygonString() throws ParseException, IOException {
		String wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:MultiSurface>" +
				  "<gml:surfaceMember>" +
				    "<gml:Polygon srsDimension=\"2\">" +
				      "<gml:exterior>" +
				        "<gml:LinearRing srsDimension=\"2\">" +
				          "<gml:posList>40.0 40.0 20.0 45.0 45.0 30.0 40.0 40.0</gml:posList>" +
				        "</gml:LinearRing>" +
				      "</gml:exterior>" +
				    "</gml:Polygon>" +
				  "</gml:surfaceMember>" +
				  "<gml:surfaceMember>" +
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
				  "</gml:surfaceMember>" +
				"</gml:MultiSurface>";
		assertEquals(expected, actual);
	}

	public void testMultiPolygonDom() throws ParseException, IOException, TransformerException, SAXException {
		String wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:MultiSurface", root.getTagName());

		Element member = getFirstElementChild(root);
		assertEquals("gml:surfaceMember", member.getTagName());
		Element polygon = getFirstElementChild(member);
		assertEquals("gml:Polygon", polygon.getTagName());
		Element exterior = getFirstElementChild(polygon);
		assertEquals("gml:exterior", exterior.getTagName());
		Element linearRing = getFirstElementChild(exterior);
		assertEquals("gml:LinearRing", linearRing.getTagName());
		Element posList = getFirstElementChild(linearRing);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("40.0 40.0 20.0 45.0 45.0 30.0 40.0 40.0", posList.getTextContent());

		member = getNextElementSibling(member);
		assertEquals("gml:surfaceMember", member.getTagName());
		polygon = getFirstElementChild(member);
		assertEquals("gml:Polygon", polygon.getTagName());
		exterior = getFirstElementChild(polygon);
		assertEquals("gml:exterior", exterior.getTagName());
		linearRing = getFirstElementChild(exterior);
		assertEquals("gml:LinearRing", linearRing.getTagName());
		posList = getFirstElementChild(linearRing);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("20.0 35.0 10.0 30.0 10.0 10.0 30.0 5.0 45.0 20.0 20.0 35.0", posList.getTextContent());
		Element interior = getNextElementSibling(exterior);
		assertEquals("gml:interior", interior.getTagName());
		linearRing = getFirstElementChild(interior);
		assertEquals("gml:LinearRing", linearRing.getTagName());
		posList = getFirstElementChild(linearRing);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("30.0 20.0 20.0 15.0 20.0 25.0 30.0 20.0", posList.getTextContent());
	}

	public void testMultiGeometryString() throws ParseException, IOException {
		String wkt = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

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

	public void testMultiGeometryDom() throws ParseException, IOException, TransformerException, SAXException {
		String wkt = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:MultiGeometry", root.getTagName());

		Element member = getFirstElementChild(root);
		assertEquals("gml:geometryMember", member.getTagName());
		Element point = getFirstElementChild(member);
		assertEquals("gml:Point", point.getTagName());
		Element pos = getFirstElementChild(point);
		assertEquals("gml:pos", pos.getTagName());
		assertEquals("40.0 10.0", pos.getTextContent());

		member = getNextElementSibling(member);
		assertEquals("gml:geometryMember", member.getTagName());
		Element lineString = getFirstElementChild(member);
		assertEquals("gml:LineString", lineString.getTagName());
		Element posList = getFirstElementChild(lineString);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("10.0 10.0 20.0 20.0 10.0 40.0", posList.getTextContent());

		member = getNextElementSibling(member);
		assertEquals("gml:geometryMember", member.getTagName());
		Element polygon = getFirstElementChild(member);
		assertEquals("gml:Polygon", polygon.getTagName());
		Element exterior = getFirstElementChild(polygon);
		assertEquals("gml:exterior", exterior.getTagName());
		Element linearRing = getFirstElementChild(exterior);
		assertEquals("gml:LinearRing", linearRing.getTagName());
		posList = getFirstElementChild(linearRing);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("40.0 40.0 20.0 45.0 45.0 30.0 40.0 40.0", posList.getTextContent());
	}

	private String deleteIdAttributes(String gml) {
		return gml.replaceAll(" gml:id=\"[^\"]+\"", "");
	}

	private Element getFirstElementChild(Node node) {
		NodeList children = node.getChildNodes();
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			if (child instanceof Element) {
				return (Element) child;
			}
		}
		return null;
	}

	private Element getNextElementSibling(Node node) {
		Node sibling0;
		Node sibling1 = node.getNextSibling();
		while(sibling1 != null) {
			if (sibling1 instanceof Element) {
				return (Element) sibling1;
			} else {
				sibling0 = sibling1;
				sibling1 = sibling0.getNextSibling();
			}
		}
		return null;
	}

    public void testPointString3_2() throws ParseException, IOException {
        String wkt = "POINT(30 10)";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:Point>" +
                  "<gml:pos>30.0 10.0</gml:pos>" +
                "</gml:Point>";
        assertEquals(expected, actual);
    }

    public void testMultiPointString3_2() throws ParseException, IOException {
        String wkt = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

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

    public void testLinestringString3_2() throws ParseException, IOException {
        String wkt = "LINESTRING (30 10, 10 30, 40 40)";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:LineString>" +
                  "<gml:posList>30.0 10.0 10.0 30.0 40.0 40.0</gml:posList>" +
                "</gml:LineString>";
        assertEquals(expected, actual);
    }

    public void testMultiLinestringString3_2() throws ParseException, IOException {
        String wkt = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:MultiCurve>" +
                  "<gml:curveMember>" +
                    "<gml:LineString srsDimension=\"2\">" +
                      "<gml:posList>10.0 10.0 20.0 20.0 10.0 40.0</gml:posList>" +
                    "</gml:LineString>" +
                  "</gml:curveMember>" +
                  "<gml:curveMember>" +
                    "<gml:LineString srsDimension=\"2\">" +
                      "<gml:posList>40.0 40.0 30.0 30.0 40.0 20.0 30.0 10.0</gml:posList>" +
                    "</gml:LineString>" +
                  "</gml:curveMember>" +
                "</gml:MultiCurve>";
        assertEquals(expected, actual);
    }

    public void testPolygonString3_2() throws ParseException, IOException {
        String wkt = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:Polygon>" +
                  "<gml:exterior>" +
                    "<gml:LinearRing>" +
                      "<gml:posList>35.0 10.0 45.0 45.0 15.0 40.0 10.0 20.0 35.0 10.0</gml:posList>" +
                    "</gml:LinearRing>" +
                  "</gml:exterior>" +
                  "<gml:interior>" +
                    "<gml:LinearRing>" +
                      "<gml:posList>20.0 30.0 35.0 35.0 30.0 20.0 20.0 30.0</gml:posList>" +
                    "</gml:LinearRing>" +
                  "</gml:interior>" +
                "</gml:Polygon>";
        assertEquals(expected, actual);
    }

    public void testMultiPolygonString3_2() throws ParseException, IOException {
        String wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:MultiSurface>" +
                  "<gml:surfaceMember>" +
                    "<gml:Polygon srsDimension=\"2\">" +
                      "<gml:exterior>" +
                        "<gml:LinearRing>" +
                          "<gml:posList>40.0 40.0 20.0 45.0 45.0 30.0 40.0 40.0</gml:posList>" +
                        "</gml:LinearRing>" +
                      "</gml:exterior>" +
                    "</gml:Polygon>" +
                  "</gml:surfaceMember>" +
                  "<gml:surfaceMember>" +
                    "<gml:Polygon srsDimension=\"2\">" +
                      "<gml:exterior>" +
                        "<gml:LinearRing>" +
                          "<gml:posList>20.0 35.0 10.0 30.0 10.0 10.0 30.0 5.0 45.0 20.0 20.0 35.0</gml:posList>" +
                        "</gml:LinearRing>" +
                      "</gml:exterior>" +
                      "<gml:interior>" +
                        "<gml:LinearRing>" +
                          "<gml:posList>30.0 20.0 20.0 15.0 20.0 25.0 30.0 20.0</gml:posList>" +
                        "</gml:LinearRing>" +
                      "</gml:interior>" +
                    "</gml:Polygon>" +
                  "</gml:surfaceMember>" +
                "</gml:MultiSurface>";
        assertEquals(expected, actual);
    }

}

