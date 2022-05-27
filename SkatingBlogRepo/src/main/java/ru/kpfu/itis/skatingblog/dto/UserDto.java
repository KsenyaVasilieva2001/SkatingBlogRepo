package ru.kpfu.itis.skatingblog.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.skatingblog.models.User;
import ru.kpfu.itis.skatingblog.validation.annotations.PasswordMatches;
import ru.kpfu.itis.skatingblog.validation.annotations.ValidEmail;
import ru.kpfu.itis.skatingblog.validation.annotations.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Setter
@Getter
@PasswordMatches
public class UserDto {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;

    @NotNull
    @NotEmpty
    private String confirmedPassword;

    @NotNull
    @NotEmpty
    @ValidEmail(message = "{errors.invalid.email}")
    private String email;


    public static UserDto get(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
       return userDto;
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::get)
                .collect(Collectors.toList());
    }
}
