-- outer join
select * from dept;
insert into dept values(null, "총무");
insert into dept values(null, "개발");
insert into dept values(null, "영업");
insert into dept values(null, "기획");

insert into employee values(null, '둘리', 1);
insert into employee values(null, '마이콜', 2);
insert into employee values(null, '또치', 3);
insert into employee values(null, '길동이', null);
select * from employee;

-- inner join
select * from employee join dept on employee.dept_no = dept.no;

-- outer join(left join) employee가 left
select employee.name as 사원, ifnull(dept.name, '없음') as 부서 from employee left join dept on employee.dept_no = dept.no;

-- right join(outer join)
select employee.name, dept.name from employee right join dept on employee.dept_no = dept.no;