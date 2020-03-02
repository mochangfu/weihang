package com.hg.gama.gamashop.service;


import com.github.pagehelper.PageInfo;
import com.hg.gama.gamashop.jpadao.GoodsRepository;
import com.hg.gama.gamashop.model.Goods;
import com.hg.gama.gamautil.jpa.GamaSpecification;
import com.hg.gama.gamautil.numasutil.Page.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.data.domain.Page;

@Component
public class PageExampleService {

    @Autowired
    GoodsRepository goodsRepository;


    //Example分页(from-0页开始-start))
    public Page<Goods> getGoodsPage0(Pageable page) {
        Goods goods = new Goods();
        goods.setType(3);
        Example<Goods> example =Example.of(goods);
        return goodsRepository.findAll(example,page);
    }
//specification(from-0页开始-start))
    public Page<Goods> getGoodsPage1(Pageable page) {
        Map< String,String > map = new HashMap<>();
        map.put("type","3");
        GamaSpecification<Goods> specification = new GamaSpecification<Goods>(map,null,null,null) ;
        return goodsRepository.findAll(specification,page);
    }
//PageUtil-1(from-1页开始-start))
    public PageInfo<Goods> getGoodsPage2(com.github.pagehelper.Page page) {
        List<Goods> goodsList= goodsRepository.findAll();
        return PageUtil.createPageInfo1(goodsList,page);
    }
    //PageUtil-2(from-1页开始-start))
    public PageInfo<Goods> getGoodsPage3(com.github.pagehelper.Page page) {
        List<Goods> goodsList= goodsRepository.findAll();
        return PageUtil.createPageInfo1(goodsList,page);
    }
    //带参数分页(from-page0-start))
    public Page<Goods> getGoodsPage4(Pageable page) {
        return goodsRepository.findAll(page);
    }



}
