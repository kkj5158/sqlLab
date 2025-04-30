-- SALARY 테이블 생성
create table SALARY (
    id Number(5) PRIMARY KEY ,
    name CHAR(25) ,
    salary NUMBER(7, 2),
    title CHAR(25) DEFAULT '사원',
    in_dat DATE DEFAULT SYSDATE,
    dept_name CHAR(25)
);

-- SALARY 테이블 드랍하기

DROP table SALARY;