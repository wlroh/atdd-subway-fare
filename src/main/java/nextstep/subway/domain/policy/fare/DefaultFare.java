package nextstep.subway.domain.policy.fare;

import org.springframework.stereotype.Component;

@Component
public class DefaultFare implements FarePolicy {

    private static final int DEFAULT_FARE = 1_250;

    @Override
    public boolean supports(FareCondition fareCondition) {
        return true;
    }

    @Override
    public int fare(FareCondition fareCondition) {
        return DEFAULT_FARE;
    }
}
