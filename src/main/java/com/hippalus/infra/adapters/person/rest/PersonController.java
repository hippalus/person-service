package com.hippalus.infra.adapters.person.rest;

import com.hippalus.domain.common.usecase.UseCaseHandler;
import com.hippalus.domain.person.model.Person;
import com.hippalus.domain.person.usecase.PersonRetrieve;
import com.hippalus.infra.adapters.person.rest.dto.PersonResponse;
import com.hippalus.infra.adapters.person.rest.dto.PersonWithCountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final UseCaseHandler<List<Person>, PersonRetrieve> useCaseHandler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonWithCountResponse> retrieve(@RequestParam String name) {
        List<Person> personList = useCaseHandler.handle(new PersonRetrieve(name));
        var personWithCountResponse = new PersonWithCountResponse(toResponse(personList));
        return ResponseEntity.ok(personWithCountResponse);
    }

    private List<PersonResponse> toResponse(List<Person> personList) {
        return personList.stream().map(PersonResponse::from).collect(Collectors.toList());
    }
}
