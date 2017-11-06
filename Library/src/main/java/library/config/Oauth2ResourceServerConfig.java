package library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http
	      .authorizeRequests()
	      .antMatchers(HttpMethod.POST,"/oauth/token").permitAll()
	      .antMatchers(HttpMethod.GET,"/books").permitAll()
	      .antMatchers(HttpMethod.GET,"/books/*").permitAll()
	      .antMatchers(HttpMethod.POST,"/books/*").hasAuthority("ROLE_ADMIN")
	      .antMatchers(HttpMethod.DELETE,"/books/*").hasAuthority("ROLE_ADMIN")
	      .antMatchers(HttpMethod.GET,"/authors").permitAll()
	      .antMatchers(HttpMethod.GET,"/authors/*").permitAll()
	      .antMatchers(HttpMethod.GET,"/libraries").permitAll()
	      .antMatchers(HttpMethod.GET,"/libraries/*").permitAll()
	      .antMatchers(HttpMethod.GET,"/books/*/reservations").hasAuthority("ROLE_ADMIN")
	      .antMatchers(HttpMethod.GET,"/books/*/reservations/*").hasAuthority("ROLE_ADMIN")
	      .antMatchers(HttpMethod.POST,"/books/*/reservations").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
	      .antMatchers(HttpMethod.PUT,"/books/*/reservations/*").hasAuthority("ROLE_ADMIN")
	      .antMatchers(HttpMethod.DELETE,"/books/*/reservations/*").hasAuthority("ROLE_ADMIN")
	      .antMatchers(HttpMethod.GET,"/books/*/reviews").permitAll()
	      .antMatchers(HttpMethod.GET,"/books/*/reviews/*").permitAll()
	      .antMatchers(HttpMethod.POST,"/books/*/reviews/*").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
	      .antMatchers(HttpMethod.DELETE,"/books/*/reviews/*").hasAuthority("ROLE_ADMIN")
	      .antMatchers(HttpMethod.GET,"/users").hasAuthority("ROLE_ADMIN")
	      .antMatchers(HttpMethod.GET,"/users/*").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")//ROLE_USER can access this resource only if his id match user.id
	      .antMatchers(HttpMethod.GET).permitAll()
	        .anyRequest()
	          .authenticated();
	}
}
