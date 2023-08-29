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
update pet set death = null where name != 'bowser';
-- select 연습
-- 문) bowser 주인의 이름은?
select owner from pet where name='bowser';

-- 문2) 1998 이후에 태어난 애들은?
select * from pet where birth >= '1998-01-01';

-- 문3) 종이 뱀이거나 새인 애들은?
select * from pet where species = 'snake' or species = 'bird';

-- 문4) order by
select name, birth from pet order by birth asc;

-- 문5) order by ~desc
select name, birth from pet order by birth desc;

-- 문6) where절에 null 다루기
select name, birth, death where death is null;

-- 문7) like 검색(패턴 검색)
select name from pet where name like 'b%';
select name from pet where name like '%fy';
select name from pet where name like '%W%';
select name from pet where name like '____';
select name from pet where name like 'b_____'; -- bowser

-- 문8) 집계: count, avg, sum, max, min ...
select count(*) from pet;
select max(birth) from pet;