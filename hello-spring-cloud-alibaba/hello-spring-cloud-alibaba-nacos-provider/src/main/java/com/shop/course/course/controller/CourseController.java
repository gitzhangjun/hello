package com.shop.course.course.controller;

import com.shop.course.util.IDWorker;
import com.shop.course.util.Result;
import com.shop.course.util.ResultUtil;
import com.shop.course.course.entity.Course;
import com.shop.course.course.service.CourseService;
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
 * (Course)表控制层
 *
 * @author makejava
 * @since 2020-03-15 23:10:10
 */
@Validated
@Slf4j
@Api(tags = "课件相关接口")
@RestController
@RequestMapping("course")
public class CourseController {
    /**
     * 服务对象
     */
    @Resource
    private CourseService courseService;

    private IDWorker idGenerate = new IDWorker(0, 3);
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Valid
    @GetMapping("selectOne")
    @ApiOperation(value ="通过ID查询课件信息", notes = "通过ID查询课件信息")
    @ApiImplicitParam(paramType = "query", name = "id", value = "课件id", required = true)
    public Result<Course> selectOne(Long id) {

        Course course = this.courseService.queryById(id);
        if( Objects.isNull(course) ) {
            String msg = "未找到对应课件信息";
            return ResultUtil.error(1, msg);
        }
        return ResultUtil.success(course);
    }

    /**
     * 添加课件
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "添加课件", notes = "根据传入的课件基本信息，创建新课件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "courseName",
                    value = "课件名字", required = true),
            @ApiImplicitParam(paramType = "query", name = "introduce",
                    value = "课件简介", required = true),
            @ApiImplicitParam(paramType = "query", name = "picture",
                    value = "图片地址", required = true, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "retailPrice",
                    value = "价格", required = true, defaultValue = "0.0")
    })
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public Result<Course> regCourse(@Valid @Size(min = 3 , max = 20 ,message = "课件名长度需在3-50范围内") String courseName ,
                                    @Valid @Size(min = 3 , max = 250 ,message = "课件简介长度需在3-250范围内") String introduce ,
                                    @Valid @Size(min = 3 , max = 250 ,message = "图片地址长度需在3-250范围内") String picture ,
                                    @Valid @NotNull(message = "价格不能为空")  Double retailPrice) {


        Course course = new Course();
        course.setId(idGenerate.nextId());
        course.setCourseName(courseName);
        course.setIntroduce(introduce);
        course.setRetailPrice(retailPrice);
        course.setPicture(picture);
        course.setCreateTime(new Date(System.currentTimeMillis()));
        Course res = this.courseService.insert(course);

        return ResultUtil.success(res);
    }

    /**
     * 更新课件信息
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "更新课件信息", notes = "根据上传信息更新课件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id",
                    value = "课件ID", required = false),
            @ApiImplicitParam(paramType = "query", name = "courseName",
                    value = "课件名字", required = false),
            @ApiImplicitParam(paramType = "query", name = "introduce",
                    value = "课件简介", required = false),
            @ApiImplicitParam(paramType = "query", name = "picture",
                    value = "图片地址", required = false, defaultValue = "null"),
            @ApiImplicitParam(paramType = "query", name = "retailPrice",
                    value = "价格", required = false, defaultValue = "0.0")
    })
    @RequestMapping(value = "/changeCourse", method = RequestMethod.POST)
    public Result<Course> changeCourse(@Valid @NotNull(message = "课件ID不能为空") Long id ,
                                       String courseName , String introduce , String picture , Double retailPrice ) {
            Course course = new Course();
            course.setId(id);
            course.setCourseName(courseName);
            course.setIntroduce(introduce);
            course.setPicture(picture);
            course = courseService.update(course);
        return ResultUtil.success(course);
    }

    /**
     * 课件信息查询
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "课件信息查询", notes = "课件信息查询，支持分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "courseName",
                    value = "课程名", required = false ) ,
            @ApiImplicitParam(paramType = "query", name = "page",
                    value = "页码", required = false,defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit",
                    value = "每页数据大小", required = false,defaultValue = "20")
    })
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Result<Course> query( String courseName,
                               @Valid @NotNull(message = "页码不能为空")  int page,
                               @Valid @Max(value = 50,message = "每页不能超过50条数据")
                               @RequestParam(value = "limit",required = false , defaultValue = "20")  int limit ) {

        List<Course> courses = this.courseService.queryAllByLimit(courseName , page - 1, limit );
        if(courses.size() > 0 ) {
            return ResultUtil.success(courses);
        } else {
            return ResultUtil.error(400,"未找到相关信息");
        }
    }



    /**
     * 课件信息总条数查询
     *
     * @param
     *
     * @return int
     */

    @ApiOperation(value = "课件信息总条数查询", notes = "课件信息总条数查询,可带账号模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "courseName",
                    value = "课件名", required = false )
    })
    @RequestMapping(value = "/queryCount", method = RequestMethod.GET)
    public Result<Integer> queryCount( @Valid @Size(max = 30 ,message = "输入信息有误")  String courseName) {
        System.out.println("courseName:" + courseName);
        int size = this.courseService.queryCount(courseName);
        return ResultUtil.success(size);
    }

    /**
     * 删除课件信息
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "删除课件", notes = "删除课件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id",
                    value = "课件ID", required = true)
    })
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result<Course> delete(@Valid @NotNull(message = "输入信息有误") Long id ) {

        Course course = this.courseService.queryById(id);

        if(null == course || "".equals(course.getCourseName())) {
            return ResultUtil.error(400,"未找到相关信息");
        } else {
            if ( this.courseService.deleteById(course.getId()) ) {
                return ResultUtil.success("删除成功");
            }else {
                return ResultUtil.success("删除失败");
            }
        }
    }
}