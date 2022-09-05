package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final 붙은 필드들을 가지고 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService{

    // 자동 주입은 공통적으로 @Autowired를 사용한다. 이게 핵심.
    // 생성자 주입을 쓰는 게 베스트다. 순수한 자바 언어의 특징을 잘 살리는 방법이다.

    //private final MemberRepository memberRepository =  new MemoryMemberRepository();
    private final MemberRepository memberRepository;// final로 불변
    private final DiscountPolicy discountPolicy;// final로 불변
    // final을 쓰면 생성자에 무언가를 누락했을 때 컴파일러가 알려준다.

    //여기로 타고 들어옴, OCP,DIP를 위반하지 않는다. 즉 필드에 넣는 구현체 값을 컨피규레이션에서 따로 지정
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //파이널은 값이 무조건 필요하므로 OCP, DIP 위반이 된다. 그래서 아래처럼 쓴다.
    //근데 이러면 구현체가 없어서 NullPointerException인데?

    //필드 주입 방식 -> 코드가 간결해서 옛날에 많이 썼지만 권장하지 않음
    // 외부에서 변경이 불가능해서 테스트가 안된다.
//    @Autowired  private final MemberRepository memberRepository;
//    @Autowired  private final DiscountPolicy discountPolicy;


    //@Autowired//생성자가 하나 일때는 생략 가능, 스프링 빈으로 등록 동시에 의존관계 주입
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }//주로 생성자 주입은 불변 객체를 주입할 때 사용
    //생성자 주입, Injection
    //생성자로 주입한다.
    //내가 썼던 @Autowired는 스프링 내부에 앱 컨피규레이션을 만들고 거기에 넣어둠
    
    //자바빈 프로퍼티 규약 -> 필드에 접근할 때 게터, 세터를 이용해라


//    @Autowired 메서드 주입 -> 일반 매서드에서 주입을 받는 것, 사용하는 일이 거의 없음
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
         Member member =  memberRepository.findById(memberId);

          int discountPrice = discountPolicy.discount(member,itemPrice);

          return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
//    @Autowired //수정자 주입 방식 -> 변경이 많은 의존 곤계를 생성할 때 쓴다.
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
}
