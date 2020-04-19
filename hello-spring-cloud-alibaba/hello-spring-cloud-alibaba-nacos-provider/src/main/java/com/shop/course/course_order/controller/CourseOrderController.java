package com.shop.course.course_order.controller;

import com.google.common.base.Splitter;
import com.shop.course.cart.entity.Cart;
import com.shop.course.course_order.entity.CourseOrder;
import com.shop.course.course_order.service.CourseOrderService;
import com.shop.course.cart.service.CartService;
import com.shop.course.course.service.CourseService;
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
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (CourseOrder)表控制层
 *
 * @author makejava
 * @since 2020-03-22 19:37:06
 */
@Validated
@Slf4j
@Api(tags = "课件订单相关接口")
@RestController
@RequestMapping("courseOrder")
public class CourseOrderController {
    /**
     * 服务对象
     */
    @Resource
    private CourseOrderService courseOrderService;


    @Resource
    private CourseService courseService;

    @Resource
    private CartService cartService;

    private IDWorker idGenerate = new IDWorker(0, 2);
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public CourseOrder selectOne(@Valid @NotNull(message = "订单ID")  Long id) {
        return this.courseOrderService.queryById(id);
    }

    /**
     * 确定下单、将购物车生产订单
     * Generate order
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "生成订单", notes = "确定下单、将购物车生产订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "cart_ids",
                    value = "购物车IDS", required = true )
    })
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public Result<CourseOrder> generateOrder(@Valid @NotNull(message = "购物车IDS")  String cart_ids) {

        Splitter split = Splitter.on(',').trimResults().omitEmptyStrings(); // 去前后空格&&去空string

        List<String> cartIds = split.splitToList(cart_ids);
        if(cartIds.size() == 0)
            cartIds.add(cart_ids);
        CourseOrder courseOrder = new CourseOrder();
        courseOrder.setOrderId(idGenerate.nextId());
        for (String cartId : cartIds) {
            Long id = Long.parseLong(cartId);
            Cart cart = cartService.queryById(id);
            courseOrder.setCourseId(cart.getCourseId());
            courseOrder.setUserId(cart.getUserId());
            courseOrder.setPrice(courseService.queryById(cart.getCourseId()).getRetailPrice());
            courseOrder.setQuantity(cart.getQuantity());
            courseOrderService.insert(courseOrder);
        }

      return ResultUtil.success("成功");
    }



    /**
     * 取消订单
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "取消订单", notes = "取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "order_id",
                    value = "购物车IDS", required = true )
    })
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public Result<CourseOrder> cancelOrder(@Valid @NotNull(message = "订单ID")  Long orderId) {

        boolean status = courseOrderService.deleteById(orderId);

        if(status){
            return ResultUtil.success("操作成功");
        }
        return ResultUtil.error(1,"操作失败");
    }


}