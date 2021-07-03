package com.hippalus.domain.person.usecase;

import com.hippalus.domain.common.model.UseCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRetrieve implements UseCase {
    private String name;
}
