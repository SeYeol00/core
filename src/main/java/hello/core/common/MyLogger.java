package hello.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {//프록시 모드 추가하면 가짜 프록시 클래스를 만들어두고 HTTP Request가 올 때 주입한다.
                        //이것이 다형성과 DI 컨테이너가 가진 큰 장점이다. 가짜 프록시는 내부에 위임 로직이 있다.
                        // 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리를 한다는 점이다.
                        // 다만 이런 스코프는 꼭 필요한 곳에서만 사용하자.
    private String uuid;
    private String requestUrl;


    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestUrl+"] "+message);
    }
    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create:"+this);

    }
    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean close:"+this);

    }
}
