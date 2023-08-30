--
-- 수학 함수
--

-- abs
select abs(1), abs(-1) from dual;

-- floor
select floor(3.14), floor (3.9999) from dual;

-- ceil
select ceil(3.14), ceil(3.9999999) from dual;

-- mod
select mod(10,3) from dual;

-- round(x): x에 가장 가까운 정수
-- round (x,d) : x값 중에 소수점 d자리에 가장 가까운 수
select round(1.489), round(1.489,1) from dual;

-- power(x,y) : x의 y승
select power(2,10), pow(2,10) from dual;

-- sign(x) : 양수 : 1, 음수 : -1, 0 : 0
select sign(2),sign(-1),sign(0) from dual;

-- greatest(x,y,....), least(x,y,....)
select greatest(1,2,3,4), least(1,2,3,4) from dual;
select greatest('A','C','D'), least('hello', 'hela', 'hell') from dual;