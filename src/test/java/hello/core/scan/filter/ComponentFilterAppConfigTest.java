package hello.core.scan.filter;

import hello.core.AutoAppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA",BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
                NoSuchBeanDefinitionException.class,
                ()->ac.getBean("beanB",BeanB.class));
    }

    @Configuration
    @ComponentScan(//제외할 어노테이션과 포함할 어노테이션을 필터링할 수 있다.
            includeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION,
                    classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION,
                    classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
