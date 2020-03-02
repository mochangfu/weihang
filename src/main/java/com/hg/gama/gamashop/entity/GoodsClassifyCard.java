/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: GoodsClassifyCard
 * Author: 25414
 * Date: 2019/5/2 16:48
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.entity;

import com.hg.gama.gamashop.model.Goods;
import com.hg.gama.gamashop.model.Type;

import java.util.List;
/*
首页分类展示卡片
 */
public class GoodsClassifyCard {

   //商品分类
    Type type;
    //商品列表
    List<Goods> goodsList;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
