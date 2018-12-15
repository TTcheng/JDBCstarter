CREATE TABLE IF NOT EXISTS STUDENT
(
  id          VARCHAR(32) PRIMARY KEY                     COMMENT '学生ID 主键',
  name        VARCHAR(30)                       NOT NULL  COMMENT '学生姓名',
  gender      ENUM('F','M')                     NOT NULL  COMMENT '性别M-Male/F-Female',
  age         INT                                         COMMENT '年龄 范围1-200',
    CHECK (age BETWEEN 1 AND 200)
) COLLATE utf8_general_ci COMMENT '学生信息表';

INSERT INTO STUDENT VALUES (replace(uuid(), '-', ''),'Jesse','M',20);
