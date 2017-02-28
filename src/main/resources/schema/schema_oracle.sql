DROP TABLE USER;

CREATE TABLE USER
( 
	USERNAME VARCHAR2(20)  NOT NULL , 
	PASSWORD VARCHAR2(500)  NOT NULL , 
	NAME VARCHAR2(20)  NOT NULL , 
	ACCOUNT_NON_EXPIRED NUMBER(1,0) NOT NULL, 
	ACCOUNT_NON_LOCKED NUMBER(1,0) NOT NULL, 
	CREDENTIALS_NON_EXPIRED NUMBER(1,0) NOT NULL, 
	ENABLED NUMBER(1,0) NOT NULL,
	REG_DATE DATE NOT NULL
);

CREATE UNIQUE INDEX USER_UIX ON USER ( USERNAME ASC ) ;
ALTER TABLE USER ADD CONSTRAINT USER_PK PRIMARY KEY ( USERNAME ) ;

COMMENT ON TABLE USER IS 'spring security 사용자' ;
COMMENT ON COLUMN USER.USERNAME IS '사용자명(ID or email)' ;
COMMENT ON COLUMN USER.PASSWORD IS '암호화된 비밀번호' ;
COMMENT ON COLUMN USER.NAME IS '암호화된 비밀번호' ;
COMMENT ON COLUMN USER.ACCOUNT_NON_EXPIRED IS 'IS_ACCOUNT_NON_EXPIRED' ;
COMMENT ON COLUMN USER.ACCOUNT_NON_LOCKED IS 'IS_ACCOUNT_NON_LOCKED' ;
COMMENT ON COLUMN USER.CREDENTIALS_NON_EXPIRED IS 'IS_CREDENTIALS_NON_EXPIRED' ;
COMMENT ON COLUMN USER.ENABLED IS '사용가능여부' ;
COMMENT ON COLUMN USER.REG_DATE IS '등록일' ;
 
 -- AUTHORITY (SPRING SECURITY 권한 )
 
 DROP TABLE AUTHORITY;
 
CREATE TABLE AUTHORITY
( 
	USERNAME VARCHAR2(20)  NOT NULL , 
	AUTHORITY_NAME VARCHAR2(20)  NOT NULL 
);

COMMENT ON TABLE AUTHORITY IS '사용자 권한' ;
COMMENT ON COLUMN AUTHORITY.USERNAME IS '사용자명(ID or email)' ;
COMMENT ON COLUMN AUTHORITY.AUTHORITY_NAME IS '사용자의 권한' ;

ALTER TABLE AUTHORITY 
    ADD CONSTRAINT AUTHORITY_FK FOREIGN KEY 
    ( 
     USERNAME
    ) 
    REFERENCES USER 
    ( 
     USERNAME
    ) 
    NOT DEFERRABLE;
 
 -- DOC_CATEGORY
 
 DROP TABLE DOC_CATEGORY;
 
CREATE TABLE DOC_CATEGORY
( 
	CODE CHAR (5 )  NOT NULL , 
	UPPER_CODE CHAR (5 ),
	NAME VARCHAR2 (256 ) NOT NULL
);
 
COMMENT ON TABLE DOC_CATEGORY IS '문서 분류(계층형)' ;
COMMENT ON COLUMN DOC_CATEGORY.CODE IS '분류코드' ;
COMMENT ON COLUMN DOC_CATEGORY.UPPER_CODE IS '상위 분류코드' ;
COMMENT ON COLUMN DOC_CATEGORY.NAME IS '분류명' ;
 
CREATE UNIQUE INDEX DOC_CATEGORY_UIX ON DOC_CATEGORY ( CODE ASC ) ;
ALTER TABLE DOC_CATEGORY ADD CONSTRAINT DOC_CATEGORY_PK PRIMARY KEY ( CODE ) ;

