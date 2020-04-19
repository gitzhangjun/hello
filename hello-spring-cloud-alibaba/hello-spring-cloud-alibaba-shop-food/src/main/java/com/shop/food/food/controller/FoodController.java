package com.shop.food.food.controller;

import com.shop.food.food.entity.Food;
import com.shop.food.food.service.FoodService;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 菜品信息控制层
 *
 * @author makejava
 * @since 2020-03-15 23:10:10
 */
@Validated
@Slf4j
@Api(tags = "菜品相关接口")
@RestController
@RequestMapping("food")
public class FoodController {
    /**
     * 服务对象
     */
    @Resource
    private FoodService foodService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Valid
    @GetMapping("selectOne")
    @ApiOperation(value ="通过ID查询菜品信息", notes = "通过ID查询菜品信息")
    @ApiImplicitParam(paramType = "query", name = "id", value = "菜品id", required = true)
    public Result<Food> selectOne(Integer id) {

        Food food = this.foodService.queryById(id);
        if( Objects.isNull(food) ) {
            String msg = "未找到对应菜品信息";
            return ResultUtil.error(1, msg);
        }
        return ResultUtil.success(food);
    }

    /**
     * 添加菜品
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "添加菜品", notes = "根据传入的菜品基本信息，创建新菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "courseName",
                    value = "菜品名字", required = true),
            @ApiImplicitParam(paramType = "query", name = "introduce",
                    value = "菜品简介", required = true),
            @ApiImplicitParam(paramType = "query", name = "picture",
                    value = "图片地址", required = true, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "retailPrice",
                    value = "价格", required = true, defaultValue = "0.0")
    })
    @RequestMapping(value = "/addFood", method = RequestMethod.POST)
    public Result<Food> regFood(@Valid @Size(min = 3 , max = 20 ,message = "菜品名长度需在3-50范围内") String courseName ,
                                  @Valid @Size(min = 3 , max = 250 ,message = "菜品简介长度需在3-250范围内") String introduce ,
                                  @Valid @Size(min = 3 , max = 250 ,message = "图片地址长度需在3-250范围内") String picture ,
                                  @Valid @NotNull(message = "价格不能为空")  Double retailPrice) {


        Food food = new Food();
        food.setFoodName(courseName);
        food.setIntroduce(introduce);
        food.setPicture(picture);
        food.setCreateTime(new Date(System.currentTimeMillis()));
        Food res = this.foodService.insert(food);

        return ResultUtil.success(res);
    }

    /**
     * 更新菜品信息
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "更新菜品信息", notes = "根据上传信息更新菜品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "courseName",
                    value = "菜品名字", required = true),
            @ApiImplicitParam(paramType = "query", name = "introduce",
                    value = "菜品简介", required = true),
            @ApiImplicitParam(paramType = "query", name = "picture",
                    value = "图片地址", required = true, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "retailPrice",
                    value = "价格", required = true, defaultValue = "0.0")
    })
    @RequestMapping(value = "/changePasswd", method = RequestMethod.POST)
    public Result<Food> changePasswd(@Valid @Size(min = 3 , max = 20 ,message = "菜品名长度需在3-50范围内") String courseName ,
                                     @Valid @Size(min = 3 , max = 250 ,message = "菜品简介长度需在3-250范围内") String introduce ,
                                     @Valid @Size(min = 3 , max = 250 ,message = "图片地址长度需在3-250范围内") String picture ,
                                     @Valid @NotNull(message = "价格不能为空")  Double retailPrice ) {
            Food food = new Food();
            food.setFoodName(courseName);
            food.setIntroduce(introduce);
            food.setPicture(picture);
            food = foodService.update(food);
        return ResultUtil.success(food);
    }

    /**
     * 菜品信息查询
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "菜品信息查询", notes = "菜品信息查询，支持分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "account",
                    value = "用户名", required = true ) ,
            @ApiImplicitParam(paramType = "query", name = "page",
                    value = "页码", required = false,defaultValue = "20"),
            @ApiImplicitParam(paramType = "query", name = "limit",
                    value = "每页数据大小", required = false,defaultValue = "1")
    })
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Result<Food> query(String courseName,
                              @Valid @NotNull(message = "页码不能为空")  int page,
                              @Valid @Max(value = 50,message = "每页不能超过50条数据")
                               @RequestParam(value = "limit",required = false , defaultValue = "20")  int limit ) {

        List<Food> cours = this.foodService.queryAllByLimit(courseName , page - 1, limit );
        if(cours.size() > 0 ) {
            return ResultUtil.success(cours);
        } else {
            return ResultUtil.error(400,"未找到相关信息");
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
            @ApiImplicitParam(paramType = "query", name = "courseName",
                    value = "菜品名", required = false )
    })
    @RequestMapping(value = "/queryCount", method = RequestMethod.POST)
    public Result<Integer> queryCount( @Valid @Size(max = 30 ,message = "输入信息有误")  String courseName) {
        System.out.println("courseName:" + courseName);
        int size = this.foodService.queryCount(courseName);
        return ResultUtil.success(size);
    }

    /**
     * 删除菜品信息
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "删除菜品", notes = "删除菜品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id",
                    value = "菜品ID", required = true)
    })
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result<Food> delete(@Valid @NotNull(message = "输入信息有误") Integer id ) {

        Food food = this.foodService.queryById(id);

        if(null == food || "".equals(food.getFoodName())) {
            return ResultUtil.error(400,"未找到相关信息");
        } else {
            if ( this.foodService.deleteById(food.getId()) ) {
                return ResultUtil.success("删除成功");
            }else {
                return ResultUtil.success("删除失败");
            }
        }
    }
}