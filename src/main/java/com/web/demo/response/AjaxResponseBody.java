package com.web.demo.response;

import com.web.demo.dto.UserDto;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    List<UserDto> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UserDto> getResult() {
        return result;
    }

    public void setResult(List<UserDto> result) {
        this.result = result;
    }
}
