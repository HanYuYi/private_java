---插入或替换
REPLACE  INTO <表名> (<字段1>, <字段1>) VALUES (<值1>, <值2>);

---插入或更新
INSERT INTO <表名> (<字段1>, <字段1>) VALUES (<值1>, <值2>) ON DUPLICATE KEY UPDATE <字段1 = <值1>, <字段1> = <值2>;

---插入或忽略
INSERT IGNORE INTO <表名> (<字段1>, <字段1>) VALUES (<值1>, <值2>);

---快照 即复制一份当前表的数据到一个新表
CREATE TABLE <新表名> SELECT * FROM <旧表名> WHERE <表达式>;

---将查询结果写入表中，前提是被写入的表已经存在
INSERT INTO <被写入的表名> (<被写入的表的字段1>, <被写入的表的字段2>)
    SELECT <查询表的字段1>, AVG(查询表的字段2)
    FROM <查询表的名称>
    GROUP BY <分组的字段>;


----------------------------------
--事务
--两条或多条语句必须要一起执行
----------------------------------
BEGIN;
UPDATE students SET score = score - 5 WHERE id = 1;
UPDATE students SET score = score + 5 WHERE id = 2;
COMMIT;
--回滚事务
--将 COMMIT 改为 ROLLBACK

--查看事务状态
SELECT @@AUTOCOMMIT;
--mysql 默认是开启事务的
--关闭事务的默认提交
SET AUTOCOMMIT=0;

----事务的并发隔离（两个或两个以上的终端在操作同一个表）
---有四种隔离级别
--Read Uncommitted 隔离级别最低，事务A会读到另事务B更新后但未提交的数据，如果事务B回滚，事务A读到的数据就是脏数据，脏读
--Read Committed
--Repeatable Read
--Serializable 严格的隔离级别，脏读、不可重复读、幻读都不会出现

--在MySQL中，如果使用InnoDB，默认的隔离级别是Repeatable Read


