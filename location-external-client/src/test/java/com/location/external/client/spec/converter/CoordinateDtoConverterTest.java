package com.location.external.client.spec.converter;

import com.location.external.client.rest.converter.CoordinateConverter;
import com.location.external.client.spec.dto.CoordinateDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinateDtoConverterTest {

    @Test
    void KATECH좌표를_Longitude로_변환_할_수_있다() {
        // given
        Double x = 310240.00;
        Double y = 551739.00;

        Double resultX = 126.981682046205;
        Double resultY = 37.5635252637636;
        // when
        CoordinateConverter coordinateConverter = new CoordinateConverter();
        CoordinateDto coordinateDto = coordinateConverter.convertKATECHToLongitude(x, y);
        Double approximationX = Math.abs(resultX - coordinateDto.getX());
        Double approximationY = Math.abs(resultY - coordinateDto.getY());

        // then
        assertThat(approximationX < 0.0001).isTrue();
        assertThat(approximationY < 0.0001).isTrue();
    }


}
