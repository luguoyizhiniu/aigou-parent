package cn.itsource.aigou.util;

/**
 * @Author: wenbing
 * @Date: 2019/2/22 15:07
 * @Version 1.0
 */
public class AjaxResult {
    private boolean success = true;
    private String message = "操作成功";

    private Object object;//对象值:供我们在返回前台的时候，可以返回一个对象


    public static AjaxResult me() {
        return new AjaxResult();
    }

    public boolean isSuccess() {
        return success;
    }

    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public AjaxResult setObject(Object object) {
        this.object = object;
        return this;
    }
}
