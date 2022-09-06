package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient client = ac.getBean(NetworkClient.class);

        ac.close();//ApplicationContext를 닫는 메서드

    }

    @Configuration
    static class LifeCycleConfig{
        //스프링 빈은 "객체생성 -> 의존 관계" 주입의 라이프 사이클을 갖는다.

        //스프링 빈의 이벤트 라이브사이클
        //"스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 ->
        // 초기화 콜백 -> 사용 -> 소멸 전 콜백 -> 스프링 종료"
        //보통 생성자 주입 방식은 빈 생성과 동시에 의존관계 주입이 된다.

        //@Bean(initMethod = "init",destroyMethod = "close")//매서드 이름을 자유롭게 줄 수 있음, 스프링에 의존하지 않음, 외부 라이브러리에 적용 가능, 종료 메서드 추론 기능도 존재
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("https://hello-spring.dev");
            return networkClient;
        }
    }
}
