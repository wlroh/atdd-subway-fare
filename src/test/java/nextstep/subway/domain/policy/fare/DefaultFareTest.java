package nextstep.subway.domain.policy.fare;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DefaultFareTest {

    private FarePolicy defaultFare;

    @BeforeEach
    void setUp() {
        defaultFare = new DefaultFare();
    }

    @DisplayName("기본 요금정책은 항상 적용한다.")
    @ParameterizedTest(name = "#{index} - 거리={0}km")
    @ValueSource(ints = {1, 10, 11, 50, 51})
    void support(int distance) {
        FareCondition fareCondition = FareCondition.builder().distance(distance).build();

        assertThat(defaultFare.supports(fareCondition)).isTrue();
    }

    @DisplayName("기본 요금정책은 거리 상관없이 기본요금을 반환한다.")
    @ParameterizedTest(name = "#{index} - 거리={0}km")
    @ValueSource(ints = {1, 10, 11, 50, 51})
    void fare(int distance) {
        FareCondition fareCondition = FareCondition.builder().distance(distance).build();

        assertAll(() -> {
            assertThat(defaultFare.supports(fareCondition)).isTrue();
            assertThat(defaultFare.fare(fareCondition)).isEqualTo(1_250);
        });
    }
}