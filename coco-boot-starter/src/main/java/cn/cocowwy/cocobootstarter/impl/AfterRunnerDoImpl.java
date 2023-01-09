package cn.cocowwy.cocobootstarter.impl;

import cn.cocowwy.cocobootstarter.annotation.AfterRunnerDo;
import cn.cocowwy.cocobootstarter.holder.BeanMethodHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link AfterRunnerDo} 功能实现
 *
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/9
 */
public class AfterRunnerDoImpl implements ApplicationRunner {

    private static final Log log = LogFactory.getLog(AfterRunnerDoImpl.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<BeanMethodHolder.AfterRunnerDoMethodWrapper> methodAfterRunnerDoList = BeanMethodHolder.afterRunnerDoList();
        if (CollectionUtils.isEmpty(methodAfterRunnerDoList)) {
            return;
        }

        List<BeanMethodHolder.AfterRunnerDoMethodWrapper> available = methodAfterRunnerDoList.stream()
                .filter(it -> it.getAfterRunnerDo().effect())
                .sorted(Comparator.comparing(a -> -a.getAfterRunnerDo().sort()))
                .collect(Collectors.toList());

        for (BeanMethodHolder.AfterRunnerDoMethodWrapper methodWrapper : available) {

            Class<?>[] paramTypes = methodWrapper.getMethod().getParameterTypes();
            boolean blockOnError = methodWrapper.getAfterRunnerDo().blockOnError();
            try {
                if (paramTypes.length > 0) {
                    // method-param can not be primitive-types
                    methodWrapper.getMethod().invoke(methodWrapper.getTarget(), new Object[paramTypes.length]);
                } else {
                    methodWrapper.getMethod().invoke(methodWrapper.getTarget());
                }
            } catch (Exception e) {
                if (blockOnError) {
                    throw new RuntimeException(e);
                } else {
                    log.error("Failed to execute method  [" + methodWrapper.getMethod().getName() + "]", e);
                }
            }

        }
    }
}
