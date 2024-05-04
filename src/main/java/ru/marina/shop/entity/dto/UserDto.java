package ru.marina.shop.entity.dto;

import lombok.*;

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
}

