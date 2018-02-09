package com.zhihao.miao.shop.vo;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 *
 * 请求的响应
 * @Date :2018/2/6
 * @since 1.0
 */
public class Response {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    private String status;
    private String message;

    public Response() {

    }

    public Response(String status) {
        this.status = status;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}

