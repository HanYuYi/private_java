--创建数据库
CREATE DATABASE IF NOT NUll EXISTS <数据库名称>;

--使用数据库
USE <数据库名称>;

--创建表
--AUTO_INCREMENT 自动增长
--常用数据类型 INT BIGINT REAL DOUBLE CHAR() VARCHAR() BOOLEAN DATE TIME DATETIME，
--具体参考：https://www.liaoxuefeng.com/wiki/1177760294764384/1179613436834240
CREATE TABLE <表名> (
    <字段名称> <数据类型> NOT NULL AUTO_INCREMENT,
    --设置主键
    PRIMARY KEY (<字段名称>)
) ENGINE=InnoDB DEFAULT CHARACTER=utf8;

---创建登录用户（要先创建数据库）
CREATE USER IF NOT EXISTS <用户名>@'%' IDENTIFIED BY '<口令>';
---用户授权
GRANT ALL PRIVILEGES ON <数据库名>.* TO <用户名>@'%' WITH GRANT OPTION;
---刷新权限
FLUSH PRIVILEGES;