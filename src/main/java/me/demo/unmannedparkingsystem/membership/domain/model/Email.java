package me.demo.unmannedparkingsystem.membership.domain.model;

import lombok.*;
import me.demo.unmannedparkingsystem.membership.domain.exception.InvalidEmailFormatException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class Email {
    private static final String SIMPLE_EMAIL_REG_EX = "^([a-zA-Z0-9]+)@([a-z]+)\\.([a-z]+)\\.?([a-z]+)$";

    @Column(name = "email", nullable = false)
    private String value;

    public Email(final String value) {
        setValue(value);
    }

    private void setValue(final String value) {
        if (Objects.isNull(value)) throw new InvalidEmailFormatException();
        if (value.isBlank()) throw new InvalidEmailFormatException();
        if (value.trim().isEmpty()) throw new InvalidEmailFormatException();
        if (!Pattern.matches(SIMPLE_EMAIL_REG_EX, value)) throw new InvalidEmailFormatException();
        this.value = value;
    }
}