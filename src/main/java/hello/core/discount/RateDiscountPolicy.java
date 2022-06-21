package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Qualifier("mainDiscountPolicy") String은 컴파일 단계에서 오타를 잡아 낼 수 없음.
@MainDiscountPolicy // 수동으로 생성한 annotation 사용하면 해결 됨
// @Primary // 동일한 타입의 Bean이 있을 경우 우선
// @Autowired는 동일한 타입의 Bean이 있을 경우 필드명, 파라미터명을 확인하고 해당 변수명과 같은 클래스를 주입한다.
// Qualifier와 Primary 둘다 있을때는 Qualifier가 우선
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }

    }

}
