package racingcar.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RacingCarApiTest.class)
class RacingCarApiTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("프레젠테이션 레이어 성공 케이스")
    @Test
    void racingCarApiTest() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/plays")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{ \"names\" : \"user1,user2,user3\", " +
                        "\"count\" : 1}")
        ).andExpect(status().isOk());
    }

    @DisplayName("프레젠테이션 레이어 실패 케이스")
    @Test
    void racingCarApiFailTest() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(post("/plays")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{ \"names\" : null, " +
                        "\"count\" : 1}")
        ).andExpect(status().isOk());
    }

}