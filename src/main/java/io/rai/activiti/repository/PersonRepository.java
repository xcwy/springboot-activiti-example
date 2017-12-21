package io.rai.activiti.repository;

import io.rai.activiti.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rai on 2017/12/21.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

  Person findByName(String name);

}