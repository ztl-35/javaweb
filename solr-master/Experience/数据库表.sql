DROP TABLE course;

--�γ̱�
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

--�γ̱������
CREATE SEQUENCE course_seq;
--��������
INSERT INTO course VALUES(course_seq.nextval,'�ߵ���ѧ','5','����ʦ','B101','��һ','12��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'���Դ���','2','����ʦ','B201','�ܶ�','9��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'������������ͳ��','2','����ʦ','B301','��һ','10��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'Ӣ������˵','2','����ʦ','A201','����','12��','ͨʶ�������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'Ӣ���д','2','����ʦ','A213','����','12��','ͨʶ�������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'�й���ͳ�Ļ�','2','����ʦ','B101','�ܶ�','9��','ͨʶ�������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'�����߼�������ϵͳ','3','����ʦ','A223','����','12��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'�������������','4','����ʦ','A229','����','16��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'Java����','4','֣��ʦ','A101','����','16��','ͨʶ�������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'��ѧ����(һ)','1','����ʦ','��������¥','��һ','9��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'��ѧ����','3','����ʦ','A401','����','12��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'���ݿ����','3','֣��ʦ','F401','�ܶ�','14��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'������ƻ���','5','����ʦ','A233','��һ','15��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'��ɢ��ѧ','4','����ʦ','A301','����','12��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'��������ԭ��','3','����ʦ','A201','�ܶ�','14��','רҵ��������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'˼����������뷨�ɻ���','3','����ʦ','B301','����','12��','ͨʶ�������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'���˼�������ԭ����','3','����ʦ','B201','����','12��','ͨʶ�������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'ë��˼����й���ɫ�������������ϵ����','4','����ʦ','B101','����','12��','ͨʶ�������޿�','����');
INSERT INTO course VALUES(course_seq.nextval,'�����ֺ�������','2','����ʦ','C201','��һ','9��','У��ѡ��','����');
INSERT INTO course VALUES(course_seq.nextval,'�߼�˼άѵ��������','2','����ʦ','C203','�ܶ�','9��','У��ѡ��','����');
INSERT INTO course VALUES(course_seq.nextval,'�������������ཡ��','2','����ʦ','C301','����','9��','У��ѡ��','����');

select * from course;



DROP SEQUENCE subject_seq;
DROP TABLE subject;

--�ο����Ŀ��
CREATE TABLE subject(
    subject_id  int(8) PRIMARY KEY,
    subject_name varchar(50),
    subject_desc varchar(200) 
);

--��Ŀ�������
CREATE SEQUENCE subject_seq;
--��������
INSERT INTO subject VALUES(subject_seq.nextval,'��ѧ','�󲿷�רҵ�ؿ���Ŀ����ֵ150��');
INSERT INTO subject VALUES(subject_seq.nextval,'Ӣ��','���бؿ���Ŀ����ֵ100��');
INSERT INTO subject VALUES(subject_seq.nextval,'����','���бؿ���Ŀ����ֵ100��');
INSERT INTO subject VALUES(subject_seq.nextval,'רҵ��','���бؿ���Ŀ����ֵ�ӱ���רҵ����');

select * from subject;


DROP SEQUENCE book_seq;
DROP TABLE book;

--�ο����
CREATE TABLE book(
    book_id  int(8) PRIMARY KEY,
    book_name varchar(80),
    book_writer varchar(35),
    book_rank varchar(20),
    book_classification varchar(20),
    book_description varchar(100),
    subject_id  int(6) REFERENCES subject (subject_id)
);

--�ο���������
CREATE SEQUENCE book_seq;
--��������
INSERT INTO book VALUES(book_seq.nextval,'����ϰȫ�顷','������ ��ʽ��','����ƪ','��ѧһ','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'����ϰȫ�顷','������ ��ʽ��','����ƪ','��ѧ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'����ϰȫ�顷','������ ��ʽ��','����ƪ','��ѧ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���������ѧ�ߵ���ѧ�ϲᡷ','����','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���������ѧ�ߵ���ѧ�²ᡷ','����','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���������ѧ���Դ�����','����','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���������ѧ������������ͳ�ơ�','����','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'����ѧ��������660�⡷','������','ǿ��ƪ','��ѧһ','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'����ѧ��������660�⡷','������','ǿ��ƪ','��ѧ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'����ѧ��������660�⡷','������','ǿ��ƪ','��ѧ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'�����Դ������塷','������','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'��������������ͳ�ƽ��塷','��ʽ��','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���ߵ���ѧ18����','����','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'�����Դ���9����','����','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'��������������ͳ��9����','����','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���ռ�Ԥ�����4�׾�','����','���ƪ','��ѧһ','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���ռ�Ԥ�����4�׾�','����','���ƪ','��ѧ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���ռ�Ԥ�����4�׾�','����','���ƪ','��ѧ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���������1800�⡷','���ҷ�','ǿ��ƪ','��ѧһ','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���������1800�⡷','���ҷ�','ǿ��ƪ','��ѧ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���������1800�⡷','���ҷ�','ǿ��ƪ','��ѧ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'��������ѧ��ϰ��ȫ��','���ҷ�','����ƪ','��ѧһ','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'��������ѧ��ϰ��ȫ��','���ҷ�','����ƪ','��ѧ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'��������ѧ��ϰ��ȫ��','���ҷ�','����ƪ','��ѧ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'�����Դ����������塷','���ҷ�','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'���ߵ���ѧ�������塷','���ҷ�','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');
INSERT INTO book VALUES(book_seq.nextval,'��������������ͳ�Ƹ������塷','���ҷ�','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','1');

INSERT INTO book VALUES(book_seq.nextval,'���ؿ�����ͻ��ȫ�顷','�ο���','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'���Ķ�ͬԴ�⿯ʱ�ľ�����','�ο���','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'�����Ѿ���ܡ�','�ο���','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'��д���߷ֹ��ԡ�','�ο���','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'���Ķ�˼·������','�ο���','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'����������ȫ������','�ο���','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'�������дʡ�','��ΰ','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'������Ӣ��ʻ㡷','������','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'�����꿼��Ӣ�������������ϰ˼·��','�Ž�','ǿ��ƪ','Ӣ��һ','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'�����꿼��Ӣ�������������ϰ˼·��','�Ž�','ǿ��ƪ','Ӣ���','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'������Ӣ���Ķ�150ƪ��','��������','����ƪ','Ӣ��һ','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'������Ӣ���Ķ�150ƪ��','��������','����ƪ','Ӣ���','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'���Ͻ��߷��Ķ�80ƪ��','�Ͻ�','����ƪ','Ӣ���','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'�����Կ�����������⡷','�ο���','���ƪ','ͨ��','�����ѧ�ӵ�ѡ��','2');
INSERT INTO book VALUES(book_seq.nextval,'��The Economist��','James Wilson','���ƪ','ͨ��','�����ѧ�ӵ�ѡ��','2');

INSERT INTO book VALUES(book_seq.nextval,'��������-֪ʶ�㾫��������','Ф����','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'��������-�����⡷','Ф����','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'��������-1000�⡷','Ф����','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'��ǿ������Ŀ��㡷','Ф����','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'����ϰȫ�顷','����ͦ','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'������������⡷','����ͦ','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'����Ƶ�����뱸�����ԡ�','����ͦ','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'���������κ��Ŀ�����','����','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'��˼���������ۺ챦�顷','��֮��','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'��˼����������900�⡷','��֮��','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'��˼�����������߼�ͼ�⡷','��֮��','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'���������κ��Ŀ�����ܡ�','����','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'��������-�ռ�Ԥ��4�׾�','Ф����','���ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'�����о��ݡ�','���','���ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'��˼���������ۿ��Դ�ٽ�����','������','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'��������-���8�׾�','Ф����','���ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'����ԭͻ��ר�','������','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'���������ο��㾫����','����','����ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');
INSERT INTO book VALUES(book_seq.nextval,'��������-���������ߡ�','Ф����','ǿ��ƪ','ͨ��','�����ѧ�ӵ�ѡ��','3');

select * from book;


DROP SEQUENCE notice_seq;
DROP TABLE notice;

--�����
CREATE TABLE notice(
	notice_id  int(6) PRIMARY KEY,
	notice_header varchar(100),
	notice_content varchar(280),
	notice_date DATE
);

--����������
CREATE SEQUENCE notice_seq;
--��������
INSERT INTO notice VALUES (notice_seq.nextval, '�������պ����ѧ�״������˹����ܷ����о���!', '���˽�,�������պ����ѧ�״������˹������о������о�������122��,ѧ����ҵ������ȵ��ٶȡ������Ⱥ�����ҵ��ҵ.', to_date('2017-09-19','yyyy-mm-dd'));
INSERT INTO notice VALUES (notice_seq.nextval, '2018��ȫ��˶ʿ�о����������Կ������򷢲�!', '���й��о���������Ϣ����Ϣ,��2018��ȫ��˶ʿ�о����������Կ��������Ѿ���������λ18����erҪע��鿴,һ��Ҫ���غÿ������򣬳��ſ���.', to_date('2017-12-08','yyyy-mm-dd'));
INSERT INTO notice VALUES (notice_seq.nextval, '2018�꿼�г��Գɼ�2��3���𹫲�', '���ݲ���ʡ�������������Ի�����������Ϣ,���гɼ�Ԥ��2��3�տ�ʼ½���������뿼���Ǽ�ʱ��ע����������������λ�����Ĳ����Ϣ', to_date('2018-01-17','yyyy-mm-dd'));
INSERT INTO notice VALUES (notice_seq.nextval, '����ϵͳ�ѿ���', '2018���е�������½��չ��,���ڳ����б���ʧ���Ŀ�������ͨ�������ֲ��ź�,����Լ��Ŀ�����.', to_date('2018-01-22','yyyy-mm-dd'));
INSERT INTO notice VALUES (notice_seq.nextval, '��������ʼ������!', '6�·�Ӣ���������������쿪ʼ�������������ñ���׼��,�ļ�����������Ϊֹ.', to_date('2018-03-06','yyyy-mm-dd'));
INSERT INTO notice VALUES (notice_seq.nextval, '2018��ɽ��ʡ����Ա���Ա������ͨ��', 'ɽ��ʡ2018��ȿ���¼�ù���Ա������������3��26��24ʱ����,ȫʡ������194027��,��ȥ�������ƽ.', to_date('2018-03-27','yyyy-mm-dd'));

select * from notice;


DROP SEQUENCE article_seq;
DROP TABLE article;

--�������� 
CREATE TABLE article(
     article_id  int(8) PRIMARY KEY,
     article_title VARCHAR(100),
     article_content VARCHAR(3000),
     article_date DATE
);

--�������������
CREATE SEQUENCE article_seq;
--��������
INSERT INTO article VALUES (article_seq.nextval, '������Щ������ֵ�Сϰ��', '�ϸ����Ϣʱ�䡢�����Լ���������ʱ��ָ���¼ƻ����ܼƻ����ռƻ���ѧϰԶ���ֻ�����˯һ�ᣬĥ�����󿳲񹤿���Ƶ��ǧ��Ҫ���ֻ����ճ���Ϣʱ���Ҫ�ϸ�ִ�У��ҵ���ϰÿ�ſγ̵ļ��ɣ���ѧ���棬����û������壬�������ڶ࣬���ھ���32�����ⲻҪ���£�������ĥ��Ӣ�﷽�棺������У�����ÿ�췭��ĺ�ϰ�ߣ���һ��������������õ����������㣺һ��Ҫ�мƻ�����Ҫ���׷�������һ��������������õ������������㡣��ʵ���Ѻ���Ҫ��һ���˺�����и���Ͳ������ǣ���һ����Ϣ�ȽϹ��ɵ������°빦���������ڼ���Ժ�һ��ϰ��ͬѧ���������ĵã����������', to_date('2018-02-15','yyyy-mm-dd'));
INSERT INTO article VALUES (article_seq.nextval, 'ʧ��δ���ǻ��£������𺽰ɣ�', '������������һ������Ϊ��������ҿ��˸���Ц��֮ǰһ���Ŭ�����׷��ˣ����ĸо�����ƴ���Ļ����Լ������������У��Ҳ��ʺ�ѧϰ����Ϊʲô��ô����Ϊʲô��ߵ��˶��ܿ��ϣ�Ϊʲôͬ��һ��һ��ѧУ�����˼��ܿ�����ȴ�����ϡ������뷨�����������������ҵ��ǣ��������ܸо����Լ��ǲ������ġ���������һ���޴�Ŀӣ�һ��С�ݽ�ȥ���ͳ������ˡ��Ҿ�������Ҫ�¸�һЩ���ҳ����Լ�ʵ�����������Ҳ���ɥʧ���ġ�ʧ���Ǻ��£������Ҹ����ĥ��������ľ�������������ġ����ԣ���սʧ�������ѣ����������ս���Ǿ���������ȥ��ǰ���и����ķ羰������~', to_date('2018-02-26','yyyy-mm-dd'));
INSERT INTO article VALUES (article_seq.nextval, '�ҵĹ���Ա�����ĵ����--���ҹ���', 'ȥ�꿼�������˵ĳɹ��ˣ��Ҽ�����̳ʱ�䲻�������������治ǳ�������ҷǳ�������Լ��ľ����ѵд����������Щ�뿼����Ա�����ѷ���Ҳϣ�������о�������Ѳ��������ֻ����������������ѧϰ���Լ�Ҳ���п��ܻ�ø���Ľ����� ϣ���ҵľ����������а����������ݱȽϿ������������ݾ��У�������ϵ�ġ���ʶ�Եġ���ѧ����ġ�������ĵȣ�����ÿ�������Եķ�Χ�Ͼ����ޣ�������Ƶĸ�ϰЧ����������ǱȽ�������.����˵�ĸ�ϰ�����Բ�������Ӳ�������ܱ��¼��ٵ��⣬���������е��⡣���Ը�ϰӦ�ص���ڶ����͵��˽⣬�Խ��ⷽ����ѧϰ�ϡ��ҽ�����Щ��������Ŀ���˽����������󣬱��ż��ȿ��⣬�����⣬����ֻ�ᵼ���±����롣����ȿ���5��6�����Ϳ������ķ��漰ǰ������⡣����һ����۵���ʶ���Ժ��ٽ���ѵ��������,�����Խ����ٶ�Ҫ��ǳ��ߣ�����ƽʱ��ѵ��һ��Ҫ���Լ���ʱ�䷶Χ����˵˵�����ˣ������˵���ź��ѣ���ϰҲû�ã��Ҳ��Ǻ���ͬ������Ҫ����Ѻ����˼�룬���Ƿǳ������Σ�յġ����ۿ���������Ķ��������������������������߼�˼ά���������ֱ���������ص�Ӧ���ڶ��Լ�������ѵ�������ϣ������Ĳ��ϲ��ڶ࣬�����ھ���', to_date('2018-03-10','yyyy-mm-dd'));

select * from article;


DROP SEQUENCE student_seq;
DROP TABLE student;

--�û��� 
CREATE TABLE student(
     user_id  int(8) PRIMARY KEY,
     user_name VARCHAR(15),
     user_password VARCHAR(20),
     user_email VARCHAR(50)
);

--�û��������
CREATE SEQUENCE student_seq;
--��������
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
