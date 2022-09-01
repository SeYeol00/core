package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository =  new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //여기로 타고 들어옴, OCP,DIP를 위반하지 않는다. 즉 필드에 넣는 구현체 값을 컨피규레이션에서 따로 지정
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //파이널은 값이 무조건 필요하므로 OCP, DIP 위반이 된다. 그래서 아래처럼 쓴다.

    //근데 이러면 구현체가 없어서 NullPointerException인데?
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //생성자 주입, Injection
    //생성자로 주입한다.
    //내가 썼던 @Autowired는 스프링 내부에 앱 컨피규레이션을 만들고 거기에 넣어둠





    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
         Member member =  memberRepository.findById(memberId);

          int discountPrice = discountPolicy.discount(member,itemPrice);

          return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
