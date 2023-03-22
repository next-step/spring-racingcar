package racingcar.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.model.Person;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PersonDAOTest {

    @Autowired
    private PersonDAO dao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        dao = new PersonDAO(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE PERSON IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE PERSON(" +
            "id INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(255), CREATED_AT DATETIME default current_timestamp)");

        String[] names = new String[]{"Jerry", "Tom", "Harry"};
        for (int i = 0; i < names.length; i++) {
            Person person = new Person(names[i]);
            dao.insertPerson(person);
        }

    }

    @Test
    void findAllPerson() {
        int count = dao.findAllPerson().size();

        assertThat(count).isEqualTo(3);
    }

    @Test
    void insertPerson() {
        Person insertPerson = new Person("testName");
        dao.insertPerson(insertPerson);

        List<Person> person = dao.findPersonByName("testName");
        assertThat(person).hasSize(1);
    }

}
