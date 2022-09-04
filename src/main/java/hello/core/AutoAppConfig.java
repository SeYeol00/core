package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(//있는 클래스들을 스프링 빈으로 자동으로 등록시켜줌
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
        //등록을 제외할 컴포넌트를 등록한다.
        //실무에서는 굳이 이렇게 안하고 그냥 스캔함
        //우리는 예제 남겨놀라고 한 거임
)
public class AutoAppConfig {
}
