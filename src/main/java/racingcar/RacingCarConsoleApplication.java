package racingcar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.dto.PlayInput;
import racingcar.dto.PlayResult;
import racingcar.service.RacingCarService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {

        RacingCarService racingCarService = new RacingCarService();

        // 자동차 입력
        Scanner scanner = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분)");
        String names = scanner.nextLine();

        // 시도 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = scanner.nextInt();

        //인풋값 설정
        PlayInput playInput = new PlayInput(names, count);
        
        //게임실행
        PlayResult playResult = racingCarService.startgame(playInput);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonStr = mapper.writeValueAsString(playResult);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
