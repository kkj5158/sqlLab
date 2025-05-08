# 📘 SQL 중간 시험: test02 - Lv.6~10 복습 심화 (문제 1~10)

이 시험은 Lv.06~10의 핵심 SQL 기능들을 실제 업무 분석 시나리오에 맞춰 조합한 문제들입니다.  
서브쿼리와 문자열 함수 기반 문제로 시작합니다.

---

## 🧱 테이블 구조 및 초기 데이터

### `init.sql`

```sql
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    department_id INT
);

CREATE TABLE departments (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE logins (
    id INT PRIMARY KEY,
    employee_id INT,
    login_time DATETIME
);

INSERT INTO departments VALUES
(1, 'Engineering'), (2, 'HR'), (3, 'Marketing');

INSERT INTO employees VALUES
(1, 'Alice Kim', 'alice.kim@gmail.com', '010-1234-5678', 1),
(2, 'Bob Park', 'bob.park@naver.com', '010-2345-6789', 1),
(3, 'Charlie Lee', 'charlie@yahoo.com', NULL, 2),
(4, 'Diana Choi', 'diana.choi@daum.net', '010-4567-8901', 3),
(5, 'Ethan Kang', 'ethan@unknown.com', NULL, NULL);

INSERT INTO logins VALUES
(1, 1, '2024-05-01 09:01:00'),
(2, 1, '2024-05-02 09:02:00'),
(3, 2, '2024-05-01 09:00:00'),
(4, 2, '2024-05-02 09:05:00'),
(5, 4, '2024-05-01 09:00:00');
```

---

## 🧪 문제 1~10

### 문제 1  
**설명:** 부서별로 이메일 도메인이 'gmail.com'인 직원 수를 구하시오.  
(`SUBSTRING_INDEX`, `GROUP BY` 활용)

- **예상 결과:**

| department_name | gmail_count |
|------------------|--------------|
| Engineering      | 1            |

---

### 문제 2  
**설명:** 이메일 도메인이 중복 없이 등장하는 도메인만 조회하시오.  
(서브쿼리 + `HAVING COUNT = 1`)

- **예상 결과:**

| domain        |
|---------------|
| daum.net      |
| unknown.com   |

---

### 문제 3  
**설명:** 이메일 아이디에 숫자가 포함된 직원만 조회하시오. (`REGEXP`)

- **예상 결과:** 없음 (현재 없음)

---

### 문제 4  
**설명:** 전화번호가 NULL인 사람에게 '미입력'이라는 값을 붙여 출력하시오. (`COALESCE`)

- **예상 결과:**

| name         | phone_display  |
|--------------|----------------|
| Charlie Lee  | 미입력          |
| Ethan Kang   | 미입력          |

---

### 문제 5  
**설명:** 'gmail.com' 도메인을 가진 직원 중, 해당 도메인이 부서 내 몇 번째로 많이 쓰이는 도메인인지 순위를 조회하시오. (`RANK()` + `PARTITION BY department_id`)

---

### 문제 6  
**설명:** 도메인별 사용 직원 수와 해당 도메인이 가장 많이 쓰이는 부서를 조회하시오.  
(서브쿼리 vs JOIN 비교형)

---

### 문제 7  
**설명:** 모든 부서 중, 도메인 종류가 가장 다양한 부서를 조회하시오.  
(`COUNT(DISTINCT ...)` + `MAX()`)

---

### 문제 8  
**설명:** 도메인이 없는 직원 (이메일 NULL 또는 '@' 미포함) 을 조회하시오. (`NOT LIKE` 또는 `NOT REGEXP`)

- **예상 결과:** 없음

---

### 문제 9  
**설명:** 가장 최근 로그인 기록이 있는 직원들의 이름과 부서명을 조회하시오.  
(서브쿼리 `MAX(login_time)` → JOIN)

---

### 문제 10  
**설명:** 이메일 도메인별로 평균 전화번호 길이를 계산하되, NULL은 제외하시오. (`LENGTH(phone)` + `GROUP BY domain`)

---

😈 다음 세트는  
📅 Lv.08 날짜 함수 + Lv.09 고급 JOIN 기반 **문제 11~20**  
JOIN + 날짜 필터 + 부서별 월간 접속률, 결측 데이터 탐지 등 실무 감각 쿼리로 간다.  
지금 바로 드가자?

