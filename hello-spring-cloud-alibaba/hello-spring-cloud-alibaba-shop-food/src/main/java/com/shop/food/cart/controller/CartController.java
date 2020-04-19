package com.shop.food.cart.controller;

import com.shop.food.cart.entity.Cart;
import com.shop.food.cart.service.CartService;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (Cart)表控制层
 *
 * @author makejava
 * @since 2020-03-22 18:10:43
 */
@Validated
@Slf4j
@Api(tags = "购物车相关接口")
@RestController
@RequestMapping("cart")
public class CartController {
    /**
     * 服务对象
     */
    @Resource
    private CartService cartService;

    /**
     * 往购物车中添加商品
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "往购物车中添加商品", notes = "往购物车中添加商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "user_id",
                    value = "用户ID", required = true ) ,
            @ApiImplicitParam(paramType = "query", name = "shop_id",
                    value = "商品ID", required = false),
            @ApiImplicitParam(paramType = "query", name = "quantity",
                    value = "数量", required = false , defaultValue = "1")
    })
    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    public Result<Cart> addCart(@Valid @NotNull(message = "用户ID不能为空")  int user_id,
                                @Valid @NotNull(message = "商品ID不能为空")  int shop_id ,
                               @RequestParam(value = "quantity",required = false , defaultValue = "1")  int quantity ) {

        Cart cart = new Cart();
        cart.setUserId(user_id);
        cart.setFoodId(shop_id);
        cart.setQuantity(quantity);
        Cart res = this.cartService.insert(cart);
        if (res == null ) {
            return ResultUtil.error(1, "添加购物车失败");
        }

        return ResultUtil.success(res);

    }


    /**
     * 更改购物车中商品数量
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "更改购物车中商品数量", notes = "更改购物车中商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "user_id",
                    value = "用户ID", required = true ) ,
            @ApiImplicitParam(paramType = "query", name = "shop_id",
                    value = "商品ID", required = false),
            @ApiImplicitParam(paramType = "query", name = "quantity",
                    value = "数量", required = false , defaultValue = "1")
    })
    @RequestMapping(value = "/chengeQuantity", method = RequestMethod.POST)
    public Result<Cart> chengeQuantity(@Valid @NotNull(message = "用户ID不能为空")  int user_id,
                                       @Valid @NotNull(message = "商品ID不能为空")  int shop_id ,
                                       @RequestParam(value = "quantity",required = false , defaultValue = "1")  int quantity ) {


        Cart cart = new Cart();
        cart.setUserId(user_id);
        cart.setFoodId(shop_id);
        cart.setQuantity(quantity);

        Cart res = this.cartService.update( cart);
        if (res == null ) {
            return ResultUtil.error(1, "更改购物车商品数量失败");
        }
        return ResultUtil.success(res);
    }




    /**
     * 删除购物车中商品
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = "删除购物车中商品", notes = "删除购物车中商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "cart_id",
                    value = "用户名", required = true )
    })
    @RequestMapping(value = "/delCartItem", method = RequestMethod.POST)
    public Result<Cart> delCartItem(@Valid @NotNull(message = "页码不能为空")  int cart_id) {

        boolean status = this.cartService.deleteById(cart_id);
        if(status ) {
            return ResultUtil.success("删除成功");
        } else {
            return ResultUtil.error(1,"删除失败");
        }
    }




    /**
     * 购物车展示
     *
     * @param
     *
     * @return 是否成功
     */

    @ApiOperation(value = " 购物车展示", notes = "购物车展示")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page",
                    value = "页码", required = false,defaultValue = "20"),
            @ApiImplicitParam(paramType = "query", name = "limit",
                    value = "每页数据大小", required = false,defaultValue = "1")
    })
    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public Result<Cart> show(@Valid @Min(value= 1, message = "页码必须 >= 1")  int page,
                               @Valid @Max(value = 50,message = "每页不能超过50条数据")
                               @RequestParam(value = "limit",required = false , defaultValue = "20")  int limit ) {

        List<Cart> carts = this.cartService.queryAllByLimit(page - 1, limit );
        if(carts.size() > 0 ) {
            return ResultUtil.success(carts);
        } else {
            return ResultUtil.error(1,"未找到相关信息");
        }
    }

}