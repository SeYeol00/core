package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

//    private final ObjectProvider<MyLogger> loggerProvider;
    private final MyLogger logger;


    public void logic(String id) {
//        MyLogger logger = loggerProvider.getObject();
        //요청이 왔을 때 프로바이더에서 꺼낸다.
        //빈의 생성을 지연시킴
        logger.log("service id = "+id);
    }
}
