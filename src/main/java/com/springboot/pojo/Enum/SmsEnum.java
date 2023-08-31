package com.springboot.pojo.Enum;

public enum SmsEnum {
    AUTH_CODE("SMS_286075057"),
    SMS_LOVE("SMS_463215117");


    private String code;

    SmsEnum(String code ) {
        this.code = code;

    }
    public String getCode() {

        return code;
    }

}
