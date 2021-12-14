package de.ingrid.geo.utils.transformation;

import org.geotools.geometry.jts.WKTReader2;
import org.locationtech.jts.io.ParseException;

public class WktUtil {

    public static boolean isValidWkt(String wkt) {
        WKTReader2 reader = new WKTReader2();
        try {
            reader.read(wkt);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
