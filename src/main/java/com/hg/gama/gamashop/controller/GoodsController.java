/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: GoodsController
 * Author: 25414
 * Date: 2019/5/2 13:05
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.controller;

import com.hg.gama.boot.sys.model.Result;
import com.hg.gama.gamashop.model.Goods;
import com.hg.gama.gamashop.service.GoodsService;
import com.hg.gama.gamautil.numasutil.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"商品业务相关接口"})
@RestController
@RequestMapping("/app")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @ApiOperation("首页商品分类展示")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "listSize" ,required = true,dataType = "int",value = "每个类型返回条数",paramType = "query")
    )
    @GetMapping("/goodsClassify")
    public Result getGoodsClassify(
            @RequestParam(value = "listSize") Integer listSize) {
        Pageable page= new PageRequest(0,listSize);
        return new Result(goodsService.getGoodsClassify(page));
    }

    @ApiOperation("Goods按body多条件查询分页查询")
    @PostMapping("/goodsList")
    public Result getGoods(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(required = false,value = "goods") String goodsquery,
            @RequestBody (required = false)Goods goods) {
         if(StringUtils.isEmpty(goodsquery)){
             goods=new Goods();
         }else {
             goods= JsonUtil.toObject(goodsquery,Goods.class);
         }
        Pageable page= new PageRequest(pageNum,pageSize);
        return new Result(goodsService.getGoodsPage0(goods,page));
    }

    @ApiOperation("Goods根据goodsId查询完整的商品详情")
    @GetMapping("/goodsDetaill")
    public Result getGoodsDetaill(
            @RequestParam(value = "id") Integer id) {
        return new Result(goodsService.getGoodsDetaill(id));
    }


}
