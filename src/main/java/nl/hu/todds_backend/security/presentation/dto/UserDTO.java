package nl.hu.todds_backend.security.presentation.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @NotBlank
    private String username;
    @Size(min = 5, max = 100)
    private String password;
}
