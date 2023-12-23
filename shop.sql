DROP TABLE IF EXISTS order_product;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS product;


-- 创建商品表
CREATE TABLE product (
    product_id INT(10) NOT NULL COMMENT '商品编号',
    product_name VARCHAR(260) NOT NULL COMMENT '商品名称',
    product_price DOUBLE DEFAULT 0 COMMENT '商品价格',
    PRIMARY KEY (`product_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO product (product_id, product_name, product_price) VALUES
(1, 'AAA', 12),
(2, 'BBB', 13),
(3, 'CCC', 1);

-- 创建订单表
CREATE TABLE `order` (
    order_id INT(10) NOT NULL COMMENT '订单编号',
    order_time DATETIME NOT NULL COMMENT '下单时间',
    order_price DOUBLE DEFAULT 0 COMMENT '订单价格',
    PRIMARY KEY (`order_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO `order` (order_id, order_time, order_price) VALUES
(1, '2023-12-15 08:00:00', 25),
(2, '2023-12-11 07:10:30', 13),
(3, '2023-12-09 17:04:50', 13),
(4, '2023-11-02 09:25:34', 14),
(5, '2023-01-03 18:45:57', 14);

-- 关系表
CREATE TABLE order_product (
    order_id INT(10),
    product_id INT(10),
    PRIMARY KEY (`product_id`, `order_id`),
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (order_id) REFERENCES `order`(order_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO order_product (order_id, product_id) VALUES
(1,2),
(1,1),
(2,2),
(3,2),
(4,2),
(4,3),
(5,2),
(5,3);