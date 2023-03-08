# coco-boot-web-starter

## 🍬 Feature1  web接口返回模糊化字段
**@PrivacyEncrypt** 标注在字段上，web接口返回序列化时自动模糊化
```java
@Data
public class Person {
    @PrivacyEncrypt(type = PrivacyTypeEnum.CUSTOMER, prefixNoHideLen = 1, suffixNoHideLen = 2)
    private String name;
    @PrivacyEncrypt(type = PrivacyTypeEnum.PHONE)
    private String mobile;
    @PrivacyEncrypt(type = PrivacyTypeEnum.EMAIL)
    private String email;
}
```

## Feature2  增加HTTP接口用于shutdown服务，注入bean即可
新增通过HTTP请求 shutdown服务的hook接口  
使用姿势如下：
```java
@Configuration
public class BeanConfig {

    @Bean
    public HttpShutdownHook httpShutdownHook() {
        return new HttpShutdownHook();
    }
}

```
请在配置类中手动注入这个接口即可  
**通过访问 HTTP 接口：** 
```java
/shutdownContext
```
即可"杀死服务"
需要注意的是⚠️：**如果使用了 context-path 请求路径请自动补齐，如果使用了拦截器等进行接口拦截，请手动放行该接口。** 
