package com.hippalus.infra.adapters.person.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonWithCountResponse {
    private Integer count;
    private List<PersonResponse> personList;

    public PersonWithCountResponse(List<PersonResponse> personList) {
        this.count = personList.size();
        this.personList = personList;
    }
}
