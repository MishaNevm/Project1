package org.example.project1.models;


import javax.validation.constraints.*;

public class Book {


    @NotEmpty(message = "Book name should be not empty")
    @Size(min = 2, max = 99, message = "Book name should be in range 2 to 99")
    private String name;
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]{2,90}", message = "Name should be in format: Abcdef, and in range 2 to 90")
    private String authorName;
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]{2,90}", message = "Surname should be in format: Abcdef, and in range 2 to 90")
    private String authorSurname;
    @Min(value = 1800, message = "Year of birth should be bigger than 1800")
    private int year;
    private int id;
    private int humanId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHumanId() {
        return humanId;
    }

    public void setHumanId(int humanId) {
        this.humanId = humanId;
    }
}
