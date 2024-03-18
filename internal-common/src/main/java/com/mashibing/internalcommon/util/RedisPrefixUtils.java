package com.mashibing.internalcommon.util;

public class RedisPrefixUtils {

    //    乘客验证码的前缀
    public static String verificationCodePrefix = "passenger-verification-code-";

    // token存储前缀
    public static String tokenPrefix = "token-";


    /**
     * 根据手机号生产key redis用
     * @param passengerPhone 手机号
     * @return
     */
    public static String generatorKeyByPhone(String passengerPhone){

        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 根据手机号和身份标识 生产token key （redis）
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone,String identity,String tokenType){

        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }


}
