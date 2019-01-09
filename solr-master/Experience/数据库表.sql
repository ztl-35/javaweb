DROP TABLE course;

--课程表
CREATE TABLE course(
       course_id  int(10) PRIMARY KEY auto_increment,
       course_name varchar(65),
       course_credit  int(10),
       course_teacher VARCHAR(15),
       course_address VARCHAR(15),
       course_time VARCHAR(10),
       course_long VARCHAR(10),
       course_feature VARCHAR(40),
       course_type VARCHAR(8) 
);

--课程表的序列
CREATE SEQUENCE course_seq;
--插入数据
INSERT INTO course VALUES(course_seq.nextval,'高等数学','5','张老师','B101','周一','12周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'线性代数','2','周老师','B201','周二','9周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'概率论与数理统计','2','齐老师','B301','周一','10周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'英语视听说','2','张老师','A201','周五','12周','通识教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'英语读写','2','崔老师','A213','周四','12周','通识教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'中国传统文化','2','张老师','B101','周二','9周','通识教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'数字逻辑与数字系统','3','包老师','A223','周三','12周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'面向对象程序设计','4','李老师','A229','周五','16周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'Java语言','4','郑老师','A101','周四','16周','通识教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'大学体育(一)','1','董老师','体育馆四楼','周一','9周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'大学物理','3','曹老师','A401','周五','12周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'数据库概论','3','郑老师','F401','周二','14周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'程序设计基础','5','崔老师','A233','周一','15周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'离散数学','4','李老师','A301','周三','12周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'计算机组成原理','3','李老师','A201','周二','14周','专业类教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'思想道德修养与法律基础','3','张老师','B301','周四','12周','通识教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'马克思主义基本原理论','3','郝老师','B201','周五','12周','通识教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'毛泽东思想和中国特色社会主义理论体系概述','4','赵老师','B101','周三','12周','通识教育必修课','考试');
INSERT INTO course VALUES(course_seq.nextval,'建筑灾害与逃生','2','王老师','C201','周一','9周','校公选课','考查');
INSERT INTO course VALUES(course_seq.nextval,'逻辑思维训练与修养','2','边老师','C203','周二','9周','校公选课','考查');
INSERT INTO course VALUES(course_seq.nextval,'海洋生物与人类健康','2','孙老师','C301','周四','9周','校公选课','考查');

select * from course;



DROP SEQUENCE subject_seq;
DROP TABLE subject;

--参考书科目表
CREATE TABLE subject(
    subject_id  int(8) PRIMARY KEY,
    subject_name varchar(50),
    subject_desc varchar(200) 
);

--科目表的序列
CREATE SEQUENCE subject_seq;
--插入数据
INSERT INTO subject VALUES(subject_seq.nextval,'数学','大部分专业必考科目，分值150分');
INSERT INTO subject VALUES(subject_seq.nextval,'英语','考研必考科目，分值100分');
INSERT INTO subject VALUES(subject_seq.nextval,'政治','考研必考科目，分值100分');
INSERT INTO subject VALUES(subject_seq.nextval,'专业课','考研必考科目，分值视报考专业而定');

select * from subject;


DROP SEQUENCE book_seq;
DROP TABLE book;

--参考书表
CREATE TABLE book(
    book_id  int(8) PRIMARY KEY,
    book_name varchar(80),
    book_writer varchar(35),
    book_rank varchar(20),
    book_classification varchar(20),
    book_description varchar(100),
    subject_id  int(6) REFERENCES subject (subject_id)
);

--参考书表的序列
CREATE SEQUENCE book_seq;
--插入数据
INSERT INTO book VALUES(book_seq.nextval,'《复习全书》','李永乐 王式安','基础篇','数学一','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《复习全书》','李永乐 王式安','基础篇','数学二','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《复习全书》','李永乐 王式安','基础篇','数学三','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《张宇带你学高等数学上册》','张宇','基础篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《张宇带你学高等数学下册》','张宇','基础篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《张宇带你学线性代数》','张宇','基础篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《张宇带你学概率论与数理统计》','张宇','基础篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《数学基础过关660题》','李永乐','强化篇','数学一','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《数学基础过关660题》','李永乐','强化篇','数学二','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《数学基础过关660题》','李永乐','强化篇','数学三','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《线性代数讲义》','李永乐','强化篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《概率论与数理统计讲义》','王式安','强化篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《高等数学18讲》','张宇','强化篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《线性代数9讲》','张宇','强化篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《概率论与数理统计9讲》','张宇','强化篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《终极预测最后4套卷》','张宇','冲刺篇','数学一','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《终极预测最后4套卷》','张宇','冲刺篇','数学二','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《终极预测最后4套卷》','张宇','冲刺篇','数学三','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《接力题典1800题》','汤家凤','强化篇','数学一','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《接力题典1800题》','汤家凤','强化篇','数学二','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《接力题典1800题》','汤家凤','强化篇','数学三','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《考研数学复习大全》','汤家凤','基础篇','数学一','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《考研数学复习大全》','汤家凤','基础篇','数学二','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《考研数学复习大全》','汤家凤','基础篇','数学三','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《线性代数辅导讲义》','汤家凤','强化篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《高等数学辅导讲义》','汤家凤','强化篇','通用','广大考研学子的选择','1');
INSERT INTO book VALUES(book_seq.nextval,'《概率论与数理统计辅导讲义》','汤家凤','强化篇','通用','广大考研学子的选择','1');

INSERT INTO book VALUES(book_seq.nextval,'《必考单词突破全书》','何凯文','基础篇','通用','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《阅读同源外刊时文精析》','何凯文','强化篇','通用','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《长难句解密》','何凯文','基础篇','通用','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《写作高分攻略》','何凯文','基础篇','通用','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《阅读思路精析》','何凯文','基础篇','通用','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《历年真题全解析》','何凯文','强化篇','通用','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《恋恋有词》','朱伟','基础篇','通用','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《考研英语词汇》','俞敏洪','基础篇','通用','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《历年考研英语真题解析及复习思路》','张剑','强化篇','英语一','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《历年考研英语真题解析及复习思路》','张剑','强化篇','英语二','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《考研英语阅读150篇》','华研外语','基础篇','英语一','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《考研英语阅读150篇》','华研外语','基础篇','英语二','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《老蒋高分阅读80篇》','老蒋','基础篇','英语二','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《绝对考场最后六套题》','何凯文','冲刺篇','通用','广大考研学子的选择','2');
INSERT INTO book VALUES(book_seq.nextval,'《The Economist》','James Wilson','冲刺篇','通用','广大考研学子的选择','2');

INSERT INTO book VALUES(book_seq.nextval,'《命题人-知识点精讲精练》','肖秀荣','基础篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《命题人-讲真题》','肖秀荣','强化篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《命题人-1000题》','肖秀荣','基础篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《强化版核心考点》','肖秀荣','强化篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《复习全书》','蒋中挺','基础篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《历年真题详解》','蒋中挺','强化篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《高频考点与备考策略》','蒋中挺','强化篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《考研政治核心考案》','徐涛','基础篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《思想政治理论红宝书》','徐之明','基础篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《思想政治理论900题》','徐之明','基础篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《思想政治理论逻辑图解》','徐之明','强化篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《考研政治核心考点解密》','万磊','强化篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《命题人-终极预测4套卷》','肖秀荣','冲刺篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《风中劲草》','杨杰','冲刺篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《思想政治理论考试大纲解析》','教育部','基础篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《命题人-冲刺8套卷》','肖秀荣','冲刺篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《马原突破专项》','任燕翔','强化篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《考研政治考点精华》','万磊','基础篇','通用','广大考研学子的选择','3');
INSERT INTO book VALUES(book_seq.nextval,'《命题人-形势与政策》','肖秀荣','强化篇','通用','广大考研学子的选择','3');

select * from book;


DROP SEQUENCE notice_seq;
DROP TABLE notice;

--公告表
CREATE TABLE notice(
	notice_id  int(6) PRIMARY KEY,
	notice_header varchar(100),
	notice_content varchar(280),
	notice_date DATE
);

--公告表的序列
CREATE SEQUENCE notice_seq;
--插入数据
INSERT INTO notice VALUES (notice_seq.nextval, '北京航空航天大学首次招收人工智能方向研究生!', '据了解,北京航空航天大学首次招收人工智能研究方向研究生共计122人,学生毕业后可优先到百度、京东等合作企业就业.', to_date('2017-09-19','yyyy-mm-dd'));
INSERT INTO notice VALUES (notice_seq.nextval, '2018年全国硕士研究生招生考试考场规则发布!', '据中国研究生招生信息网消息,《2018年全国硕士研究生招生考试考场规则》已经发布。各位18考研er要注意查看,一定要遵守好考场规则，诚信考试.', to_date('2017-12-08','yyyy-mm-dd'));
INSERT INTO notice VALUES (notice_seq.nextval, '2018年考研初试成绩2月3日起公布', '根据部分省级教育招生考试机构发布的信息,考研成绩预计2月3日开始陆续公布。请考生们及时关注研招网及各招生单位公布的查分信息', to_date('2018-01-17','yyyy-mm-dd'));
INSERT INTO notice VALUES (notice_seq.nextval, '调剂系统已开放', '2018考研调剂工作陆续展开,对于初试中表现失利的考生可以通过调剂弥补遗憾,完成自己的考研梦.', to_date('2018-01-22','yyyy-mm-dd'));
INSERT INTO notice VALUES (notice_seq.nextval, '四六级开始报名啦!', '6月份英语四六级考试明天开始报名。请大家做好报名准备,四级报名名额满为止.', to_date('2018-03-06','yyyy-mm-dd'));
INSERT INTO notice VALUES (notice_seq.nextval, '2018年山西省公务员考试报名情况通告', '山西省2018年度考试录用公务员报名工作已于3月26日24时结束,全省共报名194027人,与去年基本持平.', to_date('2018-03-27','yyyy-mm-dd'));

select * from notice;


DROP SEQUENCE article_seq;
DROP TABLE article;

--经验帖表 
CREATE TABLE article(
     article_id  int(8) PRIMARY KEY,
     article_title VARCHAR(100),
     article_content VARCHAR(3000),
     article_date DATE
);

--经验帖表的序列
CREATE SEQUENCE article_seq;
--插入数据
INSERT INTO article VALUES (article_seq.nextval, '考研那些你必须坚持的小习惯', '严格的作息时间、按照自己的能力和时间指定月计划、周计划、日计划、学习远离手机，午睡一会，磨刀不误砍柴工看视频课千万不要带手机，日常休息时间表要严格执行；找到复习每门课程的技巧：数学方面，解决好基础定义，题量不在多，在于精，32年真题不要落下，认真琢磨；英语方面：培养语感，养成每天翻译的好习惯；找一个自制力比你更好的研友陪着你：一定要有计划，不要轻易放弃，找一个自制力比你更好的有研友陪着你。其实研友很重要，一个人很容易懈怠和产生焦虑，找一个作息比较规律的研友事半功倍。备考期间可以和一起复习的同学交流经验心得，互相打气。', to_date('2018-02-15','yyyy-mm-dd'));
INSERT INTO article VALUES (article_seq.nextval, '失败未必是坏事，重新起航吧！', '当看见分数那一刻我认为是老天跟我开了个玩笑，之前一年的努力都白费了，最大的感觉就是拼命的怀疑自己，我能力不行，我不适合学习，我为什么这么弱，为什么身边的人都能考上，为什么同样一起考一个学校的人人家能考上我却考不上。各种想法充斥在内心里，但我庆幸的是，我明显能感觉到自己是不服气的。考研像是一个巨大的坑，一旦小陷进去，就出不来了。我觉得我需要勇敢一些，我承认自己实力不够，但我不能丧失信心。失败是好事，它给我更多的磨练，更多的经历，更多的信心。所以，首战失利的研友，如果你打算二战，那就请你坚持下去，前方有更美的风景等着你~', to_date('2018-02-26','yyyy-mm-dd'));
INSERT INTO article VALUES (article_seq.nextval, '我的公务员考试心得体会--与大家共勉', '去年考试我幸运的成功了，我加入论坛时间不长，但从中受益不浅，所以我非常乐意把自己的经验教训写出来，与那些想考公务员的朋友分享，也希望更多有经验的朋友参与进来，只有增进交流，互相学习，自己也才有可能获得更大的进步！ 希望我的经历对你们有帮助。察内容比较宽泛，各方面内容均有，数量关系的、常识性的、文学方面的、推理方面的等，我们每个人涉猎的范围毕竟有限，所以这科的复习效果相比申论是比较显著的.但我说的复习，绝对不是死记硬背，你能背下几百道题，背不完所有的题。所以复习应重点放在对题型的了解，对解题方法的学习上。我建议那些对行政科目不了解的朋友买到书后，别着急先看题，先做题，那样只会导致事倍功半。最好先看那5，6种题型考察你哪方面及前面的例题。有了一个宏观的认识了以后再进入训练。再有,行政对解题速度要求非常高，所以平时在训练一定要给自己定时间范围。该说说申论了，许多人说这门很难，复习也没用，我不是很赞同。首先要消除押宝的思想，这是非常错误和危险的。申论考察的是你阅读材料能力、分析概括能力、逻辑思维能力、文字表达能力。重点应放在对自己能力的训练培养上，做过的材料不在多，而在于精。', to_date('2018-03-10','yyyy-mm-dd'));

select * from article;


DROP SEQUENCE student_seq;
DROP TABLE student;

--用户表 
CREATE TABLE student(
     user_id  int(8) PRIMARY KEY,
     user_name VARCHAR(15),
     user_password VARCHAR(20),
     user_email VARCHAR(50)
);

--用户表的序列
CREATE SEQUENCE student_seq;
--插入数据
INSERT INTO student VALUES (student_seq.nextval, 'tara', '111111', '111111@qq.com');
INSERT INTO student VALUES (student_seq.nextval, 'kuzma', '222222', '222222@qq.com');
INSERT INTO student VALUES (student_seq.nextval, 'ingram', '333333', '333333@qq.com');
INSERT INTO student VALUES (student_seq.nextval, 'hart', '444444', '444444@qq.com');
INSERT INTO student VALUES (student_seq.nextval, 'kobe', '555555', '555555@qq.com');
INSERT INTO student VALUES (student_seq.nextval, 'ball', '666666', '666666@qq.com');
INSERT INTO student VALUES (student_seq.nextval, 'lopez', '777777', '777777@qq.com');
INSERT INTO student VALUES (student_seq.nextval, 'randle', '888888', '888888@qq.com');
INSERT INTO student VALUES (student_seq.nextval, 'pope', '999999', '999999@qq.com');
INSERT INTO student VALUES (student_seq.nextval, 'thomas', '000000', '000000@qq.com');

select * from student;


commit;
