package com.hippalus.infra.adapters.person.rest.dto;

import com.hippalus.domain.person.model.Person;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponse {
    private String name;
    private String surname;
    private Integer age;
    private String job;

    public static PersonResponse from(Person person) {
        return PersonResponse.builder()
                .age(person.getAge())
                .name(person.getName())
                .surname(person.getSurname())
                .job(person.getJob())
                .build();
    }
}
