# 1、初识MySQL

## 1.1 数据库定义

数据库（DB，DataBase）

概念：数据仓库，**软件**。SQL可以存储大量数据

作用：数据存储、管理



## 1.2 数据库分类

**关系型数据库：**（SQL）

- MySQL, Oracle, Sql Server, DB2, SQLlite
- 通过表和表之间，行和列之间的关系进行数据的存储（学员信息表、考勤表等）



**非关系型数据库：**（NoSQL） Not Only

- Redis, MongDB
- 非关系型数据库，对象存储，通过对象的自身的属性来决定。



**<font color='red'>DBMS(数据库管理系统)</font>** 

- 数据库的管理软件，科学有效的管理我们的数据。维护和索取数据；
- MySQL，数据库管理系统



## 1.3 MySQL简介

MySQL是一个**关系型数据库管理系统**

开源的数据库软件

体积小、速度快、总体拥有成本低，招人成本比较低

中小型网站、大型网站，集群



## 1.4 创建

1.创建一个数据库school

![image-20231103101732594](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231103101732594.png)教程里面是5.7版本，8.0版本中基本字符是<font color='red'>utf8mb3</font>，数据库排序规则是<font color='red'>utf8mb3_general_ci</font>

 2.创建一张表student

```
字段：  id, name, age;
```

![image-20231103102055843](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231103102055843.png)字符集和核对同上

3.查看表

4.添加记录   **记得刷新！**



## 1.5 链接数据库

命令行连接！

```bash
mysql -uroot -p密码 --连接数据库

update mysql.user set  uthentication_string=password('') where user='root' and Host = 'localhost'; -- 修改用户密码
flush privileges; -- 刷新权限

--------------------------------------------
-- 所有的语句都使用;结尾
show databases;  -- 查看所有的数据库

use school  --切换数据库 use 数据库名
Database changed

show tables;  -- 查看数据库中所有的表
describe student;  -- 显示数据库中所有的表的信息

create database westos;  -- 创建一个数据库  database 数据库名

exit;  --退出连接


-- 单行注释（SQL的本来的注释）
/**/（SQL的多行注释）
```

**数据库xxx语言**   CRUD增删改查！  

DDL     定义

DML     操作

DQL     查询

DCL     控制























# 2、操作数据库

操作数据库 > 操作数据库中的表 > 操作数据库中表的数据

**<font color='red'>mysql关键字不区分大小写</font>**





## 2.1 操作数据库（了解）

1.创建数据库

```sql
CREATE DATABASE [IF NOT EXISTS] westos;
```

（中括号为可选的意思）

2.删除数据库

```sql
DROP DATABASE [IF EXISTS] westos;
```

3.使用数据库

```sql
-- tab 键的上面   如果表名或字段名是一个特殊字符，就需要带``
USE school

SELECT `USER` FROM student
```

4.查看数据库

```sql
SHOW DATABASES -- 查看所有的数据库
```



对比sqlyog的可视化操作![image-20231103110225324](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231103110225324.png)

**学习思路：**

- 对照sqlyog可视化历史记录查看sql
- 固定的语法或关键字必须要强行记住！

 



## 2.2 数据库的列类型

> 数值

- tinyint            十分小的数据      1个字节
- smallint         较小的数据          2个字节
- mediumint   中等大小的数据   3个字节
- **int                  标准的整数          4个字节**          常用的     int(x),x表示int的宽度,和int的大小无关 例如int(1)可以存3个数
- bigint             较大的数据          8个字节
- float               浮点数                  4个字节
- double          浮点数                   8个字节      （精度问题！）
- decimal         字符串形式的浮点数        金融计算的时候一般使用decimal



> 字符串

- char            字符串固定大小的    0~255
- **varchar     可变字符串               0~65535**    常用的  相当于java里面的String
- tinytext       微型文本                  2^8 -1
- **text              文本串                      2^16 -1**    保存大文本 



> 时间日期

- data                YYYY-MM-DD   日期格式
- time                HH:MM:SS        事件格式
- **datetime      YYYY-MM-DD HH:MM:SS**    最常用的时间格式
- **timestamp   时间戳       1970.1.1到现在的毫秒数**    也较为常用！
- year                年份表示



> null

- 没有值，未知

- ==注意，不要使用NULL进行运算。如果使用，结果为NULL==





## 2.3 数据库的字段属性（重点）

<font color='red'>Unsigned：</font>

- 无符号的整数
- 声明了该列不能声明为负数



<font color='red'>zerofill：</font>

- 0填充的
- 不足的位数，使用0来填充           <font color='lighblue'>eg：int(3)的情况下，5---005</font>



<font color='red'>自增：</font>

- 通常理解为自增，自动在上一条记录的基础上+1（默认）
- 通常用来设计唯一的主键  index  必须是整数类型
- 可以自定义设计主键自增的起始值和步长



<font color='red'>非空：</font>Null not null

- 假设设置为 not null，如果不给它赋值，就会报错！
- NULL，如果不填写值，默认就是null！
- 如果是  就是空字符串，即""；真正的空是(NULL)



<font color='red'>默认：</font>

- 设置默认的值！
- 例如：sex，默认值为男。如果不指定该列的值





**拓展：**

```sql
/*重要字段：每一个表都必须存在以下五个字段！未来做项目用的，表示一个记录存在意义！

id   主键
`version`  乐观锁
is_delete  伪删除
gmt_create  创建时间
gmt_update  修改时间
*/
```





## 2.4 创建数据库表(重点)

```sql
-- 目标：创建一个school数据库
-- 创建学生表(列，字段)  使用SQL 创建
-- 学号int 登录密码varchar(20) 姓名 性别varchar(2) 出生日期(datatime) 家庭住址 email

-- 注意点，使用英文()，表的名称 和 字段 尽量使用 `` 括起来
-- AUTO_INCREMENT 自增
-- 所有的语句后面加, 最后一个不用加
-- PRIMARY KEY 主键，一般一个表只有唯一一个的主键！

CREATE TABLE IF NOT EXISTS `students`(
  `id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
  `pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
  `birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY(`id`) 
)ENGINE=INNODB DEFAULT CHARSET=utf8
```



格式

```sql
CREATE TABLE [IF NOT EXISTS] `表名`(
    `字段名` 列类型 [属性] [索引] [注释],
    `字段名` 列类型 [属性] [索引] [注释],
    ……
    `字段名` 列类型 [属性] [索引] [注释]
)[表类型][字符集设置][注释]
```



常用命令

```sql
SHOW CREATE DATABASE school -- 查看创建数据库的语句
SHOW CREATE TABLE students -- 查看students数据表的定义语句
DESC students -- 显示表的结构
```





## 2.5 数据表的类型

```sql
-- 关于数据库引擎
/*
INNODB  默认使用 
MYISAM  早些年使用
*/
```

|                        | MYISAM | INNODB        |
| ---------------------- | :----- | ------------- |
| 事务支持               | 不支持 | 支持          |
| 数据行锁定             | 不支持 | 支持          |
| 外键约束               | 不支持 | 支持          |
| 全文索引               | 支持   | 不支持        |
| 表空间的大小（占内存） | 较小   | 较大，约为2倍 |

常规使用操作：

- MYISAM  节约空间，速度较快
- INNODB  安全性高，事物的处理，多表多用户操作



> 在物理空间存在的位置

所有的数据库文件都存在在data目录下，一个文件夹就对应一个数据库

本质还是文件的存储！

MySQL引擎在物理文件上的区别（5.7版本）

- InnoDB在数据库表中只有一个*.frm文件，以及上级目录下的ibdata1文件
- MYISAM对应文件

​           *.frm   --表结构的定义文件

​           *.MYD  数据文件（data）

​           *.MYI    索引文件（index）



> 设置数据库表的字符集编码

```sql
CHARSET=utf8
```

不设置的话，会是mysql默认的字符集编码   （不支持中文）

MySQL的默认编码是Latin1，不支持中文

在my.ini中配置默认的编码

```sql
character-set-server=utf8
```





## 2.6 修改删除表

> 修改表

```sql
-- 修改表  ALTER TABLE 旧表名 RENAME AS 新表名
ALTER TABLE teacher RENAME AS teacher1
-- 增加表的字段  ALTER TABLE 表名 ADD 字段名 列属性
ALTER TABLE teacher1 ADD age INT(11)

