package com.shop.food.food_order.controller;

import com.google.common.base.Splitter;
import com.shop.food.cart.entity.Cart;
import com.shop.food.cart.service.CartService;
import com.shop.food.food.service.FoodService;
import com.shop.food.food_order.entity.FoodOrder;
import com.shop.food.food_order.service.FoodOrderService;
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
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (FoodOrder)表控制层
 *
 * @author makejava
 * @since 2020-03-22 19:37:06
 */
@Validated
@Slf4j
@Api(tags = "订单相关接口")
@RestController
@RequestMapping("foodOrder")
public class FoodOrderController {
    /**
     * 服务对象
     */
    @Resource
    private FoodOrderService foodOrderService;


    @Resource
    private FoodService foodService;

    @Resource
    private CartService cartService;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FoodOrder selectOne(Long id) {
        return this.foodOrderService.queryById(id);
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
    public Result<FoodOrder> generateOrder(@Valid @NotNull(message = "购物车IDS")  String ids) {

        Splitter split = Splitter.on(',').trimResults().omitEmptyStrings(); // 去前后空格&&去空string
        List<String> list1 = split.splitToList(ids);
        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setOrderId(System.currentTimeMillis());
        for (String id : list1) {
            Cart cart = cartService.queryById(Integer.parseInt(id));
            foodOrder.setFoodId(cart.getId());
            foodOrder.setUserId(cart.getUserId());
            foodOrder.setPrice(foodService.queryById(cart.getId()).getRetailPrice());
            foodOrder.setQuantity(cart.getQuantity());
            foodOrderService.insert(foodOrder);
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
    public Result<FoodOrder> cancelOrder(@Valid @NotNull(message = "订单ID")  Long orderId) {

        boolean status = foodOrderService.deleteById(orderId);

        if(status){
            return ResultUtil.success("操作成功");
        }
        return ResultUtil.error(1,"操作失败");
    }


}