# 📘 SQL 중간 시험: test02 - Lv.6~10 복습 심화 (문제 11~20)

> 이 파트는 Lv.08 날짜 함수 + Lv.09 고급 JOIN을 기반으로,  
접속 이력 및 사용자 분석, 부서 연계, 로그인 이상 탐지 등을 실전 쿼리로 연습합니다.

---

## 🧪 문제 11~20

### 문제 11  
**설명:** 가장 마지막으로 로그인한 시간과 사용자를 조회하시오. (`MAX()` + 서브쿼리)

- **예상 결과:**

| name       | login_time          |
|------------|---------------------|
| Bob Park   | 2024-05-02 09:05:00 |

---

### 문제 12  
**설명:** 부서별로 가장 마지막에 로그인한 직원을 조회하시오. (`MAX()` + JOIN)

- **예상 결과:**

| department_name | name      | login_time          |
|------------------|-----------|---------------------|
| Engineering      | Bob Park | 2024-05-02 09:05:00 |
| Marketing        | Diana    | 2024-05-01 09:00:00 |

---

### 문제 13  
**설명:** 로그인 이력이 전혀 없는 직원을 조회하시오. (`LEFT JOIN` + `IS NULL`)

- **예상 결과:**

| name         |
|--------------|
| Charlie Lee  |
| Ethan Kang   |

---

### 문제 14  
**설명:** 부서에 소속되지 않은 직원 중, 로그인 이력이 있는 경우를 조회하시오.

- **예상 결과:**

| name  | department_name |
|-------|------------------|
| 없음 | 없음             |

---

### 문제 15  
**설명:** 각 부서별 평균 로그인 횟수를 구하시오.

- **예상 결과:**

| department_name | login_count |
|------------------|--------------|
| Engineering      | 4            |
| Marketing        | 1            |

---

### 문제 16  
**설명:** 로그인 시간 기준으로 오전(AM)/오후(PM)로 나누고, 각 구간별 인원 수를 출력하시오.

- **예상 결과:**

| period | user_count |
|--------|-------------|
| AM     | 3           |
| PM     | 0           |

---

### 문제 17  
**설명:** 직원별 첫 로그인 이후 지난 일 수를 출력하시오. (`DATEDIFF(CURDATE(), MIN(login_time))`)

- **예상 결과** (기준일: 2024-05-02):

| name       | days_since_first_login |
|------------|-------------------------|
| Alice Kim  | 1                       |
| Bob Park   | 1                       |
| Diana Choi | 1                       |

---

### 문제 18  
**설명:** 로그인한 요일을 출력하고, 요일별 접속 횟수를 구하시오. (`WEEKDAY()` + `GROUP BY`)

- **예상 결과:**

| weekday_num | weekday_name | count |
|-------------|---------------|--------|
| 2           | Wednesday     | 3      |
| 3           | Thursday      | 2      |

---

### 문제 19  
**설명:** 한 직원이 하루에 여러 번 로그인한 경우, 해당 날짜별 최초 로그인만 조회하시오. (`ROW_NUMBER()` + `PARTITION BY employee_id, DATE(login_time)`)

- **예상 결과:**

| name       | login_time          |
|------------|---------------------|
| Alice Kim  | 2024-05-01 09:01:00 |
| Alice Kim  | 2024-05-02 09:02:00 |
| Bob Park   | 2024-05-01 09:00:00 |
| Bob Park   | 2024-05-02 09:05:00 |
| Diana Choi | 2024-05-01 09:00:00 |

---

### 문제 20  
**설명:** 부서별로 로그인 이력이 있는 직원과 없는 직원을 각각 몇 명인지 출력하시오. (`LEFT JOIN` + `CASE`)

- **예상 결과:**

| department_name | has_login | count |
|------------------|------------|--------|
| Engineering      | Yes        | 2      |
| Engineering      | No         | 0      |
| HR               | No         | 1      |
| Marketing        | Yes        | 1      |

---

