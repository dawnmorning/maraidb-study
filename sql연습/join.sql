-- inner join

-- 예1) : 현재 근무하고 있는 직원의 이름과 직책(title)을 모두 출력
-- emp_no 하면 에러
SELECT 
    e.emp_no
FROM
    employees e,
    titles t
WHERE
    e.emp_no = t.emp_no
        AND t.to_date = '9999-01-01'; -- join 조건 (n-1)

-- 예2) : 현재, 근무하고 있는 직원 사번, 이름과 직책을 모두 출력하되 여성 엔지니어만 출력

SELECT 
    e.emp_no, e.first_name, e.gender, t.title
FROM
    employees e,
    titles t
WHERE
    e.emp_no = t.emp_no
        AND t.to_date = '9999-01-01'
        AND e.gender = 'f'
        AND t.title = 'engineer'; -- join 조건 (n-1) 

--
-- ANSI / ISO SQL1999 JOIN 표준 문법
--

-- 1) join ~ on *
-- 예제 : 현재 직책별 평균 연봉을 큰 순서대로 출력
select t.title ,avg(s.salary)
	from titles t, salaries s
    -- join
    where t.emp_no = s.emp_no
    and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    group by t.title
    order by avg(s.salary) desc;
    
select t.title ,avg(s.salary)
	from titles t join salaries s on t.emp_no = s.emp_no
    -- join
    where t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    group by t.title
    order by avg(s.salary) desc;    
    
-- 2) Natural join
-- join 대상이 되는 테이블들에 이름이 같은 공통 컬럼이 있는 경우
-- join 조건을 암묵적으로 join이 된다.a
-- 예제) : 현재 근무하고 있는 직원의 이름과 직책을 출력
SELECT 
    e.first_name, t.title
FROM
    employees e
        JOIN
    titles t ON e.emp_no = t.emp_no
WHERE
    t.to_date = '9999-01-01'
ORDER BY e.first_name;
-- -------------------------------
SELECT 
    e.first_name, t.title
FROM
    employees e
        NATURAL JOIN
    titles t
WHERE
   -- e.emp_no = t.emp_no
        t.to_date = '9999-01-01'
ORDER BY e.first_name;
 
-- 3) join ~ using
-- natural join의 문제점
-- 예) : 현재 근무하고 있는 직원의 직책과 연봉을 출력

-- 중복된 column 여러 개면 문제
select * from titles t natural join salaries s where t.to_date = '9999-01-01' and s.to_date = '9999-01-01' ;

-- 해결1 : join ~ using
select * from titles t join salaries s using(emp_no) where t.to_date = '9999-01-01' and s.to_date = '9999-01-01' ;

-- 해결 2: join ~ on
select * from titles t join salaries s on t.emp_no = s.emp_no where t.to_date = '9999-01-01' and s.to_date = '9999-01-01' ;

-- 실습문제1
-- 현재 직원별 근무 부서를 출력
-- 사번, first_name, 부서명 순으로
select * from employees;
select * from departments;
select * from dept_emp;
select * from salaries;
select * from titles;
SELECT 
    demp.emp_no AS 사번, emp.first_name AS 이름, dpt.dept_name AS 부서
FROM
    dept_emp demp
        JOIN
    employees emp ON demp.emp_no = emp.emp_no
        JOIN
    departments dpt ON demp.dept_no = dpt.dept_no
WHERE
    demp.to_date = '9999-01-01'
order by demp.emp_no, emp.first_name, dpt.dept_name;

-- 실습문제2
-- 현재 직책별 평균연봉과 직원수를 구하되 직책별 직원수가 100명 이상인 직책만 출력
-- 직책, 평균연봉, 직원순으로
select t.title as 직책, avg(s.salary) as 평균연봉, count(*) as 직원수 
from titles t join salaries s on t.emp_no = s.emp_no 
where t.to_date = '9999-01-01' and s.to_date = '9999-01-01' group by t.title having count(t.title) >= 100
order by t.title, avg(s.salary) desc;

-- 실습문제3
-- 현재 부서별로 직책이 engineer인 직원들에 대해서만 평균 연봉을 구하시오
-- 부서이름, 평균 급여 순으로
select dept.dept_name ,avg(s.salary)
from departments dept, dept_emp demp, titles t, salaries s
where dept.dept_no = demp.dept_no
and demp.emp_no = t.emp_no
and t.emp_no = s.emp_no
and demp.to_date = '9999-01-01'
and t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
and t.title = 'engineer'
group by dept.dept_name
order by dept_name, avg(s.salary);

