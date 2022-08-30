package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository =  new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //파이널은 값이 무조건 필요하므로 OCP, DIP 위반이 된다. 그래서 아래처럼 쓴다.
    private DiscountPolicy discountPolicy;
    //근데 이러면 구현체가 없어서 NullPointerException인데?

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
         Member member =  memberRepository.findById(memberId);

          int discountPrice = discountPolicy.discount(member,itemPrice);

          return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
