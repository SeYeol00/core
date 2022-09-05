package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredTest(){
        AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        //null 값이 존재하는 @Autowired 옵션 제어
        //Member는 스프링 빈이 아니다.
        @Autowired(required = false)//기억나지? 이거 없어도 된다 느낌이다. 멀티파트파일 생각해보자
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }//없으면 출력 자체가 안 됨

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }// 호출은 되는데 null로 들어온다.
        
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }// 값이 없으면 Optional.empty로 들어옴

    }
}