ALTER TABLE DOC_CATEGORY 
    ADD CONSTRAINT UPPER_CODE_FK FOREIGN KEY 
    ( 
		UPPER_CODE
    ) 
    REFERENCES DOC_CATEGORY 
    ( 
		CODE
    ) 
    NOT DEFERRABLE;

-- DOC_SUMMARY

DROP TABLE DOC_SUMMARY;

CREATE TABLE DOC_SUMMARY
( 
	DOC_IDX CHAR (10 )  NOT NULL , 
	TITLE VARCHAR2 (256 ) NOT NULL , 
	WRITER VARCHAR2(20)  NOT NULL , 
	WRITE_DATE DATE NOT NULL,
	REWRITER VARCHAR2(20) , 
	REWRITE_DATE DATE ,
	VIEW_COUNT NUMBER(9),
	CATEGORY CHAR (5 )
);

COMMENT ON TABLE DOC_SUMMARY IS '문서 요약' ;
COMMENT ON COLUMN DOC_SUMMARY.DOC_IDX IS '기본 키' ;
COMMENT ON COLUMN DOC_SUMMARY.TITLE IS '제목' ;
COMMENT ON COLUMN DOC_SUMMARY.WRITER IS '작성자' ;
COMMENT ON COLUMN DOC_SUMMARY.WRITE_DATE IS '작성일' ;
COMMENT ON COLUMN DOC_SUMMARY.REWRITER IS '수정자' ;
COMMENT ON COLUMN DOC_SUMMARY.REWRITE_DATE IS '수정일' ;
COMMENT ON COLUMN DOC_SUMMARY.VIEW_COUNT IS '조회수' ;
COMMENT ON COLUMN DOC_SUMMARY.CATEGORY IS '분류' ;

CREATE UNIQUE INDEX DOC_SUMMARY_UIX ON DOC_SUMMARY ( DOC_IDX ASC ) ;
ALTER TABLE DOC_SUMMARY ADD CONSTRAINT DOC_SUMMARY_PK PRIMARY KEY ( DOC_IDX ) ;

ALTER TABLE DOC_SUMMARY 
    ADD CONSTRAINT USER_WRITER_FK1 FOREIGN KEY 
    ( 
     WRITER
    ) 
    REFERENCES USER 
    ( 
     USERNAME
    ) 
    NOT DEFERRABLE;

ALTER TABLE DOC_SUMMARY 
    ADD CONSTRAINT USER_WRITER_FK2 FOREIGN KEY 
    ( 
     REWRITER
    ) 
    REFERENCES USER 
    ( 
     USERNAME
    ) 
    NOT DEFERRABLE;	
	
ALTER TABLE DOC_SUMMARY 
    ADD CONSTRAINT CATEGORY_FK FOREIGN KEY 
    ( 
     CATEGORY
    ) 
    REFERENCES DOC_CATEGORY 
    ( 
     CODE
    ) 
    NOT DEFERRABLE;

DROP SEQUENCE DOC_SUMMARY_SEQ;

CREATE SEQUENCE DOC_SUMMARY_SEQ 
    INCREMENT BY 1 
    MAXVALUE 9999999999
    MINVALUE 0
    NOCACHE 
;

-- DOC_DETAIL

DROP TABLE DOC_DETAIL;

CREATE TABLE DOC_DETAIL
( 
	DOC_IDX CHAR (10 )  NOT NULL , 
	DETAIL VARCHAR2 (2048 )
);

COMMENT ON TABLE DOC_DETAIL IS '문서 상세' ;
COMMENT ON COLUMN DOC_DETAIL.DOC_IDX IS '기본 키' ;
COMMENT ON COLUMN DOC_DETAIL.DETAIL IS '상세 내용' ;

ALTER TABLE DOC_DETAIL 
    ADD CONSTRAINT DOC_IDX_FK FOREIGN KEY 
    ( 
     DOC_IDX
    ) 
    REFERENCES DOC_SUMMARY 
    ( 
     DOC_IDX
    ) 
    NOT DEFERRABLE;
 

 
 