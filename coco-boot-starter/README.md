# coco-boot-starter

## 🍬 Feature1
**@AfterRunnerDo** 标记在指定的方法上（前提是该方法所在的类是一个Bean），能在项目启动后执行该方法
```java
@Service
@Slf4j
public class AfterRunnerDoTests {
    @AfterRunnerDo(blockOnError = false, sort = 100)
    public void testAfterRunnerDoMethod(String[] args) {
        log.info("This is Cocowwy 🎃");
    }
}
```


## 🍬 Feature2
**SpringHolder** 提供Bean获取类，可获取到Bean  
```
cn.cocowwy.cocobootstarter.holder.SpringHolder
``` 
