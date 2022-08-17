package nextstep.subway.domain.policy.fare;

import org.springframework.stereotype.Component;

@Component
public class OverFiftyExtraFare implements FarePolicy {

    private static final int MINIMUM_DISTANCE = 50;
    private static final int EXTRA_FARE = 100;
    private static final int EXTRA_UNIT = 8;

    @Override
    public boolean supports(FareCondition fareCondition) {
        return fareCondition.distance() > MINIMUM_DISTANCE;
    }

    @Override
    public int fare(FareCondition fareCondition) {
        return (int) (Math.ceil((double) (fareCondition.distance() - MINIMUM_DISTANCE) / EXTRA_UNIT) * EXTRA_FARE);
    }
}
