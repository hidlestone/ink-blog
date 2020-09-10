package com.payn.ink.service.common;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 加解密测试
 * 
 * @author payn
 * @date 2020/9/9 22:59 
*/
public class PasswordTest extends InkblogApplicationTests {
    @Autowired
    private StringEncryptor encryptor;

    /**
     * 生成加密密码
     */
    @Test
    public void testGeneratePassword() {
        // 你的邮箱密码
        String password = "ysqsmdulapiabdbb";
        //RyJ5OWCNgcZsp90lT6etm7VC+96O5VIz+Fpa1EuWdcc=
        // 加密后的密码(注意：配置上去的时候需要加 ENC(加密密码))
        String encryptPassword = encryptor.encrypt(password);
        String decryptPassword = encryptor.decrypt(encryptPassword);

        System.out.println("password = " + password);
        System.out.println("encryptPassword = " + encryptPassword);
        System.out.println("decryptPassword = " + decryptPassword);
    }
}
