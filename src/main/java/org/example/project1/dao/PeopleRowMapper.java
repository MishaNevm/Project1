package org.example.project1.dao;

import org.example.project1.models.Human;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PeopleRowMapper implements RowMapper<Human> {
    @Override
    public Human mapRow(ResultSet rs, int rowNum) throws SQLException {
        Human human = new Human();
        human.setEmail(rs.getString("people_email"));
        human.setId(rs.getInt("people_id"));
        human.setName(rs.getString("people_name"));
        human.setSurname(rs.getString("people_surname"));
        human.setPatronymic(rs.getString("people_patronymic"));
        human.setYearOfBirth(rs.getInt("people_year_of_birth"));
        return human;
    }
}
