package nl.hu.todds_backend.security.application;

import nl.hu.todds_backend.security.application.exception.UsernameAlreadyExistsException;
import nl.hu.todds_backend.security.domain.User;
import nl.hu.todds_backend.security.domain.UserProfile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {
    public void register(String username, String password) throws UsernameAlreadyExistsException;
    public void save(User user);
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    public List<UserProfile> getAllUsers();
    public void deleteUser(UserProfile userProfile);
}
