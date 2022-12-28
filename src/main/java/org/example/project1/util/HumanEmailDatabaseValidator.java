package org.example.project1.util;

import org.example.project1.dao.PeopleDAO;
import org.example.project1.models.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class HumanEmailDatabaseValidator implements Validator {

    private final PeopleDAO peopleDAO;

    @Autowired
    public HumanEmailDatabaseValidator(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Human.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        List<Human> humans = peopleDAO.readAll(((Human)target).getEmail());
        if (!humans.isEmpty() && !humans.get(0).equals(target)){
            errors.rejectValue("email","","Эта почта уже занята");
        }
    }
}
