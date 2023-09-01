-- ddl
drop table member;
create table member (
	no int not null auto_increment,
    email varchar(200) not null,
    password varchar(64) not null,
    name varchar(100) not null,
    department varchar(100),
    
    primary key(no)
);

desc member;

alter table member add column idnumber char(13) not null;

alter table member drop column idnumber;

alter table member add column idnumber char(13) not null after email;

alter table member change column department dept varchar(100);
desc member;
alter table member add self_intro text;
--
-- dml
--

-- insert 
insert into member values(null, 'dawn@naver.com', password('1234'), '김종혁', '개발팀', null);

insert into member(email, name, dept, password)
values('dawn1@naver.com','김종혁1', '개발1팀', password('1234'));
select * from member;

-- update
update member
set email = 'dawny@naver.com', name = '김종핵'
-- where절 필수
where no =2;
select * from member;

-- delete, where절 필수
delete from member 
where no = 2;
select * from member;

-- transaction begin
set autocommit = 0;
select @@autocommit from dual;

insert into member(email, name, dept, password)
values('dawn1@naver.com','김종혁5', '개발5팀', password('1234'));
select * from member;

select no, email, dept from member;

commit;