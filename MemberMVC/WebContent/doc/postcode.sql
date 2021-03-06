create table POSTCODE
(
	NEW_POST_CODE   VARCHAR2(5);
	SIDO_KOR        VARCHAR2(200),
 	SIDO_ENG        VARCHAR2(30),
 	GUGUN_KOR     VARCHAR2(30),
 	GUGUN_ENG     VARCHAR2(30),
 	UPMYON_KOR      VARCHAR2(30),
 	UPMYON_ENG      VARCHAR2(200),
	DORO_NUMBER     NUMBER(30),
	DORO_KOR        VARCHAR2(100),
	DORO_ENG        VARCHAR2(200),
	UNDOR_FLAG      NUMBER(30),
	BUILDING_ORIGIN_NUMBER  NUMBER(30),
	BUILDING_REFER_NUMBER   NUMBER(30),
	BUILDING_MANAGE_NUMBER  NUMBER(30),
	MULTI_DELIVER_NAME      VARCHAR2(30),
	SIGUGUN_BUILDING_NAME   VARCHAR2(200),
	LAW_DONG_NUMBER NUMBER(30),
	LAW_DONG_NAME   VARCHAR2(100),
	RI_NAME         VARCHAR2(30),
	ADMIN_DONG_NAME VARCHAR2(100),
	SAN_FLAG        NUMBER(30),
	JIBEON_BONBEON  NUMBER(30),
	UPMYONDONG_SEQ  NUMBER(30),
	JIBEON_BUBEON   NUMBER(30),
	OLD_POST_CODE   VARCHAR2(30),
	POST_CODE_SEQ   VARCHAR2(30)
);