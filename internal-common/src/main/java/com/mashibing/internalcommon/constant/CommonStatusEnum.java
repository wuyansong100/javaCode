package com.mashibing.internalcommon.constant;

import lombok.Data;
import lombok.Getter;
import lombok.Value;


public enum CommonStatusEnum {
    /**
     * 验证码错误提示：1000-1099
     */
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),

    /**
     * Token类错误提示： 1100-1199
     */
    TOKEN_ERROR(1199,"token不正确"),

    /**
     * 用户提示 1200-1299
     */
    USER_NOT_EXISTS(1200,"当前用户不存在错误"),

    SUCESS(1,"success"),

    FAIL(0,"fail")

    ;
    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
