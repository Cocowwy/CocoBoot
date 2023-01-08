package cn.cocowwy.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 【工具】Steam操作的工具
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/6
 */
public class StreamUtils {

    /**
     * 根据字段去重
     * distinct by key
     * Demo: xx.stream().filter(distinctByKey(Person::getName))
     *
     * @param keyExtractor keyExtractor
     * @return Predicate
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
