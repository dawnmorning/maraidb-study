-- 1) 집계쿼리: select절에 통계함수(avg, max, min, coutn, sum, stddev, ...)

select avg(salary), sum(salary) from salaries;

-- 2) select 절에 group 함수(통계함수)있는 경우, 어떤 column도 select 절에 올 수 없다.
-- emp_no는 아무 의미가 없다.. 오류다.
select emp_no, avg(salary) from salaries;

-- 3) 쿼리 순서
-- 1. from : 테이블에 접근
-- 2. where : 조건에 맞는 row를 선택
-- 3. projection : 집계(임시 테이블, 메모리 캐시)
-- 4. 결과를 반환 : 출력

-- 예) 사번이 10060인 사원이 받은 평균 연봉
select avg(salary) from salaries where emp_no=10060;

-- 4) group by 참여 column은 projection이 가능하다 : select 절에 올 수 있다.
-- 예) 사원별 평균 연봉
select emp_no, avg(salary) as avg_salary from salaries group by emp_no order by avg(salary) desc;

-- 5) Having
-- 집계 결과(결과 테이블)에서 row를 선택해야 하는 경우 이미 where 절은 실행이 되었기 때문에 having 절에서 조건을 주어야 한다.
-- 필터 역할인 셈
-- 예제 : 평균 연봉이 $ 60000 이상인 사원의 사번과 평균 연봉을 출력
select emp_no, avg(salary) from salaries group by emp_no having avg(salary) >= 60000 order by avg(salary) ;

-- 6) order by
-- order by는 항상 맨 마지막! 출력 전에 한다.

-- 주의)
-- 예제 : 사번이 10060인 사원의 사번, 평균 급여, 급여 총합을 출력

-- 문법적으로 오류
-- 의미적으로 맞다(where)
select emp_no, avg(salary), sum(salary) from salaries where emp_no = 10060;

-- 문법적으로 옳게
select emp_no, avg(salary), sum(salary) from salaries group by emp_no having emp_no=10060;

-- 집계(통계) SQL 문제입니다.

-- 문제 1. 
-- 최고임금(salary)과  최저임금을 “최고임금, “최저임금”프로젝션 타이틀로 함께 출력해 보세요. 두 임금의 차이는 얼마인가요? 함께 “최고임금 – 최저임금”이란 타이틀로 출력해 보세요.
select max(salary) as 최고임금, min(salary) as 최저임금, max(salary) - min(salary) as "최고임금 - 최저임금" from salaries; 
-- 문제2.
-- 마지막으로 신입사원이 들어온 날은 언제 입니까? 다음 형식으로 출력해주세요.
-- 예) 2014년 07월 10일
select date_format(max(hire_date),'%Y년 %m월 %d일') from employees;
-- 문제3.
-- 가장 오래 근속한 직원의 입사일은 언제인가요? 다음 형식으로 출력해주세요.
-- 예) 2014년 07월 10일
select date_format(min(hire_date),'%Y년 %m월 %d일') from employees;
-- 문제4.
-- 현재 이 회사의 평균 연봉은 얼마입니까?
select avg(salary) from salaries;
-- 문제5.
-- 현재 이 회사의 최고, 최저 연봉은 얼마입니까?
select max(salary), min(salary) from salaries;
-- 문제6.
-- 최고 어린 사원의 나이와 최 연장자의 나이는?
SELECT 
    YEAR(CURDATE()) - YEAR(min(birth_date)) - (DATE_FORMAT(CURDATE(), '%m%d') < DATE_FORMAT(min(birth_date), '%m%d')) AS "최 연장자의 나이",
    YEAR(CURDATE()) - YEAR(max(birth_date)) - (DATE_FORMAT(CURDATE(), '%m%d') < DATE_FORMAT(max(birth_date), '%m%d')) AS "최고 어린 사원의 나이"
    FROM 
    employees;

