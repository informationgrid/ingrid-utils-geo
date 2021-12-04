package de.ingrid.geo.utils.transformation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.WKTReader2;
import org.locationtech.jts.io.ParseException;

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
		WKTReader2 reader = new WKTReader2();
		org.locationtech.jts.geom.Geometry geometry = reader.read(wkt);

		GeometryJSON geometryJSON = new GeometryJSON();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		geometryJSON.write(geometry, out);
		return out.toString();
	}
}

