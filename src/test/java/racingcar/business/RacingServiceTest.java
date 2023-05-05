package racingcar.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import racingcar.data.HistoryRepository;
import racingcar.data.RacingRepository;
import racingcar.presentation.dto.GameStartDto;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class RacingServiceTest {

    @Spy
    private DataSource dataSource = new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:data-test.sql")
            .build();;

    @InjectMocks
    private RacingRepository racingRepository;

    @InjectMocks
    private HistoryRepository historyRepository;
    @InjectMocks
    private RacingService racingService;

    @BeforeEach
    void setRacingService() throws SQLException {
        this.racingService = new RacingService(racingRepository, historyRepository);
    }

    @DisplayName("비즈니스 레이어 테스트")
    @Test
    void gameTest() {
        // given
        GameStartDto gameStartDto = new GameStartDto("A,B,C", 100);
        // when
        // then
        assertThat(racingService.game(gameStartDto)).isNotNull();
    }
}