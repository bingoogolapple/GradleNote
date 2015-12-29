package cn.bingoogolapple.gradle.springmvc.dto;

public class JsonResp {
    public int code;
    public String msg;
    public Object content;

    public JsonResp() {
    }

    public JsonResp(String msg) {
        this.msg = msg;
    }
}