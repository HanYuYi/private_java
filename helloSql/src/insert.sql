---添加一列到表中
ALTER TABLE <表名> ADD COLUMN <字段名> <数据类型> NOT NULL;

---添加一行到表中
INSERT INTO <表名> (<字段1>, <字段2>) VALUES (值1, 值2);
--添加两行到表中，自增字段 和 默认有值的字段 可以不用设置
INSERT INTO <表名> (<字段1>, <字段2>) VALUES
    (值1, 值2),
    (值1, 值2);