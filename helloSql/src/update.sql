---修改字段的名称
ALTER TABLE <表名> CHANGE COLUMN <旧的字段名> <新的字段名> <数据类型> NOT NULL;

---修改表里的数据，如果没有WHERE，则会修改所有数据
UPDATE <表名> SET <字段1=值1>, <字段1=值1> WHERE <表达式>;
--修改字段值可以用表达式
UPDATE <表名> SET <字段=值+50> WHERE <表达式>;