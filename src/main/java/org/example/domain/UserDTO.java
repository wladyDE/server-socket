package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDTO {
    private String login;
    private Set<Role> roles;
}
