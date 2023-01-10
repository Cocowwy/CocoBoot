package cn.cocowwy.cocobootstarter.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 * 仅标记在方法上面，会在SpringBoot项目启动后自动执行该方法
 * 注意，该方法执行不携带参数
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AfterRunnerDo {
    /**
     * 是否生效
     */
    boolean effect() default true;

    /**
     * 执行失败是否阻断项目启动
     */
    boolean blockOnError() default true;

    /**
     * sort越大，执行优先级越高
     */
    int sort() default 0;
}
