package com.qf.common;

//统一结果返回json
public class JsonResult {
    private int code;//状态码
    private String msg;//描述信息
    private Object data;//返回数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //成功
    public static JsonResult setOk(Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(200);
        jsonResult.setMsg("操作成功");
        jsonResult.setData(data);
        return jsonResult;
    }
    //失败
    public static JsonResult setError(Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(404);
        jsonResult.setMsg("操作错误");
        jsonResult.setData(data);
        return jsonResult;
    }
}
