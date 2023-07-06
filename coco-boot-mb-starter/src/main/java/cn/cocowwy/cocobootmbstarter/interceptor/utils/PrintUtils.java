package cn.cocowwy.cocobootmbstarter.interceptor.utils;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/7/6
 */
public class PrintUtils {
	public static final String LINE_BREAK = "\n";
	public static final String EMPTY_STR = "";

	/**
	 * 打印栈帧信息定位问题
	 */
	public static String printStack() {
		List<StackTraceElement> stackTraces = Arrays.stream(Thread.currentThread().getStackTrace()).collect(toList());
		StringBuilder traces = new StringBuilder();
		stackTraces.forEach(it -> traces.append(it).append(LINE_BREAK));
		return traces.toString();
	}
}
