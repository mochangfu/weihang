/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: OrderItemInfo
 * Author: 25414
 * Date: 2019/5/3 0:18
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.entity;

//下单所需要的购买每一个商品的信息
public class OrderItemInfo {
    private Integer shopId;
    private Integer goodsId;
    private Integer goodsCaseId;
    private Integer count;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsCaseId() {
        return goodsCaseId;
    }

    public void setGoodsCaseId(Integer goodsCaseId) {
        this.goodsCaseId = goodsCaseId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
