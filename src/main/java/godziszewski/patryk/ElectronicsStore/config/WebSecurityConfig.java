package godziszewski.patryk.ElectronicsStore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//security filter chain
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("user").roles("USER")
				.and()
				.withUser("admin").password("admin").roles("USER", "ADMIN");;
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
	.authorizeRequests()
	.antMatchers("/products/add").hasRole("ADMIN")
	.and()
	.requiresChannel()
	.antMatchers("/products/add").requiresSecure()
	.and()
	.requiresChannel()
	.antMatchers("/").requiresInsecure();
	}
}
