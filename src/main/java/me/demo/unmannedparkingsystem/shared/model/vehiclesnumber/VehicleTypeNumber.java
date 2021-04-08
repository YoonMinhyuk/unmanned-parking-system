package me.demo.unmannedparkingsystem.shared.model.vehiclesnumber;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class VehicleTypeNumber {
    private static final int MINIMUM_TYPE_NUMBER = 1;
    private static final int MAXIMUM_TYPE_NUMBER = 99;

    @Column(name = "vehicle_type_number", nullable = false)
    private String typeNumber;

    public VehicleTypeNumber(final int typeNumber) {
        setTypeNumber(typeNumber);
    }

    private void setTypeNumber(final int typeNumber) {
        if (typeNumber < MINIMUM_TYPE_NUMBER) throw new InvalidVehicleTypeException();
        if (typeNumber > MAXIMUM_TYPE_NUMBER) throw new InvalidVehicleTypeException();
        this.typeNumber = typeNumber < 10 ? "0" + typeNumber : Integer.toString(typeNumber);
    }
}