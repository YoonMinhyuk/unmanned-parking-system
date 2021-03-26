package me.demo.unmannedparkingsystem.membership.domain.model;

import me.demo.unmannedparkingsystem.membership.domain.exception.InvalidCellPhoneNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.*;

class CellPhoneTest {
    @Test
    @DisplayName("휴대폰 생성 성공 테스트")
    void testCellPhoneInstantiationSuccess() {
        //given
        final String number = "010-0000-0000";

        //when
        final CellPhone cellPhone = new CellPhone(number);

        //then
        assertThat(cellPhone).isNotNull();
        assertThat(cellPhone.getNumber()).isEqualTo(number);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            " ",
            "\t",
            "01012345678",
            "0101234567",
            "010123456789",
            "010-123-4567",
            "010-1234-456",
            "010-12345678",
            "0101234-5678",
            "010-1234-56789",
            "010-12345-678",
            "0101-2345-6789",
            "0101-2345-678",
            "0101-234-6789",
            "010 - 1234 - 5678",
            "010-1234 - 5678",
            "010-1234- 5678",
            "010-1234 -5678",
            "010 - 1234-5678",
            "010- 1234-5678",
            "010 -1234-5678",
            "공일공-일이삼사-오육칠팔",
            "010-일이삼사-오육칠팔",
            "010-1234-오육칠팔",
            " 010-1234-5678",
            "010-1234-5678 ",
    })
    @DisplayName("휴대폰 생성 실패 테스트")
    void testCellPhoneInstantiationFailure(final String number) {
        //given when then
        assertThatThrownBy(() -> new CellPhone(number)).isExactlyInstanceOf(InvalidCellPhoneNumberException.class);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("number 가 같으면 동등하며 hashCode 값이 같아야한다")
    void testEqualsAndHashCode(
            final CellPhone firstCellPhone,
            final CellPhone secondCellPhone,
            final boolean expected
    ) {
        //given when then
        assertThat(firstCellPhone.equals(secondCellPhone)).isEqualTo(expected);
        assertThat(firstCellPhone.hashCode() == secondCellPhone.hashCode()).isEqualTo(expected);
    }

    private static Stream<Arguments> testEqualsAndHashCode() {
        final String firstNumber = "010-1234-5678";
        final String secondNumber = "010-9876-5432";
        return Stream.of(
                arguments(new CellPhone(firstNumber), new CellPhone(firstNumber), true),
                arguments(new CellPhone(firstNumber), new CellPhone(secondNumber), false)
        );
    }
}