package nl.hu.todds_backend.security.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserProfile {
    private final String username;
}
