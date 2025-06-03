Feature: Create Task

  Background:
    Given the system is initialized
    And the database is empty

  @CreateTask @CreateTaskFull
  Scenario: Successfully create a new task
    When I create a task with the following details:
      | name     | description           | status  | priority |
      | New Task  | This is a new task.  | false   |     1    |
    Then the task should be created successfully
    And the task list should contain 1 task
    And the task list should contain a task with title "New Task"


  @CreateTask @CreateTaskWithoutPriority
  Scenario: Successfully create a new task without a priority
    When I create a task with the following details:
      | name        | description           | status  | priority |
      | MyTask      | My description        | false   |          |
    Then the task should be created successfully
    And the task list should contain 1 task

  @CreateTask @CreateTaskWithoutStatus
  Scenario: Successfully create a new task without a status
    When I create a task with the following details:
      | name        | description           | status  | priority |
      | MyTask      | My description        |         |    1     |
    Then the task should be created successfully
    And the task list should contain 1 task

  @CreateTask @CreateTaskWithoutName
  Scenario: Fail to create a task with an empty name
    When I create a task with the following details:
      | name        | description           | status | priority |
      |             | This is a new task.   | false  |    1     |
    Then the task creation should fail with an error message "Name cannot be empty"
    And the task list should contain 0 task

  @CreateTask @CreateTaskWithoutDescription
  Scenario: Fail to create a task with an empty description
    When I create a task with the following details:
      | name        | description           | status  | priority |
      | MyTask      |                       | false   |    2     |
    Then the task creation should fail with an error message "Description cannot be empty"
    And the task list should contain 0 task