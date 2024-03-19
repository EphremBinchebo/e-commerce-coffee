/**

package com.greencoffee.admin.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
     UserDetailsService userDetailsService() {
		return new GreencoffeeUserDetailsService();
	}
	 
	 @Bean
	 DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setUserDetailsService(userDetailsService());
			authProvider.setPasswordEncoder(passwordEncoder());

			return authProvider;
		}
      
	  @Bean
	  SecurityFilterChain configureHttpSecurity(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		//http.authorizeRequests().anyRequest().permitAll();
		 http.authenticationProvider(authenticationProvider());
		 http.authorizeHttpRequests( auth -> auth
		     .requestMatchers("/users/**", "/settings/**", "/countries/**", "/states/**").hasAuthority("Admin")
		     .requestMatchers("/categories/**","/brands/**","/menus/**", "/articles/**").hasAnyAuthority("Admin", "Editor")
		     .requestMatchers("/products/new", "/products/delete/**").hasAnyAuthority("Admin", "Editor")
		     .requestMatchers("/products/edit/**", "/products/save", "/products/check_unique")
			 .hasAnyAuthority("Admin", "Editor", "Salesperson")
		     .requestMatchers("/products", "/products/", "/products/detail/**", "/products/page/**")
			   .hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
		     .requestMatchers("/products/**","/articles/**", "/menus/**", "/sections/**").hasAnyAuthority("Admin", "Editor")
		     .requestMatchers("/customers/**","/shipping/**","/articles/**", "/get_shipping_cost", "/reports/**").hasAnyAuthority("Admin", "Salesperson")
		     .requestMatchers("/orders", "/orders/", "/orders/page/**", "/orders/detail/**").hasAnyAuthority("Admin", "Salesperson", "Shipper")
		     .requestMatchers("/states/list_by_country/**").hasAnyAuthority("Admin", "Salesperson")
		     .requestMatchers("/orders_shipper/update/**").hasAuthority("Shipper")
		.requestMatchers("/products/detail/**", "/customers/detail/**").hasAnyAuthority("Admin", "Editor", "Salesperson", "Assistant")
		.requestMatchers("/reviews/**", "/questions/**").hasAnyAuthority("Admin", "Assistant")
		.anyRequest().authenticated()
		)
		.formLogin(form -> form			
			.loginPage("/login")
			.usernameParameter("email")
			.permitAll()
		)	
		.logout(logout -> logout.permitAll())
		
		.rememberMe(rem -> rem
			.key("AbcDefgKLDSLmvop_0123456789")
			.tokenValiditySeconds(7 * 24 * 60 * 60) // 7 days 24 hours 60 minutes 60 seconds -> 7days 
			);
			
			
		http.headers().frameOptions().sameOrigin();	
		
     return http.build();		
	}
	
	// Before authenticated, all matchers can be ignored and all these are performed.
	// Like -> showing image in /login , all images,js and web jars files are detected  
	@Bean
	WebSecurityCustomizer configure() throws Exception {
		return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**" , "/css/**");
	}
	


	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.authenticationProvider(authenticationProvider()); }
	 */
	
//}

