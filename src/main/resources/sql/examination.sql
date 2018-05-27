/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/5/27 11:06:31                           */
/*==============================================================*/


drop table if exists college;

drop table if exists course;

drop table if exists role;

drop table if exists selectcourse;

drop table if exists student;

drop table if exists teacher;

drop table if exists user;

/*==============================================================*/
/* Table: college                                               */
/*==============================================================*/
create table college
(
   collegeid            int(12) not null auto_increment,
   collegename          varchar(32) not null,
   primary key (collegeid)
)
charset = UTF8;

alter table college comment '系别表';

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table teacher
(
   teacherId            int(12) not null auto_increment,
   teacherName          varchar(32) not null,
   sex                  varchar(16) default NULL,
   birthday             date default NULL,
   degree               varchar(32) default NULL comment '学历',
   title                varchar(32) comment '职称',
   grade                date default NULL comment '入职时间',
   collegeId            int(12) not null comment '院系',
   primary key (teacherId),
   key AK_collegeId (collegeId),
   constraint FK_Reference_teacher_college foreign key (collegeId)
      references college (collegeid)
)
charset = UTF8;

alter table teacher comment '教师表';

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course
(
   courseId             int(12) not null auto_increment,
   courseName           varchar(32) not null comment '课程名',
   teacherId            int(12) not null,
   courseTime           varchar(64) default NULL comment '开课时间',
   classRoom            varchar(64) default NULL comment '上课教师',
   courseWeek           int default NULL comment '课程学时',
   courseType           varchar(32) default NULL comment '课程类型',
   collegeId            int(12) not null comment '所属院系',
   score                double not null comment '学分',
   primary key (courseId),
   key AK_collegeId (collegeId),
   key AK_teahcerId (teacherId),
   constraint FK_Reference_course_college foreign key (collegeId)
      references college (collegeid),
   constraint FK_Reference_course_teacher foreign key (teacherId)
      references teacher (teacherId)
)
charset = UTF8;

alter table course comment '课程表';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   roleId               int(12) not null auto_increment,
   roleName             varchar(32) not null,
   permissions          varchar(300) default NULL comment '权限',
   primary key (roleId)
)
charset = UTF8;

alter table role comment '角色表';

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   studentId            int(12) not null auto_increment,
   studentName          varchar(32) not null,
   sex                  varchar(12) default NULL,
   birthday             date default NULL,
   grade                date default NULL comment '入学时间',
   collegeId            int(12) not null,
   primary key (studentId),
   key AK_collegeId (collegeId),
   constraint FK_Reference_student_college foreign key (collegeId)
      references college (collegeid)
);

alter table student comment '学生表';

/*==============================================================*/
/* Table: selectcourse                                          */
/*==============================================================*/
create table selectcourse
(
   courseId             int(12) not null,
   studentId            int(12) not null,
   mark                 int not null comment '成绩',
   primary key (courseId, studentId),
   key AK_Key_courseId (courseId),
   key AK_Key_studentId (studentId),
   constraint FK_Reference_selectcource_student foreign key (studentId)
      references student (studentId),
   constraint FK_Reference_selectcourse_course foreign key (courseId)
      references course (courseId)
);

alter table selectcourse comment '学生与课程之间的关系表';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   userId               int(12) not null auto_increment,
   userName             varchar(32) not null,
   password             varchar(64) not null,
   role                 int(12) not null default 2 comment '角色权限',
   primary key (userId),
   key AK_role (role),
   constraint FK_Reference_user_role foreign key (role)
      references role (roleId)
)
charset = UTF8;

alter table user comment '用户表';

