package com.util;

/**
 * DATE:2014/12/2
 * TIME:1:31
 * Created by guofan on 2014/12/2
 */
@SuppressWarnings("unused")
public class Result {
    boolean status;
    String msg;

    public Result() {
        status = false;
        msg = "";
    }

    public Result(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toJson() {
        return "{" + "\"status\":\"" + status + "\"," + "\"msg\":\"" + msg + "\"}";
    }
}
