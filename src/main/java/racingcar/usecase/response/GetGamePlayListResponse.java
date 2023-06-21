package racingcar.usecase.response;

import racingcar.entity.RacingPlayer;

import java.util.List;

public class GetGamePlayListResponse {

    private final List<GameWithPlayer> gameList;

    public GetGamePlayListResponse(List<GameWithPlayer> gameWithPlayerList) {
        this.gameList = gameWithPlayerList;
    }

    public List<GameWithPlayer> getGameList() {
        return gameList;
    }

    public static class GameWithPlayer {

        private final long gameId;
        private final List<RacingPlayer> playerList;

        public GameWithPlayer(long gameId, List<RacingPlayer> playerList) {
            this.gameId = gameId;
            this.playerList = playerList;
        }

        public long getGameId() {
            return gameId;
        }

        public List<RacingPlayer> getPlayerList() {
            return playerList;
        }
    }

}
