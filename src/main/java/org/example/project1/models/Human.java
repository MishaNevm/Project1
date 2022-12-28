package org.example.project1.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class Human {
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]{2,90}", message = "Name should be in format: Abcdef, and in range 2 to 90")
    private String name;
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]{2,90}", message = "Surname should be in format: Abcdef, and in range 2 to 90")
    private String surname;
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]{2,90}", message = "Patronymic should be in format: Abcdef, and in range 2 to 90")
    private String patronymic;
    @Email(message = "Email should be valid")
    private String email;
    @Min(value = 1930, message = "Year of birth should be bigger than 1930")
    private int yearOfBirth;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return yearOfBirth == human.yearOfBirth && id == human.id && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.equals(patronymic, human.patronymic) && Objects.equals(email, human.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, patronymic, email, yearOfBirth, id);
    }
}
