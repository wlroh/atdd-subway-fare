package nextstep.subway.domain;

import nextstep.subway.domain.policy.discount.DiscountCondition;
import nextstep.subway.domain.policy.discount.DiscountManager;
import nextstep.subway.domain.policy.fare.FareManager;
import nextstep.subway.domain.policy.fare.FareCondition;

public class Fare {

    private int value;
    private boolean done;

    private Fare(int value, boolean done) {
        this.value = value;
        this.done = done;
    }

    public static Fare chaining() {
        return new Fare(0, false);
    }

    public Fare calculate(FareCondition fareCondition) {
        if (done) {
            throw new IllegalStateException();
        }
        value += FareManager.fare(fareCondition);
        return this;
    }

    public Fare discount(DiscountCondition condition) {
        if (done) {
            throw new IllegalStateException();
        }
        value = DiscountManager.discount(value, condition);
        done();
        return this;
    }

    private void done() {
        done = true;
    }

    public int toInt() {
        if (isNotFinishCalculate()) {
            throw new IllegalStateException();
        }
        return value;
    }

    private boolean isNotFinishCalculate() {
        return !done;
    }
}
