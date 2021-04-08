package me.demo.unmannedparkingsystem.shared.model.vehiclesnumber;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static java.util.Objects.isNull;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"vehicleTypeNumber", "vehiclePurposeOfUse", "vehicleSerialNumber"})
@ToString
public class VehiclesNumber {
    private static final String WHITE_SPACE = " ";
    private VehicleTypeNumber vehicleTypeNumber;
    private VehiclePurposeOfUse vehiclePurposeOfUse;
    private VehicleSerialNumber vehicleSerialNumber;

    @Column(name = "vehicles_full_number", nullable = false)
    private String vehiclesFullNumber;

    public VehiclesNumber(
            final VehicleTypeNumber vehicleTypeNumber,
            final VehiclePurposeOfUse vehiclePurposeOfUse,
            final VehicleSerialNumber vehicleSerialNumber
    ) {
        setVehicleTypeNumber(vehicleTypeNumber);
        setVehiclePurposeOfUse(vehiclePurposeOfUse);
        setVehicleSerialNumber(vehicleSerialNumber);
        setVehiclesFullNumber(vehicleTypeNumber, vehiclePurposeOfUse, vehicleSerialNumber);
    }

    private void setVehicleTypeNumber(final VehicleTypeNumber vehicleTypeNumber) {
        if (isNull(vehicleTypeNumber)) throw new IllegalArgumentException("vehicleTypeNumber cannot be null");
        this.vehicleTypeNumber = vehicleTypeNumber;
    }

    private void setVehiclePurposeOfUse(final VehiclePurposeOfUse vehiclePurposeOfUse) {
        if (isNull(vehiclePurposeOfUse)) throw new IllegalArgumentException("vehiclePurposeOfUse cannot be null");
        this.vehiclePurposeOfUse = vehiclePurposeOfUse;
    }

    private void setVehicleSerialNumber(final VehicleSerialNumber vehicleSerialNumber) {
        if (isNull(vehicleSerialNumber)) throw new IllegalArgumentException("vehicleSerialNumber cannot be null");
        this.vehicleSerialNumber = vehicleSerialNumber;
    }

    private void setVehiclesFullNumber(
            final VehicleTypeNumber vehicleTypeNumber,
            final VehiclePurposeOfUse vehiclePurposeOfUse,
            final VehicleSerialNumber vehicleSerialNumber
    ) {
        this.vehiclesFullNumber = vehicleTypeNumber.getTypeNumber() + WHITE_SPACE +
                vehiclePurposeOfUse.getPurposeOfUse() + WHITE_SPACE +
                vehicleSerialNumber.getSerialNumber();
    }
}