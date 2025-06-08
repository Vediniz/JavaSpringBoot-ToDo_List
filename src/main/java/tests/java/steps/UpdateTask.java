package tests.java.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static tests.java.steps.Utils.isNullOrBlank;
import static tests.java.steps.Utils.getTaskIdByName;


public class UpdateTask {

    private Response response;
    private String baseUrl = "http://localhost:8080/todos";

    @When("I update the task with the following details:")
    public void iUpdateTheTaskWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);

        String id = getTaskIdByName("Existing Task");

        if(isNullOrBlank(id)){
            id = null;
        }

        String name = row.get("name");
        if (isNullOrBlank(name)) {
            name = null;
        }

        String description = row.get("description");
        if (isNullOrBlank(description)) {
            description = null;
        }

        String requestBody = String.format(
                "{\"id\": %s, \"name\": %s, \"description\": %s, \"status\": \"%s\", \"priority\": %s}",
                id == null ? null : "\"" + id + "\"",
                name == null ? null : "\"" + row.get("name") + "\"",
                description == null ? null : "\"" + row.get("description") + "\"",
                row.get("status"),
                row.get("priority")
        );

        response = given().log().all()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(baseUrl)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Updating task with details: " + dataTable);
    }

    @Then("the task {string} should be updated to {string}")
    public void theTaskFieldShouldBeUpdatedTo(String field, String value) {
        Response getResponse = given()
                .when()
                .get(baseUrl)
                .then()
                .statusCode(200)
                .extract().response();

        List<String> list = JsonPath.from(response.getBody().asString()).getList(field);
        String actualValue = String.valueOf(list.get(0));
        assertEquals(value, actualValue);

    }
}
