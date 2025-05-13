## 서브 쿼리 활용해서 조회하기
select e.name, e.salary
    from employees as e
where e.salary = (select max(salary) from employees);


select e.name, e.salary
    from employees as e
where e.salary in (select max(salary) from employees);


## 다른 방법으로도 풀어보기

select e.name, e.salary
    from employees as e
order by e.salary DESC
limit 1;

## 해당 방식은 동점자 처리가 어렵다. (급여가