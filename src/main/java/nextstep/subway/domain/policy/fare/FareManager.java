package nextstep.subway.domain.policy.fare;

import java.util.ArrayList;
import java.util.List;

public class FareManager {

    private static final List<FarePolicy> policies = new ArrayList<>();

    private FareManager() {
    }

    public static void addPolicy(FarePolicy farePolicy) {
        policies.add(farePolicy);
    }

    public static void clearPolicy() {
        if (policies.size() > 0) {
            policies.clear();
        }
    }

    public static int fare(FareCondition fareCondition) {
        return policies.stream().parallel()
                .filter(farePolicy -> farePolicy.supports(fareCondition))
                .mapToInt(farePolicy -> farePolicy.fare(fareCondition))
                .sum();
    }
}
