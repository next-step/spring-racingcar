package racingcar.application.dto;

import javax.validation.constraints.NotBlank;

public class GameRequest {

    @NotBlank
    private final String names;
    private final int count;

    public GameRequest(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }

    public boolean isNameEmpty() {
        return names.isBlank();
    }
}
