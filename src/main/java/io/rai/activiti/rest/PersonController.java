package io.rai.activiti.rest;

import io.rai.activiti.service.PersonService;
import java.util.List;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rai on 2017/12/21.
 */
@RestController
public class PersonController {

  @Autowired
  private PersonService personService;

  @RequestMapping(value = "/process")
  public String startProcessInstance(@RequestParam String assignee) {
    return personService.startProcess(assignee);
  }

  @RequestMapping(value = "/tasks/{assignee}")
  public String getTasks(@PathVariable("assignee") String assignee) {
    List<Task> tasks = personService.getTasks(assignee);
    return tasks.toString();
  }

  @RequestMapping(value = "/completetask")
  public String completeTask(@RequestParam String id) {
    personService.completeTask(id);
    return "Task with id " + id + " has been completed!";
  }
}
