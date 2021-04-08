package me.demo.unmannedparkingsystem.shared.model.vehiclesnumber;

import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehiclePurposeOfUse;
import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehicleSerialNumber;
import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehicleTypeNumber;
import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehiclesNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class VehiclesNumberTest {
    @ParameterizedTest
    @CsvSource({
            "12, 가, 1234",
            "11, 나, 1230",
            "01, 너, 1230",
            "02, 너, 1230",
            "01, 아, 9845"
    })
    @DisplayName("VehiclesNumber 생성 성공 테스트")
    void testVehiclesNumberInstantiationSuccess(
            final int typeNumber,
            final String purposeOfUse,
            final int serialNumber
    ) {
        //given
        final VehicleTypeNumber vehicleTypeNumber = new VehicleTypeNumber(typeNumber);
        final VehiclePurposeOfUse vehiclePurposeOfUse = new VehiclePurposeOfUse(purposeOfUse);
        final VehicleSerialNumber vehicleSerialNumber = new VehicleSerialNumber(serialNumber);
        final String fullNumber = vehicleTypeNumber.getTypeNumber() + " " +
                vehiclePurposeOfUse.getPurposeOfUse() + " " +
                vehicleSerialNumber.getSerialNumber();

        //when
        final VehiclesNumber vehiclesNumber =
                new VehiclesNumber(vehicleTypeNumber, vehiclePurposeOfUse, vehicleSerialNumber);

        //then
        assertThat(vehiclesNumber).isNotNull();
        assertThat(vehiclesNumber.getVehicleTypeNumber()).isEqualTo(vehicleTypeNumber);
        assertThat(vehiclesNumber.getVehiclePurposeOfUse()).isEqualTo(vehiclePurposeOfUse);
        assertThat(vehiclesNumber.getVehicleSerialNumber()).isEqualTo(vehicleSerialNumber);
        assertThat(vehiclesNumber.getVehiclesFullNumber()).isEqualTo(fullNumber);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("VehiclesNumber 생성 실패 테스트")
    void testVehiclesNumberInstantiationFailure(
            final VehicleTypeNumber vehicleTypeNumber,
            final VehiclePurposeOfUse vehiclePurposeOfUse,
            final VehicleSerialNumber vehicleSerialNumber,
            final String errorMessage
    ) {
        //given when then
        assertThatThrownBy(() ->
                new VehiclesNumber(vehicleTypeNumber, vehiclePurposeOfUse, vehicleSerialNumber)
        ).isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);

    }

    private static Stream<Arguments> testVehiclesNumberInstantiationFailure() {
        final VehicleTypeNumber vehicleTypeNumber = new VehicleTypeNumber(11);
        final VehiclePurposeOfUse vehiclePurposeOfUse = new VehiclePurposeOfUse("가");
        final VehicleSerialNumber vehicleSerialNumber = new VehicleSerialNumber(1234);
        return Stream.of(
                arguments(null, vehiclePurposeOfUse, vehicleSerialNumber, "vehicleTypeNumber cannot be null"),
                arguments(vehicleTypeNumber, null, vehicleSerialNumber, "vehiclePurposeOfUse cannot be null"),
                arguments(vehicleTypeNumber, vehiclePurposeOfUse, null, "vehicleSerialNumber cannot be null")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("vehicleTypeNumber, vehiclePurposeOfUse, vehicleSerialNumber 가 같으면 동등하며 hashCode 값이 같아야한다")
    void testEqualsAndHashCode(
            final VehiclesNumber first,
            final VehiclesNumber second,
            final boolean expected
    ) {
        //given when then
        assertThat(first.equals(second)).isEqualTo(expected);
        assertThat(first.hashCode() == second.hashCode()).isEqualTo(expected);

    }

    private static Stream<Arguments> testEqualsAndHashCode() {
        final VehicleTypeNumber vehicleTypeNumber = new VehicleTypeNumber(11);
        final VehiclePurposeOfUse vehiclePurposeOfUse = new VehiclePurposeOfUse("가");
        final VehicleSerialNumber vehicleSerialNumber = new VehicleSerialNumber(1234);

        return Stream.of(
                arguments(
                        new VehiclesNumber(
                                vehicleTypeNumber,
                                vehiclePurposeOfUse,
                                vehicleSerialNumber
                        ),
                        new VehiclesNumber(
                                vehicleTypeNumber,
                                vehiclePurposeOfUse,
                                vehicleSerialNumber
                        ),
                        true
                ),
                arguments(
                        new VehiclesNumber(
                                new VehicleTypeNumber(13),
                                vehiclePurposeOfUse,
                                vehicleSerialNumber
                        ),
                        new VehiclesNumber(
                                vehicleTypeNumber,
                                vehiclePurposeOfUse,
                                vehicleSerialNumber
                        ),
                        false
                ),
                arguments(
                        new VehiclesNumber(
                                vehicleTypeNumber,
                                new VehiclePurposeOfUse("호"),
                                vehicleSerialNumber
                        ),
                        new VehiclesNumber(
                                vehicleTypeNumber,
                                vehiclePurposeOfUse,
                                vehicleSerialNumber
                        ),
                        false
                ),
                arguments(
                        new VehiclesNumber(
                                vehicleTypeNumber,
                                vehiclePurposeOfUse,
                                vehicleSerialNumber
                        ),
                        new VehiclesNumber(
                                vehicleTypeNumber,
                                vehiclePurposeOfUse,
                                new VehicleSerialNumber(5678)
                        ),
                        false
                )
        );
    }
}