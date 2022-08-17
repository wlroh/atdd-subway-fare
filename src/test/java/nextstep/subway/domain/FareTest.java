package nextstep.subway.domain;

import nextstep.member.domain.Guest;
import nextstep.member.domain.Member;
import nextstep.subway.domain.policy.discount.DiscountCondition;
import nextstep.subway.domain.policy.fare.FareCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FareTest extends FarePolicyLoaderTest {

    private Line 신분당선;
    private Line 이호선;
    private Line 삼호선;
    private List<Line> lines;

    @BeforeEach
    void setUp() {
        super.setUp();

        신분당선 = new Line("신분당선", "red", 900);
        이호선 = new Line("이호선", "green", 300);
        삼호선 = new Line("이호선", "green", 0);

        lines = List.of(신분당선, 이호선, 삼호선);
    }

    @Test
    @DisplayName("요금 계산이 완료되지 전에 요금을 조회할 수 없다.")
    void invalidToInt_Not_Done() {
        Fare fare = Fare.chaining();

        assertThatThrownBy(() -> fare.toInt()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("계산이 완료된 후 다시 계산을 할 수 없다.")
    void invalidCalculate_With_Done() {
        Member guest = new Guest();
        FareCondition fareCondition = FareCondition.builder()
                .distance(10)
                .lines(lines)
                .build();
        Fare fare = Fare.chaining().calculate(fareCondition).discount(DiscountCondition.of(guest));

        assertThatThrownBy(() -> fare.calculate(fareCondition)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("요금 계산을 하면 계산이 완료된다.")
    void calculate_done() {
        Member guest = new Guest();
        FareCondition fareCondition = FareCondition.builder()
                .distance(10)
                .lines(lines)
                .build();
        Fare fare = Fare.chaining().calculate(fareCondition).discount(DiscountCondition.of(guest));

        assertThat(fare.toInt()).isEqualTo(2_150);
    }
}