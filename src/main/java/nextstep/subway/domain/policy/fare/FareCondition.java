package nextstep.subway.domain.policy.fare;

import lombok.Builder;
import nextstep.subway.domain.Line;

import java.util.Collections;
import java.util.List;

@Builder
public class FareCondition {

    private List<Line> lines;
    private int distance;

    public List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public int distance() {
        return distance;
    }
}
