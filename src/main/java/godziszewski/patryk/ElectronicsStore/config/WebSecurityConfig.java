package godziszewski.patryk.ElectronicsStore.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//security filter chain

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;

	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(authProvider());
	    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
	.csrf()
	.disable()
	.formLogin()
	.loginPage("/login").failureUrl("/loginfailed").successForwardUrl("/products")
	.and()
	.logout().logoutSuccessUrl("/products")
	.and()
	.rememberMe()
	.tokenValiditySeconds(2419200) // 4 weeks
	.key("ElectronicsStoreLoginKey")
	.and()
	.requiresChannel()
	.antMatchers("/login").requiresSecure()
	.and()
	.authorizeRequests()
	.antMatchers("/products/add").hasRole("ADMIN")
	.and()
	.requiresChannel()
	.antMatchers("/products/add").requiresSecure()
	.and()
	.requiresChannel()
	.antMatchers("/").requiresInsecure();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	
}
