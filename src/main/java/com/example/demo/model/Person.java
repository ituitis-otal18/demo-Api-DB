package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.Positive;
import java.util.UUID;

public class Person {

    //Data
    private final UUID id;
    @NonNull
    private final String name;
    @Positive
    private final int age;

    //Constructor
    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("age") int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    //Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
}
