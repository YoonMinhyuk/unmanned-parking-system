package me.demo.unmannedparkingsystem.membership.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.demo.unmannedparkingsystem.membership.domain.exception.InvalidMemberNameException;

import static java.util.Objects.isNull;

@Getter
@EqualsAndHashCode(of = "email")
@ToString
public class Member {
    private Email email;
    private String name;
    private Role role;
    private CellPhone cellPhone;

    public Member(
            final Email email,
            final String name,
            final Role role,
            final CellPhone cellPhone
    ) {
        setEmail(email);
        setName(name);
        setRole(role);
        setCellPhone(cellPhone);
    }

    private void setEmail(final Email email) {
        if (isNull(email)) throw new IllegalArgumentException("email cannot be null");
        this.email = email;
    }

    private void setName(final String name) {
        if (isNull(name)) throw new InvalidMemberNameException();
        if (name.isBlank()) throw new InvalidMemberNameException();
        if (name.trim().isEmpty()) throw new InvalidMemberNameException();
        this.name = name;
    }

    private void setRole(final Role role) {
        if (isNull(role)) throw new IllegalArgumentException("role cannot be null");
        this.role = role;
    }

    private void setCellPhone(final CellPhone cellPhone) {
        if (isNull(cellPhone)) throw new IllegalArgumentException("cellPhone cannot be null");
        this.cellPhone = cellPhone;
    }
}
