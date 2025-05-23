# 📘 SQL 중간 시험: test01 - 레벨 1~5 심화 통합 (문제 1~10)

이 시험은 SELECT, WHERE, JOIN, GROUP BY까지의 내용을 바탕으로  
복합 조건, 다중 테이블, 집계와 조건의 조합 등 실무형 SQL을 점검하는 데 목적이 있습니다.

---

## 🧱 테이블 구조 및 초기 데이터

### `init.sql`

```sql
CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    grade INT,
    class CHAR(1)
);

CREATE TABLE subjects (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE scores (
    student_id INT,
    subject_id INT,
    score INT
);

INSERT INTO students VALUES
(1, 'Alice', 1, 'A'),
(2, 'Bob', 1, 'A'),
(3, 'Charlie', 1, 'B'),
(4, 'Diana', 2, 'A'),
(5, 'Ethan', 2, 'B');

INSERT INTO subjects VALUES
(1, 'Math'), (2, 'English'), (3, 'Science');

INSERT INTO scores VALUES
(1,1,90), (1,2,95), (1,3,100),
(2,1,70), (2,2,80), (2,3,85),
(3,1,60), (3,2,75), (3,3,65),
(4,1,90), (4,2,85), (4,3,80),
(5,1,100), (5,2,95), (5,3,90);
```

---

## 🧪 문제 1~10

### 문제 1  
**설명:** 모든 학생의 평균 점수를 조회하되, **과목이 2개 이상 등록된 경우만** 조회하시오.

- **예상 결과:**

| student_name | avg_score |
|--------------|-----------|
| Alice        | 95.0      |
| Bob          | 78.3      |
| ...          | ...       |

---

### 문제 2  
**설명:** 과목별로 최고 점수를 받은 학생의 이름을 조회하시오. (`JOIN`, `GROUP BY`, `HAVING` 조합)

- **예상 결과:**

| subject_name | top_scorer |
|--------------|------------|
| Math         | Ethan      |
| English      | Alice      |
| Science      | Alice      |

---

### 문제 3  
**설명:** 같은 반(class)에서 가장 평균 점수가 높은 학생만 조회하시오.  
(같은 반 학생들 중 최고 평균인 사람)

- **예상 결과:**

| class | name    | avg_score |
|-------|---------|-----------|
| A     | Alice   | 95.0      |
| B     | Ethan   | 95.0      |

---

### 문제 4  
**설명:** 평균 점수가 전체 평균 이상인 학생만 조회하시오.

- **예상 결과:**

| name    | avg_score |
|---------|-----------|
| Alice   | 95.0      |
| Diana   | 85.0      |
| Ethan   | 95.0      |

---

### 문제 5  
**설명:** 수학 점수가 영어 점수보다 높은 학생만 조회하시오.

- **예상 결과:**

| name    | math | english |
|---------|------|---------|
| Ethan   | 100  | 95      |
| Diana   | 90   | 85      |

---

### 문제 6  
**설명:** 과목별 평균 점수와 편차(최고-최저)를 함께 조회하시오.

- **예상 결과:**

| subject | avg_score | diff |
|---------|-----------|------|
| Math    | 82.0      | 40   |
| English | 86.0      | 20   |

---

### 문제 7  
**설명:** 수학 점수 90점 이상인 학생만 조회하되, 수학 점수, 영어 점수, 과학 점수 모두 함께 보여줄 것.

- **예상 결과:**

| name    | math | english | science |
|---------|------|---------|---------|
| Alice   | 90   | 95      | 100     |
| Ethan   | 100  | 95      | 90      |

---

### 문제 8  
**설명:** 모든 과목에서 80점 이상을 받은 학생만 조회하시오.

- **예상 결과:**

| name    |
|---------|
| Alice   |
| Ethan   |
| Diana   |

---

### 문제 9  
**설명:** 각 반(class)별로 평균 점수가 가장 낮은 학생을 조회하시오.  
(`GROUP BY` + `MIN()` + `JOIN` 조합)

---

### 문제 10  
**설명:** 과목별 평균이 85점 이상이고, **최소 한 명이라도 100점 이상을 받은 과목**만 조회하시오.

---

🧠 다음 문제 11~20에서는  
- 복수 조인  
- 집계 필터링  
- 서브쿼리와 JOIN 비교  
- 유니언 조건 시뮬레이션  
- GROUP BY + HAVING + RANK() 시뮬레이션  

까지 점점 조일게 😈  
지금 바로 이어서 줄까?

# 📘 SQL 중간 시험: test01 - 레벨 1~5 심화 통합 (문제 11~20)

---

## 🧪 문제 11~20

### 문제 11  
**설명:** 각 과목별로, 해당 과목을 수강한 학생 수가 3명 이상이고 평균 점수가 85점 이상인 과목만 조회하시오.

- **예상 결과:**

| subject_name |
|--------------|
| Math         |
| Science      |

---

### 문제 12  
**설명:** 세 과목 점수 중 가장 낮은 점수가 **80점 이상**인 학생만 조회하시오.  
(즉, 최저 점수가 80 이상)

- **예상 결과:**

| name    | min_score |
|---------|------------|
| Alice   | 90         |
| Ethan   | 90         |
| Diana   | 80         |

---

### 문제 13  
**설명:** 학생별로 **가장 점수가 낮았던 과목명과 점수**를 조회하시오.  
(`MIN()` + `JOIN` 조합)

- **예상 결과:**

| name    | subject  | min_score |
|---------|----------|-----------|
| Alice   | Math     | 90        |
| Bob     | Math     | 70        |
| Charlie | Math     | 60        |

---

### 문제 14  
**설명:** 과목별로 **100점을 받은 학생이 몇 명인지** 집계하시오.

- **예상 결과:**

| subject_name | perfect_count |
|--------------|----------------|
| Math         | 1              |
| Science      | 1              |

---

### 문제 15  
**설명:** 같은 학년(grade) 내에서 **과학 점수 1등**인 학생을 조회하시오.  
(`JOIN` + `MAX()` + `GROUP BY grade`)

---

### 문제 16  
**설명:** 두 과목에서 각각 90점 이상을 받은 학생만 조회하시오.  
(예: 수학 90 이상 + 과학 90 이상)

- **예상 결과:**

| name    | math | science |
|---------|------|---------|
| Alice   | 90   | 100     |
| Ethan   | 100  | 90      |

---

### 문제 17  
**설명:** 한 과목에서 60점 이하이고, 다른 과목에서 90점 이상인 학생을 모두 조회하시오.  
(AND 조건 시뮬레이션: EXISTS + EXISTS)

---

### 문제 18  
**설명:** 학생별로 과목 점수 총합 기준으로 **상위 50%** 학생만 조회하시오.

- **예상 결과:**

| name    | total_score |
|---------|--------------|
| Ethan   | 285          |
| Alice   | 285          |
| Diana   | 255          |

---

### 문제 19  
**설명:** 수학 점수 - 과학 점수 차이가 **가장 큰 학생 1명**만 조회하시오.

- **예상 결과:**

| name    | math | science | diff |
|---------|------|---------|------|
| Charlie | 60   | 65      | -5   |
| Bob     | 70   | 85      | -15  |

👉 그 중 절대값 기준 `Bob`

---

### 문제 20  
**설명:** 각 반(class)에서 평균 점수가 전체 반 평균보다 높은 반을 조회하시오.  
(`GROUP BY class` + 비교용 서브쿼리)

- **예상 결과:**

| class |
|--------|
| A      |
| B      |

---

💯 여기까지가 **test01 - 레벨 1~5 최종 중간 시험 20문제** 풀세트!  

