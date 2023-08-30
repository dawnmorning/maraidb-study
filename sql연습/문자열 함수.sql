--
-- 문자열 함수
--

-- upper
select upper('seoul') as seoul, ucase('andong') as andong from dual;
select upper(first_name) from employees;

-- lower
select lower('SEOUL'), lcase('AnDONG') from dual;

-- substring(문자열, index, length)
select substring('Hello World',3,5) from dual;

-- 예1) : like 검색: employees 테이블에서 1989년에 입사한 직원들의 first_name, hire_date
select first_name, hire_date from employees where substring(hire_date,1,4) = '1989';

-- lpad(우정렬), rpad: 정렬함수
select lpad('1234', 10, '-'), rpad('1234',10,'-') from dual;

-- 예) 직원들의 월급을 우측 정렬 하시오
select lpad(salary,7,'*') from salaries order by salary desc;

-- trim, ltrim, rtrim
select ltrim('  hi**  '), rtrim('  hi    '), trim(leading 'h' from 'hello') from dual;
select ltrim('  hi**  '), rtrim('  hi    '), trim(trailing 'h' from 'hello') from dual;
select ltrim('  hi**  '), rtrim('  hi    '), trim(both 'h' from 'hello') from dual;

-- length
select length('Hello World') from dual;