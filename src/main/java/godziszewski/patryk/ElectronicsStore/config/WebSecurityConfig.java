package godziszewski.patryk.ElectronicsStore.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import godziszewski.patryk.ElectronicsStore.filter.CsrfHeaderFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	PersistentTokenRepository tokenRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(authProvider());
		 auth.userDetailsService(userDetailsService);
		 }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().csrfTokenRepository(csrfTokenRepository())
		.and()
		.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
		.formLogin()
		.loginPage("/login").failureUrl("/loginfailed").successForwardUrl("/products")
		.and()
		.logout()
		.logoutUrl("/logout")
		.and()
		.rememberMe()
		.rememberMeParameter("remember-me")
		.tokenRepository(tokenRepository)
		.tokenValiditySeconds(2419200) // 4 weeks
		.and()
		.requiresChannel()
		.antMatchers("/login").requiresSecure()
		.and()
		.authorizeRequests()
		.antMatchers("/products/add").hasRole("ADMIN")
		.and()
		.requiresChannel()
		.antMatchers("/products/add").requiresSecure();
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
	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
	    "remember-me", userDetailsService, tokenRepository);
	    return tokenBasedservice;
	    }
	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
	    return new AuthenticationTrustResolverImpl();
	    }
	@Bean
	public CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
		}
}
