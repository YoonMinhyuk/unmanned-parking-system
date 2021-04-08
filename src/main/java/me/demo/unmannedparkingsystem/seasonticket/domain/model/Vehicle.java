package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import lombok.*;
import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehiclesNumber;

import javax.persistence.Embeddable;

import static java.util.Objects.isNull;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class Vehicle {
    private VehiclesNumber vehiclesNumber;

    public Vehicle(final VehiclesNumber vehiclesNumber) {
        setVehiclesNumber(vehiclesNumber);
    }

    private void setVehiclesNumber(final VehiclesNumber vehiclesNumber) {
        if (isNull(vehiclesNumber)) throw new IllegalArgumentException("vehicleNumbers cannot be null");
        this.vehiclesNumber = vehiclesNumber;
    }
}