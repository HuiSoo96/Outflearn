------------------------------------------ 강좌 데이터
DROP TABLE CLASS_DATA;

DROP SEQUENCE DATA_CHAPTER_SQ;

CREATE SEQUENCE DATA_CHAPTER_SQ;

CREATE TABLE CLASS_DATA(
CLASS_NUM NUMBER REFERENCES CLASS_INFO(CLASS_NUM) ON DELETE CASCADE NOT NULL,
DATA_CHAPTER NUMBER NOT NULL,
DATA_SUBHEAD VARCHAR2(250) DEFAULT '소제목',
DATA_SQ NUMBER NOT NULL,
DATA_TITLE VARCHAR2(250) DEFAULT '타이틀',
DATA_DATA CLOB DEFAULT '영상' 
);

SELECT * FROM CLASS_DATA;

INSERT INTO CLASS_DATA VALUES(2,DATA_CHAPTER_SQ.NEXTVAL,'소제목', 1, '과정제목', '과정영상')

INSERT INTO CLASS_DATA VALUES(1,DATA_CHAPTER_SQ.NEXTVAL,'lover', 2, 'lover', 'v=iVrI4SOOsyI&')


SELECT DATA_DATA FROM CLASS_DATA WHERE CLASS_NUM IN (SELECT CLASS_NUM FROM CLASS_DATA WHERE DATA_CHAPTER = 18) 

SELECT * FROM CLASS_DATA WHERE CLASS_NUM = ;
SELECT * FROM SUB_STREAM
SELECT * FROM MAIN_STREAM

SELECT * FROM CLASS_DATA WHERE CLASS_NUM = 111 AND DATA_CHAPTER = 133 AND DATA_SQ = 2