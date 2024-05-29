package nl.hu.todds_backend.security.presentation;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.security.application.UserService;
import nl.hu.todds_backend.security.domain.UserProfile;
import nl.hu.todds_backend.security.presentation.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@Validated @RequestBody UserDTO userDTO) {
        this.userService.register(userDTO.getUsername(), userDTO.getPassword());
    }

    @GetMapping("/user")
    public List<UserProfile> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestBody UserProfile userProfile) {
        this.userService.deleteUser(userProfile);
    }
}
