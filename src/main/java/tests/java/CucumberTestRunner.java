package tests.java;

import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/tests/java/resources")
@SpringBootTest(
        classes = br.com.github.vediniz.ToDoListApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
public class CucumberTestRunner {
}
