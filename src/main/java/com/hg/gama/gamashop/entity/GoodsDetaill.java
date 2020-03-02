/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: GoodsDetaill
 * Author: 25414
 * Date: 2019/5/2 20:22
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.entity;

import com.hg.gama.gamashop.model.Goods;
import com.hg.gama.gamashop.model.GoodsCase;

import java.util.List;
import java.util.Map;

public class GoodsDetaill {
    //商品信息
    private Goods goods ;
    //商品规格信息
    private List<SpecialInfo> specialInfoList;
    //商品总数量
    private Integer amountSum;
    //商品规格组合价格对照
    private Map<String ,Integer> specialPriceMap;
    //商品规格组合
    private Map<String , GoodsCase> goodsCaseMap;

    public void setAmountSum(Integer amountSum) {
        this.amountSum = amountSum;
    }

    public Integer getAmountSum() {
        return amountSum;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<SpecialInfo> getSpecialInfoList() {
        return specialInfoList;
    }

    public void setSpecialInfoList(List<SpecialInfo> specialInfoList) {
        this.specialInfoList = specialInfoList;
    }

    public Map<String, GoodsCase> getGoodsCaseMap() {
        return goodsCaseMap;
    }

    public void setGoodsCaseMap(Map<String, GoodsCase> goodsCaseMap) {
        this.goodsCaseMap = goodsCaseMap;
    }

    public Map<String, Integer> getSpecialPriceMap() {
        return specialPriceMap;
    }

    public void setSpecialPriceMap(Map<String, Integer> specialPriceMap) {
        this.specialPriceMap = specialPriceMap;
    }
}
