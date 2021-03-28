package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class ValidityPeriod {
    @Enumerated(EnumType.STRING)
    private Type type;
    private ZonedDateTime issuedAt;
    private ZonedDateTime expiredAt;

    private ValidityPeriod(
            final Type type,
            final ZonedDateTime issuedAt,
            final ZonedDateTime expiredAt
    ) {
        setType(type);
        setIssuedAtAndExpiredAt(issuedAt, expiredAt);
    }

    public enum Type {
        WEEKLY {
            @Override
            ZonedDateTime decideExpiredAt(final ZonedDateTime issuedAt) {
                return issuedAt.plusWeeks(1)
                        .withHour(LocalTime.MAX.getHour())
                        .withMinute(LocalTime.MAX.getMinute())
                        .withSecond(LocalTime.MAX.getSecond())
                        .withNano(LocalTime.MAX.getNano());
            }
        },

        MONTHLY {
            @Override
            ZonedDateTime decideExpiredAt(final ZonedDateTime issuedAt) {
                return issuedAt.plusMonths(1)
                        .withHour(LocalTime.MAX.getHour())
                        .withMinute(LocalTime.MAX.getMinute())
                        .withSecond(LocalTime.MAX.getSecond())
                        .withNano(LocalTime.MAX.getNano());
            }
        },

        YEARLY {
            @Override
            ZonedDateTime decideExpiredAt(final ZonedDateTime issuedAt) {
                return issuedAt.plusYears(1)
                        .withHour(LocalTime.MAX.getHour())
                        .withMinute(LocalTime.MAX.getMinute())
                        .withSecond(LocalTime.MAX.getSecond())
                        .withNano(LocalTime.MAX.getNano());
            }
        };

        ValidityPeriod generateValidityPeriod() {
            final ZonedDateTime now = ZonedDateTime.now();
            return new ValidityPeriod(this, now, decideExpiredAt(now));
        }

        abstract ZonedDateTime decideExpiredAt(final ZonedDateTime issuedAt);
    }

    private void setType(final Type type) {
        if (Objects.isNull(type)) throw new IllegalArgumentException("type cannot be null");
        this.type = type;
    }

    private void setIssuedAtAndExpiredAt(
            final ZonedDateTime issuedAt,
            final ZonedDateTime expiredAt
    ) {
        if (issuedAt.isAfter(expiredAt))
            throw new IllegalArgumentException("issuedAt cannot be greater than expiredAt");
        if (issuedAt.isEqual(expiredAt)) throw new IllegalArgumentException("issueAt cannot be the same as expiredAt");
        this.issuedAt = issuedAt;
        this.expiredAt = expiredAt;
    }

    public static ValidityPeriod of(final Type type) {
        if (Objects.isNull(type)) throw new IllegalArgumentException("type cannot be null");
        return type.generateValidityPeriod();
    }
}