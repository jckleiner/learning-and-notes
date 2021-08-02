package com.learning.springsecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/*
 * Web security (@EnableWebSecurity) is just one of the ways we can configure the security.
 * The other is application/method level security.
 */
@EnableWebSecurity // tells spring that this is a web security configuration
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		- This is where you can specify the mapping of PATH -> ROLE
		- The order is crucial here.
		- The order should be from the most restrictive to the least restrictive
		- Spring returns immediately when a request matches to an antMatcher,
		  putting .antMatchers("/**").permitAll() at the first line is a bad idea
		  because it will match ALL the requests and the other matchers will be ignored
		*/
		http.authorizeRequests()
				.antMatchers("/admin").hasRole("BOSS")
				.antMatchers("/user").hasAnyRole("FIGHTER", "BOSS")
				.antMatchers("/**").permitAll()
				.and()
				.formLogin(); // enables form login
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// we can now configure the AuthenticationManagerBuilder here with our settings

		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("select user_name,pass,active " +
						"from MY_USERS " +
						"where user_name = ?")
				.authoritiesByUsernameQuery("select user_name,role " +
						"from MY_ROLES " +
						"where user_name = ?");



		/*
		// for an H2 database
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.withDefaultSchema()
				.withUser("user")
				.password("123")
				.roles("USER")
				.and()
				.withUser("admin")
				.password("123")
				.roles("ADMIN");

		auth.inMemoryAuthentication()
				.withUser("user")
				.password("123")
				.roles("USER")
				.and()
				.withUser("admin")
				.password("123")
				.roles("ADMIN");
		 */
	}


	@Bean
	public PasswordEncoder getPasswordEncoder() {
		// for testing locally
		return NoOpPasswordEncoder.getInstance();
		//		return new BCryptPasswordEncoder();
	}
}
