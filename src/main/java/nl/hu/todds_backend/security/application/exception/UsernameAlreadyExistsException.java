package nl.hu.todds_backend.security.application.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super("Username " + message + " already exists");
    }
}
