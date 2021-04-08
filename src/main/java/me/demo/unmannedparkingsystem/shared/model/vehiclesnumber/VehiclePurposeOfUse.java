package me.demo.unmannedparkingsystem.shared.model.vehiclesnumber;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class VehiclePurposeOfUse {
    private static final List<String> PURPOSE_OF_USE_REG_EXS = List.of(
            // 일반용
            "[가나다라마바]", "[너더러머버서어저]", "[고노도로모보소오조]", "[누두루무부수우주]",
            //영업용
            "[아바사자]",
            //렌트카
            "[하허호]",
            //택배
            "[배]"
    );

    @Column(name = "vehicle_purpose_of_use", nullable = false)
    private String purposeOfUse;

    public VehiclePurposeOfUse(final String purposeOfUse) {
        setPurposeOfUse(purposeOfUse);
    }

    private void setPurposeOfUse(final String purposeOfUse) {
        if (isNull(purposeOfUse)) throw new InvalidVehiclePurposeOfUseException();
        if (PURPOSE_OF_USE_REG_EXS.stream().noneMatch(regEx -> Pattern.matches(regEx, purposeOfUse.trim()))) {
            throw new InvalidVehiclePurposeOfUseException();
        }
        this.purposeOfUse = purposeOfUse.trim();
    }
}
