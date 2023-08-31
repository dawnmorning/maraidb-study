-- 

-- select 연습

--

-- 예1) : departments 테이블의 모든 데이터를 출력
select * from departments;

-- 프로젝션(projection)
-- 예2) : emplyees 테이블에서 직원 이름, 성별, 입사일을 출력
select first_name as '이름', gender as '성별', hire_date as '입사일' from employees;

-- distinct

-- 예3) : titles 테이블에서 모든 직급을 출력하세요.
select distinct title from titles;

-- 예4) : titels 테이블에서 직급은 어떤 것들이 있는지 직급이름을 한 번씩만 출력하세요.
select distinct title from titles;
select distinct title from titles limit 0,5;

--
-- where 절
--

-- 예1) : 비교 연산자 : employees 테이블에서 1991년 이전에 입사한 직원의 이름(first_name), gender, hire_date
SELECT 
    first_name, gender, hire_date
FROM
    employees
WHERE
    hire_date < '1991-01-01'
ORDER BY hire_date DESC; 

-- 예2) : 논리연산자 : employees 테이블에서 1989년 이전에 입사한 여직원의 first_name, gender, hire_date
SELECT 
    first_name, gender, hire_date
FROM
    employees
WHERE
    hire_date < '1990-01-01'
        AND gender = 'F'
ORDER BY hire_date DESC;

-- 예3) : in 연산자: dept_emp 테이블에서 부서 번호가 d005이거나 d009에 속한 사원의 사번, 부서 번호를 출력
select * from dept_emp;
select emp_no, dept_no from dept_emp where dept_no = 'd005' or dept_no = 'd009';
-- where dept_no in ('d-005', 'd-009');

-- 예4) : like 검색: employees 테이블에서 1989년에 입사한 직원들의 first_name, hire_date
select first_name, hire_date from employees where hire_date >= '1989-01-01' and hire_date <= '1989-12-31';
select first_name, hire_date from employees where hire_date between '1989-01-01' and '1989-12-31';
select first_name, hire_date from employees where hire_date like '1989%' order by hire_date;

--
-- order by
--

-- 예1) : employees 테이블에서 first_name, gender, hire_date을 입사일 빠른 순으로
select concat(first_name, ' ', last_name) as name, gender, hire_date from employees order by hire_date;

-- 예2) : salaries 테이블에서 2001년 연봉이 가장 높은 순으로 사번, 월급를 출력
select * from salaries;
select count(*) from salaries;
select emp_no, salary, from_date, to_date from salaries where from_date like '2001%' or to_date like '2001%' order by salary desc;

-- 예3) : 남자 직원의 first_name, gender, hire_date을 먼저 hire_date 순으로
select first_name, gender, hire_date from employees where gender='m' order by hire_date;

-- 예4) : 직원들의 사번, 월급을 사번 순으로 출력하되 같은 지구언의 월급이 높은 순도 반영
select emp_no, salary from salaries order by emp_no, salary desc;



-- 기본 SQL 문제입니다.

-- 문제1.
-- 사번이 10944인 사원의 이름은(first_name)
select first_name from employees where emp_no=10944;
-- 문제2. 
-- 전체직원의 다음 정보를 조회하세요. 가장 선임부터 출력이 되도록 하세요. 출력은 이름, 성별,  입사일 순서이고 “이름”, “성별”, “입사일로 컬럼 이름을 대체해 보세요.
select * from employees;
select first_name as '이름', gender as '성별', hire_date as '입사일' from employees; 
-- 문제3.
-- 여직원과 남직원은 각 각 몇 명이나 있나요?
select count(*) from employees;
select * from employees;
select count(*) from employees where gender='F';
select count(*) from employees where gender='M';
-- 문제4.
-- 현재(to_date='9999-01-01') 근무하고 있는 직원 수는 몇 명입니까? (salaries 테이블을 사용합니다.) 
select * from salaries;
select count(*) from salaries where to_date='9999-01-01';

-- 문제5.
-- 부서는 총 몇 개가 있나요?
select count(*) from departments;

-- 문제6.
-- 현재 부서 매니저는 몇 명이나 있나요?(역임 매너저는 제외)
select count(*) from dept_manager where to_date='9999-01-01';

-- 문제7.
-- 전체 부서를 출력하려고 합니다. 순서는 부서이름이 긴 순서대로 출력해 보세요.
select dept_name from departments order by length(dept_name) desc;

-- 문제8.
-- 현재 급여가 120,000이상 받는 사원은 몇 명이나 있습니까?
select * from salaries;
SELECT 
    COUNT(*)
FROM
    salaries
WHERE
    salary >= 120000
        AND to_date = '9999-01-01';

-- 문제9.
-- 어떤 직책들이 있나요? 중복 없이 이름이 긴 순서대로 출력해 보세요.
select * from titles;
select distinct title from titles order by length(title) desc;

-- 문제10
-- 현재 Enginner 직책의 사원은 총 몇 명입니까?
SELECT 
    COUNT(*)
FROM
    titles
WHERE
    to_date = '9999-01-01'
        AND title = 'Engineer';

-- 문제11
-- 사번이 13250(Zeydy)인 직원의 직책 변경 상황을 시간순으로 출력해보세요.
SELECT 
    title, from_date, to_date
FROM
    titles
WHERE
    emp_no = 13250
ORDER BY from_date;