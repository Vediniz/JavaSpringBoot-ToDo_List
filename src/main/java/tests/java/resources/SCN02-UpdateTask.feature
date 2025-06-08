Feature: Update Task

  Background:
    Given the system is initialized
    And the database is empty
    And I create a task with the following details:
      | name          | description                    | status  | priority |
      | Existing Task | This is an existing task.      | false   |     1    |
    Then the task should be created successfully
    And the task list should contain 1 task
    And the task list should contain a task with title "Existing Task"

  @UpdateTask @UpdateTaskFull
  Scenario: Successfully update an existing task
    When I update the task with the following details:
      | name         | description              | status  | priority |
      | Updated Task | Updated description      | true    |     2    |
    Then the task "name" should be updated to "Updated Task"
    And the task "description" should be updated to "Updated description"
    And the task "status" should be updated to "true"
    And the task "priority" should be updated to "2"
    Then the task list should contain 1 task
    And the task list should contain a task with title "Updated Task"

#  @UpdateTask @UpdateTaskWithoutName
#  Scenario: Fail to update a task with an empty name
#    When I update the task with the following details:
#      | name        | description           | status  | priority |
#      |             | Updated description.  | true    |     2    |
#    Then the task update should fail with an error message "must not be blank"
#    And the task list should still contain 1 task
#
#  @UpdateTask @UpdateTaskWithoutDescription
#  Scenario: Fail to update a task with an empty description
#    When I update the task with the following details:
#      | name        | description           | status  | priority |
#      | Updated Task|                       | true    |     2    |
#    Then the task update should fail with an error message "must not be blank"
#    And the task list should still contain 1 task
#
#  @UpdateTask @UpdateTaskWithoutPriority
#  Scenario: Successfully update a task without changing its priority
#    When I update the task with the following details:
#      | name        | description           | status  |
#      | Updated Task| Updated description.  | true    |
#    Then the task should be updated successfully
#    And the task list should contain 1 task
#
#  @UpdateTask @UpdateTaskWithoutStatus
#  Scenario: Successfully update a task without changing its status
#    When I update the task with the following details:
#      | name        | description           | priority |
#      | Updated Task| Updated description.  |     2    |
#    Then the task should be updated successfully
#    And the task list should contain 1 task