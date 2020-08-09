package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresql")
public class RealDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RealDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return jdbcTemplate.update("INSERT INTO person(id, name, age) VALUES (?, ?, ?)", id, person.getName(), person.getAge());
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name, age FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            int age = Integer.parseInt(resultSet.getString("age"));
            return new Person(id, name, age);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name, age FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id},(resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            int age = Integer.parseInt(resultSet.getString("age"));
            return new Person(personId, name, age);
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        //TODO update
        return 0;
    }
}
