INSERT INTO DOC_CATEGORY (CODE, UPPER_CODE, NAME) VALUES('00000', null, '전체');
INSERT INTO DOC_CATEGORY (CODE, UPPER_CODE, NAME) VALUES('00001', '00000', '분류1');
INSERT INTO DOC_CATEGORY (CODE, UPPER_CODE, NAME) VALUES('00002', '00000', '분류2');


-- user data는 password 암호화때문에 dml 만들지 못함.
-- doc data는 seq 값 return 및, username 추가하지 못해서 만들지못함.  

--INSERT INTO DOC_SUMMARY (DOC_IDX, TITLE, WRITER, WRITE_DATE, REWRITER, REWRITE_DATE, VIEW_COUNT, CATEGORY ) VALUES(LPAD(DOC_SUMMARY_SEQ.nextval, 10, '0'), '최초 문서 등록 테스트 제목', '0000000', sysdate, null, null, 0, '00000');

--INSERT INTO DOC_DETAIL (DOC_IDX, DETAIL) VALUES('0000000000', '상세 등록 테스트 상세 등록 테스트 상세 등록 테스트 상세 등록 테스트 상세 등록 테스트 상세 등록 테스트 상세 등록 테스트 상세 등록 테스트 ');