/*-
 * **************************************************-
 * ingrid-utils-geo
 * ==================================================
 * Copyright (C) 2014 - 2025 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * https://joinup.ec.europa.eu/software/page/eupl
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.geo.utils;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.ParseException;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.ingrid.geo.utils.transformation.WktToGmlTransformUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WktToGmlTransformUtilTest {

    @Test
    public void testPointString() throws ParseException, IOException {
		String wkt = "POINT(30 10)";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:Point>" +
				  "<gml:pos>30 10</gml:pos>" +
				"</gml:Point>";
		assertEquals(expected, actual);
	}

    @Test
    public void testPointDom() throws ParseException, IOException, TransformerException, SAXException {
		String wkt = "POINT(30 10)";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:Point", root.getTagName());

		Element pos = getFirstElementChild(root);
		assertEquals("gml:pos", pos.getTagName());
		assertEquals("30 10", pos.getTextContent());
	}

    @Test
    public void testPointElement() throws ParseException, IOException, TransformerException, SAXException {
        String wkt = "POINT(30 10)";
        Element gml = WktToGmlTransformUtil.wktToGml3AsElement(wkt);
        assertEquals("gml:Point", gml.getTagName());

        Element pos = getFirstElementChild(gml);
        assertEquals("gml:pos", pos.getTagName());
        assertEquals("30 10", pos.getTextContent());
    }

    @Test
    public void testMultiPointString() throws ParseException, IOException {
		String wkt = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:MultiPoint>" +
				  "<gml:pointMember>" +
				    "<gml:Point srsDimension=\"2\">" +
				      "<gml:pos>10 40</gml:pos>" +
				    "</gml:Point>" +
				  "</gml:pointMember>" +
				  "<gml:pointMember>" +
				    "<gml:Point srsDimension=\"2\">" +
				      "<gml:pos>40 30</gml:pos>" +
				    "</gml:Point>" +
				  "</gml:pointMember>" +
				  "<gml:pointMember>" +
				    "<gml:Point srsDimension=\"2\">" +
				      "<gml:pos>20 20</gml:pos>" +
				    "</gml:Point>" +
				  "</gml:pointMember>" +
				  "<gml:pointMember>" +
				    "<gml:Point srsDimension=\"2\">" +
				      "<gml:pos>30 10</gml:pos>" +
				    "</gml:Point>" +
				  "</gml:pointMember>" +
				"</gml:MultiPoint>";
		assertEquals(expected, actual);
	}

    @Test
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
		assertEquals("10 40", pos.getTextContent());

		pointMember = getNextElementSibling(pointMember);
		assertEquals("gml:pointMember", pointMember.getTagName());
		point = getFirstElementChild(pointMember);
		assertEquals("gml:Point", point.getTagName());
		pos = getFirstElementChild(point);
		assertEquals("40 30", pos.getTextContent());

		pointMember = getNextElementSibling(pointMember);
		assertEquals("gml:pointMember", pointMember.getTagName());
		point = getFirstElementChild(pointMember);
		assertEquals("gml:Point", point.getTagName());
		pos = getFirstElementChild(point);
		assertEquals("20 20", pos.getTextContent());

		pointMember = getNextElementSibling(pointMember);
		assertEquals("gml:pointMember", pointMember.getTagName());
		point = getFirstElementChild(pointMember);
		assertEquals("gml:Point", point.getTagName());
		pos = getFirstElementChild(point);
		assertEquals("30 10", pos.getTextContent());
	}

    @Test
    public void testLinestringString() throws ParseException, IOException {
		String wkt = "LINESTRING (30 10, 10 30, 40 40)";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:LineString>" +
				  "<gml:posList>30 10 10 30 40 40</gml:posList>" +
				"</gml:LineString>";
		assertEquals(expected, actual);
	}

    @Test
    public void testLinstringDom() throws ParseException, IOException, TransformerException, SAXException {
		String wkt = "LINESTRING (30 10, 10 30, 40 40)";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:LineString", root.getTagName());

		Element posList = getFirstElementChild(root);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("30 10 10 30 40 40", posList.getTextContent());
	}

    @Test
    public void testMultiLinestringString() throws ParseException, IOException {
		String wkt = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:MultiLineString>" +
				  "<gml:lineStringMember>" +
				    "<gml:LineString srsDimension=\"2\">" +
				      "<gml:posList>10 10 20 20 10 40</gml:posList>" +
				    "</gml:LineString>" +
				  "</gml:lineStringMember>" +
				  "<gml:lineStringMember>" +
				    "<gml:LineString srsDimension=\"2\">" +
				      "<gml:posList>40 40 30 30 40 20 30 10</gml:posList>" +
				    "</gml:LineString>" +
				  "</gml:lineStringMember>" +
				"</gml:MultiLineString>";
		assertEquals(expected, actual);
	}

    @Test
    public void testMultiLinstringDom() throws ParseException, IOException, TransformerException, SAXException {
		String wkt = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:MultiLineString", root.getTagName());

		Element member = getFirstElementChild(root);
		assertEquals("gml:lineStringMember", member.getTagName());
		Element lineString = getFirstElementChild(member);
		assertEquals("gml:LineString", lineString.getTagName());
		Element posList = getFirstElementChild(lineString);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("10 10 20 20 10 40", posList.getTextContent());

		member = getNextElementSibling(member);
		assertEquals("gml:lineStringMember", member.getTagName());
		lineString = getFirstElementChild(member);
		assertEquals("gml:LineString", lineString.getTagName());
		posList = getFirstElementChild(lineString);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("40 40 30 30 40 20 30 10", posList.getTextContent());
	}

    @Test
    public void testPolygonString() throws ParseException, IOException {
		String wkt = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:Polygon>" +
				  "<gml:exterior>" +
				    "<gml:LinearRing srsDimension=\"2\">" +
				      "<gml:posList>35 10 45 45 15 40 10 20 35 10</gml:posList>" +
				    "</gml:LinearRing>" +
				  "</gml:exterior>" +
				  "<gml:interior>" +
				    "<gml:LinearRing srsDimension=\"2\">" +
				      "<gml:posList>20 30 35 35 30 20 20 30</gml:posList>" +
				    "</gml:LinearRing>" +
				  "</gml:interior>" +
				"</gml:Polygon>";
		assertEquals(expected, actual);
	}

    @Test
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
		assertEquals("35 10 45 45 15 40 10 20 35 10", posList.getTextContent());

		Element interior = getNextElementSibling(exterior);
		assertEquals("gml:interior", interior.getTagName());
		linearRing = getFirstElementChild(interior);
		assertEquals("gml:LinearRing", linearRing.getTagName());
		posList = getFirstElementChild(linearRing);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("20 30 35 35 30 20 20 30", posList.getTextContent());
	}

    @Test
    public void testMultiPolygonString() throws ParseException, IOException, MismatchedDimensionException, FactoryException, TransformException {
		String wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:MultiPolygon>" +
				  "<gml:polygonMember>" +
				    "<gml:Polygon srsDimension=\"2\">" +
				      "<gml:exterior>" +
				        "<gml:LinearRing srsDimension=\"2\">" +
				          "<gml:posList>40 40 20 45 45 30 40 40</gml:posList>" +
				        "</gml:LinearRing>" +
				      "</gml:exterior>" +
				    "</gml:Polygon>" +
				  "</gml:polygonMember>" +
				  "<gml:polygonMember>" +
				    "<gml:Polygon srsDimension=\"2\">" +
				      "<gml:exterior>" +
				        "<gml:LinearRing srsDimension=\"2\">" +
				          "<gml:posList>20 35 10 30 10 10 30 5 45 20 20 35</gml:posList>" +
				        "</gml:LinearRing>" +
				      "</gml:exterior>" +
				      "<gml:interior>" +
				        "<gml:LinearRing srsDimension=\"2\">" +
				          "<gml:posList>30 20 20 15 20 25 30 20</gml:posList>" +
				        "</gml:LinearRing>" +
				      "</gml:interior>" +
				    "</gml:Polygon>" +
				  "</gml:polygonMember>" +
				"</gml:MultiPolygon>";
		assertEquals(expected, actual);

	}

    @Test
    public void testMultiPolygonDom() throws ParseException, IOException, TransformerException, SAXException, FactoryException, TransformException {
		String wkt = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))";
		Document gml = WktToGmlTransformUtil.wktToGml3AsDom(wkt);

		Element root = gml.getDocumentElement();
		assertEquals("gml:MultiPolygon", root.getTagName());

		Element member = getFirstElementChild(root);
		assertEquals("gml:polygonMember", member.getTagName());
		Element polygon = getFirstElementChild(member);
		assertEquals("gml:Polygon", polygon.getTagName());
		Element exterior = getFirstElementChild(polygon);
		assertEquals("gml:exterior", exterior.getTagName());
		Element linearRing = getFirstElementChild(exterior);
		assertEquals("gml:LinearRing", linearRing.getTagName());
		Element posList = getFirstElementChild(linearRing);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("40 40 20 45 45 30 40 40", posList.getTextContent());

		member = getNextElementSibling(member);
		assertEquals("gml:polygonMember", member.getTagName());
		polygon = getFirstElementChild(member);
		assertEquals("gml:Polygon", polygon.getTagName());
		exterior = getFirstElementChild(polygon);
		assertEquals("gml:exterior", exterior.getTagName());
		linearRing = getFirstElementChild(exterior);
		assertEquals("gml:LinearRing", linearRing.getTagName());
		posList = getFirstElementChild(linearRing);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("20 35 10 30 10 10 30 5 45 20 20 35", posList.getTextContent());
		Element interior = getNextElementSibling(exterior);
		assertEquals("gml:interior", interior.getTagName());
		linearRing = getFirstElementChild(interior);
		assertEquals("gml:LinearRing", linearRing.getTagName());
		posList = getFirstElementChild(linearRing);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("30 20 20 15 20 25 30 20", posList.getTextContent());
		
	}

    @Test
    public void testMultiGeometryString() throws ParseException, IOException {
		String wkt = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
		String gml = WktToGmlTransformUtil.wktToGml3AsString(wkt);

		String actual = deleteIdAttributes(gml);
		String expected =
				"<gml:MultiGeometry>" +
				  "<gml:geometryMember>" +
				    "<gml:Point srsDimension=\"2\">" +
				      "<gml:pos>40 10</gml:pos>" +
				    "</gml:Point>" +
				  "</gml:geometryMember>" +
				  "<gml:geometryMember>" +
				    "<gml:LineString srsDimension=\"2\">" +
				      "<gml:posList>10 10 20 20 10 40</gml:posList>" +
				    "</gml:LineString>" +
				  "</gml:geometryMember>" +
				  "<gml:geometryMember>" +
				    "<gml:Polygon srsDimension=\"2\">" +
				      "<gml:exterior>" +
				        "<gml:LinearRing srsDimension=\"2\">" +
				          "<gml:posList>40 40 20 45 45 30 40 40</gml:posList>" +
				        "</gml:LinearRing>" +
				      "</gml:exterior>" +
				    "</gml:Polygon>" +
				  "</gml:geometryMember>" +
				"</gml:MultiGeometry>";
		assertEquals(expected, actual);
	}

    @Test
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
		assertEquals("40 10", pos.getTextContent());

		member = getNextElementSibling(member);
		assertEquals("gml:geometryMember", member.getTagName());
		Element lineString = getFirstElementChild(member);
		assertEquals("gml:LineString", lineString.getTagName());
		Element posList = getFirstElementChild(lineString);
		assertEquals("gml:posList", posList.getTagName());
		assertEquals("10 10 20 20 10 40", posList.getTextContent());

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
		assertEquals("40 40 20 45 45 30 40 40", posList.getTextContent());
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

    @Test
    public void testPointString3_2() throws ParseException, IOException {
        String wkt = "POINT(30 10)";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:Point>" +
                  "<gml:pos>30 10</gml:pos>" +
                "</gml:Point>";
        assertEquals(expected, actual);
    }

    @Test
    public void testMultiPointString3_2() throws ParseException, IOException {
        String wkt = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:MultiPoint>" +
                  "<gml:pointMember>" +
                    "<gml:Point srsDimension=\"2\">" +
                      "<gml:pos>10 40</gml:pos>" +
                    "</gml:Point>" +
                  "</gml:pointMember>" +
                  "<gml:pointMember>" +
                    "<gml:Point srsDimension=\"2\">" +
                      "<gml:pos>40 30</gml:pos>" +
                    "</gml:Point>" +
                  "</gml:pointMember>" +
                  "<gml:pointMember>" +
                    "<gml:Point srsDimension=\"2\">" +
                      "<gml:pos>20 20</gml:pos>" +
                    "</gml:Point>" +
                  "</gml:pointMember>" +
                  "<gml:pointMember>" +
                    "<gml:Point srsDimension=\"2\">" +
                      "<gml:pos>30 10</gml:pos>" +
                    "</gml:Point>" +
                  "</gml:pointMember>" +
                "</gml:MultiPoint>";
        assertEquals(expected, actual);
    }

    @Test
    public void testLinestringString3_2() throws ParseException, IOException {
        String wkt = "LINESTRING (30 10, 10 30, 40 40)";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:LineString>" +
                  "<gml:posList>30 10 10 30 40 40</gml:posList>" +
                "</gml:LineString>";
        assertEquals(expected, actual);
    }

    @Test
    public void testMultiLinestringString3_2() throws ParseException, IOException {
        String wkt = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:MultiCurve>" +
                  "<gml:curveMember>" +
                    "<gml:LineString srsDimension=\"2\">" +
                      "<gml:posList>10 10 20 20 10 40</gml:posList>" +
                    "</gml:LineString>" +
                  "</gml:curveMember>" +
                  "<gml:curveMember>" +
                    "<gml:LineString srsDimension=\"2\">" +
                      "<gml:posList>40 40 30 30 40 20 30 10</gml:posList>" +
                    "</gml:LineString>" +
                  "</gml:curveMember>" +
                "</gml:MultiCurve>";
        assertEquals(expected, actual);
    }

    @Test
    public void testPolygonString3_2() throws ParseException, IOException {
        String wkt = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:Polygon>" +
                  "<gml:exterior>" +
                    "<gml:LinearRing>" +
                      "<gml:posList>35 10 45 45 15 40 10 20 35 10</gml:posList>" +
                    "</gml:LinearRing>" +
                  "</gml:exterior>" +
                  "<gml:interior>" +
                    "<gml:LinearRing>" +
                      "<gml:posList>20 30 35 35 30 20 20 30</gml:posList>" +
                    "</gml:LinearRing>" +
                  "</gml:interior>" +
                "</gml:Polygon>";
        assertEquals(expected, actual);
    }

    @Test
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
                          "<gml:posList>40 40 20 45 45 30 40 40</gml:posList>" +
                        "</gml:LinearRing>" +
                      "</gml:exterior>" +
                    "</gml:Polygon>" +
                  "</gml:surfaceMember>" +
                  "<gml:surfaceMember>" +
                    "<gml:Polygon srsDimension=\"2\">" +
                      "<gml:exterior>" +
                        "<gml:LinearRing>" +
                          "<gml:posList>20 35 10 30 10 10 30 5 45 20 20 35</gml:posList>" +
                        "</gml:LinearRing>" +
                      "</gml:exterior>" +
                      "<gml:interior>" +
                        "<gml:LinearRing>" +
                          "<gml:posList>30 20 20 15 20 25 30 20</gml:posList>" +
                        "</gml:LinearRing>" +
                      "</gml:interior>" +
                    "</gml:Polygon>" +
                  "</gml:surfaceMember>" +
                "</gml:MultiSurface>";
        assertEquals(expected, actual);
    }

    @Test
    public void testMultiGeometryString3_2() throws ParseException, IOException {
        String wkt = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
        String gml = WktToGmlTransformUtil.wktToGml3_2AsString(wkt);

        String actual = deleteIdAttributes(gml);
        String expected =
                "<gml:MultiGeometry>" + 
                    "<gml:geometryMember>" + 
                        "<gml:Point srsDimension=\"2\">" + 
                            "<gml:pos>40 10</gml:pos>" + 
                        "</gml:Point>" + 
                    "</gml:geometryMember>" + 
                    "<gml:geometryMember>" + 
                        "<gml:LineString srsDimension=\"2\">" + 
                            "<gml:posList>10 10 20 20 10 40</gml:posList>" + 
                        "</gml:LineString>" + 
                    "</gml:geometryMember>" + 
                    "<gml:geometryMember>" + 
                        "<gml:Polygon srsDimension=\"2\">" + 
                            "<gml:exterior>" + 
                                "<gml:LinearRing>" + 
                                    "<gml:posList>40 40 20 45 45 30 40 40</gml:posList>" + 
                                "</gml:LinearRing>" + 
                            "</gml:exterior>" + 
                        "</gml:Polygon>" + 
                    "</gml:geometryMember>" + 
                "</gml:MultiGeometry>";
        assertEquals(expected, actual);
    }

    @Test
    public void testMultiGeometryDom3_2() throws ParseException, IOException, TransformerException, SAXException {
        String wkt = "GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))";
        Document gml = WktToGmlTransformUtil.wktToGml3_2AsDom(wkt);

        Element root = gml.getDocumentElement();
        assertEquals("gml:MultiGeometry", root.getTagName());
    }
}

