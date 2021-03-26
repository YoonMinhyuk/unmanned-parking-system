package me.demo.unmannedparkingsystem.membership.domain.model;

import me.demo.unmannedparkingsystem.membership.domain.exception.InvalidEmailFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class EmailTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "user@gmail.com",
            "user@naver.com",
            "user@daum.net",
            "user@domain.co.kr",
            "user@nate.com",
    })
    @DisplayName("Email 생성 성공 테스트")
    void testEmailInstantiationSuccess(final String emailValue) {
        //given when
        final Email email = new Email(emailValue);

        //then
        assertThat(email).isNotNull();
        assertThat(email.getValue()).isEqualTo(emailValue);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            " ",
            "\t",
            "@gmail.com",
            "user@gmail",
            "user@gmail.",
            "user@gmailcom",
            "user!@gmail.com",
            "사용자@gmail.com",
            "user@domain.co.",
            "usergmail.com.",
    })
    @DisplayName("Email 생성 실패 테스트")
    void testEmailInstantiationFailure(final String emailValue) {
        //given when then
        assertThatThrownBy(() -> new Email(emailValue)).isExactlyInstanceOf(InvalidEmailFormatException.class);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("email value 가 같으면 동등하며 hashCode 값이 같아야한다")
    void testEqualsAndHashCode(
            final Email firstEmail,
            final Email secondEmail,
            final boolean expected
    ) {
        //given when then
        assertThat(firstEmail.equals(secondEmail)).isEqualTo(expected);
        assertThat(firstEmail.hashCode() == secondEmail.hashCode()).isEqualTo(expected);
    }

    private static Stream<Arguments> testEqualsAndHashCode() {
        final String firstEmailValue = "user1@gmail.com";
        final String secondEmailValue = "user2@gmail.com";
        return Stream.of(
                arguments(new Email(firstEmailValue), new Email(firstEmailValue), true),
                arguments(new Email(firstEmailValue), new Email(secondEmailValue), false)
        );
    }
}