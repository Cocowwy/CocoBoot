package cn.cocowwy.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 测试工程
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan("cn.cocowwy.test.*")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

//    @Bean
//    public ExplainInterceptor explainInterceptor() {
//        ExplainInterceptor explainInterceptor = new ExplainInterceptor();
////        explainInterceptor.setPrintStack(false);
////        explainInterceptor.setSubscribeType(asList("A"));
//        return explainInterceptor;
//    }

}
