package org.example.project1.dao;

import org.example.project1.models.Book;
import org.example.project1.models.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper bookRowMapper;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, BookRowMapper bookRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
    }

    public List<Book> readAll() {
        return jdbcTemplate.query("SELECT * FROM Book", bookRowMapper);
    }

    public List<Book> readFreeBooks() {
        return jdbcTemplate.query("SELECT * FROM Book WHERE people_id IS NULL",bookRowMapper);
    }

    public List<Book> readAllBooksForHuman(int peopleId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE people_id=?", bookRowMapper, peopleId);
    }

    public Book readOne(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", bookRowMapper, id)
                .stream().findAny().orElse(null);
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book(book_name, book_author_name, book_author_surname, book_year)" +
                "VALUES (?,?,?,?)", book.getName(), book.getAuthorName(), book.getAuthorSurname(), book.getYear());
    }

    public void update(Book book) {
        jdbcTemplate.update("UPDATE Book SET book_name=?,book_author_name=?,book_author_surname=?,book_year=?" +
                        "WHERE book_id=?", book.getName(), book.getAuthorName(),
                book.getAuthorSurname(), book.getYear(), book.getId());
    }

    public void appoint(Book book) {
        jdbcTemplate.update("UPDATE Book SET people_id=? WHERE book_id=?", book.getHumanId(), book.getId());
    }
    public void unAppoint(Book book) {
        jdbcTemplate.update("UPDATE Book SET people_id=? WHERE book_id=?", null, book.getId());
    }
    public void unAppointAllFromHuman(int id) {
        jdbcTemplate.update("UPDATE Book SET people_id=? WHERE people_id=?", null, id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

}
