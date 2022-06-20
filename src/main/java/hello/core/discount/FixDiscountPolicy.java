package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @Autowired는 동일한 타입의 Bean이 있을 경우 필드명, 파라미터명을 확인하고 해당 변수명과 같은 클래스를 주입한다.
// @Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
