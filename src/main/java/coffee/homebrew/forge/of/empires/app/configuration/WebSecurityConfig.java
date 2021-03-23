package drunk.homebrew.forge.of.empires.app.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
* Die Klasse zum einstellen von Spring Security.
 * @author internetz
 */
//TODO: Die Annotation '@Configuration' ist obsolet, wenn du mal in '@EnableWebSecurity' reinspringst, siehst du dass dort schon die Annotation gesetzt ist
@Configuration
@EnableWebSecurity
//TODO: Die Klasse gehört nicht in das "service"-Package, da sie weder Bestandteil von einem Service ist oder selbst einer ist. Entweder direkt direkt neben FoE.java oder in ein "config"-package verschieben
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailService userDetailsService;

    public WebSecurityConfig(UserDetailService userDetailService){
        this.userDetailsService = userDetailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/building", "/eventBuilding").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                //TODO: Wie kann ich mich ausloggen?
                .logout()
                .permitAll()
                .and()
                //TODO: Nicht einfach disablen
                .csrf().disable()
                //TODO: Wofür benötigt du HTTP-Basic Auth?
                .httpBasic();
    }


    //TODO: Wofür wird das benötigt?
    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}