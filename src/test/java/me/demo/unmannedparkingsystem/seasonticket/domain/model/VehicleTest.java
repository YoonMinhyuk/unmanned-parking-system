package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehiclePurposeOfUse;
import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehicleSerialNumber;
import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehicleTypeNumber;
import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehiclesNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class VehicleTest {
    @Test
    @DisplayName("Vehicle 생성 성공 테스트")
    void testVehicleInstantiationSuccess() {
        //given
        final VehiclesNumber vehiclesNumber = new VehiclesNumber(
                new VehicleTypeNumber(1),
                new VehiclePurposeOfUse("가"),
                new VehicleSerialNumber(1234)
        );

        // when
        final Vehicle vehicle = new Vehicle(vehiclesNumber);

        //then
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getVehiclesNumber()).isEqualTo(vehiclesNumber);
    }

    @Test
    @DisplayName("Vehicle 생성 실패 테스트")
    void testVehicleInstantiationFailure() {
        //given when then
        assertThatThrownBy(() -> new Vehicle(null))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("vehicleNumbers cannot be null");
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("vehiclesNumber 가 같으면 동등해야하며 HashCode 값이 같아야한다")
    void testEqualsAndHashCode(
            final Vehicle first,
            final Vehicle second,
            final boolean expected
    ) {
        //given when then
        assertThat(first.equals(second)).isEqualTo(expected);
        assertThat(first.hashCode() == second.hashCode()).isEqualTo(expected);
    }

    private static Stream<Arguments> testEqualsAndHashCode() {
        final VehiclesNumber fistVehiclesNumber = new VehiclesNumber(
                new VehicleTypeNumber(1),
                new VehiclePurposeOfUse("가"),
                new VehicleSerialNumber(1234)
        );

        final VehiclesNumber secondVehiclesNumber = new VehiclesNumber(
                new VehicleTypeNumber(2),
                new VehiclePurposeOfUse("나"),
                new VehicleSerialNumber(5678)
        );
        return Stream.of(
                arguments(
                        new Vehicle(fistVehiclesNumber),
                        new Vehicle(fistVehiclesNumber),
                        true
                ),
                arguments(
                        new Vehicle(fistVehiclesNumber),
                        new Vehicle(secondVehiclesNumber),
                        false
                )
        );
    }
}