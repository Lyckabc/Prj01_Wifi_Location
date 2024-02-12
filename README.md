# README.md
https://busy-apple-85c.notion.site/README-md-57ad70f041e247709e3b2b18659c5fce?pvs=4
# 📝 내 위치 기반 공공 와이파이 정보를 제공하는 웹서비스 개발

## 🔮 과제 시나리오

- 수행 목적
    
    <aside>
    💡 주어진 과제를 통하여 학습한 자바와 웹 그리고 데이터베이스의 개념과 이를 통한
    응용을 통하여 스킬 업을 하고자 함. 또한, 기본 데이터베이스 개념을 기반으로 사용해 보지
    않은 데이터베이스(SQLite)에 대해서 수행할 수 있는 기능을 배양하고자 함.
    타 시스템 호출(Open API)를 호출하는 문제 해결 능력을 키우고자 함.
    
    </aside>
    
- 간략 소개
    
    <aside>
    💡 공공 데이터 자원을 끌어와서 내가 서비스 하고자 하는 데이터로
    마이그레이션하여 웹을 기반으로 하여 원하는 형태로 출력하고자 함. 또한, 사용한 히스토리에
    대해서 데이터베이스에 저장하는 기능과 이를 출력하는 기능을 구현하고자 함.
    
    </aside>
    

[[제로베이스]백엔드스쿨_미션1_마스터반_ver02.pptx.pdf](README%20md%2057ad70f041e247709e3b2b18659c5fce/%25EC%25A0%259C%25EB%25A1%259C%25EB%25B2%25A0%25EC%259D%25B4%25EC%258A%25A4%25EB%25B0%25B1%25EC%2597%2594%25EB%2593%259C%25EC%258A%25A4%25EC%25BF%25A8_%25EB%25AF%25B8%25EC%2585%25981_%25EB%25A7%2588%25EC%258A%25A4%25ED%2584%25B0%25EB%25B0%2598_ver02.pptx.pdf)

## ⭐ 필수 준수사항

- 1. 과제를 분석하여 ERD를 통한 데이터 설계를 작성하여 제출해 주세요.
    - [x]  ERD image 추출
    - [x]  ERD table (sql insert문) 추출
