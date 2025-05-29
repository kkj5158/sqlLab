CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    grade VARCHAR(2),
    enrolled DATE
);

INSERT INTO students (id, name, age, grade, enrolled) VALUES
(1, 'Alice', 20, 'A', '2021-03-01'),
(2, 'Bob', 22, 'B', '2020-09-15'),
(3, 'Charlie', 19, 'A', '2022-01-10'),
(4, 'Diana', 21, 'C', '2021-06-05'),
(5, 'Ethan', 23, 'F', '2019-11-20'),
(6, 'Fiona', 20, 'B', '2022-02-20'),
(7, 'George', 22, 'D', '2020-12-01');