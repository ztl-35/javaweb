/*1.创建数据库*/
 drop database if exists Visitor;
 create database Visitor;
 use Visitor;
/*2.创建表*/
  create table users
  (
    ID int(4) not null primary key auto_increment,
    UserName varchar(100),
    Pwd varchar(50)  
  );
 

  create table visitors
  (
    ID int(4) not null primary key auto_increment,
    UserID int(4),
    VisitTime datetime,
    LeftTime datetime,
    ip varchar(50),
    comefrom varchar(100)
  );

 /* DATETIME 类型可用于需要同时包含日期和时间信息的值。DATE 类型可用于需要一个日期值而不需要时间部分时。  timestamp，系统自动的更新时间戳。*/
  create table history
  (
    ID int(4) not null primary key auto_increment,
    VisitID	int(4),
    VisitTime datetime,
    Url varchar(200)
  );
 
 /*为users表添加3个初始用户*/

 insert into users (username,pwd) values ("张三1","123");
 insert into users (username,pwd) values ("李四1","456");
 insert into users (username,pwd) values ("王五","789");