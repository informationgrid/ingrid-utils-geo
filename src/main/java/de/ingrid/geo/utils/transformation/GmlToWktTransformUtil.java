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
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.geotools.xml.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public final class GmlToWktTransformUtil {

    private GmlToWktTransformUtil() {
        // Disable instantiation
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


