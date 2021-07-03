package com.hippalus.infra.adapters.person.file;

import com.hippalus.domain.person.model.Person;
import com.hippalus.domain.person.port.PersonPort;
import com.hippalus.domain.person.usecase.PersonRetrieve;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonFileAdapter implements PersonPort {
    @SneakyThrows
    @Override
    public List<Person> retrieve(PersonRetrieve useCase) {
        //TODO: configure filename
        try (Stream<String> personStream = Files.lines(Path.of(ClassLoader.getSystemResource("person.csv").getPath()))) {
            return personStream.filter(s -> s.contains(useCase.getName()))
                    .map(this::toModel)
                    .collect(Collectors.toList());
        }
    }

    private  Person toModel(String line) {
        //TODO parse csv line
        return null;
    }
}
