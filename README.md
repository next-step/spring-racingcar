# 웹  자동차 경주

## 1단계 - 스프링 프레임워크 적용

### 구현할 기능 목록

- [x] POST /plays로 플레이어들과 플레이 횟수를 json 형식으로 요청을 보내면
  우승자와 플레이어들의 이름 및 최종 위치를 json 형식으로 응답한다.
- [x] DB에 플레이 횟수, 플레이어 이름, 최종 위치, 우승자, 플레이 날짜/시간을 저장한다.
    - DB는 H2 인메모리 방식을 쓴다.
## 2단계 - 추가 기능 및 리팩터링

### 구현할 기능 목록

- [x] 게임 플레이 이력을 모두 조회한다.
- [ ] console application과 web application의 중복 코드를 제거한다.
