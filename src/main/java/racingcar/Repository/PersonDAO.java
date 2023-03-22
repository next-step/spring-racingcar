package racingcar.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.model.Person;

@Repository
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Person> actorRowMapper = (resultSet, rowNum) -> {
        Person person = new Person(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("created_at")
        );
        return person;
    };

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 전체 사람 데이터
     *
     * @return List<Person>
     */
    public List<Person> findAllPerson() {
        String sql = "select id, name, created_at from person";
        return jdbcTemplate.query(sql, actorRowMapper);
    }


    /**
     * 이름으로 사람 데이터 찾기
     *
     * @param name
     * @return List<Person>
     */
    public List<Person> findPersonByName(String name) {
        String sql = "select id, name, created_at from person where name = ?";
        return jdbcTemplate.query(sql, actorRowMapper, name);
    }

    /**
     * 사람 데이터 삽입
     *
     * @param person
     * @return person primary key
     */
    public int insertPerson(Person person) {
        String sql = "insert into person (name) values (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, person.getName());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

}
