package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.History;

import java.util.List;

@Repository
public class HistoryDao {

    private static JdbcTemplate jdbcTemplate;
    public HistoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public static int insertPlayResult(String Winners, int count) {

        String inssql = "INSERT INTO  \n" +
                       "  RACING_CAR  \n"  +
                       "      ( WINNERS  \n" +
                       "      , TRIALCOUNT  \n" +
                       "      ) \n" +
                       " VALUES  \n" +
                       "      (  \n" +
                       "        ? \n" +
                       "      , ? \n" +
                       "      ) \n";

        return jdbcTemplate.update(inssql, Winners.trim(), count );

    }

    public static List<History> selectListPlayResult() {
        String sql = "SELECT  WINNERS \n" +
                     "      , TRIALCOUNT \n" +
                     "      , FORMATDATETIME(CREATED_AT,'yyyymmddhhmmss') CREATED_AT \n" +
                     "  FROM RACING_CAR \n" +
                     " ORDER BY FORMATDATETIME(CREATED_AT,'yyyymmddhhmmss') DESC \n" ;

        System.out.println(sql);

        return jdbcTemplate.query(
                sql, (rs, rowNum) -> {
                    History history = new History(
                            rs.getString("winners"),
                            rs.getInt("trialCount"),
                            rs.getString("created_at")
                    );
                    return history;
                });
    }



//    public List<Car> selectListPlay(History historyParameter) {
//        String sql = "SELECT name,  position  FROM PLAY_RESULT \n" +
//                "WHERE FORMATDATETIME(created_at,'yyyymmddhhmmss') =  ? \n" +
//                "and    winners = ?  \n" +
//                "and    trialcount =  ?";
//        return jdbcTemplate.query(
//                sql, (rs, rowNum) -> {
//                    Car car = new Car(
//                            rs.getString("name"),
//                            rs.getInt("position")
//                    );
//                    return car;
//                }, historyParameter.getCreated_at()
//                , historyParameter.getWinners()
//                , historyParameter.getTrialCount());
//    }



}
