package racingcar.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import racingcar.domain.PlayResult;

@SpringBootTest
@Rollback(false)
public class PlayResultRepositoryTest {
    private final PlayResultRepository playResultRepository;

    @Autowired
    public PlayResultRepositoryTest(PlayResultRepository playResultRepository) {
        this.playResultRepository = playResultRepository;
    }

    @Test
    void repositoryTest() {
        PlayResult playResult = new PlayResult();
        playResult.setWinners("firstCar");
        playResultRepository.save(playResult);

        PlayResult findplayResult = playResultRepository.findById(1L).orElse(null);
        assertThat(findplayResult.getWinners(), is("firstCar"));
        assertThat(findplayResult.getId(), is(1L));
    }
}