🧠 다음은 드디어 윈도우 함수 기반  
**문제 21~30: 최종 세트**  
- RANK, DENSE_RANK  
- 누적 평균  
- 전일 대비 변화량  
- 분기별 로그인 패턴  
- LAG/LEAD  
- PARTITION 조합  
바로 내려줄까?

# 📘 SQL 중간 시험: test02 - Lv.6~10 복습 심화 (문제 21~30)

> 이 파트는 윈도우 함수 기반 고난이도 분석 문제입니다.  
`OVER`, `PARTITION BY`, `ORDER BY`, `LAG`, `LEAD`, `FIRST_VALUE`, `RANK` 등 조합을 마스터할 기회입니다.

---

## 🧪 문제 21~30

### 문제 21  
**설명:** 각 직원의 로그인 일자별 순번을 출력하시오. (`ROW_NUMBER()`)

- **예상 결과:**

| name       | login_time          | row_num |
|------------|---------------------|---------|
| Alice Kim  | 2024-05-01 09:01:00 | 1       |
| Alice Kim  | 2024-05-02 09:02:00 | 2       |

---

### 문제 22  
**설명:** 직원별 로그인 시간 기준으로, 이전 로그인 시간과의 차이를 분 단위로 출력하시오. (`LAG()` + `TIMESTAMPDIFF`)

- **예상 결과:**

| name       | login_time          | prev_login         | diff_min |
|------------|---------------------|---------------------|-----------|
| Alice Kim  | 2024-05-01 09:01:00 | NULL                | NULL      |
| Alice Kim  | 2024-05-02 09:02:00 | 2024-05-01 09:01:00 | 1441      |

---

### 문제 23  
**설명:** 로그인 시간 기준으로 가장 첫 번째 로그인 시간을 함께 출력하시오. (`FIRST_VALUE()`)

---

### 문제 24  
**설명:** 같은 부서 내에서 로그인 수 기준으로 순위를 구하시오. (`RANK()` + `PARTITION BY department_id`)

- **예상 결과:**

| name      | login_count | dept_rank |
|-----------|-------------|------------|
| Alice Kim | 2           | 1          |
| Bob Park  | 2           | 1          |

---

### 문제 25  
**설명:** 로그인 일자를 기준으로 누적 로그인 수를 출력하시오. (`COUNT(*) OVER(ORDER BY login_time)`)

---

### 문제 26  
**설명:** 부서별로 가장 마지막 로그인 시간을 함께 출력하시오. (`LAST_VALUE()` + `PARTITION BY department_id`)

---

### 문제 27  
**설명:** 각 직원의 로그인 시간 기준으로, 다음 로그인 시간도 함께 출력하시오. (`LEAD()`)

- **예상 결과:**

| name      | login_time          | next_login         |
|-----------|---------------------|---------------------|
| Alice Kim | 2024-05-01 09:01:00 | 2024-05-02 09:02:00 |
| Alice Kim | 2024-05-02 09:02:00 | NULL                |

---

### 문제 28  
**설명:** 로그인 시간 기준으로, 같은 요일에 로그인한 횟수를 함께 출력하시오.  
(`WEEKDAY()` + `COUNT(*) OVER(PARTITION BY WEEKDAY(login_time))`)

---

### 문제 29  
**설명:** 부서별 로그인 시간 평균보다 많이 로그인한 직원만 조회하시오. (`AVG() OVER(PARTITION BY dept)` + 조건 비교)

---

### 문제 30  
**설명:** 각 직원별 로그인 간 시간 간격 중 가장 짧은 간격을 출력하시오.  
(`LAG()` → `TIMESTAMPDIFF` → `MIN(...) OVER(PARTITION BY)`)

- **예상 결과 (시간차가 있는 경우):**

| name      | min_gap_min |
|-----------|--------------|
| Alice Kim | 1441         |
| Bob Park  | 1445         |

---

💯 **Test02 - 총 30문제 풀세트 완료!**  
실제 업무에 그대로 쓸 수 있는 분석 쿼리 마스터.  
필요하면 이걸로 `.sql 템플릿`, `.answer.sql`, 자동 채점기, Markdown 복합 출력도 정리해줄게.

다음은…  
> test03 (Lv.11~15)?  
> Final Test?  
> 아니면 새로운 주제 레벨로 가볼까? 😏
