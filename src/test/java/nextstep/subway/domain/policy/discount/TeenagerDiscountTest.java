package nextstep.subway.domain.policy.discount;

import nextstep.member.domain.Guest;
import nextstep.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TeenagerDiscountTest {

    private DiscountPolicy teenagerDiscount;

    @BeforeEach
    void setUp() {
        teenagerDiscount = new TeenagerDiscount();
    }

    @Test
    @DisplayName("어린이 할인 정책은 어린이만 적용된다.")
    void supports() {
        Member teenager = new Member("member@email.com", "password", 15);
        Member adult = new Member("member@email.com", "password", 20);
        Member guest = new Guest();

        assertAll(() -> {
            assertThat(teenagerDiscount.supports(DiscountCondition.of(teenager))).isTrue();
            assertThat(teenagerDiscount.supports(DiscountCondition.of(adult))).isFalse();
            assertThat(teenagerDiscount.supports(DiscountCondition.of(guest))).isFalse();
        });
    }

    @Test
    @DisplayName("어린이 할인은 운임에서 350원을 공제한 금액의 50%를 할인한다.")
    void discount() {
        final int fare = 1250;

        assertThat(teenagerDiscount.discount(fare)).isEqualTo(1070);
    }
}