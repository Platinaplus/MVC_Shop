package ru.marina.shop.entity.dto;

import lombok.*;
import ru.marina.shop.entity.Role;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class UserDto {
    private String login;
    private String password;
    private String email;
    private String numberPhone;
    private Set<Role> roles;
}
