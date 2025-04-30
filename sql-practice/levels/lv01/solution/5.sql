select e.department, count(e.department)
    from employees as e
group by e.department;