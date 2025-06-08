package tests.java.steps;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Utils {
    public static String baseUrl = "http://localhost:8080/todos";
    public static boolean isNullOrBlank(String s) {
        return s == null || s.isBlank() || s.equalsIgnoreCase("null");
    }

    public static String getTaskIdByName(String taskName) {
        Response response = given()
                .when()
                .get(baseUrl)
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, Object>> tasks = response.jsonPath().getList("$");

        for (Map<String, Object> task : tasks) {
            String name = (String) task.get("name");
            if (name != null && name.equalsIgnoreCase(taskName)) {
                return task.get("id").toString();
            }
        }

        return null;
    }


}
