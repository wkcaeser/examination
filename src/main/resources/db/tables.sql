CREATE TABLE department(
  id INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  name VARCHAR(32) NOT NULL COMMENT '学院名称',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态, 0:有效, -1：无效',
  created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '院系表';

CREATE TABLE major(
  id INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  name VARCHAR(32) NOT NULL COMMENT '专业名称',
  department_id INT NOT NULL COMMENT '所属学院',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态, 0:有效, -1：无效',
  created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  INDEX (department_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '专业表';

CREATE TABLE user(
  id INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  username VARCHAR(32) NOT NULL COMMENT '用户名',
  password VARCHAR(64) NOT NULL COMMENT '密码',
  email VARCHAR(64) NOT NULL COMMENT '邮箱',
  name VARCHAR(32) NOT NULL COMMENT '姓名',
  department_id INT NOT NULL DEFAULT 0 COMMENT '学院id',
  major_id INT NOT NULL DEFAULT 0 COMMENT '专业id',
  level TINYINT NOT NULL DEFAULT 0 COMMENT '用户类别, 0:无效, 1：学生, 2：教师, 5:管理员',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态, 0：正常, -1 : 无效',
  created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE (username),
  INDEX (department_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '用户表';

CREATE TABLE lesson(
  id INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  name VARCHAR(32) NOT NULL COMMENT '课程名称',
  teacher_id INT NOT NULL COMMENT '任课教师id',
  departemnt_id INT NOT NULL COMMENT '所属学院id',
  major_id INT NOT NULL COMMENT '所属专业',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态-》0：正常, -1：无效',
  created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id),
  INDEX (teacher_id),
  INDEX (departemnt_id),
  INDEX (major_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '课程表';

CREATE TABLE exam(
  id INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  name VARCHAR(32) NOT NULL COMMENT '试卷名称',
  lesson_id INT NOT NULL COMMENT '对应课程id',
  releaseTime TIMESTAMP NOT NULL COMMENT '开考时间',
  duration INT NOT NULL COMMENT '考试时长 分钟',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态 -1：作废 0：新建  1：开考 2：结束',
  created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  INDEX (lesson_id)
)ENGINE = InnoDB DEFAULT  CHARSET = utf8 COMMENT = '试卷';

CREATE TABLE choiceQuestion(
  id INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  description VARCHAR(256) NOT NULL COMMENT '题目描述',
  optionA VARCHAR(64) NOT NULL COMMENT '选项A',
  optionB VARCHAR(64) NOT NULL COMMENT '选项B',
  optionC VARCHAR(64) NOT NULL COMMENT '选项C',
  optionD VARCHAR(64) NOT NULL COMMENT '选项D',
  answer TINYINT NOT NULL COMMENT '答案 1-A 2-B ....',
  score TINYINT NOT NULL COMMENT '分值',
  department_id INT NOT NULL COMMENT '学院id',
  major_id INT NOT NULL COMMENT '专业id',
  teacher_id INT NOT NULL COMMENT '创建者老师id',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态 -1：作废 0：正常',
  created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  INDEX (department_id),
  INDEX (major_id),
  INDEX (teacher_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '选择题库';

CREATE TABLE objectiveQuestion(
  id INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  description VARCHAR(256) NOT NULL COMMENT '题目描述',
  score TINYINT NOT NULL COMMENT '分值',
  department_id INT NOT NULL COMMENT '学院id',
  major_id INT NOT NULL COMMENT '专业id',
  teacher_id INT NOT NULL COMMENT '创建者老师id',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态 -1：作废 0：正常',
  created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  INDEX (department_id),
  INDEX (major_id),
  INDEX (teacher_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '客观题库';


CREATE TABLE examInfo(
  id INT NOT NULL AUTO_INCREMENT COMMENT '自增id',
  exam_id INT NOT NULL COMMENT '试卷id',
  question_id INT NOT NULL COMMENT '题目id',
  type INT NOT NULL COMMENT '题目类型   1:选择题  2：客观题',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态 0：正常   -1：删除',
  created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id),
  INDEX (exam_id),
  INDEX (question_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '试卷试题';