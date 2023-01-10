package cn.cocowwy.cocobootwebstarter.annotation.serializer;

import cn.cocowwy.cocobootwebstarter.annotation.PrivacyEncrypt;
import cn.cocowwy.cocobootwebstarter.annotation.enums.PrivacyTypeEnum;
import cn.cocowwy.common.util.PrivacyUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/10
 */
public class PrivacySerializer extends JsonSerializer<String> implements ContextualSerializer {

    /**
     * 脱敏类型
     */
    private PrivacyTypeEnum type;

    /**
     * 前置保留字段长，type = CUSTOMER 该值有效
     */
    private int prefixNoHideLen;

    /**
     * 后置保留字段长， type = CUSTOMER 该值有效
     */
    private int suffixNoHideLen;

    /**
     * 打码字符串
     */
    private String symbol;

    public PrivacySerializer() {
    }

    public PrivacySerializer(PrivacyTypeEnum type, int prefixNoHideLen, int suffixNoHideLen, String symbol) {
        this.type = type;
        this.prefixNoHideLen = prefixNoHideLen;
        this.suffixNoHideLen = suffixNoHideLen;
        this.symbol = symbol;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (!StringUtils.isEmpty(s) && type != null) {
            switch (type) {
                case CUSTOMER:
                    jsonGenerator.writeString(PrivacyUtil.hideValue(s, prefixNoHideLen, suffixNoHideLen, symbol));
                    break;
                case PHONE:
                    jsonGenerator.writeString(PrivacyUtil.hidePhone(s));
                    break;
                case EMAIL:
                    jsonGenerator.writeString(PrivacyUtil.hideEmail(s));
                    break;
                default:
                    throw new IllegalArgumentException("unknown privacy type enum " + type);
            }
        }
    }

    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider,
                                              final BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                PrivacyEncrypt privacyEncrypt = beanProperty.getAnnotation(PrivacyEncrypt.class);
                if (privacyEncrypt == null) {
                    privacyEncrypt = beanProperty.getContextAnnotation(PrivacyEncrypt.class);
                }
                if (privacyEncrypt != null) {
                    return new PrivacySerializer(privacyEncrypt.type(), privacyEncrypt.prefixNoHideLen(),
                            privacyEncrypt.suffixNoHideLen(), privacyEncrypt.symbol());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
