package me.demo.unmannedparkingsystem.membership.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.demo.unmannedparkingsystem.membership.domain.exception.InvalidCellPhoneNumberException;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
@ToString
public class CellPhone {
    private final static String NUMBER_REG_EX = "^(010)-([0-9]{4})-([0-9]{4})$";
    private String number;

    public CellPhone(final String number) {
        setNumber(number);
    }

    private void setNumber(final String number) {
        if (Objects.isNull(number)) throw new InvalidCellPhoneNumberException();
        if (number.isBlank()) throw new InvalidCellPhoneNumberException();
        if (number.trim().isEmpty()) throw new InvalidCellPhoneNumberException();
        if (!Pattern.matches(NUMBER_REG_EX, number)) throw new InvalidCellPhoneNumberException();
        this.number = number;
    }
}