package core.di.factory.example;

import core.annotation.Inject;
import core.annotation.Repository;

@Repository
public class JdbcQuestionRepository implements QuestionRepository {
    private MyJdbcTemplate jdbcTemplate;

    @Inject
    public JdbcQuestionRepository(MyJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MyJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
