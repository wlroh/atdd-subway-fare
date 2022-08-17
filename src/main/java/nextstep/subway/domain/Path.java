package nextstep.subway.domain;

import nextstep.member.domain.Member;
import nextstep.subway.domain.policy.discount.DiscountCondition;
import nextstep.subway.domain.policy.fare.FareCondition;

import java.util.List;
import java.util.stream.Collectors;

public class Path {
    private Sections sections;

    public Path(Sections sections) {
        this.sections = sections;
    }

    public int extractDistance() {
        return sections.totalDistance();
    }

    public int extractDuration() {
        return sections.totalDuration();
    }

    public Fare extractFare(Member member) {
        FareCondition fareCondition = generateFareCondition();
        return Fare.chaining()
                .calculate(fareCondition)
                .discount(DiscountCondition.of(member));
    }

    private FareCondition generateFareCondition() {
        return FareCondition.builder()
                .distance(sections.totalDistance())
                .lines(extractLines())
                .build();
    }

    private List<Line> extractLines() {
        return sections.values()
                .stream()
                .map(Section::getLine)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Station> getStations() {
        return sections.getStations();
    }
}
