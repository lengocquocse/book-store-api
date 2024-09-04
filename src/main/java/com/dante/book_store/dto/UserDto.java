package com.dante.book_store.dto;

import com.dante.book_store.enums.ERole;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Long> roleIds;
}
