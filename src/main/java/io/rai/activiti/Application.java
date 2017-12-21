package io.rai.activiti;

import io.rai.activiti.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by rai on 2017/12/21.
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner init(final PersonService personService) {

    return new CommandLineRunner() {
      public void run(String... strings) throws Exception {
        personService.createPersons();
      }
    };

  }
}
