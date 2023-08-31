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

--
-- dml
--



alter table member add self_intro text;