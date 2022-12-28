package org.example.project1.dao;

import org.example.project1.models.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setHumanId(rs.getInt("people_id"));
        book.setName(rs.getString("book_name"));
        book.setAuthorName(rs.getString("book_author_name"));
        book.setAuthorSurname(rs.getString("book_author_surname"));
        book.setYear(rs.getInt("book_year"));
        return book;
    }
}
