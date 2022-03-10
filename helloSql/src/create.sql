--创建数据库
CREATE DATABASE IF NOT NUll EXISTS <数据库名称>;

--使用数据库
USE <数据库名称>;

--创建表
--AUTO_INCREMENT 自动增长
--常用数据类型 INT BIGINT REAL DOUBLE CHAR() VARCHAR() BOOLEAN DATE TIME DATETIME，具体参考：https://www.liaoxuefeng.com/wiki/1177760294764384/1179613436834240
CREATE TABLE <表名> (
    <字段名称> <数据类型> NOT NULL AUTO_INCREMENT,
    --设置主键
    PRIMARY KEY (<字段名称>)
) ENGINE=InnoDB DEFAULT CHARACTER=utf8;

--插入数据
INSERT INTO <表名>(<字段1>, <字段2>) VALUES (<字段1的值>, <字段2的值>);