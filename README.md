# jwp-racingcar

# Step1 : Spring Framework applied

## 1. Create Database 

- PlayResult : 경기 결과 테이블
- Person : 자동차에 부여된 사람 테이블
- RacingCar : 경기 기록 테이블

## 2. Model 

- PlayResult : 경기 결과 객체

```java
public class PlayResult {

    private int id;
    private int trialCount;
    private String winners;
    private String createdAt;
    // ...getter & setter
}
```

- Person : 자동차에 부여된 사람 객체

```JAVA
public class Person {

    private int id;
    private String name;
    private String createdAt;
    // ...getter & setter
}
```

- RacingCar : 경기 기록 객체

```java
public class RacingCar {

    private int id;
    private int groupId;
    private int personId;
    private String name;
    private int position;
    private String createdAt;
    // ...getter & setter
}
```

- PlayResultIn : 경기 진행 파라미터 객체

```java
public class PlayResultIn {
  private String names;
  private int count;
  // ... getter & setter
}
```

- PlayResultOut : 경기 결과 리턴 데이터 객체

```java
public class PlayResultOut {
  private String winners;
  private List<RacingCar> racingCars;
  // ... getter & setter
}
```

## 3. DAO

- PersonDAO
  - [x] findAllPerson : 전체 사람 데이터
  - [x] findPersonByName :  이름으로 사람 데이터 찾기
  - [x] insertPerson : 사람 데이터 삽입
- PlayResultDAO
  - [x] findAllPlayResult : 전체 결과 데이터
  - [x] findPlayResultById : 아이디로 결과 데이터 찾기
  - [x] findPlayResultByGroupId : 그룹 아이디로 결과 데이터 찾기
  - [x] insertPlayResult : 결과 데이터 삽입
  - [x] updatePlayResult : 결과 데이터 업데이트
- RacingDAO
  - [x] findAllRacingCar : 전체 경기 기록 데이터
  - [x] findRacingCarById : 아이디로 경기 기록 찾기
  - [x] findRacingCarByGroupId : 그룹 아이디로 경기 기록 찾기
  - [x] insertRacingCar : 그룹 아이디로 경기 기록 찾기
  - [x] updateRacingCar : 경기 기록 업데이트
  - [x] updatePosition : 위치 업데이트
  - [x] getGroupId : 그룹 아이디 생성 또는 찾기
  - [x] getWinner : 승자 가져오기

## 4. Service

- RacingService
  - playList : 경기 기록 가져오기
  - racing : 경기 시작 및 기록하기
  - playRound : 실제 1경기 진행하기

## 5. Controller

- RacingController
  - plays : 경기 시작
  - playList : 경기 이력


