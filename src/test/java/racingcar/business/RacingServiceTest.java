package racingcar.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import racingcar.data.PlayHistoryRepository;
import racingcar.data.PlayResultRepository;
import racingcar.presentation.dto.GameStartDto;

import javax.sql.DataSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class RacingServiceTest {

    @Spy
    private DataSource dataSource = new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:data-test.sql")
            .build();;

    @Spy
    private PlayResultRepository racingRepository;

    @Spy
    private PlayHistoryRepository historyRepository;
    @InjectMocks
    private RacingService racingService;

    @DisplayName("비즈니스 레이어 테스트")
    @Test
    void gameTest() {
        // given
        // when
        // then
        assertThat(racingService.game("A,B,C", 100)).isNotNull();
    }
}