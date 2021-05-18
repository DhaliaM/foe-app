package coffee.homebrew.forge.of.empires.app.service;

import coffee.homebrew.forge.of.empires.app.persistence.UserEntity;
import coffee.homebrew.forge.of.empires.app.persistence.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Ein Service um die Login Daten zu lesen, mittels Spring Security.
 *
 * @author Dhalia
 */
@Service
public class UserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        UserDetails userDetails = User.withUsername(user.getUserName()).password(user.getPassword()).authorities("USER").build();

        return userDetails;

    }
}
