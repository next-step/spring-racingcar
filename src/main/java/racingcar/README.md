## Step 1 리팩토링 요구사항
- [x] PlayService 통합 테스트로 진행
  - [x] Test 컴파일 오류 해결
  - [x] save 로직 public으로 변경, 오류 시 롤백을 위한 트랜잭션 설정
  - [x] 통합 테스트로 db에 잘 저장되는지 확인
- [ ] @WebMvcTest를 사용해 PlayController 슬라이스 테스트 작성
  - [ ] PlayService mocking 후 정상, 비정상 케이스에 대한 응답 검증
- [ ] Dao에서 id가 존재하지 않는 경우에 대한 예외 처리 고민
- [ ] carName split 로직 책임 객체 고민
- [ ] PlayService 에서 랜덤 로직 분리하고 테스트 작성