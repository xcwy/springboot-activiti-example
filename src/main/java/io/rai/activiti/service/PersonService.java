package io.rai.activiti.service;

import io.rai.activiti.entity.Person;
import io.rai.activiti.repository.PersonRepository;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rai on 2017/12/21.
 */
@Component
public class PersonService {

  @Autowired
  private RepositoryService repositoryService;


  @Autowired
  private RuntimeService runtimeService;

  @Autowired
  private TaskService taskService;

  @Autowired
  private PersonRepository personRepository;

  public String startProcess(String assignee) {
    Person person = personRepository.findByName(assignee);

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("person", person);

    runtimeService.startProcessInstanceByKey("simpleProcess", variables);

    return processInfo();
  }

  public List<Task> getTasks(String assignee) {
    return taskService.createTaskQuery().taskAssignee(assignee).list();
  }

  public void completeTask(String taskId) {
    taskService.complete(taskId);
  }

  public void createPersons() {
    if (personRepository.findAll().size() == 0) {

      personRepository.save(new Person("John", new Date()));
      personRepository.save(new Person("David", new Date()));
      personRepository.save(new Person("Katherin", new Date()));
    }
  }

  private String processInfo() {
    List<Task> tasks = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("Number of process definitions : "
        + repositoryService.createProcessDefinitionQuery().count() + "--> Tasks >> ");

    for (Task task : tasks) {
      stringBuilder
          .append(task + " | Assignee: " + task.getAssignee() + " | Description: " +
              task.getDescription());
    }

    return stringBuilder.toString();
  }
}