- 2. 공공와이파이 정보는 서울시 오픈 API를 활용해 주세요.
    - [x]  **[서울시 공공와이파이 서비스 위치 정보](https://data.seoul.go.kr/dataList/OA-20883/S/1/datasetView.do)**
    - 서울시 공공와이파이 서비스 위치 정보
        
        <aside>
        💡 공공 API를 이용하기 위한 학습 절차에
        대해서는 사이트에서 이용가이드를 참조
        하시거나 인터넷 검색을 통해서 스스로
        학습해 주세요.
        (실제로 업무에서도 동일하게 진행하신
        다고 생각하시면 됩니다.)
        
        </aside>
        
- 3. JAVA기반의 다이나믹 웹서비스를 이용해서 구현해 주세요.(JSP)
    - [x]  JSP 구현
    - [x]  Servlet 구현
- 4. 기능사항
    - [x]  공공 와이파이 정보 가져오기 기능 구현
    - [x]  내 위치 정보를 입력하면 가까운 위치에 있는 와이파이 정보 20개 보여주는 기능 구현
    - [x]  내가 입력한 위치정보에 대해서 조회하는 시점에 DB에 히스토리를 저장 및 보여주는 기능 구현
    - [x]  데이터베이스는 SQLite 이용(SQLite를 이용하기가 힘들면, MySQL(or *****MariaDB*****) 이용)
- 5. 프로젝트 생성 방법
    - Eclipse 이용시
        
        Dynamic Web Project 이용
        
        - Dynamic web module version : 3.1 (Tomcat v8.5 설치 후 선택)
        - JDK 1.8
        - 이후, Configure 🡪 Convert To Maven Project 를 이용해서 라이브러리 관리를 편하게 이용(필수 사항 아님)
        - 인텔리제이 이용시(Ultimate Edition 기준)
        - New
    - 인텔리제이 이용시(Ultimate Edition 기준)
        - [x]  New Project 🡪 Java Enterprise
        - [x]  Template: Web application
        - [x]  Application server: Tomcat 8.5(설치 후 선택)
        - [x]  Language: Java
        - [x]  Build system: Maven(or ***Gradle***)
        - [x]  JDK: JDK 1.8
        - [x]  이후 자동으로 Maven(or ***Gradle***) 빌드 형태로 프로젝트 생성 됨.
- *필수아님*6.사용라이브러리
    - dependency “build.gradle”
        
        ```jsx
        plugins {
            id 'java'
            id 'war'
        }
        
        group 'com.example'
        version '1.0-SNAPSHOT'
        
        repositories {
            mavenCentral()
        }
        
        ext {
            junitVersion = '5.8.2'
        }
        
        sourceCompatibility = '1.8'
        targetCompatibility = '1.8'
        
        tasks.withType(JavaCompile) {
            options.encoding = 'UTF-8'
        }
        
        dependencies {
            // 버전다운
            // Servlet API 버전 3.1.0으로 변경
            providedCompile group: 'javax.servlet', name: 'javax.servlet-api', version: '3.1.0'
        
            implementation group: 'org.apache.tomcat', name: 'tomcat-servlet-api', version: '9.0.65'
            implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.30'
        
            implementation group: 'org.mariadb.jdbc', name: 'mariadb-java-client', version: '3.0.7'
            implementation 'com.squareup.okhttp3:okhttp:4.9.3'
        
            implementation group: 'com.google.code.gson', name: 'gson', version: '2.9.0'
            implementation group: 'javax.servlet', name: 'jstl', version: '1.2'
            compileOnly 'org.projectlombok:lombok:1.18.24'
            annotationProcessor('org.projectlombok:lombok:1.18.24')
        
            testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
            testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
            implementation 'javax.xml.ws:jaxws-api:2.3.1'
            //
        
        }
        
        test {
            useJUnitPlatform()
        }
        ```
        
    - [x]  httpClient 처리를 위해서 okhttp3 이용
    - [x]  Json 처리를 위해서 gson 이용
    - [x]  Getter/setter 처리를 위해서 lombok 이용
    - [ ]  Sqlite 연결을 위해서 sqlite-jdbc 이용 → MariaDB사용
- ⭐ 기본 기능 구현 여부 (전부 구현 완료)
    - 1.처음 실행 화면
        
        ![처음실행화면 1.png](README%20md%2057ad70f041e247709e3b2b18659c5fce/%25EC%25B2%2598%25EC%259D%258C%25EC%258B%25A4%25ED%2596%2589%25ED%2599%2594%25EB%25A9%25B4_1.png)
        
    - 2.Open API 와이파이 정보 가져오기 실행 화면
        
        ![처음실행화면 2(예시).png](README%20md%2057ad70f041e247709e3b2b18659c5fce/%25EC%25B2%2598%25EC%259D%258C%25EC%258B%25A4%25ED%2596%2589%25ED%2599%2594%25EB%25A9%25B4_2(%25EC%2598%2588%25EC%258B%259C).png)
        
        ![처음실행화면 2(신동호).png](README%20md%2057ad70f041e247709e3b2b18659c5fce/%25EC%25B2%2598%25EC%259D%258C%25EC%258B%25A4%25ED%2596%2589%25ED%2599%2594%25EB%25A9%25B4_2(%25EC%258B%25A0%25EB%258F%2599%25ED%2598%25B8).png)
        
    - 3.내 위치 가져오기(Javascript)를 한 후에 근처 WIPI 정보 보기 기능을 실행한 화면
        
        ![처음실행화면 3(예시).png](README%20md%2057ad70f041e247709e3b2b18659c5fce/%25EC%25B2%2598%25EC%259D%258C%25EC%258B%25A4%25ED%2596%2589%25ED%2599%2594%25EB%25A9%25B4_3(%25EC%2598%2588%25EC%258B%259C).png)
        
        ![처음실행화면 3(신동호).png](README%20md%2057ad70f041e247709e3b2b18659c5fce/%25EC%25B2%2598%25EC%259D%258C%25EC%258B%25A4%25ED%2596%2589%25ED%2599%2594%25EB%25A9%25B4_3(%25EC%258B%25A0%25EB%258F%2599%25ED%2598%25B8).png)
        
    - 4.위치 히스토리 목록 기능 화면
        
        ![처음실행화면 4(예시).png](README%20md%2057ad70f041e247709e3b2b18659c5fce/%25EC%25B2%2598%25EC%259D%258C%25EC%258B%25A4%25ED%2596%2589%25ED%2599%2594%25EB%25A9%25B4_4(%25EC%2598%2588%25EC%258B%259C).png)
        
        ![처음실행화면 4(신동호).png](README%20md%2057ad70f041e247709e3b2b18659c5fce/%25EC%25B2%2598%25EC%259D%258C%25EC%258B%25A4%25ED%2596%2589%25ED%2599%2594%25EB%25A9%25B4_4(%25EC%258B%25A0%25EB%258F%2599%25ED%2598%25B8).png)
        
    
- ⭐ 추가 기능 구현 여부 (북마크) X
    
    [[제로베이스]백엔드스쿨_미션1_마스터반_ver02.pptx.pdf](README%20md%2057ad70f041e247709e3b2b18659c5fce.md) 참고
    
    - 와이파이 정보 북마크 추가 기능 개발
        - 수행목적
            
            <aside>
            💡 와이파이 정보에 대한 북마크 추가 기능과 이를 담을 수 있는 북마크 그룹 관리를
            통해서 데이터베이스의 CRUD 에 대한 기능 개발과 테이블 조인 기능에 대한 학습을 통해서
            문제 해결 능력을 고도화 하고자 함.
            
            </aside>
            
        1. 와이파이 상세 정보 보기 기능 구현
        2. 북마크 그룹 목록/추가/수정/삭제 기능 구현
        3. 와이파이 북마크에 추가 및 삭제 기능 구현

## 🗓 프로젝트 기간 : 2024.01.22 ~ 2022.02.12

## 📚 사용 기술

- 개발 언어: Java 8, HTML 5, CSS, JavaScript
- DataBase: MariaDB 10.6
- API
    - [서울공공데이터(API)](https://data.seoul.go.kr/dataList/OA-20883/S/1/datasetView.do)
- Front
    - Bootstrap 5.1.3 , jquery/3.6.0
- Back , Gradle
    - Java 8 , Mariadb-java-client 3.0.7 , gson 2.9.0 , jstl 1.2
    - lombok:1.18.24 , tomcat-servlet-api 9.0.65 , okhttp:4.9.3
- 개발 환경
    - intelliJ IDEA, Gradle, Lombok , JDBC
    
    ## 💡 ERD
    
    ![https://user-images.githubusercontent.com/43702182/189212938-b87173c1-47ae-411c-916f-5431af631439.svg](https://user-images.githubusercontent.com/43702182/189212938-b87173c1-47ae-411c-916f-5431af631439.svg)
    
    ![https://user-images.githubusercontent.com/43702182/189212944-9a709fc5-ea05-45be-ae06-3137e0188ed4.svg](https://user-images.githubusercontent.com/43702182/189212944-9a709fc5-ea05-45be-ae06-3137e0188ed4.svg)
    
    ![https://user-images.githubusercontent.com/43702182/189213021-b3b90493-4446-4c30-bd5e-4196734279f7.svg](https://user-images.githubusercontent.com/43702182/189213021-b3b90493-4446-4c30-bd5e-4196734279f7.svg)
    
    ![https://user-images.githubusercontent.com/43702182/189214946-83b90ebf-8c03-4132-b40a-716e9808fb87.svg](https://user-images.githubusercontent.com/43702182/189214946-83b90ebf-8c03-4132-b40a-716e9808fb87.svg)
    
    ![https://user-images.githubusercontent.com/43702182/189214957-054a8515-4eae-4656-9662-54f99f100f30.svg](https://user-images.githubusercontent.com/43702182/189214957-054a8515-4eae-4656-9662-54f99f100f30.svg)
    
    ![https://user-images.githubusercontent.com/43702182/189214960-8813240f-49d6-4c76-888e-666d1b8286d2.svg](https://user-images.githubusercontent.com/43702182/189214960-8813240f-49d6-4c76-888e-666d1b8286d2.svg)
    
    ![https://user-images.githubusercontent.com/43702182/189214959-a83e2a19-d446-4116-b982-d2b17f22d806.svg](https://user-images.githubusercontent.com/43702182/189214959-a83e2a19-d446-4116-b982-d2b17f22d806.svg)
    
    ![https://user-images.githubusercontent.com/43702182/189214954-fc2e7e07-a129-4f71-aeb8-4d4bd90a01f7.svg](https://user-images.githubusercontent.com/43702182/189214954-fc2e7e07-a129-4f71-aeb8-4d4bd90a01f7.svg)
    
    ![https://user-images.githubusercontent.com/43702182/190194765-941ef188-383c-441c-bad9-716d63f64f9a.svg](https://user-images.githubusercontent.com/43702182/190194765-941ef188-383c-441c-bad9-716d63f64f9a.svg)
    
    ![https://user-images.githubusercontent.com/43702182/189214961-5ae8258f-5df1-45b5-9eee-b9eaee2f034f.svg](https://user-images.githubusercontent.com/43702182/189214961-5ae8258f-5df1-45b5-9eee-b9eaee2f034f.svg)
    

## 🎞프로젝트 Setting 및 Refer

- [Prj01_Wifi_Location](%5B%E1%84%80%E1%85%AD%E1%84%8B%E1%85%B2%E1%86%A8%5D%E1%84%80%E1%85%AD%E1%84%8B%E1%85%B2%E1%86%A8%2055896f50da264086af4ca3b98496628c/00%5B%E1%84%80%E1%85%AD%E1%84%8B%E1%85%B2%E1%86%A8%5D%20ZeroBase%20dda34feeca184d889c94a14eb1c3eafd/%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%20929f7e58b3e0467c8d386df5022f2c58/%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%E1%84%83%E1%85%B3%E1%86%AF%20ebad8ed6d6104bbdbe01df438c0d23d4/Prj01_Wifi_Location%20f5db230cd0de4b25af43c62b91a9440e.md)
