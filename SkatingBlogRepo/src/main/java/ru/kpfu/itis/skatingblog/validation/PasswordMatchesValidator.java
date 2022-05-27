package ru.kpfu.itis.skatingblog.validation;

import ru.kpfu.itis.skatingblog.dto.UserDto;
import ru.kpfu.itis.skatingblog.validation.annotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserDto user = (UserDto) o;
        return user.getPassword().equals(user.getConfirmedPassword());
    }
}
