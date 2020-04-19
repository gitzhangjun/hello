package com.shop.food.user.controller;

import com.alibaba.nacos.common.util.Md5Utils;
import com.shop.food.user.entity.User;
import com.shop.food.user.service.UserService;
import com.shop.food.util.Result;
import com.shop.food.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-03-15 23:08:20
 */
@Validated
@Slf4j
@Api(tags = "用户信息维护")
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Valid
    @GetMapping("selectOne")
    @ApiOperation(value ="通过ID查询用户信息", notes = "通过ID查询用户信息")
    @ApiImplicitParam(paramType = "query", name = "id", value = "用户id", required = true)
    public Result<User> selectOne(Integer id) {

        User user = this.userService.queryById(id);
        if( Objects.isNull(user) ) {
            String msg = "未找到对应用户信息";
            return ResultUtil.error(1, msg);
        }
        user.setPasswd("");
        return ResultUtil.success(user.masking());
    }




    /**
     * 用户页面显示名字
     */
    private String userName;
    /**
     * 用户真实名字
     */
    private String trueName;
    /**
     * 用户手机号码
     */
    private String mobile;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户邮编
     */
    private String zipCode;
    /**
     * 用户邮编
     */
    private String deliveryAddre;

    /**
     * 用户注册
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "用户注册", notes = "根据传入的用户基本信息添加一个新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account",
                    value = "用户名", required = true),
            @ApiImplicitParam(paramType = "query", name = "passwd",
                    value = "密码", required = true),
            @ApiImplicitParam(paramType = "query", name = "userName",
                    value = "昵称", required = false,defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "trueName",
                    value = "真实姓名", required = false,defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "email",
                    value = "邮箱", required = true,defaultValue = "def@def.com"),
            @ApiImplicitParam(paramType = "query", name = "zipCode",
                    value = "邮编", required = false,defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "deliveryAddre",
                    value = "收货地址", required = false,defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "mobile",
                    value = "手机号码", required = true, defaultValue = "null")
    })
    @RequestMapping(value = "/regUser", method = RequestMethod.POST)
    public Result<User> regUser(@Valid @Size(min = 3 , max = 20 ,message = "账号长度需在3-20范围了") String account ,
                                @Valid @Size(min = 3 , max = 20 ,message = "密码长度需在3-20范围了") String passwd ,
                                @Valid @Size(min = 1 , max = 20 ,message = "用户昵称长度需在1-20范围内") String userName ,
                                String trueName ,
                                @Valid @Email(message = "邮箱格式不正确") String email ,
                                String zipCode ,
                                String deliveryAddre ,
                                String mobile) {

        User user = this.userService.queryByAccount(account);
        if(user != null){
            return ResultUtil.error(1,"登录账号已经被占用");
        }

        User newUser = new User();
        newUser.setAccount(account);
        newUser.setPasswd(Md5Utils.getMD5(passwd.getBytes()));
        newUser.setMobile(mobile);
        newUser.setUserName(userName);
        newUser.setTrueName(trueName);
        newUser.setEmail(email);
        newUser.setZipCode(zipCode);
        newUser.setDeliveryAddre(deliveryAddre);
        newUser.setCreateTime(new Date(System.currentTimeMillis()));
        User res = this.userService.insert(newUser);

        return ResultUtil.success(res);
    }


    /**
     * 用户登录账号校验
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "用户登录账号校验", notes = "根据用户登录账号查询用户是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account",
                    value = "用户名", required = true)
    })
    @RequestMapping(value = "/queryByAccount", method = RequestMethod.POST)
    public Result<User> queryByAccount(@Valid @Size(min = 3 , max = 20 ,message = "账号长度需在3-20范围了") String account) {


        User user = this.userService.queryByAccount(account);

        if( Objects.isNull(user) ) {
            String msg = "用户信息不存在";
            ResultUtil.error(1, msg);
        }
        user.setPasswd("");
        return ResultUtil.error(0, user.masking());

    }


    /**
     * 用户登录
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "用户登录", notes = "根据用户名用户密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account",
                    value = "用户名", required = true),
            @ApiImplicitParam(paramType = "query", name = "passwd",
                    value = "密码", required = true)
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<User> login(@Valid @Size(min = 3 , max = 20 ,message = "账号长度需在3-20范围了") String account ,
                                @Valid @Size(min = 3 , max = 20 ,message = "密码长度需在3-20范围了") String passwd ) {


        User user = this.userService.login(account, Md5Utils.getMD5(passwd.getBytes()));

        if( Objects.isNull(user) ) {
            String msg = "登录失败";
            return ResultUtil.error(1, msg);
        }
        user.setPasswd("");
        return ResultUtil.success(user.masking());

    }

    /**
     * 修改用户密码
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "修改用户密码", notes = "根据用户ID修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id",
                    value = "用户id", required = true),
            @ApiImplicitParam(paramType = "query", name = "oldPasswd",
                    value = "旧密码", required = true),
            @ApiImplicitParam(paramType = "query", name = "newPasswd",
                    value = "新密码", required = true)
    })
    @RequestMapping(value = "/changePasswd", method = RequestMethod.POST)
    public Result<User> changePasswd(@Valid @NotNull(message = "输入信息有误") Integer id ,
                                      @Valid @Size(min = 3 , max = 20 ,message = "输入信息有误") String oldPasswd ,
                                      @Valid @Size(min = 3 , max = 20 ,message = "密码长度需在3-20范围了") String newPasswd ) {

        User user = this.userService.queryById(id);

        if(user.getPasswd().equals(Md5Utils.getMD5(oldPasswd.getBytes()))){
            String passwd = Md5Utils.getMD5(newPasswd.getBytes());
            user.setPasswd(passwd);
            User res = this.userService.update(user);
            if (res.getPasswd().equals(passwd))
                return ResultUtil.success("密码修改成功");
        } else {
            ResultUtil.error(1,"旧密码错误");
        }
        return ResultUtil.error(1,"密码修改失败");
    }

    /**
     * 用户信息查询
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "用户信息查询", notes = "用户信息查询，支持分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account",
                    value = "用户名", required = true ) ,
            @ApiImplicitParam(paramType = "query", name = "page",
                    value = "页码", required = false,defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit",
                    value = "每页数据大小", required = false,defaultValue = "20")
    })
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Result<User> query( @Valid @Size(min = 0 ,message = "输入信息有误")  String account,
                                @Valid @NotNull(message = "页码不能为空")  int page,
                                @Valid @Max(value = 50,message = "每页不能超过50条数据")
                                @RequestParam(value = "limit",required = false , defaultValue = "20")  int limit ) {

        List<User> users = this.userService.queryAllByLimit(account , page - 1, limit );
        if(users.size() > 0 ) {
            return ResultUtil.success(users);
        } else {
            return ResultUtil.error(1,"未找到相关信息");
        }
    }



    /**
     * 用户信息总条数查询
     *
     * @param
     *
     * @return int
     */

    @ApiOperation(value = "用户信息总条数查询", notes = "用户信息总条数查询,可带账号模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account",
                    value = "用户名", required = false )
    })
    @RequestMapping(value = "/queryCount", method = RequestMethod.POST)
    public Result<Integer> queryCount( @Valid @Size(max = 30 ,message = "输入信息有误")  String account) {
        System.out.println("account:" + account);
        int size = this.userService.queryCount(account);
        return ResultUtil.success(size);
    }

    /**
     * 删除用户
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "删除用户", notes = "删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id",
                    value = "用户ID", required = true)
    })
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result<User> delete(@Valid @NotNull(message = "输入信息有误") Integer id ) {

        User user = this.userService.queryById(id);

        if(null == user || "".equals(user.getAccount())) {
            return ResultUtil.error(1,"未找到相关信息");
        } else {
            if ( this.userService.deleteById(user.getUserId()) ) {
                return ResultUtil.success("删除成功");
            }else {
                return ResultUtil.error(1,"删除失败");
            }
        }
    }
}