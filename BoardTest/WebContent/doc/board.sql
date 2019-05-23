DROP TABLE repboard;
DELETE FROM repboard;

CREATE TABLE repboard (
	BOARD_SEQ		NUMBER(10)  primary key,
	PARENT_SEQ		NUMBER(10),  /** 원글인 경우:0, 답글인 경우:원글번호 **/
	BOARD_SUBJECT	VARCHAR2(255),
	BOARD_WRITER	VARCHAR2(100),
	BOARD_CONTENTS	VARCHAR2(4000),
	BOARD_DATE		TIMESTAMP(6),
	BOARD_PASSWORD	VARCHAR2(20),
	BOARD_VIEWCOUNT	NUMBER(6)
);
COMMIT;


insert into repboard values(1, 0, '1', '1', '1', systimestamp, '1', 0); 
insert into repboard values(2, 0, '2', '2', '2', systimestamp, '2', 0);
insert into repboard values(3, 1, '1-r1', '1-r1', '1-r1', systimestamp, '1-r1', 0);
insert into repboard values(4, 2, '2-r1', '2-r1', '2-r1', systimestamp, '2-r1', 0);
insert into repboard values(5, 0, '3', '3', '3', systimestamp, '3', 0);
insert into repboard values(6, 4, '2-r1-r1', '2-r1-r1', '2-r1-r1', systimestamp, '2-r1-r1', 0);
insert into repboard values(7, 2, '2-r2', '2-r2', '2-r2', systimestamp, '2-r2', 0);
insert into repboard values(8, 1, '1-r2', '1-r2', '1-r2', systimestamp, '1-r2', 0);
COMMIT;


drop sequence board_seq;

create sequence board_seq start with 9;


-- 원글 찾기 (parent_seq = 0 -> parent_seq를 parent로 갖는 값을 검색, 가장 나중에 쓴 글이 먼저 보이게, 깊이 우선 방식)
-- START WITH ~ CONNECT BY
-- SIBLINGS : 같은 level에서의 정렬
SELECT board_seq, parent_seq, to_char(board_date, 'hh:mm:ss')
FROM repboard
START WITH parent_seq = 0
CONNECT BY PRIOR board_seq = parent_seq
ORDER SIBLINGS BY board_seq desc;