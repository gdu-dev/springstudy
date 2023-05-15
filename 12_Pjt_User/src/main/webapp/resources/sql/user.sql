-- 시퀀스
DROP SEQUENCE USER_SEQ;
CREATE SEQUENCE USER_SEQ NOCACHE;

-- 테이블 삭제 순서는 테이블 생성 순서의 역순이다.
DROP TABLE USER_ACCESS;
DROP TABLE USER;

-- 회원
CREATE TABLE USER (
    USER_NO        NUMBER             NOT NULL,
    ID             VARCHAR2(40 BYTE)  NOT NULL UNIQUE,  -- ID 정규식에 반영
    PW             VARCHAR2(64 BYTE)  NOT NULL,         -- SHA-256 암호화 방식 사용
    NAME           VARCHAR2(40 BYTE),                   -- 이름
    GENDER         VARCHAR2(2 BYTE),                    -- M, F, NO
    EMAIL          VARCHAR2(100 BYTE) NOT NULL,         -- 이메일
    MOBILE         VARCHAR2(15 BYTE),                   -- 하이픈 제외(-) 후 저장
    BIRTHYEAR      VARCHAR2(4 BYTE),                    -- 출생년도(YYYY)
    BIRTHDATE      VARCHAR2(4 BYTE),                    -- 출생월일(MMDD)
    POSTCODE       VARCHAR2(5 BYTE),                    -- 우편번호
    ROAD_ADDRESS   VARCHAR2(100 BYTE),                  -- 도로명주소
    JIBUN_ADDRESS  VARCHAR2(100 BYTE),                  -- 지번주소
    DETAIL_ADDRESS VARCHAR2(100 BYTE),                  -- 상세주소
    EXTRA_ADDRESS  VARCHAR2(100 BYTE),                  -- 참고항목
    AGREECODE      NUMBER             NOT NULL,         -- 동의여부(0:필수, 1:위치, 2:이벤트, 3:위치+이벤트)
    JOINED_AT      DATE,                                -- 가입일
    PW_MODIFIED_AT DATE,                                -- 비밀번호변경일
    AUTOLOGIN_ID   VARCHAR2(32 BYTE),                   -- 자동로그인할 때 사용하는 ID(SESSION_ID를 사용함)
    AUTOLOGIN_EXPIRED_AT DATE                           -- 자동로그인 만료일
);

ALTER TABLE USER
    ADD CONSTRAINT PK_USER
        PRIMARY KEY(USER_NO);


-- 회원 접속 기록(회원마다 마지막 로그인 날짜 1개만 기록)
CREATE TABLE USER_ACCESS (
    ID            VARCHAR2(40 BYTE) NOT NULL UNIQUE,    -- 로그인한 사용자 ID
    LAST_LOGIN_AT DATE                                  -- 마지막 로그인 날짜
);

ALTER TABLE USER_ACCESS
    ADD CONSTRAINT FK_USER_ACCESS
        FOREIGN KEY(ID) REFERENCES USER(ID)
            ON DELETE CASCADE;


-- 탈퇴 (탈퇴한 아이디로 재가입이 불가능)
DROP TABLE LEAVE_USER;
CREATE TABLE LEAVE_USER (
    ID        VARCHAR2(40 BYTE) NOT NULL UNIQUE,
    JOINED_AT DATE,  -- 가입일
    LEAVED_AT DATE   -- 탈퇴일
);


-- 휴면 (1년 이상 로그인을 안하면 휴면 처리)
DROP TABLE SLEEP_USER;
CREATE TABLE SLEEP_USER (
    USER_NO        NUMBER             NOT NULL,
    ID             VARCHAR2(40 BYTE)  NOT NULL UNIQUE,  -- ID 정규식에 반영
    PW             VARCHAR2(64 BYTE)  NOT NULL,         -- SHA-256 암호화 방식 사용
    NAME           VARCHAR2(40 BYTE),                   -- 이름
    GENDER         VARCHAR2(2 BYTE),                    -- M, F, NO
    EMAIL          VARCHAR2(100 BYTE) NOT NULL,         -- 이메일
    MOBILE         VARCHAR2(15 BYTE),                   -- 하이픈 제외(-) 후 저장
    BIRTHYEAR      VARCHAR2(4 BYTE),                    -- 출생년도(YYYY)
    BIRTHDATE      VARCHAR2(4 BYTE),                    -- 출생월일(MMDD)
    POSTCODE       VARCHAR2(5 BYTE),                    -- 우편번호
    ROAD_ADDRESS   VARCHAR2(100 BYTE),                  -- 도로명주소
    JIBUN_ADDRESS  VARCHAR2(100 BYTE),                  -- 지번주소
    DETAIL_ADDRESS VARCHAR2(100 BYTE),                  -- 상세주소
    EXTRA_ADDRESS  VARCHAR2(100 BYTE),                  -- 참고항목
    AGREECODE      NUMBER             NOT NULL,         -- 동의여부(0:필수, 1:위치, 2:이벤트, 3:위치+이벤트)
    JOINED_AT      DATE,                                -- 가입일
    PW_MODIFIED_AT DATE,                                -- 비밀번호변경일
    SLEPT_AT       DATE                                 -- 휴면일
);