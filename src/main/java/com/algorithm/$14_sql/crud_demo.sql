-- 1：
-- sql格式化数字
-- SELECT FORMAT(12332.123456, 4); ---12332.1234
-- SELECT FORMAT(12332.123456, 2); ---12332.12

-- 2：
-- 删除
-- DELETE ul FROM user_left ul WHERE ul.id=5
-- http://blog.csdn.net/chs_jdmdr/article/details/46708917

-- 3：
-- mysql联合修改
-- UPDATE user_left AS ul , dept_left dl SET ul.`name`="update111" WHERE ul.dept_id = dl.dept_id AND  dl.dept_id = 1

-- update tableName tn set tn.property1 = "x1 ",tn.property2 = "x2 ",  where tn.property3 = "x3 "

-- 4: oracle 联合修改


-- <if test="roleName != null and roleName.trim() != ''">   -- 调用的是java的trim()方法；

