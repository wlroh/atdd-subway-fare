package nextstep.subway.domain.policy.discount;

public interface DiscountPolicy {

    int NOT_DISCOUNT_FARE = 350;

    boolean supports(DiscountCondition condition);

    int discount(int fare);
}
