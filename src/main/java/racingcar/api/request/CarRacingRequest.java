package racingcar.api.request;

/**
 * CarRacing 요청 객체
 */
public class CarRacingRequest {

    private String names; // 플레이어 명 목록
    private int count; // 게임 실행 횟수

    public String getNames() {
        return this.names;
    }
    
    public int getCount() {
        return this.count;
    }
}
