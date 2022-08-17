package nextstep.subway.domain.policy.fare;

import org.springframework.stereotype.Component;

@Component
public class ElevenToFiftyExtraFare implements FarePolicy {

    private static final int MINIMUM_DISTANCE = 10;
    private static final int MAXIMUM_DISTANCE = 50;
    private static final int EXTRA_FARE = 100;
    private static final int EXTRA_UNIT = 5;
    private static final int FULL_FARE = 800;

    @Override
    public boolean supports(FareCondition fareCondition) {
        return fareCondition.distance() > MINIMUM_DISTANCE;
    }

    @Override
    public int fare(FareCondition fareCondition) {
        if (fareCondition.distance() >= MAXIMUM_DISTANCE) {
            return FULL_FARE;
        }
        return (int) (Math.ceil((double) (fareCondition.distance() - MINIMUM_DISTANCE) / EXTRA_UNIT) * EXTRA_FARE);
    }
}
