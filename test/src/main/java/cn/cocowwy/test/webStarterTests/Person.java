package cn.cocowwy.test.webStarterTests;

import cn.cocowwy.cocobootwebstarter.annotation.PrivacyEncrypt;
import cn.cocowwy.cocobootwebstarter.annotation.enums.PrivacyTypeEnum;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/1/10
 */
public class Person {
    @PrivacyEncrypt(type = PrivacyTypeEnum.CUSTOMER, prefixNoHideLen = 1, suffixNoHideLen = 2)
    private String name;
    @PrivacyEncrypt(type = PrivacyTypeEnum.PHONE)
    private String mobile;
    @PrivacyEncrypt(type = PrivacyTypeEnum.EMAIL)
    private String email;
    @PrivacyEncrypt(type = PrivacyTypeEnum.ALL)
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
