package com.shopupspot.userauthenticationservice.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.shopupspot.userauthenticationservice.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http .authorizeHttpRequests((requests) -> requests
	 * .requestMatchers("/", "/api/", "/api/register/").permitAll()
	 * .anyRequest().authenticated()
	 * 
	 * ).csrf((csrf) -> csrf.disable());
	 * 
	 * 
	 * return http.build(); }
	 */
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
            .authorizeRequests((authorize) -> authorize.requestMatchers("/api/register")
                .permitAll() // Allow access to /api/register without authentication
                .requestMatchers("/api/**").authenticated() // Require authentication for other /api/** endpoints
            )
            .httpBasic(); // Use HTTP Basic authentication (you can use other methods like formLogin or OAuth)
        // @formatter:on
        return http.build();
    }*/
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic();
		/*http.formLogin()
		.successHandler(authenticationSuccessHandler)
		.failureHandler(authenticationFailureHandler);*/
		//.defaultSuccessUrl("/home", true);
		//http.authenticationProvider(authenticationProvider);
		/*http.authorizeRequests()
		.anyRequest().authenticated();*/
		http
		//.csrf((csrf) -> csrf
        //        .ignoringRequestMatchers(mvc.pattern("/api/register")) // Disable CSRF protection for /api/register
         //   )
		.csrf().disable()
		.authorizeRequests()
		.requestMatchers("/api/register", "/api/login").permitAll()
		.anyRequest().authenticated()
		.and().addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);;
		//.anyRequest().permitAll();
		
		
		
        return http.build();
    }
	
	@Scope("prototype")
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
		return new MvcRequestMatcher.Builder(introspector);
	}
	
	
	
	

	/*
	 * @Bean public UserDetailsService userDetailsService() { UserDetails user =
	 * User.withDefaultPasswordEncoder() .username("user") .password("password")
	 * .roles("USER") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user); }
	 */
}