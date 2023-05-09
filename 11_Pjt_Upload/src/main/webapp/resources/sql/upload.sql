-- 1게시글 - 1첨부 - 1테이블
-- 1게시글 - 다중첨부 - 2테이블

-- 다중 첨부 게시판

-- 첨부 파일 정보 테이블
DROP TABLE ATTACH;
CREATE TABLE ATTACH (
    ATTACH_NO       NUMBER NOT NULL,              -- PK
    PATH            VARCHAR2(300 BYTE) NOT NULL,  -- 첨부 파일 경로
    ORIGIN_NAME     VARCHAR2(300 BYTE) NOT NULL,  -- 첨부 파일의 원래 이름
    FILESYSTEM_NAME VARCHAR2(50 BYTE) NOT NULL,   -- 첨부 파일의 저장 이름
    DOWNLOAD_COUNT  NUMBER,                       -- 다운로드 횟수
    HAS_THUMBNAIL   NUMBER,                       -- 썸네일이 있으면 1, 없으면 0
    UPLOAD_NO       NUMBER                        -- 게시글 FK
);

-- 게시글 정보 테이블
DROP TABLE UPLOAD;
CREATE TABLE UPLOAD (
    UPLOAD_NO      NUMBER NOT NULL,               -- PK
    UPLOAD_TITLE   VARCHAR2(1000 BYTE) NOT NULL,  -- 제목
    UPLOAD_CONTENT CLOB,                          -- 내용
    CREATED_AT     TIMESTAMP,                     -- 작성일
    MODIFIED_AT    TIMESTAMP                      -- 수정일
);

-- 기본키
ALTER TABLE ATTACH
    ADD CONSTRAINT PK_ATTACH
        PRIMARY KEY(ATTACH_NO);
ALTER TABLE UPLOAD
    ADD CONSTRAINT PK_UPLOAD
        PRIMARY KEY(UPLOAD_NO);

-- 외래키
ALTER TABLE ATTACH
    ADD CONSTRAINT FK_ATTACH_UPLOAD
        FOREIGN KEY(UPLOAD_NO) REFERENCES UPLOAD(UPLOAD_NO)
            ON DELETE CASCADE;

-- 시퀀스
DROP SEQUENCE ATTACH_SEQ;
CREATE SEQUENCE ATTACH_SEQ NOCACHE;
DROP SEQUENCE UPLOAD_SEQ;
CREATE SEQUENCE UPLOAD_SEQ NOCACHE;
