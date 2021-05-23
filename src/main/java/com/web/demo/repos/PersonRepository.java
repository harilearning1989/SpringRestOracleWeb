package com.web.demo.repos;

import com.web.demo.entities.Person;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Procedure(name = Person.NamedQuery_MoveToHistory)
    String movePersonToHistory(@Param("person_id_in") long personId);

    @Procedure(name = Person.NamedQuery_FetchFromHistory)
    List<Person> fetchPersonHistory();
}
