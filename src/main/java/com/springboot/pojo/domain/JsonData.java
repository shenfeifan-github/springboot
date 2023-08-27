package com.springboot.pojo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

@Data
@Accessors(chain = true)
public class JsonData<T> {

    private int code;

    private String message;

    private int count;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public JsonData() {}

    public JsonData(int ret) {
        this.code = ret;
        this.message = "ok!";
    }
    public static JsonData success(Object object, String msg,int count) {
        JsonData jsonData = new JsonData(10200);
        jsonData.data = StringUtils.isEmpty(object) ? "" : object;
        jsonData.message = msg;
        jsonData.count = count;
        return jsonData;
    }

    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(10200);
        jsonData.data = StringUtils.isEmpty(object) ? "" : object;
        jsonData.message = msg;
        return jsonData;
    }

    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(10200);
        jsonData.message = "";
        jsonData.data = StringUtils.isEmpty(object) ? null : object;
        return jsonData;
    }

    public static JsonData success() {
        return new JsonData(10200);
    }

    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(10500);
        jsonData.message = msg;
        return jsonData;
    }
    public static JsonData fail(Object object, String msg) {
        JsonData jsonData = new JsonData(10500);
        jsonData.data = StringUtils.isEmpty(object) ? "" : object;
        jsonData.message = msg;
        return jsonData;
    }

    public static JsonData fail(Integer httpStatus, String msg) {
        JsonData jsonData = new JsonData(httpStatus);
        jsonData.message = msg;
        return jsonData;
    }

    public JsonData(int code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = StringUtils.isEmpty(data) ? null : data;
    }
}
