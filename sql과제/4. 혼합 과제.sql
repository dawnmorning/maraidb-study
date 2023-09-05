-- 혼합 SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
from employees emp
join salaries sal on emp.emp_no = sal.emp_no and sal.to_date = '9999-01-01'
where sal.salary > (select avg(sal.salary)
from salaries sal where sal.to_date ='9999-01-01' );

-- 문제2.  (x)
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

-- 문제3. from절에 두고 join방식
-- 현재, 자신의 부서 평균 연봉보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요
SELECT
    emp.emp_no AS "사번",
    emp.first_name AS "이름",
    sal.salary AS "연봉"
FROM
    employees emp
JOIN
    salaries sal ON emp.emp_no = sal.emp_no AND sal.to_date = '9999-01-01'
JOIN
    dept_emp demp ON emp.emp_no = demp.emp_no AND demp.to_date = '9999-01-01'
WHERE
    sal.salary > (
        SELECT AVG(sal_inner.salary)
        FROM salaries sal_inner
        JOIN dept_emp demp_inner ON sal_inner.emp_no = demp_inner.emp_no AND demp_inner.to_date = '9999-01-01'
        WHERE demp_inner.dept_no = demp.dept_no AND sal_inner.to_date = '9999-01-01'
    )
ORDER BY sal.salary DESC;


-- 문제4.
-- 현재, 사원들의 사번, 이름,  매니저이름, 부서 이름으로 출력해 보세요.
SELECT 
    a.emp_no, 
    a.first_name, 
    mgr.first_name AS '매니저 이름',
    dept.dept_name AS '부서 이름'
FROM 
    employees a
JOIN 
    dept_emp b ON a.emp_no = b.emp_no AND b.to_date = '9999-01-01'
JOIN 
    dept_manager c ON b.dept_no = c.dept_no AND c.to_date = '9999-01-01'
JOIN 
    employees mgr ON c.emp_no = mgr.emp_no
JOIN 
    departments dept ON b.dept_no = dept.dept_no;
-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
SELECT 
    emp_outer.emp_no as 사번,
    emp_outer.first_name as 이름,
    t_outer.title as 직책,
    sal_outer.salary as 연봉
FROM
    employees emp_outer
        JOIN
    salaries sal_outer ON emp_outer.emp_no = sal_outer.emp_no
        AND sal_outer.to_date = '9999-01-01'
        JOIN
    titles t_outer ON emp_outer.emp_no = t_outer.emp_no
        AND t_outer.to_date = '9999-01-01'
        JOIN
    dept_emp d_emp_outer ON emp_outer.emp_no = d_emp_outer.emp_no
        AND d_emp_outer.to_date = '9999-01-01'
WHERE
    d_emp_outer.dept_no = (SELECT 
            d_emp.dept_no
        FROM
            salaries sal
                JOIN
            dept_emp d_emp ON sal.emp_no = d_emp.emp_no
                AND d_emp.to_date = '9999-01-01'
                AND sal.to_date = '9999-01-01'
        GROUP BY d_emp.dept_no
        ORDER BY AVG(sal.salary) DESC
        LIMIT 1)
ORDER BY sal_outer.salary DESC;


-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
-- 총무 20000 having = 
select dept.dept_name, avg(sal.salary)
from salaries sal
join dept_emp d_emp on sal.emp_no = d_emp.emp_no and d_emp.to_date = '9999-01-01'
join departments dept on d_emp.dept_no = dept.dept_no
group by dept.dept_name
order by avg(sal.salary) desc
limit 1;

-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- Engineer 40000 having = 
select t.title, avg(sal.salary)
from salaries sal
join titles t on sal.emp_no = t.emp_no and t.to_date = '9999-01-01'
group by t.title
order by avg(sal.salary) desc
limit 1;

-- 문제8. from절
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
SELECT
    dept.dept_name AS "부서이름",
    emp.first_name AS "사원이름",
    emp_sal.salary AS "연봉",
    mgr.first_name AS "매니저 이름",
    mgr_sal.salary AS "매니저 연봉"
FROM
    employees emp
JOIN
    salaries emp_sal ON emp.emp_no = emp_sal.emp_no AND emp_sal.to_date = '9999-01-01'
JOIN
    dept_emp demp ON emp.emp_no = demp.emp_no AND demp.to_date = '9999-01-01'
JOIN
    departments dept ON demp.dept_no = dept.dept_no
JOIN
    dept_manager dmgr ON dept.dept_no = dmgr.dept_no AND dmgr.to_date = '9999-01-01'
JOIN
    employees mgr ON dmgr.emp_no = mgr.emp_no
JOIN
    salaries mgr_sal ON mgr.emp_no = mgr_sal.emp_no AND mgr_sal.to_date = '9999-01-01'
WHERE
    emp_sal.salary > mgr_sal.salary
ORDER BY
    dept.dept_name, emp_sal.salary DESC;


