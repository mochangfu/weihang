/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: OrderController
 * Author: 25414
 * Date: 2019/5/3 0:24
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.controller;

import com.hg.gama.boot.sys.model.Result;
import com.hg.gama.boot.sys.model.Status;
import com.hg.gama.gamashop.entityquery.OrderVo;
import com.hg.gama.gamashop.enumType.OrderStatus;
import com.hg.gama.gamashop.service.OrderService;
import com.hg.gama.gamautil.numasutil.util.JsonUtil;
import com.hg.gama.gamautil.sessionutil.LoginUserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@Api(tags = {"订单业务相关接口"})
@RestController
@RequestMapping("/app")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("订单业务-下单")
    @PostMapping("/placeOrder")
    public Result placeOrder(
            @RequestParam(value="orderVo",required = false) String orderVoJson,
            @RequestBody (required = false)OrderVo orderVo) {
        try{
            orderVo= JsonUtil.toObject(orderVoJson,OrderVo.class);
            orderService.placeOrder(orderVo);
            return new Result(Status.SUCCESS,"下单成功");
        }catch (Exception ne){
            return new Result(Status.FAIL,"下单失败"+ne.getMessage());
        }
    }
    @ApiOperation("订单业务-//确认收货")
    @GetMapping("/confirmReceipt")
    public Result confirmReceipt(@RequestParam(value = "id") Integer id) {
        try{
            orderService.confirmReceipt( id );
            return new Result(Status.SUCCESS,"支付成功");
        }catch (Exception e){
         return new Result(Status.FAIL,"支付失败,系统异常");
        }
    }
    @ApiOperation("订单业务-取消订单")
    @GetMapping("/cancelOrder")
    public Result cancelOrder(@RequestParam(value = "id") Integer id) {
        try{
            orderService.cancelOrder(OrderStatus.CANCEL, id );
            return new Result(Status.SUCCESS,"支付成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Status.FAIL,"支付失败,系统异常");
        }
    }
    @ApiOperation("订单业务-删除未完成订单")
    @GetMapping("/deleteOrder")
    public Result deleteOrder(@RequestParam(value = "id") Integer id) {
        try{
            orderService.cancelOrder(OrderStatus.DELETED, id );
            return new Result(Status.SUCCESS,"支付成功");
        }catch (Exception e){
            return new Result(Status.FAIL,"支付失败,系统异常");
        }
    }

    @ApiOperation("订单业务-查询订单")
    @GetMapping("/getOrders")
    public Result getOrders(
            @RequestParam(value = "orderStatus" ,required = false) OrderStatus orderStatus,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "beginDate"  ,required = false) Date beginDate,
            @RequestParam(value = "endDate",required = false) Date endDate
            ) {
        try{
            Pageable page= new PageRequest(pageNum,pageSize);
            Integer userId= LoginUserContext.getContext().getCurrentUser().getUser().getId();
            return new Result(orderService.getOrders(userId,orderStatus,beginDate,endDate,page));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Status.FAIL,"查询失败,系统异常");
        }
    }

}
