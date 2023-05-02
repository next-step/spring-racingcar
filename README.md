# jwp-racingcar
[학습테스트로 배우는 Spring](https://edu.nextstep.camp/c/X1pbG30l)

---



# 🚀 1단계 - 스프링 프레임워크 적용

## 요구사항
### 기능 요구사항 
> - [X] 웹 요청 / 응답 구현하기
>   - [X] 웹을 통해 게임을 플레이 할 수 있도록 구현
>   - [X] 사용자는 자동차 경주 게임을 플레이하기 위해 요청을 보낼 수 있어야 한다
> 
> - [X] DB 연동하기 
>    - [X] H2 DB를 연동하기
>    - [X] 자동차 경주 게임의 플레이 이력을 DB에 저장하기
>       - [X] DB에 저장되는 정보 
>           - [X] 플레이 횟수(trialCount)
>           - [X] 플레이어 별 최종 이동 거리 (이름(name), 최종 위치(position))
>           - [X] 우승자(winners)
>           - [X] 플레이한 날짜/시간



---


# 🚀 2단계 - 추가 기능 및 리팩터링 

## 요구사항
### 기능 요구사항
> - [ ] 게임 플레이 이력 조회 API 구현   
>   - [ ] web application에서 DB에 저장된 플레이 이력을 요청하면 응답하는 기능을 구현
>
> 
> - [ ] 기존 기능 수정 - 출력 방석 수정
>    - [ ] console application의 출력을 변경
>       - [ ] console application에서 플레이의 중간 과정을 출력하는 로직을 제거
>       - [ ] console application에서 web application과 동일하게 우승자와 player 별 최종 이동거리를 출력하도록 수정
>
> 
> - [ ] 리팩터링 - 중복 코드 제거
>   - [ ] 두 application은 입출력과 데이터 저장 방식을 제외하고는 내부 비즈니스 로직은 동일해졌기 때문에   
      두 application의 비즈니스 로직은 XXXService라는 객체를 도출 하여 중복 제거 가능


