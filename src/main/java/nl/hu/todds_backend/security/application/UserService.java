package nl.hu.todds_backend.security.application;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.security.application.exception.UsernameAlreadyExistsException;
import nl.hu.todds_backend.security.data.PersistUserDTOMapper;
import nl.hu.todds_backend.security.data.UserRepository;
import nl.hu.todds_backend.security.domain.User;
import nl.hu.todds_backend.security.domain.UserProfile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PersistUserDTOMapper userDTOMapper;

    @Override
    public void register(String username, String password) throws UsernameAlreadyExistsException {
        try {
            this.loadUserByUsername(username);
            throw new UsernameAlreadyExistsException(username);
        } catch (UsernameNotFoundException e) {
            this.save(new User(username, this.passwordEncoder.encode(password)));
        }
    }

    @Override
    public void save(User user) {
        this.userRepository.save(this.userDTOMapper.toDTO(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userDTOMapper.fromDTO(this.userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username)));
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return this.userRepository.findAll().stream().map(user -> new UserProfile(user.getUsername())).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(UserProfile userProfile) {
        this.userRepository.delete(this.userRepository.getById(userProfile.getUsername()));
    }
}
