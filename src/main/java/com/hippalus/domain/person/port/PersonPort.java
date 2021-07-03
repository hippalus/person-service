package com.hippalus.domain.person.port;

import com.hippalus.domain.person.model.Person;
import com.hippalus.domain.person.usecase.PersonRetrieve;

import java.util.List;

public interface PersonPort {

    List<Person> retrieve(PersonRetrieve useCase);
}
