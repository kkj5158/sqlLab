SELECT name, salary
FROM employees
WHERE salary = (SELECT MAX(salary) FROM employees);


SELECT MAX(salary) FROM employees;