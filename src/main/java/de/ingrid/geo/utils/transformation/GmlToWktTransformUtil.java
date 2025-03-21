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
package de.ingrid.geo.utils.transformation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.geotools.xsd.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class GmlToWktTransformUtil extends WktUtil {

    private GmlToWktTransformUtil() {
        // Disable instantiation
    }

    public static String gml3ToWktString(String input) throws SAXException, IOException, ParserConfigurationException {
        String wkt = null;
        if(input != null && !input.isEmpty()) {
            org.geotools.gml3.GMLConfiguration config = new org.geotools.gml3.GMLConfiguration();
            Parser parser = new Parser(config);
            Object wktObj = parser.parse(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            wkt = wktObj.toString();
        }
        return wkt;
    }

    public static String gml3ToWktString(Node node) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(node), new StreamResult(writer));
        return GmlToWktTransformUtil.gml3ToWktString(writer.toString());
    }

    public static String gml3ToWktString(Document doc) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String wkt = null;
        if(doc != null) {
            String namespace = "http://www.opengis.net/gml/3.2";
            String prefix = "gml";
            StringWriter buffer = new StringWriter();
            Element elem = doc.getDocumentElement();
            elem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix, namespace);
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(buffer));
            String str = buffer.toString();
            wkt = gml3ToWktString(str);
        }
        return wkt;
    }

    public static String gml3_2ToWktString(String input) throws SAXException, IOException, ParserConfigurationException {
        String wkt = null;
        if(input != null && !input.isEmpty()) {
            org.geotools.gml3.v3_2.GMLConfiguration config = new org.geotools.gml3.v3_2.GMLConfiguration();
            Parser parser = new Parser(config);
            Object wktObj = parser.parse(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            wkt = wktObj.toString();
        }
        return wkt;
    }

    public static String gml3_2ToWktString(Node node) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(node), new StreamResult(writer));
        return GmlToWktTransformUtil.gml3_2ToWktString(writer.toString());
    }

    public static String gml3_2ToWktString(Document doc) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String wkt = null;
        if(doc != null) {
            String namespace = "http://www.opengis.net/gml/3.2";
            String prefix = "gml";
            StringWriter buffer = new StringWriter();
            Element elem = doc.getDocumentElement();
            elem.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + prefix, namespace);
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(buffer));
            String str = buffer.toString();
            wkt = gml3_2ToWktString(str);
        }
        return wkt;
    }
}


