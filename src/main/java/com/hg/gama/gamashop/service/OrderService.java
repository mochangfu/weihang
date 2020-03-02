/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: OrderService
 * Author: 25414
 * Date: 2019/5/3 0:29
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamashop.service;

import com.hg.gama.gamashop.entity.OrderDo;
import com.hg.gama.gamashop.entityquery.OrderVo;
import com.hg.gama.gamashop.enumType.OrderStatus;
import com.hg.gama.gamashop.jpadao.GoodsCaseDao;
import com.hg.gama.gamashop.jpadao.OrderDao;
import com.hg.gama.gamashop.jpadao.OrderItemsDao;
import com.hg.gama.gamashop.model.OrderInfo;
import com.hg.gama.gamashop.model.OrderItems;
import com.hg.gama.gamashop.model.User;
import com.hg.gama.gamautil.jpa.GamaSpecification;
import com.hg.gama.gamautil.numasutil.exception.GamaException;
import com.hg.gama.gamautil.sessionutil.LoginUserContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderService {

    @Autowired
    private GoodsCaseDao goodsCaseDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemsDao orderItemsDao;

    //下单
    @Transactional
    public Integer placeOrder(OrderVo orderVo) {
        try {
            User user = LoginUserContext.getContext().getCurrentUser().getUser();
            final OrderInfo order = new OrderInfo();
            List<OrderItems> orderItemsLit = new ArrayList<>();
            BeanUtils.copyProperties(orderVo, order);
            order.setSn(System.currentTimeMillis() + "_" + user.getId());
            order.setUserId(user.getId());
            order.setOrderName(order.getSn());
            order.setStatus(OrderStatus.UNPAY.toString());
            order.setCreated(new Date(System.currentTimeMillis()));
            order.setCreator(user.getId());
            final OrderInfo newOrder = orderDao.save(order);
            orderVo.getOrderItems().stream().forEach(item -> {
                //减少库存
                goodsCaseDao.reduceAmount(item.getCount(), item.getGoodsId());
                //查看库存是否<0
                Integer amount = goodsCaseDao.findById(item.getGoodsId()).get().getStock();
                if (amount < 0) throw new GamaException("库存不足");
                OrderItems orderItems = new OrderItems();
                BeanUtils.copyProperties(item, orderItems);
                orderItems.setOrderId(newOrder.getId());
                orderItems.setCreated(order.getCreated());
                orderItems.setCreator(order.getCreator());
                orderItems.setName(order.getOrderName());
                orderItems.setShopId(1);
                orderItemsLit.add(orderItems);
            });
            orderItemsDao.saveAll(orderItemsLit);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GamaException("异常，请稍后重试");
        }
    }

    //支付
    @Transactional
    public Integer payOrder(Integer orderId) {
        /* 获得第三方支付反馈信息后执行修改状态 */
        try {
            User user = LoginUserContext.getContext().getCurrentUser().getUser();
            OrderInfo orderInfo=orderDao.findById(orderId).get();
            orderInfo.setUpdated(new Date(System.currentTimeMillis()));
            return orderDao.updateStatusById(OrderStatus.PAYED.toString(), new Date(System.currentTimeMillis()),orderId);
        } catch (Exception e) {
            throw new GamaException("异常，请稍后重试");
        }
    }

    //确认收货
    @Transactional
    public Integer confirmReceipt(Integer orderId) {
        try {
            User user = LoginUserContext.getContext().getCurrentUser().getUser();
            return orderDao.updateStatusById(OrderStatus.RECEIVED.toString(),new Date(System.currentTimeMillis()), orderId);
        } catch (Exception e) {
            throw new GamaException("异常，请稍后重试");
        }
    }

    //取消订单  //删除未完成订单
    @Transactional
    public Integer cancelOrder(OrderStatus orderStatus,Integer orderId) {

        try {
            User user = LoginUserContext.getContext().getCurrentUser().getUser();
            List<OrderItems> orderItemsList = orderItemsDao.findAllByOrderId(orderId);
            orderItemsList.stream().forEach(item -> {
                goodsCaseDao.addAmount(item.getCount(), item.getGoodsCaseId());
            });
            return orderDao.updateStatusById(orderStatus.toString(), new Date(System.currentTimeMillis()),orderId);
        } catch (Exception e) {
            throw e;
        }
    }

    public Page<OrderDo> getOrders(Integer userId, OrderStatus orderStatus, Date beginDate, Date endDate, Pageable page ){
        Map< String,String > map = new HashMap<>();
        map.put("userId",userId.toString());
        if(null!=orderStatus){
            map.put("status",orderStatus.toString());
        }
        GamaSpecification<OrderInfo> specification = new GamaSpecification<OrderInfo>(map,"created",beginDate,endDate) ;
        Page<OrderInfo> orderInfos=  orderDao.findAll(specification,page);

        List<OrderDo>   orderVos=new ArrayList<>();
        orderInfos.getContent().stream().forEach(o->{
            OrderDo orderDo=new OrderDo();
            BeanUtils.copyProperties(o,orderDo);
            orderVos.add(orderDo);
        });
        return new PageImpl(orderVos);
    }
}

