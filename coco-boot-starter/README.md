# coco-boot-starter

## 🍬 Feature1
**@AfterRunnerDo** 标记在指定的方法上（前提是该方法所在的类是一个Bean），能在项目启动后执行该方法
```java
@Service
@Slf4j
public class AfterRunnerDoTests {
    @AfterRunnerDo(blockOnError = false, sort = 100)
    public void testAfterRunnerDoMethod100() {
        log.info("This is Cocowwy 🎃 100");
    }

    @AfterRunnerDo(blockOnError = false, sort = 99)
    public void testAfterRunnerDoMethod99() {
        log.info("This is Cocowwy 🎃 99");
    }
}
```
注意：
该注解必须标记在public方法上
blockOnError： 表示发生异常时是否阻碍容器的启动  
sort： sort的值越大，执行的优先级越高


## 🍬 Feature2
**SpringHolder** 提供Bean获取类，可获取到Bean  
```
cn.cocowwy.cocobootstarter.holder.SpringHolder
``` 
