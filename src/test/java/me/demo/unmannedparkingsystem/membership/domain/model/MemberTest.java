package me.demo.unmannedparkingsystem.membership.domain.model;

import me.demo.unmannedparkingsystem.membership.domain.exception.InvalidMemberNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MemberTest {
    @ParameterizedTest
    @MethodSource
    @DisplayName("Member 생성 성공 테스트")
    void testMemberInstantiationSuccess(
            final Role role
    ) {
        //given
        final Email email = new Email("userEmail@gmail.com");
        final String name = "userName";
        final CellPhone cellPhone = new CellPhone("010-1234-5678");

        //when
        final Member member = new Member(email, name, role, cellPhone);

        //then
        assertThat(member).isNotNull();
        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getName()).isEqualTo(name);
        assertThat(member.getRole()).isEqualTo(role);
        assertThat(member.getCellPhone()).isEqualTo(cellPhone);
    }

    private static Stream<Arguments> testMemberInstantiationSuccess() {
        return Stream.of(
                arguments(Role.ADMIN),
                arguments(Role.STAFF),
                arguments(Role.PARKING_LOT_OWNER),
                arguments(Role.USER)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Member 생성 실패 테스트")
    void testMemberInstantiationFailure(
            final Email email,
            final String name,
            final Role role,
            final CellPhone cellPhone,
            final Class<?> expectedExceptionClass
    ) {
        //given when then
        assertThatThrownBy(
                () -> new Member(email, name, role, cellPhone)
        ).isExactlyInstanceOf(expectedExceptionClass);
    }

    private static Stream<Arguments> testMemberInstantiationFailure() {
        final Email email = new Email("userEmail@gmail.com");
        final String name = "userName";
        final Role role = Role.USER;
        final CellPhone cellPhone = new CellPhone("010-1234-5678");

        return Stream.of(
                arguments(null, name, role, cellPhone, IllegalArgumentException.class),
                arguments(email, null, role, cellPhone, InvalidMemberNameException.class),
                arguments(email, "", role, cellPhone, InvalidMemberNameException.class),
                arguments(email, " ", role, cellPhone, InvalidMemberNameException.class),
                arguments(email, "\t", role, cellPhone, InvalidMemberNameException.class),
                arguments(email, name, null, cellPhone, IllegalArgumentException.class),
                arguments(email, name, role, null, IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Email 이 같으면 동등하며 hashCode 값이 같아야한다")
    void testEqualsAndHashCode(
            final Member firstMember,
            final Member secondMember,
            final boolean expected
    ) {
        //given when then
        assertThat(firstMember.equals(secondMember)).isEqualTo(expected);
        assertThat(firstMember.hashCode() == secondMember.hashCode()).isEqualTo(expected);
    }

    private static Stream<Arguments> testEqualsAndHashCode() {
        final Email user1Email = new Email("user1@gmail.com");
        final Email user2Email = new Email("user2@gmail.com");

        return Stream.of(
                arguments(
                        new Member(user1Email, "name", Role.USER, new CellPhone("010-1234-5678")),
                        new Member(user1Email, "name", Role.USER, new CellPhone("010-1234-5678")),
                        true
                ),
                arguments(
                        new Member(user1Email, "name1", Role.USER, new CellPhone("010-1234-5678")),
                        new Member(user1Email, "name", Role.USER, new CellPhone("010-1234-5678")),
                        true
                ),
                arguments(
                        new Member(user1Email, "name", Role.ADMIN, new CellPhone("010-1234-5678")),
                        new Member(user1Email, "name", Role.USER, new CellPhone("010-1234-5678")),
                        true
                ),
                arguments(
                        new Member(user1Email, "name", Role.USER, new CellPhone("010-5678-5678")),
                        new Member(user1Email, "name", Role.USER, new CellPhone("010-1234-5678")),
                        true
                ),
                arguments(
                        new Member(user1Email, "name", Role.USER, new CellPhone("010-1234-5678")),
                        new Member(user2Email, "name", Role.USER, new CellPhone("010-1234-5678")),
                        false
                )
        );
    }
}