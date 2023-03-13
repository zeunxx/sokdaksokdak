# sokdaksokdak 속닥속닥

2022년 2학기 건국대학교 소프트웨어 아키텍처 - 기말 프로젝트

#### 개인적 회고록 - 노션


<br>

## 속닥속닥 

키워드 다이어리 어플리케이션 : 키워드를 지정하고 일기를 작성 및 열람



 <Br>
 
 ### 목적
 
 매일 새로운 키워드에 따라 일기를 작성하고 열람하는 App의 개발
 수업시간때 배운 객체 지향 개발과 디자인 패턴을 프로젝트에 적용해 재사용성과 확장 가능성이 있는 어플리케이션 개발
 
1. 디자인 패턴
  - View와 Model의 독립성 및 새로운 Data Source가 들어와도 일관성 유지
  - 새로운 기능/로그인 방식/테마가 추가되어도 기존 코드는 변경되지 않고 재사용 및 확장 가능
2. 키워드 랜덤 출력 / 일기 작성 (메인 기능) 
  - 사용자와 프로젝트 제작 목적과 요구사항에 맞는 올바른 인터렉션 수행
 
 <BR>
  
### 🔎 역할

|이름|역할|
|------|---|
|백혜선(팀장) - [sxunea](https://github.com/sxunea)|캘린더를 이용하여,원하는 날짜의 일기를 열람하는 기능 구현 (MVVM 패턴 적용)|
|박지은 - [zeunxx](https://github.com/zeunxx)|앱의 전체적인 UI/UX 디자인 구현 및 테마 변경 기능 구현 (Abstract Factory 패턴 적용)|
|김태혁 - [rlaxoqkf](https://github.com/rlaxoqkf)|여러 방식의 소셜 로그인, 구현 및 마이페이지에서 로그아웃/탈퇴 기능 구현 (Strategy 패턴 적용)|
|정경은 - [ro-el-c](https://github.com/ro-el-c)|DB 설계 및 키워드 추천 여부에 따른 일기 작성 기능 구현 (Singleton, Repository 패턴 적용)|

<br>


### 💻 웹 기술 스택

- Kotlin
- Room DB


<br>

### 아키텍처

#### 1. Usecase Diagram

<img width="400" alt="image" src="https://user-images.githubusercontent.com/81572478/224689886-c08a730f-97e0-41c6-8eed-44f602c6345b.png">

 #### 📌 품질 요구사항
 
1) 기능성: 사용자의 요구사항인 일기 작성 및 조회, 캘린더 조회, 테마 변경이 가능한가?
2) 신뢰성: 일기가 database에 오류 없이 저장되고 조회되는가?
3) 사용성: 사용자가 앱을 처음 실행할 때 설명이 없어도 사용할 수 있는가?
4) 확장성: 앱에 새로운 기능/로그인 방식/ 테마가 추가가 가능한가?

<br>

#### 2. Conceptual Diagram

<img width="439" alt="image" src="https://user-images.githubusercontent.com/81572478/224690047-9eaebd25-b7c5-4123-b359-da4fe7130a8c.png">

<Br>

#### 3. Collaboration Diagram 
- 디자인 패턴이 적용된 버전이므로 적용 전 버전은 하단의 ppt에서 확인 가능

![ClassDiagram1](https://user-images.githubusercontent.com/81572478/224690884-cc085353-c2e6-4292-9658-a22e3d2044cc.png)

- 연두색) MVVM 패턴 
  - risk : 프로젝트에 데이터를 활용하는 새로운 기능이 추가되거나 코드를 재사용 하고자 할 때, 모든 구현 사항을 View에 반영하면 재사용에 어려움이 있음
  - 전체 프로젝트에 MVVM 패턴, Repository 패턴 적용
  - API 등의 remote data source가 추가되어도 Repository가 데이터의 일관성 유지

- 하늘색) Singleton 패턴
  - risk : 데이터베이스 객체 생성의 비용이 크다는 문제 해결 및 인스턴스 다중 생성 방지
  - Database 사용에 적용 ➡️ Instance 중복 생성을 방지, 프로그램 전반에서 데이터의 일치성 보장

- 주황색) Abstract Factory Pattern 패턴
  - risk : 테마 변경 시, 관련된 모든 코드의 수정을 필요로 함
  - 테마 변경 구현에 absract factory 패턴 적용
  - 여러 테마 중 하나를 선택해 시스템을 설정하고, 기존에 설정한 테마를 다른 것으로 변경하고 싶을 때 사용
  - 새로운 테마를 추가할 때 테마와 관련된 부분을 한번에 생성하고, 기존 코드를 변경하지 않아도 됨
  
- 노란색) Strategy 패턴 
  - risk : 소셜 로그인 방식 추가시, 많은 코드의 수정을 필요로 함
  - 카카오나 구글로그인에 필요한 알고리즘을 따로 정의하여 사용함 
  - 추후에 다른 소셜로그인 방식을 추가하고자 할 때, 해당 소셜에 대한 알고리즘만 정의하면 사용가능
  - 확장성 및 변경 가능성 고려

<br><Br>

### ⭐ 최종 발표 ppt


- ppt

[최종발표용PDF.pdf](https://github.com/zeunxx/sokdaksokdak/files/10956790/PDF.pdf)

- 최종 보고서 

[최종_보고서_4팀_속닥속닥.docx](https://github.com/zeunxx/sokdaksokdak/files/10956991/_._4._.docx)


<Br>

### ➕ overview
<img width="144" alt="image" src="https://user-images.githubusercontent.com/81572478/224695154-0c64904a-1c90-4cd9-9bec-2c30e50d76bb.png"> |
<img width="144" alt="image" src="https://user-images.githubusercontent.com/81572478/224695359-a868e7fb-f254-4cb4-a658-76892f83c96d.png"> |
<img width="141" alt="image" src="https://user-images.githubusercontent.com/81572478/224695484-40744b64-02a9-4958-bb22-1a9a590d9344.png"> |
<img width="142" alt="image" src="https://user-images.githubusercontent.com/81572478/224695541-9d5f0a8e-271c-4ad7-a955-899b1064fa9e.png"> |

<img width="147" alt="image" src="https://user-images.githubusercontent.com/81572478/224696424-a179cf4a-1d93-45a3-8dcd-5e7295f9a497.png"> |
<img width="142" alt="image" src="https://user-images.githubusercontent.com/81572478/224695606-7df9a726-969a-4279-9673-3f1771a8c546.png"> |
<img width="143" alt="image" src="https://user-images.githubusercontent.com/81572478/224696263-08dc1a6f-2f50-4228-ae5e-14f3c6347be6.png"> |
<img width="142" alt="image" src="https://user-images.githubusercontent.com/81572478/224695684-9f321017-e7af-46ed-b89a-39dc098e7cdf.png"> |







<br>


