package nextstep.subway.domain.policy.discount;

import java.util.ArrayList;
import java.util.List;

public class DiscountManager {

    private static final List<DiscountPolicy> policies = new ArrayList<>();

    private DiscountManager() {
    }

    public static void addPolicy(DiscountPolicy discountPolicy) {
        policies.add(discountPolicy);
    }

    public static void clearPolicy() {
        if (policies.size() > 0) {
            policies.clear();
        }
    }

    public static int discount(int fare, DiscountCondition condition) {
        DiscountPolicy policy = findDiscount(condition);
        return policy.discount(fare);
    }

    private static DiscountPolicy findDiscount(DiscountCondition condition) {
        return policies.stream().parallel()
                .filter(discountPolicy -> discountPolicy.supports(condition))
                .findAny()
                .orElse(new NotDiscount());
    }
}
