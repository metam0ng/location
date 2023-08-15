package com.location.external.client.rest.converter;

import com.location.external.client.spec.dto.CoordinateDto;
import org.locationtech.proj4j.*;
import org.springframework.stereotype.Component;

@Component
public class CoordinateConverter {

    private static final String KATECH = "+proj=tmerc +lat_0=38 +lon_0=128 +k=0.9999 +x_0=400000 +y_0=600000 +ellps=bessel +units=m +no_defs +towgs84=-115.80,474.99,674.11,1.16,-2.31,-1.63,6.43";
    private static final String WGS84 = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";

    public CoordinateDto convertKATECHToLongitude(Double x, Double y) {
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CRSFactory csFactory = new CRSFactory();

        CoordinateReferenceSystem katechCRS = csFactory.createFromParameters("KATECH", KATECH);
        CoordinateReferenceSystem wgs84CRS = csFactory.createFromParameters("WGS84", WGS84);

        CoordinateTransform transform = ctFactory.createTransform(katechCRS, wgs84CRS);

        ProjCoordinate katechCoord = new ProjCoordinate(x, y); // 예시 좌표
        ProjCoordinate wgs84Coord = new ProjCoordinate();

        transform.transform(katechCoord, wgs84Coord);

        return CoordinateDto.of(wgs84Coord.x, wgs84Coord.y);
    }

}
