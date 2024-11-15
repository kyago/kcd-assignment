# KCD Assignment

이 프로젝트는 Spring Boot를 사용하여 POS 상품 관리, 주문 관리를 위한 RESTful API를 구현한 것입니다.

## 기술 스택
- Java 17
- Spring Boot 3.3.5
- Spring Data JPA
- PostgreSQL
- Springdoc OpenAPI

## 패키지 구조

```
com.kcd.assignment
├── AssignmentApplication.java
├── common                       - 모듈간 공통으로 사용되는 클래스들이 위치합니다.
│   ├── config 
│   └── utils
├── order                        - 주문 관련 클래스들이 위치합니다.    
│   ├── controller
│   ├── dto                      - 데이터 전송 객체 클래스들이 위치합니다.
│   ├── entity                   - 주문 관련 엔티티 클래스들이 위치합니다.
│   ├── exception                - 주문 관련 예외 클래스들 및 에러 처리 핸들러가 위치합니다.
│   ├── model                    - 주문 관련 모델 클래스들이 위치합니다.
│   ├── repository               - 주문 관련 데이터베이스 접근을 위한 리포지토리 인터페이스들이 위치합니다.
│   └── service                  - 주문 관련 비즈니스 로직을 처리하는 서비스 클래스들이 위치합니다.
└── product
    ├── controller
    ├── dto
    ├── entity
    ├── model
    ├── repository
    └── service
```

## 데이터베이스 스키마

프로젝트의 데이터베이스 스키마는 `src/main/resources/schema.sql` 파일에 정의되어 있습니다. 이 파일에는 테이블 생성 및 관계 설정에 대한 SQL 스크립트가 포함되어 있습니다.
