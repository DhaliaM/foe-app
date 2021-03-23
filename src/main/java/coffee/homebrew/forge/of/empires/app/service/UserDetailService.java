package drunk.homebrew.forge.of.empires.app.service;

import drunk.homebrew.forge.of.empires.app.persistence.UserEntity;
import drunk.homebrew.forge.of.empires.app.persistence.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
/**
 *  TODO: Ist so nicht ganz richtig, hier wird ja nix validiert. Hier wird lediglich der Benutzer gelesen, welcher später von Spring validiert wird.
 *  Ein Service um die Login Daten zu validieren durch Spring Security.
 *
 * @author Dhalia
 */
// TODO: Den Service hier anzusiedeln ist per se richtig. Aber damit vermischt sich Business-Logik mit Authentifizierung/Authorisierung, was die App undurchsichtiger macht.
@Service
public class UserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO: Beans in einem Service/Component/Repository zu erstellen, ist für mich ein no-go. Sowas gehört in Java-Configs (Klassen die eine Configuration-Annotation besitzen), also die WebSecurityConfig
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(userName);
        if(user == null) {
            throw new UsernameNotFoundException(userName);
        }
        //TODO: Variablenname passt nicht für mich, weil es sich nicht um ein Password handelt. Besser wäre userDetails oder user.
        UserDetails userPassword = User.withUsername(user.getUserName()).password(user.getPassword()).authorities("USER").build();

        return userPassword;

    }
}
