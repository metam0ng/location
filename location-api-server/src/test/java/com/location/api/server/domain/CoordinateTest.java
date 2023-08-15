package com.location.api.server.domain;

import com.location.api.server.testsupport.service.FakeErrorRangeHolder;
import com.location.external.client.spec.dto.CoordinateDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinateTest {

    @Test
    void x와_y로_coordinate를_생성_할_수_있다() {
        // given
        double x = 12.232349847982;
        double y = 137.398102389012;

        // when
        Coordinate coordinate = Coordinate.of(x, y);

        // then
        assertThat(coordinate.getX()).isEqualTo(x);
        assertThat(coordinate.getY()).isEqualTo(y);

    }

    @Test
    void CoordinateDto로_coordinate를_생성_할_수_있다() {
        // given
        double x = 12.232349847982;
        double y = 137.398102389012;

        CoordinateDto coordinateDto = CoordinateDto.builder()
                .x(x)
                .y(y)
                .build();

        // when
        Coordinate coordinate = Coordinate.from(coordinateDto);

        // then
        assertThat(coordinate.getX()).isEqualTo(x);
        assertThat(coordinate.getY()).isEqualTo(y);

    }

    @Test
    void 오차범위_내의_coordinat는_true() {
        // given
        double x1 = 12.2323488888;
        double y1 = 137.3981077777;

        Coordinate first = Coordinate.builder()
                .x(x1)
                .y(y1)
                .build();

        double x2 = 12.2323499999;
        double y2 = 137.3981000000;

        Coordinate second = Coordinate.builder()
                .x(x2)
                .y(y2)
                .build();

        // when
        boolean result = first.isEquals(second, new FakeErrorRangeHolder());

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 오차범위_밖의_coordinat는_fase() {
        // given
        double x1 = 12.1323488888;
        double y1 = 137.4981077777;

        Coordinate first = Coordinate.builder()
                .x(x1)
                .y(y1)
                .build();

        double x2 = 12.2323499999;
        double y2 = 137.3981000000;

        Coordinate second = Coordinate.builder()
                .x(x2)
                .y(y2)
                .build();

        // when
        boolean result = first.isEquals(second, new FakeErrorRangeHolder());

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 오차범위_밖_x의_coordinat는_fase() {
        // given
        double x1 = 12.2323488888;
        double y1 = 137.3981077777;

        Coordinate first = Coordinate.builder()
                .x(x1)
                .y(y1)
                .build();

        double x2 = 12.3323499999;
        double y2 = 137.3981000000;

        Coordinate second = Coordinate.builder()
                .x(x2)
                .y(y2)
                .build();

        // when
        boolean result = first.isEquals(second, new FakeErrorRangeHolder());

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 오차범위_밖_y의_coordinat는_fase() {
        // given
        double x1 = 12.2323488888;
        double y1 = 137.3981077777;

        Coordinate first = Coordinate.builder()
                .x(x1)
                .y(y1)
                .build();

        double x2 = 12.2323499999;
        double y2 = 137.4981000000;

        Coordinate second = Coordinate.builder()
                .x(x2)
                .y(y2)
                .build();

        // when
        boolean result = first.isEquals(second, new FakeErrorRangeHolder());

        // then
        assertThat(result).isFalse();
    }

}