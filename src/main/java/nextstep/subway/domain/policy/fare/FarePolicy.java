package nextstep.subway.domain.policy.fare;

public interface FarePolicy {

    boolean supports(FareCondition fareCondition);

    int fare(FareCondition fareCondition);
}
