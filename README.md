# West2_work3

## 一、学习内容

主要学习了基础的sql语言

跟着教程学了增删改查、联表等

学习了事务（增删改必备！）

刚开始学习的是JDBC，后来又学习了Mybatis，数据库连接池学了一点点，还不太会



## 二、任务完成情况

基本实现了小型的订单管理系统，运用了mysql、mybatis。

### 项目结构

#### mysql

创建了两张基础表product（商品）和order（订单），建立了一张中间表order_product，以实现联表

#### java

- dao
  - Order实体类对应的OrderMapper接口
  - Product实体类对应的ProductMapper接口
- pojo
  - Order实体类
  - Product实体类
- utils
  - MybatisUtils用于创建Sqlsession

- resources
  - 各个接口对应的xml文件
  - mybatis-config.xml配置文件
  - db.propeties连接数据库用的数据
- test
  - OrderManageTest运用junit4对商品、订单的增删改查等操作进行测试



### 问题

写的过程中还是有点混乱