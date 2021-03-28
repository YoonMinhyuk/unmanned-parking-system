package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import lombok.*;

import javax.persistence.*;

import static java.util.Objects.isNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "idx_vehicles_full_number", columnNames = {"vehicles_full_number"}),
        indexes = @Index(name = "idx_member_id", columnList = "memberId")
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"issuer", "vehicle", "validityPeriod"})
@ToString
public class SeasonTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Issuer issuer;
    private Vehicle vehicle;
    private ValidityPeriod validityPeriod;

    public SeasonTicket(
            final Issuer issuer,
            final Vehicle vehicle,
            final ValidityPeriod validityPeriod
    ) {
        setIssuer(issuer);
        setVehicle(vehicle);
        setValidityPeriod(validityPeriod);
    }

    private void setIssuer(final Issuer issuer) {
        if (isNull(issuer)) throw new IllegalArgumentException("issuer cannot be null");
        this.issuer = issuer;
    }

    private void setVehicle(final Vehicle vehicle) {
        if (isNull(vehicle)) throw new IllegalArgumentException("vehicle cannot be null");
        this.vehicle = vehicle;
    }

    private void setValidityPeriod(final ValidityPeriod validityPeriod) {
        if (isNull(validityPeriod)) throw new IllegalArgumentException("ValidityPeriod cannot be null");
        this.validityPeriod = validityPeriod;
    }
}