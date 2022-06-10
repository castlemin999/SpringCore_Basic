package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.*;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy; // DIP를 지키기 위해 누군가가 구현체를 주입해줘야함 (FixDiscountPolicy or RateDiscountPolicy)

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
