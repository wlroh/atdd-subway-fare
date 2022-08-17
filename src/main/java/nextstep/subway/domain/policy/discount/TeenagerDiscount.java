package nextstep.subway.domain.policy.discount;

import org.springframework.stereotype.Component;

@Component
public class TeenagerDiscount implements DiscountPolicy {

    private static final int MINIMUM_AGE = 13;
    private static final int MAXIMUM_AGE = 19;
    private static final double DISCOUNT_RATE = 0.8;

    @Override
    public boolean supports(DiscountCondition condition) {
        return condition.age() >= MINIMUM_AGE && condition.age() < MAXIMUM_AGE;
    }

    @Override
    public int discount(int fare) {
        return (int) ((fare - NOT_DISCOUNT_FARE) * DISCOUNT_RATE + NOT_DISCOUNT_FARE);
    }
}
