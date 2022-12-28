package org.example.project1.dao;


import org.example.project1.models.Book;
import org.example.project1.models.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PeopleDAO {

    private final JdbcTemplate jdbcTemplate;
    private final PeopleRowMapper peopleRowMapper;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate, PeopleRowMapper peopleRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.peopleRowMapper = peopleRowMapper;
    }

    public List<Human> readAll() {
        return jdbcTemplate.query("SELECT * FROM People", peopleRowMapper);
    }
    public List<Human> readAll(String email) {
        return jdbcTemplate.query("SELECT * FROM People WHERE people_email=?",peopleRowMapper, email);
    }

    public Human readOne(int id) {
        return jdbcTemplate.query("SELECT * FROM People WHERE people_id=?", peopleRowMapper, id).
                stream().findAny().orElse(null);
    }


    public void create(Human human) {
        jdbcTemplate.update("INSERT INTO People(people_name, people_surname," +
                        "people_patronymic, people_year_of_birth, people_email) VALUES (?,?,?,?,?)", human.getName(),
                human.getSurname(), human.getPatronymic(), human.getYearOfBirth(), human.getEmail());
    }

    public void update(Human human) {
        jdbcTemplate.update("UPDATE People set people_name=?,people_surname=?," +
                        "people_patronymic=?, people_year_of_birth=?, people_email=? WHERE people_id=?", human.getName(),
                human.getSurname(), human.getPatronymic(), human.getYearOfBirth(), human.getEmail(), human.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM People WHERE people_id=?", id);
    }
}
