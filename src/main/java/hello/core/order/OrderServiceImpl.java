package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.*;

public class OrderServiceImpl implements OrderService{

    /*
     * DIP 원칙
     * 고차원 모듈은 저차원 모듈에 의존하면 안된다.
     * OrderServiceImpl은 DiscountPolicy에만 의존해야 DIP가 성립되는데
     * FixDiscountPolicy or RateDiscountPolicy 둘에도 의존하기 떄문에 DIP에 위반한다.
     * 고로 DIP를 지키기 위해 외부에서 구현체를 주입해줘야함.
     * */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
