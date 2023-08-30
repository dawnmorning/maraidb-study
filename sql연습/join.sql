-- inner join

-- 예1) : 현재 근무하고 있는 직원의 이름과 직책(title)을 모두 출력
-- emp_no 하면 에러
select e.emp_no from employees e, titles t where e.emp_no = t.emp_no and t.to_date = '9999-01-01'; -- join 조건 (n-1)