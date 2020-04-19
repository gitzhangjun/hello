package com.shop.course.admin.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 测试接口参数校验规则
 */
@Data
public class ValidationModel {
    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 邮箱
     */
    @NotBlank(message = "email 不能为空")
    @Email(message = "email 格式不正确")
    private String email;

    /**
     * 年龄
     */
    @Range(min = 18, max = 60, message = "年龄必须在 {min} 至 {max} 之间")
    private String age;

}
