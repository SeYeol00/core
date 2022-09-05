package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    //MemberService memberService = new MemberServiceImpl();
    //OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        Long memberId = 1L;

        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);


    }
    @Test
    void fieldInjectionTest(){
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.setDiscountPolicy(new RateDiscountPolicy());
//        orderService.setMemberRepository(new MemoryMemberRepository());
        orderService.createOrder(1L,"itemA",3000);
        //필드로 값을 넣고 싶은데 넣을 방법이 없고 이러면 결국 세터를 써야한다.
        //근데 이러면 세터에 @Autowired를 붙이는게 낫지
        //임의로 생성하는 얘는 당연히 안된다.
        //그냥 쓰지 마라 필드 주입은
    }
}
