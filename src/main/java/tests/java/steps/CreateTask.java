package tests.java.steps;

import br.com.github.vediniz.entity.Todo;
import br.com.github.vediniz.repository.TodoRepository;
import static tests.java.steps.Utils.isNullOrBlank;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(classes = br.com.github.vediniz.ToDoListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CreateTask {

    @Autowired
    private TodoRepository todoRepository;
//    private Todo createdTask;

    private Response response;
    private String baseUrl = "http://localhost:8080/todos";


    @Given("the system is initialized")
    public void theSystemIsInitialized() {
        System.out.println("System is initialized");
    }

    @Given("the database is empty")
    public void theDatabaseIsEmpty() {
        todoRepository.deleteAll();
        System.out.println("Database cleaned");
    }


    @When("I create a task with the following details:")
    public void iCreateATaskWithTheFollowingDetails(DataTable dataTable) {
        Map<String, String> row = dataTable.asMaps().get(0);

        String name = row.get("name");
        if (isNullOrBlank(name)) {
            name = null;
        }

        String description = row.get("description");
        if (isNullOrBlank(description)) {
            description = null;
        }

        String requestBody = String.format(
                "{\"name\": %s, \"description\": %s, \"status\": \"%s\", \"priority\": %s}",
                name == null ? null : "\"" + name + "\"",
                description == null ? null : "\"" + description + "\"",
                row.get("status"),
                row.get("priority")
        );

        response = given().log().all()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(baseUrl);
    }



    @Then("the task should be created successfully")
    public void theTaskShouldBeCreatedSuccessfully() {}

    @Then("the task list should contain {int} task")
    public void theTaskListShouldContainTask(int expectedCount) {
        Response getResponse = given()
                .when()
                .get(baseUrl);

        getResponse.then()
                .statusCode(200)
                .body("", hasSize(expectedCount));
    }

    @Then("the task list should contain a task with title {string}")
    public void theTaskListShouldContainTaskWithTitle(String expectedTitle) {
        Response getResponse = given()
                .when()
                .get(baseUrl);

        getResponse.then()
                .statusCode(200).body("name", hasItem(expectedTitle));
    }

    @Then("the task creation should fail with an error message {string}")
    public void theTaskCreationShouldFailWithAnErrorMessage(String errorMessage) {
        System.out.println(response.asString());
        response.then().statusCode(400).body("error", equalTo(errorMessage));

    }
}
