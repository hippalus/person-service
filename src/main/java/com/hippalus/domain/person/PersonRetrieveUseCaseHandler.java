package com.hippalus.domain.person;

import com.hippalus.domain.common.usecase.UseCaseHandler;
import com.hippalus.domain.person.model.Person;
import com.hippalus.domain.person.port.PersonPort;
import com.hippalus.domain.person.usecase.PersonRetrieve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonRetrieveUseCaseHandler implements UseCaseHandler<List<Person>, PersonRetrieve> {
    private final PersonPort personPort;
    @Override
    public List<Person> handle(PersonRetrieve useCase) {
        return personPort.retrieve(useCase);
    }
}
