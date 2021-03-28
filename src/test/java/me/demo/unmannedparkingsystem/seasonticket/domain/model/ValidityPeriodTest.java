package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ValidityPeriodTest {
    @ParameterizedTest
    @MethodSource
    @DisplayName("ValidityPeriod 생성 성공 테스트")
    void testValidityPeriodInstantiationSuccess(
            final ValidityPeriod.Type type
    ) {
        //given when
        final ValidityPeriod validityPeriod = ValidityPeriod.of(type);

        //then
        assertThat(validityPeriod).isNotNull();
        assertThat(validityPeriod.getIssuedAt()).isNotNull();
        assertThat(validityPeriod.getExpiredAt()).isNotNull();
        assertThat(validityPeriod.getExpiredAt().toLocalTime()).isEqualTo(LocalTime.MAX);

        final ZonedDateTime issuedAt = validityPeriod.getIssuedAt();
        final ZonedDateTime expiredAt = validityPeriod.getExpiredAt();

        switch (validityPeriod.getType()) {
            case WEEKLY:
                assertThat(ChronoUnit.DAYS.between(issuedAt, expiredAt)).isEqualTo(7);
                assertThat(ChronoUnit.WEEKS.between(issuedAt, expiredAt)).isEqualTo(1);
                break;
            case MONTHLY:
                assertThat(ChronoUnit.WEEKS.between(issuedAt, expiredAt)).isEqualTo(4);
                assertThat(ChronoUnit.MONTHS.between(issuedAt, expiredAt)).isEqualTo(1);
                break;
            case YEARLY:
                assertThat(ChronoUnit.MONTHS.between(issuedAt, expiredAt)).isEqualTo(12);
                assertThat(ChronoUnit.YEARS.between(issuedAt, expiredAt)).isEqualTo(1);
                break;
        }
    }

    private static Stream<Arguments> testValidityPeriodInstantiationSuccess() {
        return Stream.of(
                arguments(ValidityPeriod.Type.WEEKLY),
                arguments(ValidityPeriod.Type.MONTHLY),
                arguments(ValidityPeriod.Type.YEARLY)
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("ValidityPeriod 생성 실패 테스트")
    void testValidityPeriodInstantiationFailure(final ValidityPeriod.Type type) {
        //given when then
        assertThatThrownBy(() -> ValidityPeriod.of(type))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("type cannot be null");
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("type, issuedAt, expiredAt 이 같으면 동등하며 HashCode 값이 같아야한다")
    void testEqualsAndHashCode(
            final ValidityPeriod first,
            final ValidityPeriod second,
            final boolean expected
    ) {
        //given when then
        assertThat(first.equals(second)).isEqualTo(expected);
        assertThat(first.hashCode() == second.hashCode()).isEqualTo(expected);
    }

    private static Stream<Arguments> testEqualsAndHashCode() throws InterruptedException {
        final ValidityPeriod firstWeekly = ValidityPeriod.of(ValidityPeriod.Type.WEEKLY);
        final ValidityPeriod monthly = ValidityPeriod.of(ValidityPeriod.Type.MONTHLY);
        final ValidityPeriod yearly = ValidityPeriod.of(ValidityPeriod.Type.MONTHLY);

        Thread.sleep(100);
        final ValidityPeriod secondWeekly = ValidityPeriod.of(ValidityPeriod.Type.WEEKLY);

        return Stream.of(
                arguments(firstWeekly, firstWeekly, true),
                arguments(monthly, monthly, true),
                arguments(yearly, yearly, true),
                arguments(firstWeekly, secondWeekly, false),
                arguments(firstWeekly, monthly, false),
                arguments(firstWeekly, yearly, false),
                arguments(monthly, yearly, false)
        );
    }
}