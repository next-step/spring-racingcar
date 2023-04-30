package racingcar.presentation.dto;

public class GameStartDto {
    private String names;
    private int count;

    public GameStartDto(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }

    public boolean isNotValid() {
        return this.names.isEmpty() || this.count == 0;
    }
}
