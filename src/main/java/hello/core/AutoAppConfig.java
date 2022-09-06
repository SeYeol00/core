package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(//있는 클래스들을 스프링 빈으로 자동으로 등록시켜줌
        //basePackages = "hello.core.member",
        //우선적으로 컴포넌트 스캔을 돌릴 패키지를 설정한다.
        //여러 개를 둘 수도 있다.
        //basePackageClasses = AutoAppConfig.class,
        //컴포넌트 스캔을 시작할 클래스 지정도 된다.
        //지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
        //등록을 제외할 컴포넌트를 등록한다.
        //실무에서는 굳이 이렇게 안하고 그냥 스캔함
        //우리는 예제 남겨놀라고 한 거임
)
public class AutoAppConfig {

    //@Bean(name = "memoryMemberRepository")
//    @Bean
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }//이름이 같으면 수동 빈 등록이 자동 빈 등록을 오버라이딩 한다.
    //최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 이름이 같으면 충돌하여 오류를 내도록 설계하였다.
}
