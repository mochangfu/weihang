/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: OrderVo
 * Author: 25414
 * Date: 2019/5/3 0:10
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.entityquery;

import com.hg.gama.gamashop.entity.OrderItemInfo;

import java.util.List;

/*
下单所需要的实体json对象
 */
public class OrderVo  {

     private Integer userId;

    private Integer addressId;

    List<OrderItemInfo> orderItems;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public List<OrderItemInfo> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemInfo> orderItems) {
        this.orderItems = orderItems;
    }
}
