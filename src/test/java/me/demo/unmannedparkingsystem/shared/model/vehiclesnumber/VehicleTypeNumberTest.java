package me.demo.unmannedparkingsystem.shared.model.vehiclesnumber;

import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.InvalidVehicleTypeException;
import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehicleTypeNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VehicleTypeNumberTest {
    @Test
    @DisplayName("VehicleTypeNumber 생성 성공 테스트")
    void testVehicleTypeNumberInstantiationSuccess() {
        //given when then
        Stream.iterate(1, typeNumber -> typeNumber + 1).limit(99).forEach(typeNumber -> {
            final VehicleTypeNumber vehicleTypeNumber = new VehicleTypeNumber(typeNumber);
            assertThat(vehicleTypeNumber).isNotNull();
            if (typeNumber < 10) {
                assertThat(vehicleTypeNumber.getTypeNumber()).isEqualTo("0" + typeNumber);
            } else {
                assertThat(vehicleTypeNumber.getTypeNumber()).isEqualTo(typeNumber.toString());
            }
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 100})
    @DisplayName("VehicleTypeNumber 생성 실패 테스트")
    void testVehicleTypeNumberInstantiationFailure(final int typeNumber) {
        //given when then
        assertThatThrownBy(() -> new VehicleTypeNumber(typeNumber))
                .isExactlyInstanceOf(InvalidVehicleTypeException.class);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, true",
            "1, 2, false"
    })
    @DisplayName("typeNumber 가 같으면 동등하며 hashCode 값이 같아야한다")
    void testEqualsAndHashCode(
            final int firstTypeNumber,
            final int secondTypeNumber,
            final boolean expected
    ) {
        //given
        final VehicleTypeNumber first = new VehicleTypeNumber(firstTypeNumber);
        final VehicleTypeNumber second = new VehicleTypeNumber(secondTypeNumber);

        // when then
        assertThat(first.equals(second)).isEqualTo(expected);
        assertThat(first.hashCode() == second.hashCode()).isEqualTo(expected);
    }
}