## Step 1 리팩토링 요구사항
- [x] PlayService 통합 테스트로 진행
  - [x] Test 컴파일 오류 해결
  - [x] save 로직 public으로 변경, 오류 시 롤백을 위한 트랜잭션 설정
  - [x] 통합 테스트로 db에 잘 저장되는지 확인
- [x] @WebMvcTest를 사용해 PlayController 슬라이스 테스트 작성
  - [x] PlayService mocking 후 정상, 비정상 케이스에 대한 응답 검증
- [x] Dao에서 id가 존재하지 않는 경우에 대한 예외 처리 고민
- [x] carName split 로직 책임 객체 고민
- [x] PlayService 에서 랜덤 로직 분리하고 테스트 작성

## Step 2 리팩토링 요구사항
- [ ] PlayService 관련
  - [ ] MovingStrategy 테스트용 클래스 만들어서 Component로 등록
  - [ ] MovingStrategy 에 따른 PlayResult 반환 값 테스트

## Step 2 미션 요구사항
- [ ] 게임 플레이 이력 조회 API 구현
  - [ ] ResponseDto, Controller, test 구현
  - [ ] Service 로직, dao, test 구현
- [ ] console application 출력 방식 변경
  - [ ] 중간 과정 출력 로직 제거
  - [ ] 결과 출력 방식을 '우승자, player 별 최종 이동거리' 로 변경
- [ ] 중복 코드 제거
  - [ ] PlayService 활용해 중복 코드 제거하기