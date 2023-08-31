-- 조인(JOIN) SQL 문제입니다.

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select e.emp_no as 사번, e.first_name as 이름, s.salary as 연봉 from employees e join salaries s on e.emp_no = s.emp_no where s.to_date = '9999-01-01' order by s.salary ;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select e.emp_no as 사번, e.first_name as 이름, t.title as 직책 from employees e join titles t on e.emp_no = t.emp_no where t.to_date = '9999-01-01';

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..
SELECT 
    e.emp_no AS 사번, 
    e.first_name AS 이름, 
    COALESCE(dept.dept_name, '부서 없음') AS 부서
FROM
    employees e 
    left JOIN dept_emp demp ON e.emp_no = demp.emp_no AND demp.to_date = '9999-01-01'
    left JOIN departments dept ON demp.dept_no = dept.dept_no;

-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
SELECT distinct
    emp.emp_no AS 사번,
    emp.first_name AS 이름,
    sal.salary AS 연봉,
    t.title AS 직책,
    dept.dept_name AS 부서
FROM employees emp
JOIN salaries sal ON emp.emp_no = sal.emp_no AND sal.to_date = '9999-01-01'
JOIN titles t ON emp.emp_no = t.emp_no AND t.to_date = '9999-01-01'
JOIN dept_emp demp ON emp.emp_no = demp.emp_no AND demp.to_date = '9999-01-01'
JOIN departments dept ON demp.dept_no = dept.dept_no
ORDER BY emp.first_name;

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. 
-- (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 이름은 first_name과 last_name을 합쳐 출력 합니다.
SELECT 
    emp.emp_no, concat(emp.first_name, ' ' ,emp.last_name)  as "expierenced TL"
FROM
    employees emp
join titles t on emp.emp_no = t.emp_no
where t.title = 'technique leader';

-- 문제6.
-- 직원 이름(last_name) 중에서 s로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
SELECT 
    emp.last_name AS 이름,
    dept.dept_name AS 부서명,
    t.title AS 직책
FROM
    employees emp
join dept_emp demp on emp.emp_no = demp.emp_no and demp.to_date = '9999-01-01'
join departments dept on dept.dept_no = demp.dept_no
join titles t on emp.emp_no = t.emp_no and t.to_date = '9999-01-01'
where emp.last_name like 's%';

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
select concat(emp.first_name, ' ' ,emp.last_name) as 사원,
    t.title as 직책,
    sal.salary as 연봉
from employees emp
left join titles t on emp.emp_no = t.emp_no and t.to_date = "9999-01-01"
left JOIN salaries sal ON emp.emp_no = sal.emp_no AND sal.to_date = '9999-01-01'
where t.title = 'engineer' and sal.salary >= 40000
order by sal.salary desc;
-- 문제8.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select dept.dept_name, avg(sal.salary) as avg_salary
from dept_emp demp
join salaries sal on sal.emp_no = demp.emp_no AND demp.to_date = '9999-01-01'
join departments dept on demp.dept_no = dept.dept_no
group by dept.dept_name
order by avg_salary desc;
-- 문제9.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select t.title as 직책, avg(sal.salary) as 평균_연봉
from salaries sal
join titles t on sal.emp_no = t.emp_no and t.to_date = '9999-01-01'
group by t.title
order by 평균_연봉 desc;
