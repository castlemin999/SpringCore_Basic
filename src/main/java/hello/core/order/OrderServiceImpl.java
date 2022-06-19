package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // Lombok 생성자 자동 생성
public class OrderServiceImpl implements OrderService{


/*
    //필드 생성자 (권장X)
    @Autowired private final MemberRepository memberRepository;
    @Autowired private final DiscountPolicy discountPolicy;

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    //setter 에서도 의존관계 주입이 가능하다.(수정자 주입)
    // 테스트를 할때 수정자 주입으로 되어있으면 사용자가 직접 임의의 구현체를 넣어줄수가 없음. (권장X)
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
 */



    /*
     * DIP 원칙
     * 고차원 모듈은 저차원 모듈에 의존하면 안된다.
     * OrderServiceImpl은 DiscountPolicy에만 의존해야 DIP가 성립되는데
     * FixDiscountPolicy or RateDiscountPolicy 둘에도 의존하기 떄문에 DIP에 위반한다.
     * 고로 DIP를 지키기 위해 외부에서 구현체를 주입해줘야함.
     * */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // @Autowired가 생략 되어도 @Component가 있는 클래스의 생성자 메서드가 하나일때는 스프링이 알아서 Autowired 처리 해준다.
    // Lombok @RequiredArgsConstructor을 통해 생성자 자동 생성
    /*
        public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
