--
-- subquery
--

--
-- 1) select 절 : select(select...), insert into t value(...)
--

--
-- 2) select의 from절의 서브쿼리
--

select * 
from (select now() as n, sysdate() as s, 3+1 as r from dual) a;

--
-- 3) select의 where절 또는 having의 서브쿼리
--

-- 예) 현재, Fai Bale이 근무하는 부서에서 근무하는 다른 직원의 사번, 이름을 출력
-- sol1) 비추
select demp.dept_no from employees e join dept_emp demp on e.emp_no = demp.emp_no where demp.to_date = '9999-01-01' and concat(first_name, ' ', last_name) = 'Fai Bale';

select e.empno, e.first_name from employees e join dept_emp demp on e.emp_no = demp.emp_no where demp.to_date = '9999-01-01' and concat(first_name, ' ', last_name) = 'Fai Bale';

-- sol2) subquery 사용
SELECT 
    a.emp_no, a.first_name
FROM
    employees a,
    dept_emp b
WHERE
    a.emp_no = b.emp_no
        AND b.to_date = '9999-01-01'
        AND dept_no = (SELECT 
            b.dept_no
        FROM
            employees a,
            dept_emp b
        WHERE
            a.emp_no = b.emp_no
                AND b.to_date = '9999-01-01'
                AND CONCAT(first_name, ' ', last_name) = 'Fai Bale');
                
-- 3-1) 단일행 연산자 : =, > , <, >=, <=, <>. !=
-- 예재 : 현재 근무중인 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름과 급여
-- subquery 먼저 짜고 main query 짜기

-- subquery
select avg(salary) from salaries where to_date = '9999-01-01';
-- main query
SELECT 
    e.first_name,
    s.salary
FROM
    employees e,
    salaries s
WHERE
    e.emp_no = s.emp_no
        AND s.to_date = '9999-01-01'
        AND s.salary < (SELECT 
            AVG(salary)
        FROM
            salaries
        WHERE
            to_date = '9999-01-01')
	order by s.salary desc;
-- 예2) : 현재 근무중인 가장 적은 평균 급여의 직책과 그 급여를 출력
-- Engineer 20000
-- subquery
SELECT 
    t.title, AVG(s.salary)
FROM
    titles t,
    salaries s
WHERE
    t.emp_no = s.emp_no
        AND t.to_date = '9999-01-01'
        AND s.to_date = '9999-01-01'
GROUP BY t.title
having avg(s.salary) = (select min(avg_salary) from (SELECT 
    avg(s.salary) as avg_salary
FROM
    salaries s,
    titles t
WHERE
    s.emp_no = t.emp_no
        AND s.to_date = '9999-01-01'
        AND t.to_date = '9999-01-01'
group by t.title) sub);


select min(avg_salary) from (SELECT 
    avg(s.salary) as avg_salary
FROM
    salaries s,
    titles t
WHERE
    s.emp_no = t.emp_no
        AND s.to_date = '9999-01-01'
        AND t.to_date = '9999-01-01'
group by t.title) sub;

-- sol2) top-k
SELECT 
    t.title, AVG(s.salary)
FROM
    titles t,
    salaries s
WHERE
    t.emp_no = s.emp_no
        AND t.to_date = '9999-01-01'
        AND s.to_date = '9999-01-01'
GROUP BY t.title
order by avg(s.salary)
limit 0, 1;

-- 3-2) 복수행 연산자: in, not in, 비교 연산자 any, 비교 연산자 all

-- any 
-- 1. =any : in
-- 2. >any, >= anu : 최소값
-- 3. <any, <any : 최대값
-- 4. != any, <>any : not in

-- all 사용법
-- 1. = all : (x)
-- 2. >all, >=all : 최대값
-- 3. <all, <=all : 최소값
-- 2. != all, <>all

-- 예제3) 현재 근무중인 사람 중 급여가 50000 이상인 직원의 이름과 급여를 출력하세요.(급여 내림차순)
-- 둘리  65000
-- 또치  70000

-- sol1) join
-- select e.first_name, s.salary from employees e, salaries s where e.emp_no = s.emp_no and s.to_date = '9999-01-01' and s.salary>= 50000 order by s.salary;
-- select * from employees, salaries where    ; 
SELECT 
    e.first_name, s.salary
FROM
    employees e,
    salaries s
WHERE
    e.emp_no = s.emp_no
        AND s.to_date = '9999-01-01'
        AND (e.emp_no , s.salary) IN (SELECT 
            e.emp_no, s.salary
        FROM
            salaries s,
            employees e
        WHERE
            salary >= 50000
                AND to_date = '9999-01-01');
                
-- 문제4) : 현재 근무중인 인원 중 각 부서별로 최고 급여를 받고 있는 직원의 이름과 연봉 (복수 행 / 다중 컬럼)

-- so1) where subquery in
SELECT 
    e.first_name, demp.dept_no, dept.dept_name, s.salary
FROM
    employees e,
    salaries s,
    dept_emp demp,
    departments dept
WHERE
    s.emp_no = e.emp_no
        AND e.emp_no = demp.emp_no
        AND demp.dept_no = dept.dept_no
        AND demp.to_date = '9999-01-01'
        AND s.to_date = '9999-01-01'
        AND (demp.dept_no , s.salary) IN (SELECT 
            demp.emp_no, MAX(s.salary)
        FROM
            salaries s,
            dept_emp demp
        WHERE
            s.emp_no = demp.emp_no
                AND s.to_date = '9999-01-01'
                AND demp.to_date = '9999-01-01'
        GROUP BY demp.dept_no);

-- sol2) from절 subquery, join
SELECT 
    e.first_name, demp.dept_no, dept.dept_name, s.salary
FROM
    employees e,
    salaries s,
    dept_emp demp,
    departments dept,
    (SELECT 
            demp.emp_no, MAX(s.salary) as max_salary
        FROM
            salaries s,
            dept_emp demp
        WHERE
            s.emp_no = demp.emp_no
                AND s.to_date = '9999-01-01'
                AND demp.to_date = '9999-01-01'
        GROUP BY demp.dept_no) f
WHERE
    s.emp_no = e.emp_no
        AND e.emp_no = demp.emp_no
        AND demp.dept_no = dept.dept_no
        and dept.dept_no = f.dept_no
        AND demp.to_date = '9999-01-01'
        AND s.to_date = '9999-01-01'
        and s.salary = f.max_salary;

