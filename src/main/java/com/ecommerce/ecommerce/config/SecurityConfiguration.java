package com.ecommerce.ecommerce.config;

import com.ecommerce.ecommerce.constants.SecurityConstants;
import com.ecommerce.ecommerce.pojo.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import com.ecommerce.ecommerce.service.CustomUserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackages = {"com.ecommerce.ecommerce.dao"})
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JwtAuthenticationEntryPoint unAuthorizedHandler;

	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter() { return new JWTAuthenticationFilter();}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(unAuthorizedHandler).and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.headers().frameOptions().sameOrigin()
				.and()
				.authorizeRequests()
				.antMatchers(
						"/",
						"/**/*.png",
						"/**/*.gif",
						"/favicon.ico",
						"/**/*.jpg",
						"/**/*.svg",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js"
				).permitAll()
				.antMatchers(SecurityConstants.SIGN_UP_URL).permitAll()
				.anyRequest().authenticated();

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

//		http.authorizeRequests()
//			.antMatchers("**/secured/**").authenticated()
//			.anyRequest().permitAll()
//			.and().formLogin().permitAll();
	}







	
	
}
