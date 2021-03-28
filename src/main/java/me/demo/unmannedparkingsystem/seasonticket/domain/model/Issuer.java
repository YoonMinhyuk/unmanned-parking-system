package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import lombok.*;

import javax.persistence.Embeddable;

import static java.util.Objects.isNull;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class Issuer {
    private Long memberId;

    public Issuer(final Long memberId) {
        setMemberId(memberId);
    }

    private void setMemberId(final Long memberId) {
        if (isNull(memberId)) throw new IllegalArgumentException("memberId cannot be null, negative or zero");
        if (memberId <= 0) throw new IllegalArgumentException("memberId cannot be null, negative or zero");
        this.memberId = memberId;
    }
}