package me.demo.unmannedparkingsystem.seasonticket.domain.model;

import me.demo.unmannedparkingsystem.seasonticket.domain.exception.InvalidVehiclePurposeOfUseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class VehiclePurposeOfUseTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "가", "나", "다", "라", "마", "바",
            "너", "더", "러", "머", "버", "서", "어", "저",
            "고", "노", "도", "로", "모", "보", "소", "오", "조",
            "누", "두", "루", "무", "부", "수", "우", "주",
            "아", "바", "사", "자",
            "하", "허", "호",
            "배",
            "가 ", "나 ", "다 ", "라 ", "마 ", "바 ",
            "너 ", "더 ", "러 ", "머 ", "버 ", "서 ", "어 ", "저 ",
            "고 ", "노 ", "도 ", "로 ", "모 ", "보 ", "소 ", "오 ", "조 ",
            "누 ", "두 ", "루 ", "무 ", "부 ", "수 ", "우 ", "주 ",
            "아 ", "바 ", "사 ", "자 ",
            "하 ", "허 ", "호 ",
            "배 ",
            " 가", " 나", " 다", " 라", " 마", " 바",
            " 너", " 더", " 러", " 머", " 버", " 서", " 어", " 저",
            " 고", " 노", " 도", " 로", " 모", " 보", " 소", " 오", " 조",
            " 누", " 두", " 루", " 무", " 부", " 수", " 우", " 주",
            " 아", " 바", " 사", " 자",
            " 하", " 허", " 호",
            " 배",
            " 가 ", " 나 ", " 다 ", " 라 ", " 마 ", " 바 ",
            " 너 ", " 더 ", " 러 ", " 머 ", " 버 ", " 서 ", " 어 ", " 저 ",
            " 고 ", " 노 ", " 도 ", " 로 ", " 모 ", " 보 ", " 소 ", " 오 ", " 조 ",
            " 누 ", " 두 ", " 루 ", " 무 ", " 부 ", " 수 ", " 우 ", " 주 ",
            " 아 ", " 바 ", " 사 ", " 자 ",
            " 하 ", " 허 ", " 호 ",
            " 배 ",
            "  가  ", "  나  ", "  다  ", "  라  ", "  마  ", "  바  ",
            "  너  ", "  더  ", "  러  ", "  머  ", "  버  ", "  서  ", "  어  ", "  저  ",
            "  고  ", "  노  ", "  도  ", "  로  ", "  모  ", "  보  ", "  소  ", "  오  ", "  조  ",
            "  누  ", "  두  ", "  루  ", "  무  ", "  부  ", "  수  ", "  우  ", "  주  ",
            "  아  ", "  바  ", "  사  ", "  자  ",
            "  하  ", "  허  ", "  호  ",
            "  배  ",
            "가\t", "나\t", "다\t", "라\t", "마\t", "바\t",
            "너\t", "더\t", "러\t", "머\t", "버\t", "서\t", "어\t", "저\t",
            "고\t", "노\t", "도\t", "로\t", "모\t", "보\t", "소\t", "오\t", "조\t",
            "누\t", "두\t", "루\t", "무\t", "부\t", "수\t", "우\t", "주\t",
            "아\t", "바\t", "사\t", "자\t",
            "하\t", "허\t", "호\t",
            "배\t",
            "\t가", "\t나", "\t다", "\t라", "\t마", "\t바",
            "\t너", "\t더", "\t러", "\t머", "\t버", "\t서", "\t어", "\t저",
            "\t고", "\t노", "\t도", "\t로", "\t모", "\t보", "\t소", "\t오", "\t조",
            "\t누", "\t두", "\t루", "\t무", "\t부", "\t수", "\t우", "\t주",
            "\t아", "\t바", "\t사", "\t자",
            "\t하", "\t허", "\t호",
            "\t배",
            "\t가\t", "\t나\t", "\t다\t", "\t라\t", "\t마\t", "\t바\t",
            "\t너\t", "\t더\t", "\t러\t", "\t머\t", "\t버\t", "\t서\t", "\t어\t", "\t저\t",
            "\t고\t", "\t노\t", "\t도\t", "\t로\t", "\t모\t", "\t보\t", "\t소\t", "\t오\t", "\t조\t",
            "\t누\t", "\t두\t", "\t루\t", "\t무\t", "\t부\t", "\t수\t", "\t우\t", "\t주\t",
            "\t아\t", "\t바\t", "\t사\t", "\t자\t",
            "\t하\t", "\t허\t", "\t호\t",
            "\t배\t"
    })
    @DisplayName("VehiclePurposeOfUse 생성 성공 테스트")
    void testVehiclePurposeOfUseInstantiationSuccess(final String purposeOfUse) {
        //given when
        final VehiclePurposeOfUse vehiclePurposeOfUse = new VehiclePurposeOfUse(purposeOfUse);

        //then
        assertThat(vehiclePurposeOfUse).isNotNull();
        assertThat(vehiclePurposeOfUse.getPurposeOfUse()).isEqualTo(purposeOfUse.trim());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            "같", "힣", "헿", "ㄱ", "삁", "넣", "꾹", "!", "@", "깎"
    })
    @DisplayName("VehiclePurposeOfUse 생성 실패 테스트")
    void testVehiclePurposeOfUseInstantiationFailure(final String purposeOfUse) {
        //given when then
        assertThatThrownBy(() -> new VehiclePurposeOfUse(purposeOfUse))
                .isExactlyInstanceOf(InvalidVehiclePurposeOfUseException.class);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("purposeOfUse 가 같으면 동등하며 hashCode 값이 같아야한다")
    void testEqualsAndHashCode(
            final VehiclePurposeOfUse first,
            final VehiclePurposeOfUse second,
            final boolean expected
    ) {
        //given when then
        assertThat(first.equals(second)).isEqualTo(expected);
        assertThat(first.hashCode() == second.hashCode()).isEqualTo(expected);
    }

    private static Stream<Arguments> testEqualsAndHashCode() {
        return Stream.of(
                arguments(new VehiclePurposeOfUse("가"), new VehiclePurposeOfUse("가"), true),
                arguments(new VehiclePurposeOfUse("가"), new VehiclePurposeOfUse("나"), false)
        );
    }
}