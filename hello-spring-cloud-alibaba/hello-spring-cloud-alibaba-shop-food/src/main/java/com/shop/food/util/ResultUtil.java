package com.shop.food.util;

public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setStatus(200);
        result.setMessage("成功");
        result.setData(object);
        return result;
    }

    public static Result success(String message) {
        Result result = new Result();
        result.setStatus(200);
        result.setMessage("成功");
        result.setData(message);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer status, String msg) {
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(msg);
        return result;
    }
}
