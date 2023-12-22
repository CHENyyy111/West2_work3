package com.chenyyy111.dao;

import com.chenyyy111.pojo.Order;
import com.chenyyy111.pojo.Product;
import com.chenyyy111.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class OrderManageTest {
    ///检验商品类
    //查询所有商品
    @Test
    public void testGetProductsList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        List<Product> productsList = productMapper.getProductsList();
        for (Product product : productsList){
            System.out.println(product);
        }
        sqlSession.close();
    }

    //以商品价格的升序形式查询所有商品
    @Test
    public void testGetProductsListByPriceAsc(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        List<Product> productsList = productMapper.getProductsListByPriceAsc();
        for (Product product : productsList){
            System.out.println(product);
        }
        sqlSession.close();
    }

    //通过商品编号查询商品
    @Test
    public void testGetProductById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product productById = productMapper.getProductById(1);
        System.out.println(productById);
        sqlSession.close();
    }

    //添加一个商品
    @Test
    public void testAddProduct(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try{
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            int i = productMapper.addProduct(new Product(3, "CCC", 6));
            if(i > 0){
                System.out.println("插入成功！");
            }
        }catch (Exception e) {
            System.out.println("插入失败！");
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    //修改商品
    @Test
    public void testUpdateProduct(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        int i = productMapper.updateProduct(new Product(3,"DDD", 2));
        if(i > 0){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    //通过商品编号删除商品
    @Test
    public void testDeleteProduct(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        int i = productMapper.deleteProduct(1);
        if(i > 0){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
        sqlSession.commit();
        sqlSession.close();
    }












    ///检验订单类
    //查询所有订单
    @Test
    public void testGetOrderList() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> allOrders = orderMapper.getOrdersList();
        for (Order order : allOrders){
            System.out.println(order);
        }
        sqlSession.close();
    }

    //以订单时间的升序形式查询所有订单
    @Test
    public void testGetOrderListByTimeAsc() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> allOrders = orderMapper.getOrdersListByTimeAsc();
        for (Order order : allOrders){
            System.out.println(order);
        }
        sqlSession.close();
    }

    //以订单价格的升序形式查询所有订单
    @Test
    public void testGetOrderListByPriceAsc() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> allOrders = orderMapper.getOrdersListByPriceAsc();
        for (Order order : allOrders){
            System.out.println(order);
        }
        sqlSession.close();
    }

    //通过订单号查询订单
    @Test
    public void testGetOrderById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order orderById = orderMapper.getOrderById(1);
        System.out.println(orderById);
        sqlSession.close();
    }

    //修改订单信息
    @Test
    public void testUpdateOrder(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order order = orderMapper.getOrderById(4);
        try{
            List<Product> productsList = order.getProducts();
            order.setProducts(productsList);
            order.calculateOrderPrice();
            int i = orderMapper.updateOrder(order);
            if(i > 0) {
                System.out.println("修改成功！");
            }
        }catch (Exception e){
            System.out.println("修改失败！");
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    //删除订单信息
    @Test
    public void testDeleteOrder(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        int i = orderMapper.deleteOrder(1);
        if(i > 0){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
        sqlSession.commit();
        sqlSession.close();
    }


    //添加订单和商品的关系
    //添加关系后需要手动更新
    @Test
    public void testAddOrderProduct(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        //创建订单和商品对象
        Order order = orderMapper.getOrderById(4);
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = productMapper.getProductById(2);
        if(order == null){
            //订单编号在数据库不存在
            order = new Order(4, LocalDateTime.now(), 0);
            orderMapper.addOrder(order);
        }

        //添加商品到订单的商品列表
        order.setProducts(Arrays.asList(product));

        //插入订单
        order.calculateOrderPrice();

        //获取订单和商品的ID
        int orderId = order.getOrderId();
        int productId = product.getProductId();

        //添加订单和商品的关系
        orderMapper.addOrderProduct(orderId, productId);

        sqlSession.commit();
        sqlSession.close();
    }

    //插入订单
    @Test
    public void testAddOrder() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try{
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            int i = orderMapper.addOrder(new Order(6,LocalDateTime.now(), 70));
            if(i > 0){
                System.out.println("插入成功！");
            }
        }catch (Exception e){
            System.out.println("插入失败！");
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

}
