package com.shop.course.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * http请求返回的最外层对象
 * Created by zhangjun
 */
@Setter
@Getter
@ToString
public class Result<T> {
    /** 错误码. */
    private Integer status;

    /** 提示信息. */
    private String message;

    /** 具体的内容. */
    private T data;
}
