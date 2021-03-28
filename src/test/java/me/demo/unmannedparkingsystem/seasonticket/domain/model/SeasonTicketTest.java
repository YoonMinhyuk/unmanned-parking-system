package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SeasonTicketTest {
    @Test
    @DisplayName("SeasonTicket 생성 성공 테스트")
    void testSeasonTicketInstantiationSuccess() {
        //given
        final Issuer issuer = new Issuer(1L);
        final Vehicle vehicle = new Vehicle(
                new VehiclesNumber(new VehicleTypeNumber(1), new VehiclePurposeOfUse("가"), new VehicleSerialNumber(1234))
        );
        final ValidityPeriod validityPeriod = ValidityPeriod.of(ValidityPeriod.Type.MONTHLY);

        //when
        final SeasonTicket seasonTicket = new SeasonTicket(issuer, vehicle, validityPeriod);

        //then
        assertThat(seasonTicket).isNotNull();
        assertThat(seasonTicket.getIssuer()).isEqualTo(issuer);
        assertThat(seasonTicket.getVehicle()).isEqualTo(vehicle);
        assertThat(seasonTicket.getValidityPeriod()).isEqualTo(validityPeriod);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("SeasonTicket 생성 실패 테스트")
    void testSeasonTicketInstantiationFailure(
            final Issuer issuer,
            final Vehicle vehicle,
            final ValidityPeriod validityPeriod,
            final String errorMessage
    ) {
        //given when then
        assertThatThrownBy(() -> new SeasonTicket(issuer, vehicle, validityPeriod))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    private static Stream<Arguments> testSeasonTicketInstantiationFailure() {
        final Issuer issuer = new Issuer(1L);
        final Vehicle vehicle = new Vehicle(
                new VehiclesNumber(new VehicleTypeNumber(1), new VehiclePurposeOfUse("가"), new VehicleSerialNumber(1234))
        );
        final ValidityPeriod validityPeriod = ValidityPeriod.of(ValidityPeriod.Type.MONTHLY);

        return Stream.of(
                arguments(null, null, null, "issuer cannot be null"),
                arguments(null, vehicle, validityPeriod, "issuer cannot be null"),
                arguments(issuer, null, validityPeriod, "vehicle cannot be null"),
                arguments(issuer, vehicle, null, "ValidityPeriod cannot be null")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("issuer, vehicle, validityPeriod 가 같으면 동등하며 HashCode 값이 같아야한다")
    void testEqualsAndHashCode(
            final SeasonTicket firstTicket,
            final SeasonTicket secondTicket,
            final boolean expected
    ) {
        //given when then
        assertThat(firstTicket.equals(secondTicket)).isEqualTo(expected);
        assertThat(firstTicket.hashCode() == secondTicket.hashCode()).isEqualTo(expected);
    }

    private static Stream<Arguments> testEqualsAndHashCode() {
        final Issuer issuer = new Issuer(1L);
        final Vehicle vehicle = new Vehicle(
                new VehiclesNumber(new VehicleTypeNumber(1), new VehiclePurposeOfUse("가"), new VehicleSerialNumber(1234))
        );
        final ValidityPeriod validityPeriod = ValidityPeriod.of(ValidityPeriod.Type.MONTHLY);

        return Stream.of(
                arguments(
                        new SeasonTicket(issuer, vehicle, validityPeriod),
                        new SeasonTicket(issuer, vehicle, validityPeriod),
                        true
                ),
                arguments(
                        new SeasonTicket(issuer, vehicle, validityPeriod),
                        new SeasonTicket(new Issuer(2L), vehicle, validityPeriod),
                        false
                ),
                arguments(
                        new SeasonTicket(issuer, vehicle, validityPeriod),
                        new SeasonTicket(
                                issuer,
                                new Vehicle(new VehiclesNumber(
                                        new VehicleTypeNumber(2),
                                        new VehiclePurposeOfUse("나"),
                                        new VehicleSerialNumber(5678)
                                )),
                                validityPeriod
                        ),
                        false
                ),
                arguments(
                        new SeasonTicket(issuer, vehicle, validityPeriod),
                        new SeasonTicket(issuer, vehicle, ValidityPeriod.of(ValidityPeriod.Type.WEEKLY)),
                        false
                ),
                arguments(
                        new SeasonTicket(issuer, vehicle, validityPeriod),
                        new SeasonTicket(issuer, vehicle, ValidityPeriod.of(ValidityPeriod.Type.YEARLY)),
                        false
                )
        );
    }
}