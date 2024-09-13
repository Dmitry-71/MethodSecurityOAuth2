package netology.ru.methodsecurityoauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
//    final User.UserBuilder userBuilder = User.builder().passwordEncoder(new BCryptPasswordEncoder()::encode);

    @Bean
    UserDetailsService userAuthentication() {
        UserDetails admin = userBuilder.username("admin").password("adminpwd").roles("READ", "WRITE", "DELETE").build();
        UserDetails reader = userBuilder.username("reader").password("readerpwd").roles("READ").build();
        UserDetails writer = userBuilder.username("writer").password("writerpwd").roles("WRITE").build();
        UserDetails killer = userBuilder.username("killer").password("killerpwd").roles("DELETE").build();
        UserDetails alex = userBuilder.username("alexey").password("alexeypwd").roles("READ", "WRITE").build();
        UserDetails guest = userBuilder.username("guest").password("guestpwd").authorities("READ").build();
        return new InMemoryUserDetailsManager(admin, reader, writer, killer, alex, guest);
    }

    @Bean
    public SecurityFilterChain filters(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults());

        return http.build();
    }
}
