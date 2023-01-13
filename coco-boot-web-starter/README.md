# coco-boot-web-starter

## 🍬 Feature1
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
