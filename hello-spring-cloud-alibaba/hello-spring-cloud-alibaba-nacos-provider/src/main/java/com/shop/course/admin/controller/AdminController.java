package com.shop.course.admin.controller;

import com.alibaba.nacos.common.util.Md5Utils;
import com.shop.course.admin.entity.Admin;
import com.shop.course.admin.entity.ValidationModel;
import com.shop.course.admin.service.AdminService;
import com.shop.course.util.IDWorker;
import com.shop.course.util.Result;
import com.shop.course.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * (Admin)管理员信息控制器
 *
 * @author zhangjun
 * @since 2020-03-15 13:00:57
 */
@Validated
@Slf4j
@Api(tags = "管理员信息维护")
@RestController
@RequestMapping("admin")
public class AdminController {

    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    private IDWorker idGenerate = new IDWorker(0, 5);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Valid
    @GetMapping("selectOne")
    @ApiOperation(value ="通过ID查询管理员用户信息", notes = "通过ID查询管理员用户信息")
    @ApiImplicitParam(paramType = "query", name = "id", value = "管理员id", required = true)
    public Result<Admin> selectOne(Long id) {

        Admin admin = this.adminService.queryById(id);

        if( Objects.isNull(admin) ) {
            String msg = "未找到对应管理员信息";
            return ResultUtil.error(1, msg);
        }
        admin.setPasswd("");
        return ResultUtil.success(admin);
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
    @RequestMapping(value = "/queryByAccount", method = RequestMethod.GET)
    public Result<Admin> queryByAccount(@Valid @Size(min = 3 , max = 20 ,message = "账号长度需在3-20范围了") String account) {


        Admin admin = this.adminService.queryByAccount(account);

        if( Objects.isNull(admin) ) {
            String msg = "用户信息不存在";
        }
        admin.setPasswd("");
        return ResultUtil.error(0, admin.masking());

    }


    /**
     * 登录
     *
     * @param account 主键
     * @return 单条数据
     */
    @Valid
    @ApiOperation(value = "管理员登录", notes = "根据用户名密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account",
                    value = "用户名", required = true),
            @ApiImplicitParam(paramType = "query", name = "passwd",
                    value = "密码", required = true)
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Admin> login(@Valid @Size(min = 3 , max = 20 ,message = "账号长度需在3-20范围了") String account ,
                                  @Valid @Size(min = 3 , max = 20 ,message = "密码长度需在3-20范围了") String passwd ) {

        Admin admin = this.adminService.login(account,Md5Utils.getMD5(passwd.getBytes()));
        if( Objects.isNull(admin) ) {
            String msg = "登录失败";
            return ResultUtil.error(1, msg);
        }

        return ResultUtil.success(admin);
    }



    /**
     * 管理员注册
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "新增管理员", notes = "根据传入的用户名和密码添加一个管理员用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account",
                    value = "用户名", required = true),
            @ApiImplicitParam(paramType = "query", name = "passwd",
                    value = "密码", required = true),
            @ApiImplicitParam(paramType = "query", name = "mobile",
                    value = "手机号码", required = true, defaultValue = "null")
    })
    @RequestMapping(value = "/regAdmin", method = RequestMethod.POST)
    public Result<Admin> regAdmin(@Valid @Size(min = 3 , max = 20 ,message = "账号长度需在3-20范围了") String account ,
                                  @Valid @Size(min = 3 , max = 20 ,message = "密码长度需在3-20范围了") String passwd ,
                                  @Valid @Size(min = 6 , max = 20 ,message = "电话号码长度需在6-20范围了")  String mobile) {

        Admin admin = this.adminService.queryByAccount(account);
        if(admin != null){
            return ResultUtil.error(1,"登录账号已经被占用");
        }
        Admin newAdmin = new Admin();
        newAdmin.setId(idGenerate.nextId());
        newAdmin.setAccount(account);
        newAdmin.setPasswd(Md5Utils.getMD5(passwd.getBytes()));
        newAdmin.setMobile(mobile);
        newAdmin.setCreateTime(new Date(System.currentTimeMillis()));
        Admin res = this.adminService.insert(newAdmin);

        return ResultUtil.success(res.masking());
    }

    /**
     * 修改管理员密码
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "修改管理员密码", notes = "根据用户ID修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id",
                    value = "用户id", required = true),
            @ApiImplicitParam(paramType = "query", name = "oldPasswd",
                    value = "旧密码", required = true),
            @ApiImplicitParam(paramType = "query", name = "newPasswd",
                    value = "新密码", required = true)
    })
    @RequestMapping(value = "/changePasswd", method = RequestMethod.POST)
    public Result<Admin> changePasswd(@Valid @NotNull(message = "输入信息有误") Long id ,
                                  @Valid @Size(min = 3 , max = 20 ,message = "输入信息有误") String oldPasswd ,
                                  @Valid @Size(min = 3 , max = 20 ,message = "密码长度需在3-20范围了") String newPasswd ) {

        Admin admin = this.adminService.queryById(id);

        if(admin.getPasswd().equals(Md5Utils.getMD5(oldPasswd.getBytes()))){
            String passwd = Md5Utils.getMD5(newPasswd.getBytes());
            admin.setPasswd(passwd);
            Admin res = this.adminService.update(admin);
            if (res.getPasswd().equals(passwd))
                    return ResultUtil.success("密码修改成功");
        } else {
            ResultUtil.error(1,"旧密码错误");
        }
        return ResultUtil.error(1,"密码修改失败");
    }

    /**
     * 管理员信息查询
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "管理员信息查询", notes = "管理员信息查询，支持分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account",
                    value = "用户名", required = false ) ,
            @ApiImplicitParam(paramType = "query", name = "page",
                    value = "页码", required = false,defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit",
                    value = "每页数据大小", required = false,defaultValue = "20")
    })
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Result<Admin> query( @Valid @Size(min = 0 ,message = "输入信息有误")  String account,
                                @Valid @NotNull(message = "页码不能为空")  int page,
                                @Valid @Max (value = 50,message = "每页不能超过50条数据")
                                @RequestParam(value = "limit",required = false , defaultValue = "20")  int limit ) {

        List<Admin> admins = this.adminService.queryAllByLimit(account , page - 1, limit );
        if(admins.size() > 0 ) {
            return ResultUtil.success(admins);
        } else {
           return ResultUtil.error(1,"未找到相关信息");
        }
    }



    /**
     * 管理员信息总条数查询
     *
     * @param
     *
     * @return int
     */

    @ApiOperation(value = "管理员信息总条数查询", notes = "管理员信息总条数查询,可带账号模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account",
                    value = "用户名", required = false )
    })
    @RequestMapping(value = "/queryCount", method = RequestMethod.GET)
    public Result<Integer> queryCount( @Valid @Size(max = 30 ,message = "输入信息有误")  String account) {
        System.out.println("account:" + account);
        int size = this.adminService.queryCount(account);
        return ResultUtil.success(size);
    }

    /**
     * 删除管理员
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "删除管理员", notes = "删除管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id",
                    value = "用户ID", required = true)
    })
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result<Admin> delete(@Valid @NotNull(message = "输入信息有误") Long id ) {

        Admin admin = this.adminService.queryById(id);

        if(null == admin || "".equals(admin.getAccount())) {
               return ResultUtil.error(1,"未找到相关信息");
        } else {
            if ( this.adminService.deleteById(admin.getId()) ) {
                return ResultUtil.success("删除成功");
            }else {
                return ResultUtil.error(1,"删除失败");
            }
        }
    }


    /**
     * 测试校验规则接口
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "校验规则测试接口【废弃】", notes = "校验规则测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query" , name = "validationModel",
                    value = "校验规则对象" , dataType = "ValidationModel" , required = false)
    })
    @RequestMapping(value = "/validation", method = RequestMethod.POST)
    public Result<Admin> validation(@Validated ValidationModel validationModel) {

        log.info("接收到参数为 [{}]", validationModel);

        return ResultUtil.success(validationModel);
    }


}