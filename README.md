# 2단계 - 추가 기능 및 리팩터링

- [x] 게임 플레이 이력 조회 API 구현
- [x] 기존 기능 수정 - 출력 방석 수정
- [x] 리팩터링 - 중복 코드 제거
- [x] console application의 출력을 변경합니다.
- [x] console application에서 플레이의 중간 과정을 출력하는 로직을 제거합니다.
- [x] console application에서 web application과 동일하게 우승자와 player 별 최종 이동거리를 출력하도록 수정합니다.
- [x] console application과 web application의 중복 코드를 제거합니다.
- [x] 두 application은 입출력과 데이터 저장 방식을 제외하고는 내부 비즈니스 로직은 동일해졌습니다.
- [x] 두 application의 비즈니스 로직은 XXXService라는 객체를 도출 하여 중복 제거를 할 수 있습니다. (레이어드 아키텍처 참고) H2 DB를 연동합니다.
# 게임 플레이 이력 조회 API 구현

- web application에서 DB에 저장된 플레이 이력을 요청하면 응답하는 기능을 구현합니다.
- 아래의 API 요구사항에 맞춰서 기능을 구현합니다.

> Request

```
GET /plays HTTP/1.1
```

> Response

```
HTTP/1.1 200
Content-Type: application/json

{
HTTP/1.1 200
Content-Type: application/json

[
    {
        "winners": "워니",
        "racingCars": [
            {
                "name": "워니",
                "position": 6
            },
            {
                "name": "제이슨",
                "position": 4
            },
            {
                "name": "브라운",
                "position": 3
            },
        ]
    },
    {
        "winners": "워니,제이슨,브라운",
        "racingCars": [
            {
                "name": "워니",
                "position": 6
            },
            {
                "name": "제이슨",
                "position": 6
            },
            {
                "name": "브라운",
                "position": 6
            },
        ]
    }
]
```

# 기존 기능 수정 - 출력 방석 수정

- console application의 출력을 변경합니다.
  - console application에서 플레이의 중간 과정을 출력하는 로직을 제거합니다.
  - console application에서 web application과 동일하게 우승자와 player 별 최종 이동거리를 출력하도록 수정합니다.

# 리팩터링 - 중복 코드 제거

- console application과 web application의 중복 코드를 제거합니다.
  - 두 application은 입출력과 데이터 저장 방식을 제외하고는 내부 비즈니스 로직은 동일해졌습니다.
  - 두 application의 비즈니스 로직은 XXXService라는 객체를 도출 하여 중복 제거를 할 수 있습니다. (레이어드 아키텍처 참고) H2 DB를 연동합니다.
