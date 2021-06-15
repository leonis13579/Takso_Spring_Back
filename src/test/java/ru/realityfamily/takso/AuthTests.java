package ru.realityfamily.takso;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import ru.realityfamily.takso.Controllers.AuthController;
import ru.realityfamily.takso.Models.AuthData;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TaksoApplication.class, PostgreTestConfig.class})
class AuthTests {

    @Test
    void AuthTestCorrect() {
        AuthData authData = new AuthData();
        authData.setLogin("driver");
        authData.setPassword("driver");

        assertThat(new AuthController().login(authData).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void AuthTestIncorrect() {
    }
}
