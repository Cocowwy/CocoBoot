package cn.cocowwy.common.util;

/**
 * 【工具】隐私数据隐藏工具类
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/10
 */
public class PrivacyUtil {

    private static final String DEFAULT_SYMBOL = "*";

    /**
     * 隐藏手机号中间四位
     */
    public static String hidePhone(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 隐藏邮箱
     */
    public static String hideEmail(String email) {
        return email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
    }


    /**
     * 对字符串进行脱敏操作
     * @param origin          原始字符串
     * @param prefixNoHideLen 左侧需要保留几位明文字段
     * @param suffixNoHideLen 右侧需要保留几位明文字段
     * @param symbol         用于遮罩的字符串, 如'*'
     * @return 脱敏后结果
     */
    public static String hideValue(String origin, int prefixNoHideLen, int suffixNoHideLen, String symbol) {
        if (origin == null) {
            return null;
        }

        symbol = symbol == null ? DEFAULT_SYMBOL : symbol;

        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = origin.length(); i < n; i++) {
            if (i < prefixNoHideLen) {
                sb.append(origin.charAt(i));
                continue;
            }
            if (i > (n - suffixNoHideLen - 1)) {
                sb.append(origin.charAt(i));
                continue;
            }
            sb.append(symbol);
        }
        return sb.toString();
    }
}
