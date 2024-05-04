package ru.marina.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // disable protection against cross-site request forgery
                // Cross-Site Request Forgery (CSRF)
                //.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("styles/style.css").permitAll()
                                .requestMatchers("/img/*").permitAll()

                                // specify which pages should be protected by authentication
                                .requestMatchers("/admin").hasRole("USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                // specify which pages should not be protected by authentication
                                .requestMatchers("/authentication", "/logout", "/registration", "/index","/forgot-password")
                                .permitAll()
                                // any pages not described in the matchers above
                                // will be available to users with user and admin roles
                                .anyRequest()
                                .hasAnyRole("USER", "ADMIN")
                )
                .formLogin((formLogin) ->
                        formLogin
                                // Specify what url request will be sent when accessing protected pages
                                .loginPage("/login")
                                // Specify to which address the data from the form will be sent.
                                // We have set the url that is used by default to process the authentication form using Spring Security.
                                // Spring Security will wait for the object from the authentication form and then check the login and password against the data in the database
                                .loginProcessingUrl("/lk")
                                // Specify which url to direct the user to after successful authentication.
                                // The second argument specifies "true" so that the redirection goes anyway after successful authentication
                                .defaultSuccessUrl("/product", true)
                                .permitAll()
                                // Specify where the user should be redirected when authentication fails.
                                // An error object will be passed to the request, which will check on the form and if this object is present, the request will display the message "Incorrect login or password"
                                .failureUrl("/authentication?error")
                )
                .logout((logout) ->
                        logout

                                // same as above
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/authentication")
                                .permitAll()
                );
        return http.build();
    }
}