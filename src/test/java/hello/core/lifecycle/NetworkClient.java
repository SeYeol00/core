package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{//implements InitializingBean, DisposableBean {
                                    //이건 사실 거의 사용하지 않음 오래된 기술
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = "+ url);
//        connect();
//        call("초기화 연결 메세지");
    }


    public void setUrl(String url) {
        this.url = url;
    }

    //서비스를 시작할 때 호출
    public void connect(){
        System.out.println("connect: "+url);
    }

    public void call(String message){
        System.out.println("call: "+ url+" message = "+ message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close "+url);
    }

    //이걸 사용하자, 스프링 권장 사항
    @PostConstruct//의존관계 주입이 끝나면 호출하겠다.
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }
    //이걸 사용하자, 스프링 권장 사항
    @PreDestroy//빈이 종료될 때 호출하겠다.
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }


//    @Override//의존관계 주입이 끝나면 호출하겠다.
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    @Override//빈이 종료될 때 호출하겠다.
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }
}
