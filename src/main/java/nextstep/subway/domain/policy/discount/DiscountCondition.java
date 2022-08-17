package nextstep.subway.domain.policy.discount;

import nextstep.member.domain.Member;

public class DiscountCondition {

    private int age;

    private DiscountCondition(int age) {
        this.age = age;
    }

    public static DiscountCondition of(Member member) {
        return new DiscountCondition(member.getAge());
    }

    public int age() {
        return age;
    }
}
