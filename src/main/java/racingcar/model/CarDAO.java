package racingcar.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import racingcar.domain.Car;

public class CarDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Car> getCars() {

        String query = """
                SELECT *
                FROM CAR
                """;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>());

        // return jdbcTemplate.query("select * from offers", new RowMapper<Car>() {
        // public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Car car = new Car(rs.getString("name"));

        // return car;
        // }
        // });
    }

}
