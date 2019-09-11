package com.liuht777.project.template.vo.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 登录参数
 *
 * @author liuht
 * @create 2019-09-11 15:26
 */
@Data
public class LoginParam {

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号", required = true)
    @NotEmpty(message = "登录账号不能为空")
    private String account;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码", required = true)
    @NotEmpty(message = "登录密码不能为空")
    private String password;
}
