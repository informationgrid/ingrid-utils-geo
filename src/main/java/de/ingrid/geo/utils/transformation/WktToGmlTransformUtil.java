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
package de.ingrid.geo.utils.transformation;

import java.io.IOException;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;

import org.geotools.geometry.jts.WKTReader2;
import org.geotools.gml3.GML;
import org.geotools.gml3.GMLConfiguration;
import org.geotools.xsd.Configuration;
import org.geotools.xsd.Encoder;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class WktToGmlTransformUtil extends WktUtil {

	private WktToGmlTransformUtil() {
		// Disable instantiation
	}

	/**
	 * Converts the given geometry from WKT to GML3.
	 *
	 * @param wkt geometry to convert as WKT
	 * @return the given geometry converted to GML3 as a String object
	 *
	 * @throws ParseException if the WKT String cannot be parsed
	 * @throws IOException if there is an error constructing the GML string
	 */
	public static String wktToGml3AsString(String wkt) throws ParseException, IOException {
		try {
			String gml = wktToGml3(wkt, String.class);

			// Remove namespace declarations
			int idx0 = gml.indexOf(' ');
			int idx1 = gml.indexOf('>');
			String str = gml.substring(idx0, idx1);
			gml = gml.replace(str, "");

			// Add id Attributes
			return gml.replaceAll("<gml:(Point|MultiPoint|LineString|MultiLineString|Polygon|MultiPolygon|MultiGeometry)\\b", "<gml:$1 gml:id=\"$1_ID_" + UUID.randomUUID() + "\"");
		} catch (TransformerException|SAXException e) {
			// Should never happen
			throw new RuntimeException("Unexpected error while converting WKT to GML3", e);
		}
	}


	/**
	 * Converts the given geometry from WKT to GML3.
	 *
	 * @param wkt geometry to convert as WKT
	 * @return the given geometry converted to GML3 as a DOM Document
	 *
	 * @throws ParseException if the WKT String cannot be parsed
	 * @throws IOException if there is an error constructing the GML DOM document
	 * @throws TransformerException if there is an error constructing the GML DOM document
	 * @throws SAXException if there is an error constructing the GML DOM document
	 */
	public static Document wktToGml3AsDom(String wkt) throws ParseException, IOException, TransformerException, SAXException	{
		Document doc = wktToGml3(wkt, Document.class);
		String[] tagNames = {"Point", "MultiPoint", "LineString", "MultiLineString", "Polygon", "MultiPolygon", "MultiGeometry"};
		for(String tagName: tagNames) {
			NodeList tags = doc.getElementsByTagName("gml:" + tagName);
			for(int i=0; i<tags.getLength(); i++) {
				Element element = (Element) tags.item(i);
				element.setAttribute("gml:id", tagName + "_ID_" + UUID.randomUUID());
			}
		}

		return doc;
	}

    public static Element wktToGml3AsElement(String wkt) throws ParseException, IOException, TransformerException, SAXException    {
        Document doc = wktToGml3AsDom(wkt);
        if(doc != null) {
            Element elem = doc.getDocumentElement();
            return elem;
        } else {
            throw new IllegalArgumentException("Cannot convert: " + wkt);
        }
    }

	private static <T> T wktToGml3(String wkt, Class<T> klasse) throws ParseException, IOException, TransformerException, SAXException {
		// Adapted from https://gis.stackexchange.com/a/244875
		WKTReader reader = new WKTReader();
		Geometry geometry = reader.read(wkt);

		QName qName;
		if (geometry instanceof Point) {
			qName = GML.Point;
		} else if (geometry instanceof MultiPoint) {
			qName = GML.MultiPoint;
		} else if (geometry instanceof LineString) {
			qName = GML.LineString;
		} else if (geometry instanceof MultiLineString) {
			qName = GML.MultiLineString;
		} else if (geometry instanceof Polygon) {
			qName = GML.Polygon;
		} else if (geometry instanceof MultiPolygon) {
			qName = GML.MultiPolygon;
		} else if (geometry instanceof GeometryCollection) {
			qName = GML.MultiGeometry;
		} else {
			throw new IllegalArgumentException("Geometry type is currently not supported: " + geometry.getGeometryType());
		}

		GMLConfiguration config = new GMLConfiguration();
		Encoder encoder = new Encoder(config);
		encoder.setOmitXMLDeclaration(true);

		if (klasse == String.class) {
			@SuppressWarnings("unchecked")
			T t = (T) encoder.encodeAsString(geometry, qName);
			return t;
		} else if(klasse == Document.class) {
			@SuppressWarnings("unchecked")
			T t = (T) encoder.encodeAsDOM(geometry, qName);
			return t;
		} else {
			throw new IllegalArgumentException("Cannot convert to type: " + klasse.getName());
		}
	}

    public static String wktToGml3_2AsString(String wkt) throws ParseException, IOException {
        try {
            String gml = wktToGml3_2(wkt, String.class);

            // Remove namespace declarations
            int idx0 = gml.indexOf(' ');
            int idx1 = gml.indexOf('>');
            String str = gml.substring(idx0, idx1);
            gml = gml.replace(str, "");

            // Add id Attributes
            return gml.replaceAll("<gml:(Point|MultiPoint|LineString|MultiLineString|Polygon|MultiPolygon|MultiGeometry)\\b", "<gml:$1 gml:id=\"$1_ID_" + UUID.randomUUID() + "\"");
        } catch (TransformerException|SAXException e) {
            // Should never happen
            throw new RuntimeException("Unexpected error while converting WKT to GML3", e);
        }
    }

    public static Document wktToGml3_2AsDom(String wkt) throws ParseException, IOException, TransformerException, SAXException    {
        Document doc = wktToGml3_2(wkt, Document.class);
        String[] tagNames = {"Point", "MultiPoint", "LineString", "MultiCurve", "Polygon", "MultiSurface", "MultiGeometry"};
        for(String tagName: tagNames) {
            NodeList tags = doc.getElementsByTagName("gml:" + tagName);
            for(int i=0; i<tags.getLength(); i++) {
                Element element = (Element) tags.item(i);
                element.setAttribute("gml:id", tagName + "_ID_" + UUID.randomUUID());
            }
        }

        return doc;
    }

    public static Element wktToGml3_2AsElement(String wkt) throws ParseException, IOException, TransformerException, SAXException    {
        Document doc = wktToGml3_2AsDom(wkt);
        if(doc != null) {
            Element elem = doc.getDocumentElement();
            return elem;
        } else {
            throw new IllegalArgumentException("Cannot convert: " + wkt);
        }
    }

    private static <T> T wktToGml3_2(String wkt, Class<T> klasse) throws ParseException, IOException, TransformerException, SAXException {
        // Adapted from https://gis.stackexchange.com/a/244875
        WKTReader2 reader = new WKTReader2();
        Geometry geometry = reader.read(wkt);

        QName qName;
        if (geometry instanceof Point) {
            qName = org.geotools.gml3.v3_2.GML.Point;
        } else if (geometry instanceof MultiPoint) {
            qName = org.geotools.gml3.v3_2.GML.MultiPoint;
        } else if (geometry instanceof LineString) {
            qName = org.geotools.gml3.v3_2.GML.LineString;
        } else if (geometry instanceof MultiLineString) {
            qName = org.geotools.gml3.v3_2.GML.MultiCurve;
        } else if (geometry instanceof Polygon) {
            qName = org.geotools.gml3.v3_2.GML.Polygon;
        } else if (geometry instanceof MultiPolygon) {
            qName = org.geotools.gml3.v3_2.GML.MultiSurface;
        } else if (geometry instanceof GeometryCollection) {
            qName = org.geotools.gml3.v3_2.GML.MultiGeometry;
        } else {
            throw new IllegalArgumentException("Geometry type is currently not supported: " + geometry.getGeometryType());
        }

        Configuration config = new org.geotools.gml3.v3_2.GMLConfiguration();
        Encoder encoder = new Encoder(config);
        encoder.setOmitXMLDeclaration(true);

        if (klasse == String.class) {
            @SuppressWarnings("unchecked")
            T t = (T) encoder.encodeAsString(geometry, qName);
            return t;
        } else if(klasse == Document.class) {
            @SuppressWarnings("unchecked")
            T t = (T) encoder.encodeAsDOM(geometry, qName);
            return t;
        } else {
            throw new IllegalArgumentException("Cannot convert to type: " + klasse.getName());
        }
    }
}

