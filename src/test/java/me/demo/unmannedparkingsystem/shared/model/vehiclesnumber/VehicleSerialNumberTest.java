package me.demo.unmannedparkingsystem.shared.model.vehiclesnumber;

import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.InvalidVehicleSerialNumberException;
import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehicleSerialNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VehicleSerialNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {
            1234, 1023, 1203, 1230
    })
    @DisplayName("VehicleSerialNumber 생성 성공 테스트")
    void testVehicleSerialNumberInstantiationSuccess(final int serialNumber) {
        //given when
        final VehicleSerialNumber vehicleSerialNumber = new VehicleSerialNumber(serialNumber);

        //then
        assertThat(vehicleSerialNumber).isNotNull();
        assertThat(vehicleSerialNumber.getSerialNumber()).isEqualTo(serialNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0123,
            12345,
            1,
            12,
            123
    })
    @DisplayName("VehicleSerialNumber 생성 실패 테스트")
    void testVehicleSerialNumberInstantiationFailure(
            final int serialNumber
    ) {
        //given when then
        assertThatThrownBy(() -> new VehicleSerialNumber(serialNumber))
                .isExactlyInstanceOf(InvalidVehicleSerialNumberException.class);
    }

    @ParameterizedTest
    @CsvSource({
            "1234, 1234, true",
            "1234, 1230, false"
    })
    @DisplayName("serialNumber 가 같으면 동등하며 hashCode 값이 같아야한다")
    void tstEqualsAndHashCode(
            final int firstSerialNumber,
            final int secondSerialNumber,
            final boolean expected
    ) {
        //given
        final VehicleSerialNumber first = new VehicleSerialNumber(firstSerialNumber);
        final VehicleSerialNumber second = new VehicleSerialNumber(secondSerialNumber);

        //when then
        assertThat(first.equals(second)).isEqualTo(expected);
        assertThat(first.hashCode() == second.hashCode()).isEqualTo(expected);
    }
}