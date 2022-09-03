package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링이 라이브러리를 통해서 AppConfig의 다른 클래스를 하나 만들어서
//조작한 클래스를 스프링 빈으로 등록한다.
//이렇게 조작한 클래스로 싱글톤을 지킨다.
//@Configuration을 붙이지 않으면 싱글톤을 보장하지 못한다.
//스프링 설정 정도에는 항상 @Configuration을 넣자
//@Autowired는 내가 전에 쓰던 방식, 의존관계 자동 주입이다.
@Configuration
public class AppConfig {
    //스프링 컨테이너 초기 형태, 인터페이스와 구현체를 연결해준다.
    //의존관계에 대한 고민은 여기에서 전담하고 컨트롤러와 서비스는 실행에 집중한다.
    // 컨트롤러, 서비스, 레포지토리는 인터페이스로만 연결이 되어야한다.
    //인터페이스는 다 적는다.
    //여길 보면 전체적인 구조를 볼 수 있다.
    //이건 구성 영역
    //확장엔 열려있고 기능에 닫혀있다. OCP


    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
        //구현체로 메모리멤버리포지토리를 쓸게요
    }
    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }//구현체로 메모리 멤버 리포지토리 쓸거야
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }//구현체로 이 두 개를 쓸게요
    @Bean
    public DiscountPolicy discountPolicy(){
        // return new FixDiscountPolicy()
        return new RateDiscountPolicy();
    }//구현체는 픽스드 디스카운트 쓸 거야
}