-- 修改表的字段（重命名，修改约束）
-- ALTER TABLE 表名 MODIFY 字段名 列属性[]
ALTER TABLE teacher1 MODIFY age VARCHAR(11)  -- 修改约束（无法重命名）
-- ALTER TABLE 表名 CHANGE 旧名字 新名字 列属性[]
ALTER TABLE teacher1 CHANGE age age1 INT(1)  -- 字段重命名（可以用于修改约束，但和重命名绑定）

-- 删除表的字段  ALTER TABLE 表名 DROP 字段名
ALTER TABLE teacher1 DROP age1
```



> 删除表

```sql
-- 删除表
DROP TABLE IF EXISTS teacher1
```

<font color='red'>**所有的创建和删除操作尽量加上判断，以免报错**</font>



🔺注意点：

- ``   字段名，使用这个包裹！
- --   /**/    注释
- sql关键字大小不敏感，建议大家写小写
- 所有的符号都用英文！





















# 3、MySQL数据管理

## 3.1 外键（了解即可）

> 方式一：在创建表的时候，增加约束（麻烦，比较复杂）

```sql
CREATE TABLE `grade`(
  `gradeid` INT(10) NOT NULL AUTO_INCREMENT COMMENT '年级id',
  `gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
  PRIMARY KEY (`gradeid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

-- 学生表的 gradeid 字段  要去引用年级表的 gradeid
-- 定义外键key
-- 给这个外键添加约束（执行引用）
CREATE TABLE IF NOT EXISTS `students`(
  `id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
  `pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
  `birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
  `gradeid` INT(10) NOT NULL COMMENT '学生的年级',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY(`id`),
  KEY `FK_gradeid` (`gradeid`),
  CONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8
```

删除有外键关系的表的时候，必须要先删除引用别人的表（从表），再删除被引用的表（主表）

在上面的例子中，students就是从表，grade是主表



> 方式二：创建表成功后，添加外键约束

```sql
CREATE TABLE `grade`(
  `gradeid` INT(10) NOT NULL AUTO_INCREMENT COMMENT '年级id',
  `gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
  PRIMARY KEY (`gradeid`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

-- 学生表的 gradeid 字段  要去引用年级表的 gradeid
-- 定义外键key
-- 给这个外键添加约束（执行引用）
CREATE TABLE IF NOT EXISTS `students`(
  `id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
  `pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
  `birthday` DATETIME DEFAULT NULL COMMENT '出生日期',
  `gradeid` INT(10) NOT NULL COMMENT '学生的年级',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

-- 创建表的时候没有外键关系
ALTER TABLE `students` 
ADD CONSTRAINT `FK_gradeid` FOREIGN KEY(`gradeid`) REFERENCES `grade`(`gradeid`);
-- ALTER TABLE 表 ADD CONSTRAINT 约束名 FOREIGN KEY(作为外键的列) REFERENCES 被引用的表(被引用的字段)
```

以上的操作都是物理外键，数据库级别的外键，我们不建议使用！（原因：建表麻烦，关系混乱。避免数据库过多造成困扰，方法一方法二了解即可）



==<font color='red'>**最佳实现**</font>==

- 数据库就是单纯的表，只用来存数据，只有行（数据）和列（字段）
- 我们想使用多张表的数据，想使用外键（程序去实现）





## 3.2 DML语言（全部记住）

**数据库意义：**数据存储，数据管理

DML语言：数据操作语言

- Insert
- update
- delete

核心：增删改



## 3.3 添加

> insert

```sql
-- 插入语句（添加）
-- INSERT into 表名([字段名1, 字段名2, 字段名3])values('值1'), ('值2'), ('值3'), ...
INSERT INTO `grade`(`gradename`) VALUE ('大四')

-- 由于主键自增，我们可以省略主键（如果不写表的字段，它就会一一匹配）
INSERT INTO `grade` VALUE ('大三')
-- 相当于INSERT into `grade`(`gradeid`, `gradename`) VALUE ('大三'), 会报错！

-- 一般写插入语句，我们一定要数据和字段一一对应！

-- 插入多个字段
INSERT INTO `grade`(`gradename`) 
VALUES('大二'), ('大一')  -- 对应注意事项3.

INSERT INTO `student`(`name`) VALUES ('张三')

INSERT INTO `student`(`name`, `gradeid`, `pwd`, `sex`) VALUES ('张三', 'aaaaaa', '男')

INSERT INTO `student`(`name`, `gradeid`, `pwd`, `sex`) 
VALUES ('李四', 1, 'aaaaaa', '男'), ('王五', 2, 'aaaaaa', '男')

INSERT INTO `student` VALUES (5, '李四', '男', '2000-01-01', 1, '西安', 'email')  -- 对应注意事项2.
```

<font color='red'>**语法：**</font>`INSERT into 表名([字段名1, 字段名2, 字段名3])values('值1'), ('值2'), ('值3'), ...`

**注意事项：**

1. 字段和字段之间使用**英文逗号**隔开
2. 字段可以省略的，但是后面的值必须要一一对应，不能少
3. 可以同时插入多条数据，VALUES后面的值需要使用**,**隔开   `VALUES(), (), ...`



## 3.4 修改

> update     修改谁   （条件）  set原来的值=新值

```sql
-- 修改学员名字，带了条件
UPDATE `student` SET `name`='狂神' WHERE id = 1;

-- 不指定条件的情况下，会改动所有表！
UPDATE `student` SET `name`='长江七号';  -- 干了这个就寄了！！！

-- 修改多个属性，逗号隔开
UPDATE `student` SET `name`='小猫咪', `email`='1471123190@qq.com', `sex`='女' WHERE id=1;

-- 语法：
-- UPDATE 表名 SET column_name = value, [column_name = value, ...] WHERE [条件]
```

条件：where子句   运算符  id等于某个值，大于某个值，在某个区间内修改……

| 操作符           | 含义              | 范围            | 结果  |
| ---------------- | ----------------- | --------------- | ----- |
| =                | 等于              | eg: 5=6         | false |
| <> 或 !=         | 不等于            | eg: 5<>6        | true  |
| >                | 大于              |                 |       |
| <                | 小于              |                 |       |
| >=               | 大于等于          |                 |       |
| <=               | 小于等于          |                 |       |
| BETWEEN...AND... | 在某个范围内      | [2, 5]          |       |
| AND              | 我和你 相当于&&   | eg: 5>1 AND 1>2 | false |
| OR               | 我或你 相当于\|\| | eg: 5>1 OR 1>2  | true  |

```sql
-- 通过多个条件定位数据，无上限，可以一直AND或者OR
UPDATE `student` SET `name`='长江7号' WHERE `name`='长江七号' AND `sex`='女';
```

<font color='red'>**语法：**</font>`UPDATE 表名 SET column_name = value, [column_name = value, ...] WHERE [条件]`

**注意事项：**

1. column_name是数据库的列，尽量带上``
2. 条件  筛选的条件，如果没有制定，则会修改所有的列
3. value，是一个具体的值，也可以是一个变量
4. 多个设置的属性之间，使用英文逗号隔开

```SQL
UPDATE `student` SET `birthday`=CURRENT_TIME WHERE `name`='长江7号' AND `sex`='女';
```







## 3.5 删除

> delete 命令

<font color='red'>**语法：**</font>`delete from 表名 [where 条件]`

```sql
-- 删除数据（避免这样写，会全部删除）
DELETE FROM `student`

-- 删除指定数据
DELETE FROM `student` WHERE id=1;
```



> TRUNCATE 命令

作用：完全清空一个数据库表，表的结构和索引约束不会变！

```sql
-- 清空 student 表
TRUNCATE `student`
```



> delete 和 TRUNCATE 区别

- 相同点：都能删除数据，都不会删除表结构(即表头)

- 不同：

  ​       TRUNCATE   重新设置自增列，计数器会归零

  ​       TRUNCATE   不会影响事务

```sql
-- 测试delete 和 TRUNCATE 区别
CREATE TABLE `test`(
  `id` INT(4) NOT NULL AUTO_INCREMENT,
  `coll` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `test`(`coll`) VALUES ('1'), ('2'), ('3');

DELETE FROM `test`   -- 不会影响自增

TRUNCATE TABLE `test`   -- 自增清空，从1开始
```

了解即可：`DELETE删除的问题`，重启数据库会发生一些现象：

- InnoDB  自增列会从1开始（原因：数据存在内存中，断电即丢失）
- MyISAM  继续从上一个自增量开始（原因：数据存在文件中，不会丢失）





# 4、DQL查询数据（最重点）

## 4.1 DQL

（Data Query LANGUAGE: 数据查询语言)

- 所有的查询操作都用它   Select
- 简单的查询，复杂的查询它都能做
- <font color = red>数据库中最核心的语言，最重要的语句</font>
- 使用频率最高的语句



Select完整的语法：（复杂，后面会总结出比较简洁的表格）

```sql
SELECT
    [ALL | DISTINCT | DISTINCTROW]
      [HIGH_PRIORITY]
      [STRAIGHT_JOIN]
      [SQL_SMALL_RESULT] [SQL_BIG_RESULT] [SQL_BUFFER_RESULT]
      [SQL_CACHE | SQL_NO_CACHE] [SQL_CALC_FOUND_ROWS]
    select_expr [, select_expr]
    [FROM table_references
      [PARTITION partition_list]
    [WHERE where_condition]
    [GROUP BY {col_name | expr | position}
      [ASC | DESC], ... [WITH ROLLUP]]
    [HAVING where_condition]
    [ORDER BY {col_name | expr | position}
      [ASC | DESC], ...]
    [LIMIT {[offset,] row_count | row_count OFFSET offset}]
    [PROCEDURE procedure_name(argument_list)]
    [INTO OUTFILE 'file_name'
        [CHARACTER SET charset_name]
        export_options
      | INTO DUMPFILE 'file_name'
      | INTO var_name [, var_name]]
    [FOR UPDATE | LOCK IN SHARE MODE]]
```



简洁表：

```sql
SELECT [ALL | DISTINCT]
{* | table.* | [table.field1[AS alias1][,table.field2[AS alias2]][, ...]]}
FROM table_name [AS table_alias]
    [LEFT | RIGHT | INNER JOIN table_name2]  -- 联合查询
    [WHERE ...]  -- 指定结果需满足的条件
    [GROUP BY ...]  -- 指定结果按照哪个字段来分组
    [HAVING]  -- 过滤分组的记录必须满足的次要条件
    [ORDER BY ...]  -- 指定查询记录按一个或多个条件排序
    [LIMIT {[offset,]row_count | row_countOFFSET offset}];
    -- 指定查询的记录从哪条至哪条
```

**注意：[]括号代表可选的，{}括号代表必选的**





## 4.2 制定查询字段

```sql
-- 查询全部的学生    SELECT 字段 FROM 表
SELECT * FROM student

-- 查询指定字段
SELECT `studentno`, `studentname` FROM student

-- 别名，给结果起一个名字 AS    可以给字段起别名，也可以给表起别名
SELECT `studentno` AS 学号, `studentname` AS 学生姓名 FROM student AS s

-- 函数 concat(a, b)    输出是ab
SELECT CONCAT ('姓名：', studentname) AS 新名字 FROM student
```

<font color='red'>**语法：**</font>`SELECT 字段, ... FROM 表`

> 有的时候，列的名字不是那么的见名知意。我们起别名     AS           字段名 AS 别名      表名 AS 别名



> 去重  distinct 

作用：去除SELECT查询出来的结果中重复的数据，重复的数据只显示一条

```sql
-- 查询一下有哪些同学参加了考试（即哪些学生有成绩）
SELECT * FROM result  -- 查询全部的考试成绩
SELECT `studentno` FROM result
SELECT DISTINCT `studentno` FROM result  -- 发现重复数据，去重
```



> 数据库的列（表达式）

```sql
SELECT VERSION()  -- 查询系统版本 （函数）
SELECT 100*3-1 AS 计算结果  -- 用来计算 （表达式）
SELECT @@auto_increment_increment  -- 查询自增的步长 （变量）


-- 学员考试成绩 +1分 查看
SELECT `studentno`, `studentresult`+1 AS 提分后 FROM `result`
```

<font color = red>数据库中的表达式：文本值，列，NULL，函数，计算表达式，系统变量...</font>

<font color='red'>**语法：**</font>`SELECT 表达式, ... FROM 表`





## 4.3 where 条件子句

作用：检索数据中**符合条件**的值

搜索的条件由一个或者多个表达式组成！结果是布尔值

> 逻辑运算符

| 运算符         | 语法                       | 描述                           |
| -------------- | -------------------------- | ------------------------------ |
| AND     &&     | a AND b         a && b     | 逻辑与，两个都为真，结果为真   |
| OR        \|\| | a OR b            a \|\| b | 逻辑或，其中一个为真，结果为真 |
| NOT      !     | NOT a             ! a      | 逻辑非，真为假，假为真         |

<font color = red>尽量使用英文字母</font>

```SQL
-- ======================== WHERE =======================
SELECT studentno, studentresult FROM result

-- 查询考试成绩在95到100之间的
SELECT studentno, studentresult FROM result 
WHERE studentresult >= 95 AND studentresult <= 100

-- AND  &&
SELECT studentno, studentresult FROM result 
WHERE studentresult >= 95 && studentresult <= 100

-- 模糊查询（区间）   BETWEEN...AND...
SELECT studentno, studentresult FROM result 
WHERE studentresult BETWEEN 95 AND 100

-- 除了1000号学生之外的同学的成绩
SELECT studentno, studentresult FROM result
WHERE studentno != 1000

-- !=  NOT
SELECT studentno, studentresult FROM result
WHERE NOT studentno = 1000
```



>模糊查询：比较运算符

| 运算符      | 语法                         | 描述                                                         |
| ----------- | ---------------------------- | ------------------------------------------------------------ |
| IS NULL     | a IS NULL                    | 如果操作符a为NULL，结果为真                                  |
| IS NOT NULL | a IS NOT NULL                | 如果操作符a为NOT NULL，结果为真                              |
| BETWEEN     | a BETWEEN b AND c            | 若操作符a在操作符b和c之间，结果为真                          |
| **LIKE**    | a LIKE b                     | SQL匹配，如果操作符a匹配b，结果为真                          |
| **IN**      | a IN (a~1~, a~2~, a~3~, ...) | 假设操作符a在a~1~, 或者a~2~, 或者a~3~其中的某一个值中，结果为真 |

🔺**<font color = red>注意：%在LIKE中用，IN中不可用！！</font>**



```sql
-- ======================== 模糊查询 =======================
-- 查询姓张的同学
-- LIKE结合  %(代表0到任意个字符)  _(一个字符)
SELECT studentno, studentname FROM `student`
WHERE studentname LIKE '张%'

-- 查询姓张的同学且名只有一个字
SELECT studentno, studentname FROM `student`
WHERE studentname LIKE '张_'

-- 查询名字中间有嘉字的同学
SELECT studentno, studentname FROM `student`
WHERE studentname LIKE '%嘉%'


-- ===== IN （具体的一个或者多个值）=====
-- 查询 1000， 1001号学员
SELECT studentno, studentname FROM `student`
WHERE studentno IN (1000, 1001);

-- 查询在北京朝阳的学生
SELECT studentno, studentname FROM `student`
WHERE `address` IN ('北京朝阳');


-- =====NULL    NOT NULL=====
-- 查询地址为空的学生  空：NULL或者''
SELECT studentno, studentname FROM `student`
WHERE address = '' OR address IS NULL

-- 查询有出生日期的同学   i.e.不为空
SELECT studentno, studentname FROM `student`
WHERE borndate IS NOT NULL

-- 查询没有出生日期的同学   i.e.为空
SELECT studentno, studentname FROM `student`
WHERE borndate IS NULL
```





## 4.4 联表查询

> JOIN  对比

![image-20231105194858598](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231105194858598.png)

![image-20231105195048319](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231105195048319.png)

```sql
-- =================== 联表查询 JOIN =================
-- 查询参加了考试的同学（学号，姓名，科目编号，分数）
SELECT * FROM `student`
SELECT * FROM `result`

/* 思路
1.分析需求，分析查询的字段来自哪些表（如果超过一张表要用连接查询）
2.确定使用哪种连接查询（一共有7种）
确定交叉点（这两个表中哪个数据是相同的）
判断的条件：学生表中的studentno = 成绩表种的studentno
*/

SELECT s.studentno, studentname, subjectno, studentresult
FROM student AS s
INNER JOIN result AS r
WHERE s.studentno = r.studentno

-- RIGHT JOIN 
SELECT s.studentno, studentname, subjectno, studentresult
FROM student s  -- AS省略掉了
RIGHT JOIN result r
ON s.studentno = r.studentno

-- LEFT JOIN 
SELECT s.studentno, studentname, subjectno, studentresult
FROM student s  -- AS省略掉了
LEFT JOIN result r
ON s.studentno = r.studentno
```

| 操作       | 描述                                             |
| ---------- | ------------------------------------------------ |
| INNER JOIN | 如果表中至少有一个匹配，就返回结果               |
| LEFT JOIN  | 会从左表中返回所有结果，即使右表中没有匹配的结果 |
| RIGHT JOIN | 会从右表中返回所有结果，即使左表中没有匹配的结果 |

```sql
-- 查询缺考的同学
SELECT s.studentno, studentname, subjectno, studentresult
FROM student s  -- AS省略掉了
LEFT JOIN result r
ON s.studentno = r.studentno
WHERE studentresult IS NULL



-- 思考题（查询参加考试的同学信息：学号，学生姓名，科目名称，分数）
-- 👆重点在参加考试的同学，所以第一步要将result作为主表，即result中对应出现的名字全部要输出
/* 思路
1.分析需求，分析查询的字段来自哪些表（如果超过一张表要用连接查询） student、result、subject
2.确定使用哪种连接查询（一共有7种）
确定交叉点（这两个表中哪个数据是相同的）
判断的条件：学生表中的studentno = 成绩表种的studentno
*/
SELECT s.studentno, studentname, subjectname, studentresult
FROM student s
RIGHT JOIN result r
ON r.studentno = s.studentno
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno


-- 我要查询哪些数据：SELECT ...
-- 从哪几个表中查：FROM 表 XXX JOIN 连接的表 ON 交叉条件
--                                           👆指的是两个表中共通的 
-- 假设存在一种多张表查询，慢慢来，从查询两张表开始，然后再慢慢增加

-- FROM a LEFT JOIN b    以a表为准
-- FROM a RIGHT JOIN b   以b表为准
```



> 自连接（了解）

==自己的表和自己的表连接，核心：**一张表拆为两张一样的表即可**==

父类

| categoryid | categoryname |
| ---------- | ------------ |
| 2          | 信息技术     |
| 3          | 软件开发     |
| 5          | 美术设计     |

子类

| pid  | categoryid | categoryname |
| ---- | ---------- | ------------ |
| 3    | 4          | 数据库       |
| 2    | 8          | 办公信息     |
| 3    | 6          | web开发      |
| 5    | 7          | ps技术       |

操作：查询父类对应的子类关系

| 父类     | 子类     |
| -------- | -------- |
| 信息技术 | 办公信息 |
| 软件开发 | 数据库   |
| 软件开发 | web开发  |
| 美术设计 | ps技术   |



```sql
-- 查询父子信息：把一张表看为两个一模一样的表
SELECT a.categoryname AS '父栏目', b.categoryname AS '子栏目'
FROM `category` AS a, `category` AS b
WHERE a.`categoryid` = b.`pid`
```







## 4.5 分页和排序

> 排序

```sql
-- 排序：升序ASC，降序DESC
-- ORDER BY 通过哪个字段排序，怎么排
-- 查询的结果根据成绩降序排序
SELECT s.studentno, studentname, subjectname, studentresult
FROM `student` s
LEFT JOIN `result` r
ON s.studentno = r.studentno
INNER JOIN `subject` sub
ON s.gradeid = sub.gradeid
WHERE subjectname = '数据库结构-1'
ORDER BY studentresult DESC 
```



> 分页

```sql
-- 假设100万条数据
-- 为什么要分页？
-- 缓解数据库压力，给人的体验更好     如果不分页就是瀑布流了（无限往下刷，刷不完）

-- 分页，每页只显示3条数据
-- 语法：limit 起始值,页面的大小
-- 网页应用：当前,总的页数,页面的大小
-- LIMIT 0,3     1~3
-- LIMIT 1,3     2~4
-- LIMIT 3,3     4~6
SELECT s.studentno, studentname, subjectname, studentresult
FROM `student` s
LEFT JOIN `result` r
ON s.studentno = r.studentno
INNER JOIN `subject` sub
ON s.gradeid = sub.gradeid
WHERE subjectname = '数据库结构-1'
ORDER BY studentresult DESC 
LIMIT 3, 3

-- 第一页  LIMIT 0,3     (1-1)*3=0
-- 第二页  LIMIT 3,3     (2-1)*3=3
-- 第三页  LIMIT 6,3     (3-1)*3=6
-- 第N页   LIMIT ?,?  -> (n-1)*pageSize,pageSize
-- 【pageSize：页面大小】
-- 【(n-1)*pageSize：起始值】
-- 【n：当前页】
-- 【数据总数/页面大小 + 1 = 总页数】
```

<font color='red'>**语法：**</font>`LIMIT(查询起始值, pageSize)`





## 4.6 子查询

where (这个值是计算出来的)

本质：`在where语句中嵌套一个查询语句`

```sql
-- ================ where ====================
-- 1、查询 高等数学-1 的所有考试结果（学号，科目编号，成绩），降序排列
-- 方式一：使用连接查询
SELECT studentno, subjectname, studentresult
FROM `result` r
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
WHERE subjectname = '高等数学-1'
ORDER BY studentresult DESC

-- 方式二：使用子查询（由里及外）
-- 查询所有 高等数学-1 的学生学号
SELECT studentno, subjectname, studentresult
FROM `result`
WHERE subjectno = (
  SELECT subjectno FROM `subject` 
  WHERE subjectname = '高等数学-1'  
)  -- 注意，这边返回的只能是唯一值



-- 查询分数不小于80分的学生的学号姓名
SELECT s.studentno, studentname
FROM `student` s
INNER JOIN `result` r
ON s.studentno = r.studentno
WHERE studentresult >= 80

-- 在这个基础上增加一个科目，高等数学-1
-- 可以不用联表 
SELECT s.studentno, studentname
FROM `student` s
INNER JOIN `result` r
ON s.studentno = r.studentno
WHERE studentresult >= 80 AND subjectno = (
  SELECT subjectno FROM `subject`
  WHERE subjectname = '高等数学-1'
)

-- 再改造（由里及外）
SELECT studentno, studentname FROM student WHERE studentno IN (
  SELECT studentno FROM result WHERE studentresult >= 80 AND subjectno = (
    SELECT subjectno FROM `subject` WHERE subjectname = '高等数学-1'
  )
)


-- 查询课程为高等数学-1且分数不小于80分的学生的学号姓名
SELECT s.studentno, studentname
FROM `student` s
INNER JOIN `result` r
ON s.studentno = r.studentno
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
WHERE studentresult >= 80 AND subjectname = '高等数学-1'
```





## 4.7 分组和过滤

```sql
-- 查询不同课程的平均分，最高分，最低分
-- 核心：（根据不同的课程分组）
SELECT subjectname, AVG(studentresult) AS 平均分, MAX(studentresult) AS 最高分, MIN(studentresult) AS 最低分
FROM result r
INNER JOIN `subject` sub
ON r.subjectno = sub.subjectno
GROUP BY subjectname  -- 通过什么字段来分组
HAVING 平均分 > 80
```





## 4.8 小结

顺序很重要：

SELECT去重 要查询的字段 FROM表   （注意：表和字段可以取别名）

xxx JOIN 要连接的表 ON 等值判断

WHERE （具体的值，子查询语句）

GROUP BY （通过哪个字段来分组）

HAVING （过滤分组后的信息，条件和WHERE一样，只是位置不同）

ORDER BY  ...（通过哪个字段排序）[升序/降序]

LIMIT startindex, pageSize



业务层面：

查询：跨表、跨数据库



**具体看4.1**

































# 5、MySQL函数

## 5.1 常用函数

```sql
-- ==================== 常用函数 =====================

-- 数学运算
SELECT ABS(-8)  -- 绝对值
SELECT CEILING(9.4)  -- 向上取整
SELECT FLOOR(9.4)  -- 向下取整
SELECT RAND()  -- 随机数，返回一个[0,1]之间的随机数
SELECT SIGN(123)  -- 判断一个数的符号  0返回0，正数返回1，负数返回-1

-- 字符串函数
SELECT CHAR_LENGTH('即使再小的帆也能远航')  -- 字符串长度
SELECT CONCAT('我', '爱', '你')  -- 拼接字符串
SELECT INSERT('我爱编程helloworld', 1, 2, '超级热爱')  -- 从某个位置开始替换某个长度的字符串
SELECT LOWER('KEIndiwnf')  -- 转小写字母
SELECT UPPER('sdfEF')  -- 转大写字母
SELECT INSTR('sdfjowhcn', 'j')  -- 返回第一次出现的子串的索引值  🔺字符串第一位索引值是1！
SELECT REPLACE('狂神说坚持就能成功', '坚持', '努力')  -- 替换出现的指定字符串
SELECT SUBSTR('狂神说坚持就能成功', 4, 6)  -- 返回指定的子字符串（str, 起始位置, 截取数量）
SELECT REVERSE('狂神说坚持就能成功')  -- 反转

-- 查询姓张的同学，名字 邹
SELECT REPLACE(studentname,'张','邹') FROM student
WHERE studentname LIKE '张%'

-- 时间和日期函数（记住）
SELECT CURRENT_DATE()  -- 获取当前日期
SELECT CURDATE()  -- 获取当前日期
SELECT NOW()  -- 获取当前的时间
SELECT LOCALTIME()  -- 获取本地时间
SELECT SYSDATE()  -- 系统时间

SELECT YEAR(NOW())  -- 年
SELECT MONTH(NOW())  -- 月
SELECT DAY(NOW())  -- 日
SELECT HOUR(NOW())  -- 时
SELECT MINUTE(NOW())  -- 分
SELECT SECOND(NOW())  -- 秒

-- 系统
SELECT SYSTEM_USER()
SELECT USER()
SELECT VERSION()
```







## 5.2 聚合函数（常用）

| 函数名称 | 描述   |
| -------- | ------ |
| COUNT()  | 计数   |
| SUM()    | 求和   |
| AVG()    | 平均值 |
| MAX()    | 最大值 |
| MIN()    | 最小值 |
| ...      | ...    |

```sql
-- ================== 聚合函数 =================
-- 都能够统计 表中的数据（想查询一个表中由多少个记录，就使用COUNT()）
SELECT COUNT(borndate) FROM student  -- COUNT(字段)，会忽略所有的NULL值
SELECT COUNT(*) FROM student  -- COUNT(*)，不会忽略所有的NULL值，本质：计算行数
SELECT COUNT(1) FROM result  -- COUNT(1)，不会忽略所有的NULL值，本质：计算行数

SELECT SUM(studentresult) AS 总和, AVG(studentresult) AS 平均分, MAX(studentresult) AS 最高分, MIN(studentresult) AS 最低分
FROM result
```







## 5.3 数据库级别的MD5加密（扩展）

什么是MD5？

主要增强算法复杂度和不可逆性。

MD5不可逆，具体的值的MD5是一样的

MD5破解网站的原理，背后有一个字典，MD5加密后的值  加密前的值



```sql
-- ============== 测试MD5 加密 ==============
CREATE TABLE `testmd5`(
  `id` INT(4) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `pwd` VARCHAR(50) NOT NULL,
  PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

-- 明文密码
INSERT INTO testmd5 VALUES
(1, 'zhangsan', '123456'),
(2, 'lisi', '123456'),
(3, 'wangwu', '123456')

-- 加密
UPDATE testmd5 SET pwd=MD5(pwd)  -- 加密全部的密码

-- 插入的时候就加密
INSERT INTO testmd5 VALUES(4, '小明', MD5('123456'))

-- 如何校验：将用户传递进来的密码进行md5加密，然后比对加密后的值
SELECT * FROM testmd5 WHERE `name`='小明' AND pwd=MD5('123456')
```



































# 6、事务

什么是事务？

<font color = red>要么都成功，要么都失败</font>

——————————————————————

1.SQL执行    A给B转账                A 1000-->-200    B 200

2.SQL执行    B收到A的钱            A 800      B 400

——————————————————————

将一组SQL放在一个批次中去执行



> 事务原则：ACID原则        原子性（Atomicty），一致性（Consistency），隔离性（Isolation），持久性（Durability）

**原子性（Atomicty）：**上例中，要么都成功，要么都失败

**一致性（Consistency）：**上例中，事务执行前后总钱数是一致的

**持久性（Consistency）：**事务没有提交，恢复到原状；事务已经提交，持久化到数据库的文件内。事务一旦提交就不可逆

**隔离性（Isolation）：**例如，A给B转账和C给B转账同时发生，互不影响



> 隔离所导致的一些问题  （脏读，幻读……）

**脏读：**指一个事务读取了另外一个事务未提交的数据

**不可重复读：**在一个事务内读取表中的某一行数据，多次读取结果不同。（这个不一定是错误，只是某些场合不对）

幻读：是指在一个事务内读取到了别的事务插入的数据，导致前后读取不一致





> 执行事务

```sql
-- ================== 事务 ==================
-- mysql 是默认开启事务自动提交的
SET autocommit = 0 /* 关闭 */
SET autocommit = 1 /* 开启（默认的） */

-- 手动处理事务
SET autocommit = 0  -- 关闭自动提交
-- 事务开启
START TRANSACTION  -- 标记一个事务的开始，从这个之后的sql都在同一事务内

INSERT xx
INSERT xx

-- 提交：持久化（提交成功！）
COMMIT
-- 回滚：回到原来的样子（提交失败！）
ROLLBACK

-- 事务结束
SET autocommit = 1  -- 开启自动提交

-- 了解       保存点类似于游戏的存档点
SAVEPOINT 保存点的名称  -- 设置一个事务的保存点
ROLLBACK TO SAVEPOINT 保存点的名称  -- 回滚到保存点
RELEASE SAVEPOINT 保存点的名称  -- 撤销保存点
```

![image-20231107205410887](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231107205410887.png)





> 模拟场景

```sql
-- 转账
CREATE DATABASE shop CHARACTER SET utf8 COLLATE utf8_general_ci
USE shop

CREATE TABLE `account`(
  `id` INT(3) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `money` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `account`(`name`,`money`) VALUES('A',2000),('B',10000)

-- 模拟转账：事务
SET autocommit = 0;  -- 关闭自动提交
START TRANSACTION  -- 开启一个事物

UPDATE `account` SET money=money-500 WHERE `name`='A'  -- A减500
UPDATE `account` SET money=money+500 WHERE `name`='B'  -- B加500

COMMIT;  -- 提交事务，数据就被持久化啦！！
ROLLBACK;  -- 回滚

SET autocommit = 1;  -- 恢复默认值
```



































# 7、索引

> MySQL官方对索引的定义为：**索引（index）是帮助MySQL高效获取数据的数据结构。**    假设没有运用索引用时0.5s，用了索引用时0.00001s
>
> <font color = green>本质：索引是数据结构。</font>

## 7.1 索引的分类

> 在一个表中，主键索引只能有一个，唯一索引可以有多个

- 主键索引  (PRIMARY KEY)

​	      唯一的标识，主键不可重复，只能有一个列作为主键

- 唯一索引  (UNIQUE KEY)

  ​       避免重复的列出现，唯一索引可以重复，多个列都可以表示为唯一索引

- 常规索引  (KEY / INDEX)

​              默认的，INDEX / KEY关键字来设置

- 全文索引  (FULLTEXT)

​              在特定的数据库引擎下才有，过去只有MyISAM有

​              作用：快速定位数据



基础语法：

```sql
-- ================== 索引的使用 ==================
-- 1.在创建表的时候给字段增加索引
-- 2.创建完毕后，增加索引

-- 显示所有的索引信息
SHOW INDEX FROM student

-- 增加一个全文索引  索引名（列名）
ALTER TABLE school.`student` ADD FULLTEXT INDEX `studentname`(`studentname`)

-- EXPLAIN 分析SQL执行的状况
EXPLAIN SELECT * FROM student;  -- 非全文索引

EXPLAIN SELECT * FROM student WHERE MATCH(studentname) AGAINST ('张');
```









## 7.2 测试索引

```sql
CREATE TABLE `app_user` (
`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50) DEFAULT'' COMMENT'用户昵称',
`email` VARCHAR(50) NOT NULL COMMENT'用户邮箱',
`phone` VARCHAR(20) DEFAULT'' COMMENT'手机号',
`gender` TINYINT(4) UNSIGNED DEFAULT '0'COMMENT '性别（0：男;1:女）',
`password` VARCHAR(100) NOT NULL COMMENT '密码',
`age` TINYINT(4) DEFAULT'0'  COMMENT '年龄',
`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT = 'app用户表'

-- 插入100万条数据
DELIMITER $$  -- 写函数之前必须要写，标志
CREATE FUNCTION mock_data()
RETURNS INT DETERMINISTIC
BEGIN
  DECLARE num INT DEFAULT 1000000;
  DECLARE i INT DEFAULT 0;
  
  WHILE i<num DO
    -- 插入语句
    INSERT INTO app_user(`name`,`email`,`phone`,`gender`,`password`,`age`)VALUES(CONCAT('用户', i), '24736743@qq.com', CONCAT('18', FLOOR(RAND()*(999999999-100000000)+100000000)),FLOOR(RAND()*2), UUID(), FLOOR(RAND()*100));
    SET i = i+1;
  END WHILE;
  RETURN i;
END;

SELECT mock_data();

SELECT * FROM app_user WHERE `name`='用户9999';

EXPLAIN SELECT * FROM app_user WHERE `name`='用户9999';

-- id_表名_字段名
-- CREATE INDEX 索引名 ON 表(字段)
CREATE INDEX id_app_user ON app_user(`name`);
```

运用索引前：![image-20231108202922391](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231108202922391.png)

运用索引后：![image-20231108202713508](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231108202713508.png)

<font color = red>索引在小数据量的时候用处不大，但是在大数据的时候区别十分明显！</font>







## 7.3 索引原则

- 索引不是越多越好
- 不要对经常变动的数据加索引
- 小数据量的表不需要加索引
- 索引一般加在常用来查询的字段上



> 索引的数据结构

Hash类型的索引

Btree：INNODB的默认数据结构



































# 8、权限管理和备份

## 8.1 用户管理

> SQL yog 可视化管理

![image-20231108210351589](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231108210351589.png)



> SQL命令操作

用户表：mysql.user

本质：对这张表进行增删改查

```sql
-- 创建用户  CREATE USER 用户名 IDENTIFIED BY '密码'
CREATE USER chenyyy IDENTIFIED BY '123456'

-- 修改密码 （修改当前用户密码）
ALTER PASSWORD = PASSWORD('111111')

-- 修改密码 （修改指定用户密码）
SET PASSWORD FOR chenyyy = '111111'

-- 重命名  RENAME USER 原来的名字 TO 新的名字
RENAME USER chenyyy TO chenyyy111;


-- 用户授权  ALL PRIVILEGES 全部权限，库.表
-- ALL PRIVILEGES 除了给别人授权，其他都能够干 
GRANT ALL PRIVILEGES ON *.* TO chenyyy111

-- 查询权限
`user` -- 查看指定用户的权限
SHOW GRANTS FOR root@localhost

-- 撤销权限  REVOKE 哪些权限，在哪个库撤销，给谁撤销
REVOKE ALL PRIVILEGES ON *.* FROM chenyyy111

-- 删除用户
DROP USER chenyyy111
```







## 8.2 MySQL备份

为什么要备份：

- 保证重要数据不丢失
- 数据转移

MySQL数据库备份的方式：

- 直接拷贝物理文件
- 在sqlyog这种可视化工具中手动导出

​             在想要导出的表或者库中右键选择**备份或导出**

![image-20231108213836904](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231108213836904.png)

- 使用命令行导出   **mysqldump** 命令行中使用

```sql
# mysqldump -h主机 -u用户名 -p密码 数据库 表名 >物理磁盘位置/文件名
C:\Windows\System32>mysqldump -hlocalhost -uroot -p832203212 school student >D:/a.sql

# mysqldump -h主机 -u用户名 -p密码 数据库 表1 表2 表3 >物理磁盘位置/文件名
C:\Windows\System32>mysqldump -hlocalhost -uroot -p832203212 school student result >D:/b.sql

# mysqldump -h主机 -u用户名 -p密码 数据库 >物理磁盘位置/文件名
C:\Windows\System32>mysqldump -hlocalhost -uroot -p832203212 school >D:/c.sql


# 导入
# 登录情况下，切换到指定的数据库
C:\Windows\System32>mysql -uroot  -p832203212    # 登录
mysql> use school    # 切换指定的数据库
# source 备份文件
mysql> source d:a.sql
```

假设要备份数据库，防止数据丢失。

把数据库给朋友，直接给sql文件就可以了





























# 9、规范数据库设计

## 9.1 为什么需要设计

==当数据库比较复杂的时候，我们就需要设计了==

**糟糕的数据库设计：**

- 数据冗余，浪费空间
- 数据插入和删除都会麻烦、产生异常【屏蔽所有物理外键】
- 程序的性能差

**良好的数据库设计：**

- 节省内存空间
- 保证数据库的完整性

- 方便我们开发系统

软件开发中，关于数据库的设计

- 分析需求：分析业务和需要处理的数据的需求
- 概要设计：设计关系图  E-R图



**设计数据库的步骤：（以个人博客为例）**

- 收集信息，分析需求

  - 用户表（用户登录注销，用户的个人信息，写博客，创建分类）

  - 分类表（文章分类，谁创建的）

  - 文章表（文章的信息）
  - 评论表
  - 友链表（友链信息）
  - 自定义表（系统信息，某个关键的字，或者一些著字段）   key：value
  - 说说表（发表心情...id,content,create_time...）

- 标识实体（把需求落地到每个字段）

- 标识实体之间的关系

  - 写博客：user --> blog
  - 创建分类：user --> category
  - 关注：user --> user
  - 友链：links
  - 评论：user - user - blog



## 9.2 三大范式    ?

**为什么需要数据规范化？**

- 信息重复

- 更新异常
- 插入异常
  - 无法正常显示信息

- 删除异常

  - 丢失有效信息

  

> 三大范式（了解 ）

**第一范式（1NF）**

原子性：保证每一列不可再分![image-20231109194559982](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231109194559982.png)👆显然下面这张表更好，每一行都满足原子性！



**第二范式（2NF）**

前提：满足第一范式

每张表只描述一件事情![image-20231109195541145](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231109195541145.png)

<font color = pink>感觉有点奇怪</font>





**第三范式（3NF）**

前提：满足第一范式和第二范式

确保数据表中的每一列数据都和主键直接相关，而不能间接相关。![image-20231109195929982](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231109195929982.png)



（规范数据库的设计）



==都按照规范设计好吗？==

不好！

这涉及 **规范性 和 性能问题**

阿里数据库规矩：关联查询的表不得超过三张表

- 考虑商业化的需求和目标，（成本，用户体验）数据库的性能更加重要
- 在规范性能的问题时，需要适当考虑一下规范性！
- 故意给某些表增加一些冗余的字段。（从多表查询中变为单表查询）
- 故意增加一些计算列（从大数据量降低为小数据量的查询：例如增加索引）

























# 10、JDBC (重点)

## 10.1 数据库驱动

驱动：声卡、显卡、数据库

![image-20231109202434429](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231109202434429.png)

我们的程序会通过数据库驱动和数据库打交道









## 10.2 JDBC

SUN公司为例简化开发人员（对数据库的统一）的操作，提供了一个（Java操作数据库的）规范，俗称JDBC

这些规范的实现由具体的厂商去做

对于开发人员来说，我们只需要掌握JDBC接口的操作即可！

![image-20231109203050379](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231109203050379.png)

java.sql

javax.sql

还需要导入一个数据库驱动包







## 10.3 第一个JDBC程序

> 创建测试数据库

```sql
CREATE DATABASE jdbcStudy CHARACTER SET utf8 COLLATE utf8_general_ci;

USE jdbcStudy;

CREATE TABLE users(
  id INT PRIMARY KEY,
  NAME VARCHAR(40),
  PASSWORD VARCHAR(40),
  email VARCHAR(60),
  birthday DATE
);

INSERT INTO users(id, NAME, PASSWORD, email, birthday)
VALUES(1, 'zhansan', '123456', 'zs@sina.com', '1980-12-04'),
(2, 'lisi', '123456', 'lisi@sina.com', '1981-12-04'),
(3, 'wangwu', '123456', 'wangwu@sina.com', '1979-12-04');
```



1. 创建一个普通项目

2. 导入数据库驱动

   ![image-20231109205421892](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231109205421892.png)

3. 编写测试代码

```java
package com.chenyyy111.lesson01;

import java.sql.*;

/**
 * 我的第一个JDBC程序
 */
public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");  // 固定写法，加载驱动

        //2.用户信息和url
        //useUnicode=true  支持中文编码
        //characterEncoding=utf8  设置中文字符集为utf8
        //useSSL=true  使用安全的连接
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "832203212";

        //3.连接成功，返回数据库对象    Connection代表数据库
        Connection connection = DriverManager.getConnection(url,username,password);

        //4.执行SQL的对象    Statement是执行sql的对象
        Statement statement = connection.createStatement();

        //5.执行SQL的对象去执行SQL，可能存在结果，查看返回结果
        String sql = "SELECT * FROM users";

        ResultSet resultSet = statement.executeQuery(sql);  //返回的结果集。 结果集中封装了我们全部的查询出来的结果

        while(resultSet.next()){
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("name"));
            System.out.println("pwd=" + resultSet.getObject("password"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("birth=" + resultSet.getObject("birthday"));
            System.out.println("======================================");
        }

        //6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
```

步骤总结：

1. 加载驱动
2. 连接数据库 DriverManager
3. 获取执行sql的对象 Statement
4. 获得返回的结果集
5. 释放连接





> DriverManager

```java
// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());  不推荐
Class.forName("com.mysql.cj.jdbc.Driver");  // 固定写法，加载驱动

 Connection connection = DriverManager.getConnection(url,username,password);
//connection  代表数据库

//数据库设置自动提交
connection.setAutoCommit();
//事务提交
connection.commit();
//事务回滚
connection.rollback();
```



> URL

```java
String url = "jdbc:mysql://localhost:3306/jdbcstudy? seUnicode=true&characterEncoding=utf8&useSSL=true";

//mysql的端口号默认为：3306
// 协议://主机地址:端口号/数据库名?参数1&参数2&参数3
//oralce的端口号默认为：1521
//jdbc:oracle:thin:@localhost:1521:sid
```



> Statement  执行SQL的对象      PrepareStatement执行的对象

```java
String sql = "SELECT * FROM users";  //编写SQL

statement.executeQuery();  //查询操作返回ResultSet
statement.execute();  //执行任何SQL（说明有判断的过程，效率相对较低）
statement.executeUpdate();  //更新、插入、删除都使用这个，返回受影响的行的数量
```



> ResultSet  查询的结果集：封装了所有的查询结果

获得指定的数据类型

```java
resultSet.getObject();  //在不指定列类型的情况下使用
//如果知道列的类型就使用指定的类型
resultSet.getString();
resultSet.getInt();
resultSet.getFloat();
resultSet.getDate();
```

便利

```java
resultSet.beforeFirst();  //移动到最前面
resultSet.afterLast();  //移动到最后面
resultSet.next();  //移动到下一个数据
resultSet.previous();//移动到前一行
resultSet.absolute(row);//移动到指定行row
```



> 释放资源

```java
resultSet.close();
statement.close();
connection.close();  
//耗资源，用完关掉！
```







## 10.4 Statement对象

<font color = red>Jdbc中的statement对象用于向数据库发送SQL语句，想完成对数据库的增删改查，只需要通过这个对象向数据库发送增删改查语句即可。</font>

Statement对象的executeUpdate方法，用于向数据库发送增、删、改的sql语句，executeUpdate执行完后，将会返回一个整数（即增删改语句导致了数据库几行数据发生了变化）

Statement.executeQuery方法用于向数据库发送查询语句，executeQuery方法返回代表查询结果的ResultSet对象。



> CRUD操作-create

使用executeUpdate(String sql)方法完成数据添加操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "insert into user(...,) values(...,)";
int num = st.executeUpdate(sql);
if(num > 0){
    System.out.println("插入成功！！！");
}
```



> CRUD操作-delete

使用executeUpdate(String sql)方法完成数据删除操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "delete from user where id = 1";
int num = st.executeUpdate(sql);
if(num > 0){
    System.out.println("删除成功！！！");
}
```



> CRUD操作-update

使用executeUpdate(String sql)方法完成数据修改操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "update user set name = '' where name = ''";
int num = st.executeUpdate(sql);
if(num > 0){
    System.out.println("修改成功！！！");
}
```



> CRUD操作-read

使用executeQuery(String sql)方法完成数据查询操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "select * from user where id = 1";
ResultSet rs = st.executeQuery(sql);
while(rs.next()){
    //根据获取列的数据类型，分别调用rs的相应方法映射到java对象中
}
```





> 代码实现

1. 提取工具类

```java
package com.chenyyy111.lesson02.utils;

import com.mysql.cj.protocol.Resultset;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Description
 */
public class JdbcUtils {

    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static{
        try {
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            //1.驱动只用加载一次
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);

    }

    //释放连接资源                数据库连接对象    执行操作对象     结果集
    public static void release(Connection conn, Statement st, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
```

2. 编写增删改的方法：`executeUpdate`

```java
package com.chenyyy111.lesson02;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();  //获取数据库连接
            st = conn.createStatement();  //获取SQL的执行对象
            String sql = "INSERT INTO users(id, `name`, `password`, `email`, `birthday`)" +
                    "VALUES(4, 'kuangshen', '123456', '2342432423@qq.com', '2020-01-01');";
            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("插入成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

```java
package com.chenyyy111.lesson02;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {
    public static void main(String[] args){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "delete from users where name = 'kuangshen';";
            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("删除成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

```java
package com.chenyyy111.lesson02;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
    public static void main(String[] args){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "update users set `name` = 'kuangshen' where `id` = 1";
            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("修改成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

3. 查询：`executeQuery`

```java
package com.chenyyy111.lesson02;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {
    public static void main(String[] args){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            String sql = "select * from users where id = 2";

            rs= st.executeQuery(sql);  //查询完毕返回一个结果集

            if(rs.next()){  //如果有多条数据需要输出就要用while  （单条数据可以用if也可以用while）
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```





> SQL注入的问题

SQL存在漏洞，会被攻击导致数据泄露，<font color = red>SQL会被拼接，主要是有 **OR** 的原因</font>

```java
package com.chenyyy111.lesson02;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL注入 {
    public static void main(String[] args){
        //login("kuangshen","123456");  //正常登录
        login("'or '1=1", "' or '1=1");  //技巧
    }

    //登录业务
    public static void login(String username, String password){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            //select * from users where name = ' ' or '1=1' and password = '' or '1=1'
            String sql = "select * from users where name = '" + username + "' and password = '" + password + "'";
            rs = st.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("password"));
                System.out.println("====================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }

    }
}
```









## 10.5 PreparedStatement对象

PreparedStatement可以防止SQL注入。效果更好！



1. 新增

```java
package com.chenyyy111.lesson03;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.*;
import java.util.Date;

public class TestInsert {
    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            //区别Statement
            ///使用 ? 占位符代替参数
            String sql = "INSERT INTO users(id, `name`, `password`, `email`, `birthday`) VALUES(?,?,?,?,?)";

            st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行

            ///手动给参数赋值
            st.setInt(1, 4);//id
            st.setString(2, "qinjiang");
            st.setString(3, "1232112");
            st.setString(4, "2473467@qq.com");
            ////注意点：sql.Date     数据库   java.sql.Date()
            ////       util.Date    Java     new Date().getTime()获得时间戳
            st.setDate(5, new java.sql.Date(new Date().getTime()));

            //执行
            int i = st.executeUpdate();
            if (i>0){
                System.out.println("插入成功！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn, st, null);
        }
    }
}
```

2. 删除

```java
package com.chenyyy111.lesson03;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDelete {
    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            String sql = "delete from users where id=?";

            st = conn.prepareStatement(sql);

            st.setInt(1, 4);

            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("删除成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

3. 更新

```java
package com.chenyyy111.lesson03;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "update users set name = ? where id = ?";
            st = conn.prepareStatement(sql);

            st.setString(1, "狂神");
            st.setInt(2, 1);

            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("更新成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

4. 查询

```java
package com.chenyyy111.lesson03;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSelect {
    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from users where id = ?"; //编写SQL
            st = conn.prepareStatement(sql); //预编译
            st.setInt(1, 1); //传递参数
            rs = st.executeQuery();//执行

            if(rs.next()){
                System.out.println(rs.getString("name"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```

5. 防止SQL注入

```java
package com.chenyyy111.lesson03;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.*;

public class SQL注入 {
    public static void main(String[] args){
        login("lisi","123456");  //正常登录
//        login("''or '1=1", "123456");

    }

    //登录业务
    public static void login(String username, String password) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            ///PreparedStatement 防止SQL注入的本质，把传递进来的参数当做字符
            ///假设其中存在转义字符，会被直接转义
            String sql = "select * from users where name = ? and password = ?";

            st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);

            rs = st.executeQuery(); //查询完毕会返回一个结果集
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("password"));
                System.out.println("====================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```







## 10.7 使用IDEA连接数据库

连接数据库👇

![image-20231112113211568](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231112113211568.png)

连接成功后，选择数据库👇

![image-20231112113447636](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231112113447636.png)

双击数据库查看表格👇

![image-20231112113931424](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231112113931424.png)

更新数据👇

![image-20231112114529745](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231112114529745.png)



![image-20231112114510592](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231112114510592.png)

连接失败，查看原因👇

![image-20231112120234965](C:\Users\14711\AppData\Roaming\Typora\typora-user-images\image-20231112120234965.png)







## 10.8 事务

<font color = red>要么都成功，要么都失败</font>

> ACID原则

原子性：要么全部完成，要么都不完成

一致性：总数不变

**隔离性：多个进程互不干扰**

持久性：一旦提交不可逆，持久化到数据库了



隔离性问题：

脏读：一个事务读取了另一个没有提交的事务。

不可重复读：在同一个事务内，重复读取表中的数据，表数据发生了改变。

虚读（幻读）：在一个事务内，读取到了别人插入的数据，导致前后读出的结果不一致。



> 代码实现

1. 开启事务`conn.setAutoCommit(false);`
2. 一组业务执行完毕，提交事务
3. 可以在catch语句中显示的定义回滚语句，且默认失败就会回滚

```java
package com.chenyyy111.lesson04;

import com.chenyyy111.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//事务
public class TestTransaction2 {
    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            //关闭数据库的自动提交，自动会开启事务
            conn.setAutoCommit(false);

            String sql1 = "update account set money = money - 100 where name = 'A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();

//            int x = 1/0; //报错

            String sql2 = "update account set money = money + 100 where name = 'B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            //业务完毕，提交事务
            conn.commit();
            System.out.println("成功");

        }catch (SQLException e){
            try {
                conn.rollback(); //如果失败则回滚事务   这句话不写也会回滚，默认失败就回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
```







## 10.9 数据库连接池

数据库连接 --- 执行完毕 --- 释放

从连接到释放是非常浪费系统资源的

**池化技术：准备一些预先的资源，过来就连接预先准备好的**



最小连接数：一般等于常用连接数

最大连接数：业务最高承载上限

等待超时：例如100ms



编写连接池：实现一个接口  DataSource



> 开源数据源实现

DBCP

C3P0

Druid：阿里巴巴的



使用了这些数据库连接池之后，我们在项目开发中就不需要编写连接数据库的代码了！



> DBCP

需要用到的jar包

commons-dbcp-1.4     commons-pool-1.6



> C3P0

需要用到的jar包

c3p0-0.9.5.5     mchange-commons-java-0.2.19



> 结论

无论使用什么数据源，本质还是一样的，DataSource接口不会变，方法就不会变



Druid
