/*1.�������ݿ�*/
 drop database if exists Visitor;
 create database Visitor;
 use Visitor;
/*2.������*/
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

 /* DATETIME ���Ϳ�������Ҫͬʱ�������ں�ʱ����Ϣ��ֵ��DATE ���Ϳ�������Ҫһ������ֵ������Ҫʱ�䲿��ʱ��  timestamp��ϵͳ�Զ��ĸ���ʱ�����*/
  create table history
  (
    ID int(4) not null primary key auto_increment,
    VisitID	int(4),
    VisitTime datetime,
    Url varchar(200)
  );
 
 /*Ϊusers�����3����ʼ�û�*/

 insert into users (username,pwd) values ("����1","123");
 insert into users (username,pwd) values ("����1","456");
 insert into users (username,pwd) values ("����","789");