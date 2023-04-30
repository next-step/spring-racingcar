package racingcar.domain;

public class PlayHistory {

    private int id;
    private int playResultId;
    private String name;
    private int position;

    public static PlayHistory of(int playResultId, RacingCar racingCar) {
        return PlayHistory.builder()
                .playResultId(playResultId)
                .name(racingCar.getName())
                .position(racingCar.getPosition())
                .build();
    }

    public static PlayHistoryBuilder builder() {
        return new PlayHistoryBuilder();
    }

    public int getId() {
        return id;
    }

    public int getPlayResultId() {
        return playResultId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public static class PlayHistoryBuilder {
        private int id;
        private int playResultId;
        private String name;
        private int position;

        public PlayHistoryBuilder id(int id) {
            this.id = id;
            return this;
        }

        public PlayHistoryBuilder playResultId(int playResultId) {
            this.playResultId = playResultId;
            return this;
        }

        public PlayHistoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PlayHistoryBuilder position(int position) {
            this.position = position;
            return this;
        }

        public PlayHistory build() {
            PlayHistory playHistory = new PlayHistory();
            playHistory.id = this.id;
            playHistory.playResultId = this.playResultId;
            playHistory.name = this.name;
            playHistory.position = this.position;
            return playHistory;
        }
    }

}
