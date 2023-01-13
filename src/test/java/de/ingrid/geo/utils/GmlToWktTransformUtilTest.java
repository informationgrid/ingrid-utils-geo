/*-
 * **************************************************-
 * ingrid-utils-geo
 * ==================================================
 * Copyright (C) 2014 - 2023 wemove digital solutions GmbH
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.ingrid.geo.utils.transformation.GmlToWktTransformUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GmlToWktTransformUtilTest {

    @Test
    void testGml3ToWktPointString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:Point xmlns:gml=\"http://www.opengis.net/gml\" gml:id=\"Point_ID_7d8bfe21-328a-400e-b38d-549f2cc7cef8\" srsDimension=\"2\"><gml:pos>40 10</gml:pos></gml:Point>";
        String wkt = GmlToWktTransformUtil.gml3ToWktString(gml);
        assertEquals("POINT (40 10)", wkt);
    }

    @Test
    void testGml3ToWktPointNode() throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        String gml = "<gml:Point xmlns:gml=\"http://www.opengis.net/gml\" gml:id=\"Point_ID_7d8bfe21-328a-400e-b38d-549f2cc7cef8\" srsDimension=\"2\"><gml:pos>40 10</gml:pos></gml:Point>";
        Node node = stringToNode(gml);
        String wkt = GmlToWktTransformUtil.gml3ToWktString(node);
        assertEquals("POINT (40 10)", wkt);
    }

    @Test
    void testGml3_2ToWktPointString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:Point xmlns:gml=\"http://www.opengis.net/gml/3.2\" gml:id=\"Point_ID_7d8bfe21-328a-400e-b38d-549f2cc7cef8\" srsDimension=\"2\"><gml:pos>40 10</gml:pos></gml:Point>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(gml);
        assertEquals("POINT (40 10)", wkt);
    }

    @Test
    void testGml3_2ToWktPointNode() throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        String gml = "<gml:Point xmlns:gml=\"http://www.opengis.net/gml/3.2\" gml:id=\"Point_ID_7d8bfe21-328a-400e-b38d-549f2cc7cef8\" srsDimension=\"2\"><gml:pos>40 10</gml:pos></gml:Point>";
        Node node = stringToNode(gml);
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(node);
        assertEquals("POINT (40 10)", wkt);
    }

    @Test
    void testGml3_2ToWktPointDoc() throws SAXException, IOException, ParserConfigurationException, TransformerException {
        String gml = "<gml:Point gml:id=\"Point_ID_7d8bfe21-328a-400e-b38d-549f2cc7cef8\" srsDimension=\"2\"><gml:pos>40 10</gml:pos></gml:Point>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(stringToDoc(gml));
        assertEquals("POINT (40 10)", wkt);
    }

    @Test
    void testGml3ToWktLineStringString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:LineString xmlns:gml=\"http://www.opengis.net/gml\" gml:id=\"LineString_ID_7412ae7c-30d6-4d7f-8939-a89f9d480907\" srsDimension=\"2\"><gml:posList>10 10 20 20 10 40</gml:posList></gml:LineString>";
        String wkt = GmlToWktTransformUtil.gml3ToWktString(gml);
        assertEquals("LINESTRING (10 10, 20 20, 10 40)", wkt);
    }

    @Test
    void testGml3_2ToWktLineStringString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:LineString xmlns:gml=\"http://www.opengis.net/gml/3.2\" gml:id=\"LineString_ID_7412ae7c-30d6-4d7f-8939-a89f9d480907\" srsDimension=\"2\"><gml:posList>10 10 20 20 10 40</gml:posList></gml:LineString>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(gml);
        assertEquals("LINESTRING (10 10, 20 20, 10 40)", wkt);
    }

    @Test
    void testGml3_2ToWktLineStringDoc() throws SAXException, IOException, ParserConfigurationException, TransformerException {
        String gml = "<gml:LineString gml:id=\"LineString_ID_7412ae7c-30d6-4d7f-8939-a89f9d480907\" srsDimension=\"2\"><gml:posList>10 10 20 20 10 40</gml:posList></gml:LineString>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(stringToDoc(gml));
        assertEquals("LINESTRING (10 10, 20 20, 10 40)", wkt);
    }

    @Test
    void testGml3ToWktPolygonString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:Polygon xmlns:gml=\"http://www.opengis.net/gml\" gml:id=\"Polygon_ID_f22a8f11-2300-4281-9d66-ecc09a101533\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>35.0 10.0 45.0 45.0 15.0 40.0 10.0 20.0 35.0 10.0</gml:posList></gml:LinearRing></gml:exterior><gml:interior><gml:LinearRing><gml:posList>20.0 30.0 35.0 35.0 30.0 20.0 20.0 30.0</gml:posList></gml:LinearRing></gml:interior></gml:Polygon>";
        String wkt = GmlToWktTransformUtil.gml3ToWktString(gml);
        assertEquals("POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))", wkt);
    }

    @Test
    void testGml3_2ToWktPolygonString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:Polygon xmlns:gml=\"http://www.opengis.net/gml/3.2\" gml:id=\"Polygon_ID_f22a8f11-2300-4281-9d66-ecc09a101533\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>35.0 10.0 45.0 45.0 15.0 40.0 10.0 20.0 35.0 10.0</gml:posList></gml:LinearRing></gml:exterior><gml:interior><gml:LinearRing><gml:posList>20.0 30.0 35.0 35.0 30.0 20.0 20.0 30.0</gml:posList></gml:LinearRing></gml:interior></gml:Polygon>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(gml);
        assertEquals("POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))", wkt);
    }

    @Test
    void testGml3_2ToWktPolygonDoc() throws SAXException, IOException, ParserConfigurationException, TransformerException {
        String gml = "<gml:Polygon gml:id=\"Polygon_ID_f22a8f11-2300-4281-9d66-ecc09a101533\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>35.0 10.0 45.0 45.0 15.0 40.0 10.0 20.0 35.0 10.0</gml:posList></gml:LinearRing></gml:exterior><gml:interior><gml:LinearRing><gml:posList>20.0 30.0 35.0 35.0 30.0 20.0 20.0 30.0</gml:posList></gml:LinearRing></gml:interior></gml:Polygon>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(stringToDoc(gml));
        assertEquals("POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))", wkt);
    }

    @Test
    void testGml3ToWktMultiPointString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:MultiPoint xmlns:gml=\"http://www.opengis.net/gml\" gml:id=\"MultiPoint_ID_51fa7104-d537-4c8f-8994-cfd582202487\" srsDimension=\"2\"><gml:pointMember><gml:Point gml:id=\"Point_ID_f0c29a56-1b9f-42ce-906c-b80a528b7841\" srsDimension=\"2\"><gml:pos>10.0 40.0</gml:pos></gml:Point></gml:pointMember><gml:pointMember><gml:Point gml:id=\"Point_ID_b526e421-dce0-4352-a0e1-d25fcf6f84cb\" srsDimension=\"2\"><gml:pos>40.0 30.0</gml:pos></gml:Point></gml:pointMember><gml:pointMember><gml:Point gml:id=\"Point_ID_41373d68-b40a-4949-87ea-69cc27b8ce8e\" srsDimension=\"2\"><gml:pos>20.0 20.0</gml:pos></gml:Point></gml:pointMember><gml:pointMember><gml:Point gml:id=\"Point_ID_380b72df-90ce-43cc-91b3-8250152e7cec\" srsDimension=\"2\"><gml:pos>30.0 10.0</gml:pos></gml:Point></gml:pointMember></gml:MultiPoint>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(gml);
        assertEquals("MULTIPOINT ((10 40), (40 30), (20 20), (30 10))", wkt);
    }

    @Test
    void testGml3_2ToWktMultiPointString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:MultiPoint xmlns:gml=\"http://www.opengis.net/gml/3.2\" gml:id=\"MultiPoint_ID_51fa7104-d537-4c8f-8994-cfd582202487\" srsDimension=\"2\"><gml:pointMember><gml:Point gml:id=\"Point_ID_f0c29a56-1b9f-42ce-906c-b80a528b7841\" srsDimension=\"2\"><gml:pos>10.0 40.0</gml:pos></gml:Point></gml:pointMember><gml:pointMember><gml:Point gml:id=\"Point_ID_b526e421-dce0-4352-a0e1-d25fcf6f84cb\" srsDimension=\"2\"><gml:pos>40.0 30.0</gml:pos></gml:Point></gml:pointMember><gml:pointMember><gml:Point gml:id=\"Point_ID_41373d68-b40a-4949-87ea-69cc27b8ce8e\" srsDimension=\"2\"><gml:pos>20.0 20.0</gml:pos></gml:Point></gml:pointMember><gml:pointMember><gml:Point gml:id=\"Point_ID_380b72df-90ce-43cc-91b3-8250152e7cec\" srsDimension=\"2\"><gml:pos>30.0 10.0</gml:pos></gml:Point></gml:pointMember></gml:MultiPoint>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(gml);
        assertEquals("MULTIPOINT ((10 40), (40 30), (20 20), (30 10))", wkt);
    }

    @Test
    void testGml3_2ToWktMultiPointDoc() throws SAXException, IOException, ParserConfigurationException, TransformerException {
        String gml = "<gml:MultiPoint gml:id=\"MultiPoint_ID_51fa7104-d537-4c8f-8994-cfd582202487\" srsDimension=\"2\"><gml:pointMember><gml:Point gml:id=\"Point_ID_f0c29a56-1b9f-42ce-906c-b80a528b7841\" srsDimension=\"2\"><gml:pos>10.0 40.0</gml:pos></gml:Point></gml:pointMember><gml:pointMember><gml:Point gml:id=\"Point_ID_b526e421-dce0-4352-a0e1-d25fcf6f84cb\" srsDimension=\"2\"><gml:pos>40.0 30.0</gml:pos></gml:Point></gml:pointMember><gml:pointMember><gml:Point gml:id=\"Point_ID_41373d68-b40a-4949-87ea-69cc27b8ce8e\" srsDimension=\"2\"><gml:pos>20.0 20.0</gml:pos></gml:Point></gml:pointMember><gml:pointMember><gml:Point gml:id=\"Point_ID_380b72df-90ce-43cc-91b3-8250152e7cec\" srsDimension=\"2\"><gml:pos>30.0 10.0</gml:pos></gml:Point></gml:pointMember></gml:MultiPoint>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(stringToDoc(gml));
        assertEquals("MULTIPOINT ((10 40), (40 30), (20 20), (30 10))", wkt);
    }

    @Test
    void testGml3ToWktMultiLineStringString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:MultiCurve xmlns:gml=\"http://www.opengis.net/gml\" gml:id=\"MultiCurve_ID_22cd67d7-e93a-46d9-ac3a-b70fb9c8cef5\" srsDimension=\"2\"><gml:curveMember><gml:LineString gml:id=\"LineString_ID_d74fdf2c-d39d-4e34-b9b7-8a0bf8d61a99\" srsDimension=\"2\"><gml:posList>10.0 10.0 20.0 20.0 10.0 40.0</gml:posList></gml:LineString></gml:curveMember><gml:curveMember><gml:LineString gml:id=\"LineString_ID_a703de7d-a8af-4517-86f1-862cd76e97b1\" srsDimension=\"2\"><gml:posList>40.0 40.0 30.0 30.0 40.0 20.0 30.0 10.0</gml:posList></gml:LineString></gml:curveMember></gml:MultiCurve>";
        String wkt = GmlToWktTransformUtil.gml3ToWktString(gml);
        assertEquals("MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))", wkt);
    }

    @Test
    void testGml3_2ToWktMultiLineStringString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:MultiCurve xmlns:gml=\"http://www.opengis.net/gml/3.2\" gml:id=\"MultiCurve_ID_22cd67d7-e93a-46d9-ac3a-b70fb9c8cef5\" srsDimension=\"2\"><gml:curveMember><gml:LineString gml:id=\"LineString_ID_d74fdf2c-d39d-4e34-b9b7-8a0bf8d61a99\" srsDimension=\"2\"><gml:posList>10.0 10.0 20.0 20.0 10.0 40.0</gml:posList></gml:LineString></gml:curveMember><gml:curveMember><gml:LineString gml:id=\"LineString_ID_a703de7d-a8af-4517-86f1-862cd76e97b1\" srsDimension=\"2\"><gml:posList>40.0 40.0 30.0 30.0 40.0 20.0 30.0 10.0</gml:posList></gml:LineString></gml:curveMember></gml:MultiCurve>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(gml);
        assertEquals("MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))", wkt);
    }

    @Test
    void testGml3_2ToWktMultiLineStringDoc() throws SAXException, IOException, ParserConfigurationException, TransformerException {
        String gml = "<gml:MultiCurve gml:id=\"MultiCurve_ID_22cd67d7-e93a-46d9-ac3a-b70fb9c8cef5\" srsDimension=\"2\"><gml:curveMember><gml:LineString gml:id=\"LineString_ID_d74fdf2c-d39d-4e34-b9b7-8a0bf8d61a99\" srsDimension=\"2\"><gml:posList>10.0 10.0 20.0 20.0 10.0 40.0</gml:posList></gml:LineString></gml:curveMember><gml:curveMember><gml:LineString gml:id=\"LineString_ID_a703de7d-a8af-4517-86f1-862cd76e97b1\" srsDimension=\"2\"><gml:posList>40.0 40.0 30.0 30.0 40.0 20.0 30.0 10.0</gml:posList></gml:LineString></gml:curveMember></gml:MultiCurve>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(stringToDoc(gml));
        assertEquals("MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))", wkt);
    }

    @Test
    void testGml3ToWktMultiPolygonString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:MultiSurface xmlns:gml=\"http://www.opengis.net/gml\" gml:id=\"MultiSurface_ID_5384207c-44f7-4ba8-9af4-7cb5514fcbfb\" srsDimension=\"2\"><gml:surfaceMember><gml:Polygon gml:id=\"Polygon_ID_95000c23-8e48-429a-ba7f-86baa6925b2e\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>40.0 40.0 20.0 45.0 45.0 30.0 40.0 40.0</gml:posList></gml:LinearRing></gml:exterior></gml:Polygon></gml:surfaceMember><gml:surfaceMember><gml:Polygon gml:id=\"Polygon_ID_b2a228fb-4cba-4edb-a223-a408d9366302\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>20.0 35.0 10.0 30.0 10.0 10.0 30.0 5.0 45.0 20.0 20.0 35.0</gml:posList></gml:LinearRing></gml:exterior><gml:interior><gml:LinearRing><gml:posList>30.0 20.0 20.0 15.0 20.0 25.0 30.0 20.0</gml:posList></gml:LinearRing></gml:interior></gml:Polygon></gml:surfaceMember></gml:MultiSurface>";
        String wkt = GmlToWktTransformUtil.gml3ToWktString(gml);
        assertEquals("MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))", wkt);
    }

    @Test
    void testGml3_2ToWktMultiPolygonString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:MultiSurface xmlns:gml=\"http://www.opengis.net/gml/3.2\" gml:id=\"MultiSurface_ID_5384207c-44f7-4ba8-9af4-7cb5514fcbfb\" srsDimension=\"2\"><gml:surfaceMember><gml:Polygon gml:id=\"Polygon_ID_95000c23-8e48-429a-ba7f-86baa6925b2e\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>40.0 40.0 20.0 45.0 45.0 30.0 40.0 40.0</gml:posList></gml:LinearRing></gml:exterior></gml:Polygon></gml:surfaceMember><gml:surfaceMember><gml:Polygon gml:id=\"Polygon_ID_b2a228fb-4cba-4edb-a223-a408d9366302\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>20.0 35.0 10.0 30.0 10.0 10.0 30.0 5.0 45.0 20.0 20.0 35.0</gml:posList></gml:LinearRing></gml:exterior><gml:interior><gml:LinearRing><gml:posList>30.0 20.0 20.0 15.0 20.0 25.0 30.0 20.0</gml:posList></gml:LinearRing></gml:interior></gml:Polygon></gml:surfaceMember></gml:MultiSurface>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(gml);
        assertEquals("MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))", wkt);
    }

    @Test
    void testGml3_2ToWktMultiPolygonDoc() throws SAXException, IOException, ParserConfigurationException, TransformerException {
        String gml = "<gml:MultiSurface gml:id=\"MultiSurface_ID_5384207c-44f7-4ba8-9af4-7cb5514fcbfb\" srsDimension=\"2\"><gml:surfaceMember><gml:Polygon gml:id=\"Polygon_ID_95000c23-8e48-429a-ba7f-86baa6925b2e\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>40.0 40.0 20.0 45.0 45.0 30.0 40.0 40.0</gml:posList></gml:LinearRing></gml:exterior></gml:Polygon></gml:surfaceMember><gml:surfaceMember><gml:Polygon gml:id=\"Polygon_ID_b2a228fb-4cba-4edb-a223-a408d9366302\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>20.0 35.0 10.0 30.0 10.0 10.0 30.0 5.0 45.0 20.0 20.0 35.0</gml:posList></gml:LinearRing></gml:exterior><gml:interior><gml:LinearRing><gml:posList>30.0 20.0 20.0 15.0 20.0 25.0 30.0 20.0</gml:posList></gml:LinearRing></gml:interior></gml:Polygon></gml:surfaceMember></gml:MultiSurface>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(stringToDoc(gml));
        assertEquals("MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))", wkt);
    }

    @Test
    void testGml3ToWktMultiGeometryString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:MultiGeometry xmlns:gml=\"http://www.opengis.net/gml\" gml:id=\"MultiGeometry_ID_e086c17f-e2e1-46d5-a9c8-4a8ac14fbf83\" srsDimension=\"2\"><gml:geometryMember><gml:Point gml:id=\"Point_ID_7d8bfe21-328a-400e-b38d-549f2cc7cef8\" srsDimension=\"2\"><gml:pos>40 10</gml:pos></gml:Point></gml:geometryMember><gml:geometryMember><gml:LineString gml:id=\"LineString_ID_7412ae7c-30d6-4d7f-8939-a89f9d480907\" srsDimension=\"2\"><gml:posList>10 10 20 20 10 40</gml:posList></gml:LineString></gml:geometryMember><gml:geometryMember><gml:Polygon gml:id=\"Polygon_ID_7c816d63-ccd7-4863-b3b1-a5ef7cd5ab0f\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>40 40 20 45 45 30 40 40</gml:posList></gml:LinearRing></gml:exterior></gml:Polygon></gml:geometryMember></gml:MultiGeometry>";
        String wkt = GmlToWktTransformUtil.gml3ToWktString(gml);
        assertEquals("GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))", wkt);
    }

    @Test
    void testGml3_2ToWktMultiGeometryString() throws SAXException, IOException, ParserConfigurationException {
        String gml = "<gml:MultiGeometry xmlns:gml=\"http://www.opengis.net/gml/3.2\" gml:id=\"MultiGeometry_ID_e086c17f-e2e1-46d5-a9c8-4a8ac14fbf83\" srsDimension=\"2\"><gml:geometryMember><gml:Point gml:id=\"Point_ID_7d8bfe21-328a-400e-b38d-549f2cc7cef8\" srsDimension=\"2\"><gml:pos>40 10</gml:pos></gml:Point></gml:geometryMember><gml:geometryMember><gml:LineString gml:id=\"LineString_ID_7412ae7c-30d6-4d7f-8939-a89f9d480907\" srsDimension=\"2\"><gml:posList>10 10 20 20 10 40</gml:posList></gml:LineString></gml:geometryMember><gml:geometryMember><gml:Polygon gml:id=\"Polygon_ID_7c816d63-ccd7-4863-b3b1-a5ef7cd5ab0f\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>40 40 20 45 45 30 40 40</gml:posList></gml:LinearRing></gml:exterior></gml:Polygon></gml:geometryMember></gml:MultiGeometry>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(gml);
        assertEquals("GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))", wkt);
    }

    @Test
    void testGml3_2ToWktMultiGeometryDoc() throws SAXException, IOException, ParserConfigurationException, TransformerException {
        String gml = "<gml:MultiGeometry gml:id=\"MultiGeometry_ID_e086c17f-e2e1-46d5-a9c8-4a8ac14fbf83\" srsDimension=\"2\"><gml:geometryMember><gml:Point gml:id=\"Point_ID_7d8bfe21-328a-400e-b38d-549f2cc7cef8\" srsDimension=\"2\"><gml:pos>40 10</gml:pos></gml:Point></gml:geometryMember><gml:geometryMember><gml:LineString gml:id=\"LineString_ID_7412ae7c-30d6-4d7f-8939-a89f9d480907\" srsDimension=\"2\"><gml:posList>10 10 20 20 10 40</gml:posList></gml:LineString></gml:geometryMember><gml:geometryMember><gml:Polygon gml:id=\"Polygon_ID_7c816d63-ccd7-4863-b3b1-a5ef7cd5ab0f\" srsDimension=\"2\"><gml:exterior><gml:LinearRing><gml:posList>40 40 20 45 45 30 40 40</gml:posList></gml:LinearRing></gml:exterior></gml:Polygon></gml:geometryMember></gml:MultiGeometry>";
        String wkt = GmlToWktTransformUtil.gml3_2ToWktString(stringToDoc(gml));
        assertEquals("GEOMETRYCOLLECTION (POINT (40 10), LINESTRING (10 10, 20 20, 10 40), POLYGON ((40 40, 20 45, 45 30, 40 40)))", wkt);
    }

    private Document stringToDoc(String gml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(gml));

        Document doc = db.parse(is);
        return doc;
    }
    
    private Node stringToNode(String gml) throws SAXException, IOException, ParserConfigurationException {
        Element node =  DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse(new ByteArrayInputStream(gml.getBytes()))
            .getDocumentElement();
        return node;
    }
}

