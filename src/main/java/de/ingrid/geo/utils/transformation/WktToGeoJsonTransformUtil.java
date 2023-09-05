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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.referencing.CRS;
import org.locationtech.jts.algorithm.Orientation;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
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
		Geometry geometry = reader.read(wkt);
		if(geometry.isValid()) {
            geometry = checkOrientation(geometry);
        }
		GeometryJSON geometryJSON = new GeometryJSON(10);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		geometryJSON.write(geometry, out);
		return out.toString();
	}

	public static String wktToGeoJsonTransform(String wkt, String dstEpsg) throws ParseException, IOException, MismatchedDimensionException, NoSuchAuthorityCodeException, FactoryException, TransformException {
	    return wktToGeoJsonTransform(wkt, "4326", dstEpsg);
	}

    public static String wktToGeoJsonTransform(String wkt, String srcEpsg, String dstEpsg) throws ParseException, IOException, NoSuchAuthorityCodeException, FactoryException, MismatchedDimensionException, TransformException {
        CoordinateReferenceSystem sourceCRS = CRS.parseWKT(CoordTransformUtil.getInstance().getWKTString(CoordTransformUtil.getInstance().getCoordTypeByEPSGCode(srcEpsg)));;
        CoordinateReferenceSystem targetCRS = CRS.parseWKT(CoordTransformUtil.getInstance().getWKTString(CoordTransformUtil.getInstance().getCoordTypeByEPSGCode(dstEpsg)));
        MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, true);

        WKTReader2 reader = new WKTReader2();
        Geometry geometry = reader.read(wkt);
        if(geometry.isValid()) {
            geometry = checkOrientation(geometry);
        }
        GeometryJSON geometryJSON = new GeometryJSON(10);
        Geometry targetGeometry = JTS.transform(geometry, transform);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        geometryJSON.write(targetGeometry, out);
        return out.toString();
    }
    
    private static Geometry checkOrientation(Geometry geom) {
        Geometry tmpGeom = geom;
        switch (tmpGeom.getGeometryType()) {
        // Check polygon orientation
        case Polygon.TYPENAME_POLYGON:
            if(tmpGeom.isValid()) {
                if(Orientation.isCCW(tmpGeom.getCoordinates())) {
                    tmpGeom = tmpGeom.reverse();
                }
            }
            break;
        default:
            break;
        }
        return tmpGeom;
    }
}

