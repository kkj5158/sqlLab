## 서브 쿼리 활용해서 조회하기
select e.name, e.salary
    from employees as e
where e.salary = (select max(salary) from employees);

;


select max(e.salary)
    from employees as e;


## 다른 방법으로도 풀어보기