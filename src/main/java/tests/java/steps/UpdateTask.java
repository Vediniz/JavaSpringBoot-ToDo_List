package tests.java.steps;

import io.cucumber.java.en.When;

public class UpdateTask {

    @When("I update the task with the following details:")
    public void iUpdateTheTaskWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {

        System.out.println("Updating task with details: " + dataTable);
    }
}

