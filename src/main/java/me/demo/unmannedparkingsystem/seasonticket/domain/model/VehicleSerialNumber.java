package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import lombok.*;
import me.demo.unmannedparkingsystem.seasonticket.domain.exception.InvalidVehicleSerialNumberException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class VehicleSerialNumber {
    private static final String SERIAL_NUMBER_REG_EX = "^([1-9])([0-9]{3})$";

    @Column(name = "vehicle_serial_number", nullable = false)
    private int serialNumber;

    public VehicleSerialNumber(final int serialNumber) {
        setSerialNumber(serialNumber);
    }

    private void setSerialNumber(final int serialNumber) {
        if (!Pattern.matches(SERIAL_NUMBER_REG_EX, String.valueOf(serialNumber))) {
            throw new InvalidVehicleSerialNumberException();
        }
        this.serialNumber = serialNumber;
    }
}
