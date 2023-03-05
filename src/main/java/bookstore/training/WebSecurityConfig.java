package bookstore.training;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain configure
	(HttpSecurity http
	) throws Exception
	{
	http.authorizeHttpRequests()
	.requestMatchers("/resources/**", "/signup", "/about").permitAll()
	.requestMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/booklist", true)
			.permitAll()
			.and()
			.logout()
			.permitAll()
			.and()
			.httpBasic();
	
	return http.build();
	}
	@SuppressWarnings("deprecation")
	@Bean
	public UserDetailsService userDetailsService() {
	List<UserDetails> users = new ArrayList();
	UserDetails user = User.withDefaultPasswordEncoder()
	.username("user")
	.password("user1")
	.roles("USER")
	.build();
	users.add(user);
	
	user = User.withDefaultPasswordEncoder()
	.username("admin")
	.password("admin1")
	.roles("ADMIN")
	.build();
	users.add(user);
	
	return new InMemoryUserDetailsManager(users);
	}

}
