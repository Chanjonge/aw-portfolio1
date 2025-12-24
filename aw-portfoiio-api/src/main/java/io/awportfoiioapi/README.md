# 멀티 스텝 폼 시스템

단계별 양식 작성 및 관리 시스템입니다. 구글 폼과 유사한 인터페이스로 다단계 양식을 만들고 제출받을 수 있습니다.

## 주요 기능

-   ✅ **단계별 양식**: 5단계 이상의 다단계 양식 지원
-   ✅ **유효성 검증**: 각 질문별 최소 글자 수 요구사항 설정
-   ✅ **관리자 시스템**: 최고 관리자와 일반 관리자 권한 분리
-   ✅ **질문 관리**: 최고 관리자가 질문을 자유롭게 추가/수정/삭제
-   ✅ **제출 내역 조회**: 제출된 양식을 관리자 페이지에서 확인
-   ✅ **깔끔한 디자인**: 블랙 앤 화이트 테마

## 기술 스택

-   **Frontend**: Next.js 14 (App Router), React, TypeScript
-   **Styling**: Tailwind CSS
-   **Backend**: Spring Boot, SpringSecurity, JPA, QueryDSL
-   **Database**: MySQL JPA 기반 ORM 구조
-   **Authentication / Authorization**: JWT 기반 인증, Role 기반 권한 제어
-   **File Storage**: AWS S3 (파일 관리)
-   **CI / CD**: GitHub Actions 기반 자동 빌드 및 배포
-   **Infra**: Aws EC2 , Aws ELB , Aws RDS , Aws S3

## 백엔드 설정

1. JDK 17설정
2. 환경변수 값 세팅
3. @SpringBootApplication 있는곳에서 실행


## 기본 계정 정보

초기 설정 후 다음 계정으로 로그인할 수 있습니다:

-   **이메일**: admin@example.com
-   **비밀번호**: admin123
-   **역할**: 최고 관리자

⚠️ **프로덕션 환경에서는 반드시 비밀번호를 변경하세요!**

## 사용 방법

### 최고 관리자

1. `/admin/login`에서 로그인
2. 최고 관리자 페이지에서:
    - 새로운 관리자 계정 생성
    - 질문 추가/수정/삭제
    - 단계별 질문 구성

### 일반 관리자

1. 최고 관리자가 생성한 계정으로 로그인
2. 대시보드에서 제출된 양식 확인

### 일반 사용자

1. 메인 페이지에서 "양식 작성하기" 클릭
2. 단계별로 질문에 답변 (최소 글자 수 충족 필요)
3. 마지막 단계에서 제출

## 프로젝트 구조

```
awportfolioapi
├─ advice/                # 전역 예외 처리, AOP
│
├─ apiresponse/           # 공통 API 응답 객체 (ApiResponse 등)
│
├─ category/              # 카테고리 도메인
│
├─ config/                # 설정 클래스 (QueryDSL, Security, CORS 등)
│
├─ file/                  # 파일 업로드/관리 (S3, CommonFile)
│
├─ hierarchy/             # 계층 구조 관련 도메인
│
├─ log/                   # 로깅 관련
│
├─ mapperd/               # Mapper (DTO ↔ Entity 변환)
│
├─ member/                # 회원 도메인
│
├─ memberrole/            # 회원-권한 매핑
│
├─ options/               # 옵션, 코드성 데이터
│
├─ portfolio/             # 포트폴리오 도메인
│
├─ question/              # 질문(문항) 도메인
│
├─ refresh/               # RefreshToken 관리
│
├─ resources/             # 리소스(메뉴/권한 대상)
│
├─ resourcesrole/         # 리소스-권한 매핑
│
├─ role/                  # 권한(Role)
│
├─ rsa/                   # RSA 암호화 관련
│
├─ security/              # Spring Security (Filter, Provider 등)
│
├─ submission/            # 제출(Submission) 엔티티 단위
│
├─ submissions/           # 제출 조회/관리 (복수/리스트 성격)
│
├─ userlist/              # 사용자 리스트 조회용
│
├─ users/                 # 사용자 관리
│
├─ utils/                 # 공통 유틸
│
└─ AwPortfolioApiApplication

```

## 데이터베이스 스키마

### CATEGORY
- 카테고리 정보 관리

### COMMON_FILE

- 공통 파일 정보 (업로드된 파일 메타데이터, 경로 등)

### MEMBER

- 회원 정보 (로그인 정보, 기본 회원 데이터)

### MEMBER_ROLE
- 회원과 권한 간 매핑 정보

### OPTIONS

- 질문 또는 폼에서 사용하는 선택 옵션 정보

### PORTFOLIO

- 포트폴리오 정보 (양식 묶음, 제목, 설명 등)

### QUESTION

- 질문 정보 (단계, 제목, 설명, 최소 글자 수, 순서, 필수 여부)

### REFRESH_TOKEN

- 리프레시 토큰 정보 (인증 유지용 토큰)

### ROLE

- 권한 정보 (역할 정의)

### ROLE_HIERARCHY

- 권한 계층 구조 정보

### RSA

- RSA 키 정보 (암호화 / 복호화 용도)

### SUBMISSION

- 제출 정보 (사용자가 작성한 양식 데이터)

### URL_RESOURCES

- URL 리소스 정보 (보안 대상 URL)

### URL_RESOURCES_ROLE

- URL 리소스와 권한 간 매핑 정보


## 배포

- GitHub Repository에 환경 변수(시크릿 값)를 등록하여 민감한 정보를 안전하게 관리합니다.
  - 예: DB 접속 정보, JWT 시크릿 키, AWS/S3 자격 증명 등
- GitHub Actions를 사용하여 CI/CD 파이프라인을 구성하였습니다.
- main 브랜치에 코드가 push 되면 자동으로 빌드 및 배포가 수행됩니다.
- 백엔드(Spring Boot)는 서버 환경에서 자동으로 재시작되며,
  프론트엔드(Next.js)는 빌드 후 서비스에 즉시 반영됩니다.
- 이를 통해 별도의 수동 배포 없이 코드 변경 사항이 자동으로 운영 환경에 반영됩니다.