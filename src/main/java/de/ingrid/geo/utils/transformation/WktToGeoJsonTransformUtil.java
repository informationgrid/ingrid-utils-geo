package de.ingrid.geo.utils.transformation;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.geojson.geom.GeometryJSON;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class WktToGeoJsonTransformUtil {

	private WktToGeoJsonTransformUtil() {
		// Disable instantiation
	}

	/**
	 * Converts the given geometry from WKT to GeoJSON.
	 *
	 * @param wkt geometry to convert as WKT
	 * @return the given geometry converted to GeoJSON
	 *
	 * @throws ParseException if the WKT String cannot be parsed
	 * @throws IOException if there is an error constructing the GeoJSON object
	 */
	public static String wktToKml(String wkt) throws ParseException, IOException {
		WKTReader reader = new WKTReader();
		Geometry geometry = reader.read(wkt);

		GeometryJSON geometryJSON = new GeometryJSON();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		geometryJSON.write(geometry, out);
		return out.toString();
	}
}

