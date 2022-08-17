package nextstep.subway.domain.policy.discount;

public class NotDiscount implements DiscountPolicy {

    @Override
    public boolean supports(DiscountCondition condition) {
        return false;
    }

    @Override
    public int discount(int fare) {
        return fare;
    }
}
