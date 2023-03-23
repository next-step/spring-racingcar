# jwp-racingcar

- [x] 자동차 경주 코드 가져오기
- [x] 웹 요청/응답 구현하기
- [x] DB 연동하기

# 웹 요청/응답 구현하기

웹을 통해 게임을 플레이 할 수 있도록 구현하세요.
사용자는 자동차 경주 게임을 플레이하기 위해 다음과 같은 요청을 보낼 수 있습니다.

> Request

```
POST /plays HTTP/1.1
content-type: application/json; charset=UTF-8
host: localhost:8080

{
    "names": "워니,제이슨,브라운",
    "count": 50
}
```

애플리케이션은 받은 요청에 대해 자동차 경주를 진행하고, 우승자와 각 자동차들의 최종 위치를 JSON 형식으로 응답합니다.

> Response

```
HTTP/1.1 200
Content-Type: application/json

{
    "winners": "워니",
    "racingCars": [
        {
            "name": "워니",
            "position": 9
        },
        {
            "name": "제이슨",
            "position": 7
        },
        {
            "name": "브라운",
            "position": 3
        },
    ]
}
```

- 페이지를 통해 기능이 정상적으로 동작하는 지 확인할 수 있습니다.
  - 1, 2번 요구사항이 잘 수행되었다면 해당 기능을 페이지에서 확인 가능
  - localhost:8080으로 접속하면 자동차 경주 게임 페이지에 접근

# DB연동하기

- H2 DB를 연동합니다.
- 자동차 경주 게임의 플레이 이력을 DB에 저장합니다.
  - DB에 저장되는 정보는 다음과 같습니다.
  - 플레이 횟수(trialCount)
  - 플레이어 별 최종 이동 거리 (이름(name), 최종 위치(position))
  - 우승자(winners)
  - 플레이한 날짜/시간
