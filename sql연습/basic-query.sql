select version(), current_date(), now() from dual;

-- 수학 함수도 사용할 수 있다. (사칙 연산도 된다)
select pi() / 4 from dual;

-- 대소문자 구분하지 않음.
select  VERsion(), current_DAte(), NoW() froM duaL;

-- table 생성: DDL
create table pet(
	name varchar(100),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- schema 확인
show tables;
describe pet;
desc pet;

-- 삭제: DDL
drop table pet;

-- insert : DML(Create)
insert into pet values('도마', '윾가', '닥스훈트', 'm', '2018-01-01', null);

-- select : DML(Read)
select * from pet;

-- update : DML(Update)
update pet set name = 'Doma' where name='도마';

-- delete : DML(Delete)
delete from pet where name='Doma';

-- load data
load data local infile 'd:\pet.txt' into table pet;

-- select 연습
-- 문) bowser 주인의 이름은?
select owner from pet where name='bowser';
