/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: AddressController
 * Author: 25414
 * Date: 2019/6/15 16:35
 * Description: 地址管理接口
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.controller;

import com.hg.gama.boot.sys.model.Result;
import com.hg.gama.gamashop.jpadao.AddressRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"地址管理接口"})
@RestController
@RequestMapping("/app")
public class AddressController {
    @Autowired
    private  AddressRepository addressRepository;

    @ApiOperation("Address按body多条件查询分页查询")
    @GetMapping("/addressList")
    public Result getAddress(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
            ) {
        Pageable page= new PageRequest(pageNum,pageSize);
        return new Result(addressRepository.findAll(page));
    }


}
