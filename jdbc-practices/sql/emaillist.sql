-- emaillist

-- insert
insert into emaillist values(null, '김', '종혁', 'dawn@naver.com');
insert into emaillist values(null,'장','은영','gsjang0807@gmail.com');
insert into emaillist values (null, 'cho', 'yon', 'yoncho@naver.com');
insert into guestbook values (null,?,?,?,?);

-- findAll
select no, first_name, last_name, email from emaillist order by no desc;
desc guestbook;
select * from guestbook;
-- delete
delete from emaillist where email='dawn@naver.com';
