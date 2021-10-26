package de.ingrid.geo.utils.transformation;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.gml3.GML;
import org.geotools.gml3.GMLConfiguration;
import org.geotools.xml.Encoder;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.UUID;

public final class WktToGmlTransformUtil {

	private WktToGmlTransformUtil() {
		// Disable instantiation
	}

	/**
	 * Converts the given geometry from WKT to GML3.
	 *
	 * @param wkt geometry to convert as WKT
	 * @return the given geometry converted to GML3
	 *
	 * @throws ParseException if the WKT String cannot be parsed
	 * @throws IOException if there is an error constructing the GML string
	 */
	public static String wktToGml3(String wkt) throws ParseException, IOException {
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

		String gml =  encoder.encodeAsString(geometry, qName);

		// Remove namespace declarations
		int idx0 = gml.indexOf(' ');
		int idx1 = gml.indexOf('>');
		String str = gml.substring(idx0, idx1);
		gml = gml.replace(str, "");

		// Add id Attributes
		return gml.replaceAll("<gml:(Point|MultiPoint|LineString|MultiLineString|Polygon|MultiPolygon|MultiGeometry)\\b", "<gml:$1 gml:id=\"$1_ID_" + UUID.randomUUID() + "\"");
	}
}

