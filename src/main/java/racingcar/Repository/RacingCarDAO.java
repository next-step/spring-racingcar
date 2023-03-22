package racingcar.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.model.RacingCar;

@Repository
public class RacingCarDAO {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<RacingCar> actorRowMapper = (resultSet, rowNum) -> {
        RacingCar racingCar = new RacingCar(
            resultSet.getInt("id"),
            resultSet.getInt("group_id"),
            resultSet.getInt("person_id"),
            resultSet.getString("name"),
            resultSet.getInt("position")
        );
        return racingCar;
    };

    public RacingCarDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 전체 경기 기록 데이터
     *
     * @return List<RacingCar>
     */
    public List<RacingCar> findAllRacingCar() {
        String sql = "select id, group_id, person_id, c, position, created_at from racing_car";
        return jdbcTemplate.query(sql, actorRowMapper);
    }


    /**
     * 아이디로 경기 기록 찾기
     *
     * @param id
     * @return List<RacingCar>
     */
    public List<RacingCar> findRacingCarById(int id) {
        String sql = "select id, group_id, person_id, name, position, created_at from racing_car where id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, id);
    }

    /**
     * 그룹 아이디로 경기 기록 찾기
     *
     * @param groupId
     * @return List<RacingCar>
     */
    public List<RacingCar> findRacingCarByGroupId(int groupId) {
        String sql = "select id, group_id, person_id, name, position, created_at from racing_car where group_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, groupId);
    }

    /**
     * 경기 기록 삽입
     *
     * @param racingCar
     * @return id
     */
    public int insertRacingCar(RacingCar racingCar) {
        String sql = "insert into racing_car (group_id, person_id, name, position) values (?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, racingCar.getGroupId());
            ps.setInt(2, racingCar.getPersonId());
            ps.setString(3, racingCar.getName());
            ps.setInt(4, racingCar.getPosition());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    /**
     * 경기 기록 업데이트
     *
     * @param racingCar
     */
    public void updateRacingCar(RacingCar racingCar) {
        String sql = "update racing_car set group_id = ? , person_id = ? , name = ? , position = ? where id = ?";
        this.jdbcTemplate.update(sql, racingCar.getGroupId(), racingCar.getPersonId(),
            racingCar.getName(), racingCar.getPosition(), racingCar.getId());
    }

    /**
     * 위치 업데이트
     *
     * @param racingCar
     * @param randomNumber
     */
    public void updatePosition(RacingCar racingCar, int randomNumber) {
        if (randomNumber >= 4) {
            String sql = "update racing_car set position = ? where group_id = ? and person_id = ?";
            this.jdbcTemplate.update(sql, racingCar.getPosition() + 1, racingCar.getGroupId(),
                racingCar.getPersonId());
        }
    }

    /**
     * 그룹 아이디 생성 또는 찾기
     *
     * @return groupId
     */
    public int getGroupId() {
        String sql = "select ifnull(max(group_id),0) + 1 from racing_car";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 승자 가져오기
     *
     * @param groupId
     * @return List<RacingCar>
     */
    public List<RacingCar> getWinner(int groupId) {
        String sql = "select id, group_id, person_id, name, position, created_at from racing_car where group_id = ? and position = (select max(position) from racing_car where group_id = ?)";
        return jdbcTemplate.query(sql, actorRowMapper, groupId, groupId);
    }
}
