package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration

// @Component 가 붙어있는 클래스를 읽어서 스프링에 Bean을 등록해준다.
@ComponentScan(
        // 시작하는 위치 (member 포함 하위 패키지가 componentscan의 위치가 된다.)
        // 지정하지 않는다면 선언한 곳 부터 탐색
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        // @Configuration 이 붙은 클래스는 필터링 한다. (AppConfig 예제 코드를 위해서)
        // @Configuration에도 @Component가 붙어있다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


}
