package com.sajan.pms.dto;

import com.sajan.pms.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
