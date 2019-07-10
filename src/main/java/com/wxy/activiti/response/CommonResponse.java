package com.wxy.activiti.response;

import lombok.Data;

/**
 * @Author wxy
 * @Date 19-7-10 下午3:11
 * @Description TODO
 **/
@Data
public class CommonResponse {
    private Integer code;
    private String msg;
    private Object data;

    public CommonResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
