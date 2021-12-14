package de.ingrid.geo.utils.transformation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.referencing.CRS;
import org.locationtech.jts.io.ParseException;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public final class WktToGeoJsonTransformUtil extends WktUtil {

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
	public static String wktToGeoJson(String wkt) throws ParseException, IOException {
		WKTReader2 reader = new WKTReader2();
		org.locationtech.jts.geom.Geometry geometry = reader.read(wkt);

		GeometryJSON geometryJSON = new GeometryJSON();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		geometryJSON.write(geometry, out);
		return out.toString();
	}

	public static String wktToGeoJsonTransform(String wkt, String dstEpsg) throws ParseException, IOException, MismatchedDimensionException, NoSuchAuthorityCodeException, FactoryException, TransformException {
	    return wktToGeoJsonTransform(wkt, "4326", dstEpsg);
	}

    public static String wktToGeoJsonTransform(String wkt, String srcEpsg, String dstEpsg) throws ParseException, IOException, NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException {
        WKTReader2 reader = new WKTReader2();
        org.locationtech.jts.geom.Geometry geometry = reader.read(wkt);

        CoordinateReferenceSystem sourceCRS = CRS.parseWKT(CoordTransformUtil.getInstance().getWKTString(CoordTransformUtil.getInstance().getCoordTypeByEPSGCode(srcEpsg)));;
        CoordinateReferenceSystem targetCRS = CRS.parseWKT(CoordTransformUtil.getInstance().getWKTString(CoordTransformUtil.getInstance().getCoordTypeByEPSGCode(dstEpsg)));

        MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, true);
        GeometryJSON geometryJSON = new GeometryJSON();
        org.locationtech.jts.geom.Geometry targetGeometry = JTS.transform(geometry, transform);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        geometryJSON.write(targetGeometry, out);
        return out.toString();
    }
}

