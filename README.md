## EternyBackend

---

## 📖 목차

1. [🔎 프로젝트 소개](#-프로젝트-소개)
2. [🎯 프로젝트 기간](#-프로젝트-기간)
3. [🧑‍💻 팀원 소개 및 역할 분담](#-팀원-소개-및-역할-분담)
4. [🚀 주요 기능](#-주요-기능)
5. [⚙️ 기술 스택](#-기술-스택)
6. [📃 API 설계서](#-api-설계서)
7. [🛠 ERD](#-erd)
8. [🛠 아키텍처](#-아키텍처)
9. [❓ 기술적 의사결정](#-기술적-의사결정)
10. [📈 성능 개선](#-성능-개선)
11. [🚨 트러블 슈팅](#-트러블-슈팅)

## 🔎 프로젝트 소개

-
## 🎯 프로젝트 기간
- 1차 MVP 개발 기간 : 2025.04.20 ~ 2025.00.00

<details>
  <summary>🎇 프로젝트 실행 방법</summary>

### 1️⃣ Git Clone
  ```bash
  git clone 
```

### 2️⃣ .env 파일 설정

```
# Docker
MYSQL_ROOT_PASSWORD={데이터베이스 비밀번호}
MYSQL_DATABASE={데이터베이스 이름}

# MySQL
DB_USERNAME={데이터베이스 username}
DB_PASSWORD={username의 비밀번호}

# JWT Secret Key
JWT_SECRET_KEY={JWT KEY값}

# Encryption Secret Key
ENCRYPTION_SECRET_KEY={암호화 KEY값}

# 스프링 데이터베이스 URL
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/{데이터베이스 이름}?useSSL=false&allowPublicKeyRetrieval=true
```

### 3️⃣ Docker 이미지 빌드
``` bash
docker buildx build --platform linux/amd64 -f eureka-server/Dockerfile -t eureka-server:latest . --load
```

### 4️⃣ Docker Compose로 컨테이너 실행
```bash
docker-compose up --build -d
```    
</details>

## 🧑‍💻 팀원 소개 및 역할 분담

|                      **김민주**                      |                     **조시훈**                     | 
|:-------------------------------------------------:|:-----------------------------------------------:|
| <img src="" height=150 width=150> <br/> **백엔드**   | <img src="" height=150 width=150> <br/> **백엔드** | 


## 🚀 주요 기능
### 📌 주 기능 1
- 부 기능 1
- 부 기능 2
- 부 기능 3
### 📌 주 기능 2
- 부 기능 1
- 부 기능 2
- 부 기능 3
### 📌 주 기능 3
- 부 기능 1
- 부 기능 2
- 부 기능 3

## ⚙️ 기술 스택
- OS : Windows11, Ubuntu 24.04 LTS
- Backend : Java 17, Spring Boot 3.4.0
- Database : MySQL, Redis, Spring Data JPA
- Security : Spring Security, OAuth2.0, JWT
- Test Tool : Postman
- DevOps : Docker, Git
- ETC : InteliJ

## 📃 API 설계서
-
## 🛠 ERD
-
## 🛠 아키텍처
-

## ❓ 기술적 의사결정

<details>
<summary> 1️⃣ </summary>

</details>

<details>
  <summary> 2️⃣ </summary>

</details>

<details>

  <summary> 3️⃣ </summary>

</details>

<details>
  <summary> 4️⃣ </summary>

</details>


<details>

  <summary> 5️⃣ </summary>

</details>

<details>
  <summary> 6️⃣ </summary>

</details>

<details>
    <summary> 7️⃣ </summary>

</details>

<details>
      <summary> 8️⃣ </summary>

</details>

## 📈 성능 개선

## 🚨 트러블 슈팅