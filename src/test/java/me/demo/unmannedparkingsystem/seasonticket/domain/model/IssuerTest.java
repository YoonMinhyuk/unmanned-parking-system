package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class IssuerTest {
    @Test
    @DisplayName("Issuer 생성 성공 테스트")
    void testIssuerInstantiationSuccess() {
        //given
        final Long memberId = 1L;

        //when
        final Issuer issuer = new Issuer(memberId);

        //then
        assertThat(issuer).isNotNull();
        assertThat(issuer.getMemberId()).isEqualTo(memberId);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {-1, 0})
    @DisplayName("Issuer 생성 실패 테스트")
    void testIssuerInstantiationFailure(
            final Long memberId
    ) {
        //given when then
        assertThatThrownBy(() -> new Issuer(memberId))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("memberId cannot be null, negative or zero");
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("memberId 가 같으면 동등하며 hashCode 값이 같아야한다")
    void testEqualsAndHashCode(
            final Issuer firstIssuer,
            final Issuer secondIssuer,
            final boolean expected
    ) {
        //given when then
        assertThat(firstIssuer.equals(secondIssuer)).isEqualTo(expected);
        assertThat(firstIssuer.hashCode() == secondIssuer.hashCode()).isEqualTo(expected);
    }

    private static Stream<Arguments> testEqualsAndHashCode() {
        return Stream.of(
                arguments(new Issuer(1L), new Issuer(1L), true),
                arguments(new Issuer(1L), new Issuer(2L), false)
        );
    }
}