package com.chenyyy111.dao;

import com.chenyyy111.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OrderMapper {
    //查询所有订单
    List<Order> getOrdersList();

    //以订单时间的升序形式查询所有订单
    List<Order> getOrdersListByTimeAsc();

    //以订单价格的升序形式查询所有订单
    List<Order> getOrdersListByPriceAsc();

    //通过订单号查询订单
    Order getOrderById(int orderId);

    //插入订单
    int addOrder(Order order);

    //修改订单信息
    int updateOrder(Order order);

    //删除订单信息
    int deleteOrder(int orderId);

    //添加订单和商品的关系
    int addOrderProduct(@Param("orderId") int orderId, @Param("productId") int productId);

}
