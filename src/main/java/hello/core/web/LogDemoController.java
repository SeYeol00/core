package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;
                //껍데기 마이로거를 주입 받아있다 의존관계 주입이 가짜 프록시 객체가 주입되는 것이다.


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
//        MyLogger myLogger = myLoggerProvider.getObject();
        //요청이 왔을 때 프로바이더에서 꺼낸다.
        //빈의 생성을 지연시킴
        String requestUrl = request.getRequestURL().toString();
        myLogger.setRequestUrl(requestUrl);

        myLogger.log("controller test");
        Thread.sleep(2000);
        logDemoService.logic("testId");
        return "OK";
    }
}
