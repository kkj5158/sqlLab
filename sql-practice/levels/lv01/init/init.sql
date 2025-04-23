DROP TABLE IF EXISTS employees;


CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(100),
    salary INT,
    hire_date DATE
);

INSERT INTO employees (id, name, department, salary, hire_date) VALUES
(1, 'Alice', 'HR', 5000, '2020-01-15'),
(2, 'Bob', 'Engineering', 6000, '2019-03-22'),
(3, 'Charlie', 'Engineering', 5500, '2021-06-10'),
(4, 'Diana', 'Marketing', 5200, '2018-11-30'),
(5, 'Ethan', 'HR', 4800, '2022-02-01');