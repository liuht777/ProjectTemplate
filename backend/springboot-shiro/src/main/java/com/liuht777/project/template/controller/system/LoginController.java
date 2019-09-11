package com.liuht777.project.template.controller.system;

import com.iflytek.lingxi.common.util.constant.DefaultErrorCode;
import com.iflytek.lingxi.common.util.web.Result;
import com.liuht777.project.template.config.shiro.SecurityHelper;
import com.liuht777.project.template.config.shiro.SessionUser;
import com.liuht777.project.template.vo.system.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户登录
 *
 * @author liuht
 * 2018/10/19 17:45
 */
@Api(tags = "用户登录")
@RestController
@RequestMapping("/api/v1/authc")
public class LoginController {

    /**
     * 用户登录
     *
     * @param param 登录参数
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping("/login")
    public ResponseEntity<Result> authLogin(@Valid @RequestBody LoginParam param) {
        final UsernamePasswordToken token = new UsernamePasswordToken(param.getAccount(), param.getPassword());
        SecurityUtils.getSubject().login(token);
        return ResponseEntity.ok(Result.success(param.getAccount()));
    }

    /**
     * 查询当前登录用户的信息
     *
     * @return 登录用户
     */
    @ApiOperation(value = "查询当前登录用户的信息", notes = "查询当前登录用户的信息")
    @GetMapping("/userinfo")
    public ResponseEntity<Result> getUserInfo() {
        final SessionUser loginUser = SecurityHelper.getLoginUser();
        if (loginUser == null) {
            Result.failure(DefaultErrorCode.NO_LOGIN);
        }
        return ResponseEntity.ok(Result.success(loginUser));
    }

    /**
     * 未登录
     *
     * @return 未登录提示信息
     */
    @ApiOperation(value = "未登录提示信息", notes = "未登录提示信息")
    @GetMapping("/nologin")
    public ResponseEntity<Result> nologin() {
        return ResponseEntity.ok(Result.failure(DefaultErrorCode.NO_LOGIN));
    }

    /**
     * 没有权限访问地址
     *
     * @return 没有权限提示信息
     */
    @ApiOperation(value = "没有权限返回提示信息", notes = "没有权限返回提示信息")
    @GetMapping("/unauthorized")
    public ResponseEntity<Result> unauthorized() {
        return ResponseEntity.ok(Result.failure(DefaultErrorCode.ACCESS_DENIED));
    }

    /**
     * 登出
     *
     * @return 退出登录结果
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping("/logout")
    public ResponseEntity<Result> logout() {
        SecurityUtils.getSubject().logout();
        return ResponseEntity.ok(Result.success(null));
    }
}
