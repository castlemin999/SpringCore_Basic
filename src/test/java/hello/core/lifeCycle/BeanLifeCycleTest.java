package hello.core.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    /**
     * 생성자 주입: 객체의 생성, 의존관계 주입이 동시에 일어남
     * setter, Field 주입: 객체의 생성 -> 의존관계 주입으로 라이프 사이클이 나누어져 있음
     * 생성자에서 초기화 작업은 필드같은 간단 초기화는 괜찮지만
     * 데이터베이스 커넥션풀을 설정하 것과 같은 무거운 작업을 하는것은 생성자에서 초기화 하는것보다
     * 분리해서 해주는게 유지보수 차원에서 더 좋다.
     * 따라서 스프링빈의 생명주기에 맞춰
     * 빈 객체 생성 -> 의존 관계 주입이 끝나고
     * 스프링에서 콜백을 주고 그때 무거운 초기화 작업을 진행한다.
     */

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{
        // 초기화 종료 메서드 설정
        // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("www.naver.com");
            return networkClient;
        }
    }
}
