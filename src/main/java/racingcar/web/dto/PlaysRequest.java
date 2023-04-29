package racingcar.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaysRequest {
    private String names;
    private Integer count;

    @Override
    public String toString() {
        return "names : " + names + " count : " + count;
    }
}
