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
import com.hg.gama.gamashop.service.PageExampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"分页方法例子"})
@RestController
@RequestMapping("/app")
public class PageController {
    @Autowired
    PageExampleService goodsService;
    @ApiOperation("所有Goods分页查询--JPA结合Example分页（sql级别分页）")
    @GetMapping("/goodsPage0")
    public Result getGoodsPage0(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {
        Pageable page= new PageRequest(pageNum,pageSize);
        return new Result(goodsService.getGoodsPage0(page));
    }
    @ApiOperation("所有Goods分页查询--JPA结合specification分页方式（sql级别分页）")
    @GetMapping("/goodsPage1")
    public Result getGoodsPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {
        Pageable page= new PageRequest(pageNum,pageSize);
        return new Result(goodsService.getGoodsPage1(page));
    }


    @ApiOperation("所有Goods分页查询--PageUtil方法1（数据查询后分页）效率低")
    @GetMapping("/goodsPage2")
    public Result getGoodsPage2(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {
        com.github.pagehelper.Page page= new com.github.pagehelper.Page(pageNum,pageSize);
        return new Result(goodsService.getGoodsPage2(page));
    }

    @ApiOperation("所有Goods分页查询--PageUtil方法2（数据查询后分页）效率低")
    @GetMapping("/goodsPage3")
    public Result getGoodsPage3(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {
        com.github.pagehelper.Page page= new com.github.pagehelper.Page(pageNum,pageSize);
        return new Result(goodsService.getGoodsPage3(page));
    }

    @ApiOperation("所有Goods分页查询-JPA自带分页方法（sql级别分页）（不好用）")
    @GetMapping("/goodsPage4")
    public Result getGoodsPage4(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize) {
        Pageable page= new PageRequest(pageNum,pageSize);
        return new Result(goodsService.getGoodsPage4(page));
    }


}
