package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import lombok.*;
import me.demo.unmannedparkingsystem.shared.model.vehiclesnumber.VehiclesNumber;

import javax.persistence.*;

import static java.util.Objects.isNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "idx_vehicles_full_number", columnNames = {"vehicles_full_number"}),
        indexes = @Index(name = "idx_member_id", columnList = "memberId")
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"issuer", "vehiclesNumber", "validityPeriod"})
@ToString
public class SeasonTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Issuer issuer;
    private VehiclesNumber vehiclesNumber;
    private ValidityPeriod validityPeriod;

    public SeasonTicket(
            final Issuer issuer,
            final VehiclesNumber vehiclesNumber,
            final ValidityPeriod validityPeriod
    ) {
        setIssuer(issuer);
        setVehiclesNumber(vehiclesNumber);
        setValidityPeriod(validityPeriod);
    }

    private void setIssuer(final Issuer issuer) {
        if (isNull(issuer)) throw new IllegalArgumentException("issuer cannot be null");
        this.issuer = issuer;
    }

    private void setVehiclesNumber(final VehiclesNumber vehiclesNumber) {
        if (isNull(vehiclesNumber)) throw new IllegalArgumentException("vehiclesNumber cannot be null");
        this.vehiclesNumber = vehiclesNumber;
    }

    private void setValidityPeriod(final ValidityPeriod validityPeriod) {
        if (isNull(validityPeriod)) throw new IllegalArgumentException("ValidityPeriod cannot be null");
        this.validityPeriod = validityPeriod;
    }
}