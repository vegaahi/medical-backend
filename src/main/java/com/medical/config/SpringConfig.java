<<<<<<< HEAD
//package com.medical.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
=======
////package com.medical.config;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.context.annotation.Lazy;
////import org.springframework.security.authentication.AuthenticationProvider;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.Customizer;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.NoOpPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.provisioning.InMemoryUserDetailsManager;
////import org.springframework.security.web.SecurityFilterChain;
////
////@Configuration
////@EnableWebSecurity
////public class SpringConfig {
//// 
////	private UserDetailsService userDetailsService;
////     
////	public SpringConfig(UserDetailsService userDetailsService) {
////        this.userDetailsService = userDetailsService;
////    }
//// 
////	@Bean
////	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////      http.csrf(customizer->customizer.disable());
////	  http.authorizeHttpRequests(request->request.anyRequest().authenticated());
////	  //http.formLogin(Customizer.withDefaults());
////	  http.httpBasic(Customizer.withDefaults());
////	  http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////		return http.build();
////	}
////	@Bean
////	public AuthenticationProvider authenticationProvider() {
////		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////		 provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
////         provider.setUserDetailsService(userDetailsService);
////		return provider;	
////	}
////	
////	
//////	@Bean
//////	public UserDetailsService userDetailsService(){
//////        UserDetails user1=User.withDefaultPasswordEncoder()
//////                             .username("dilip")
//////                             .password("d@123")
//////                             .roles("USER")
//////                             .build();
//////        UserDetails user2=User.withDefaultPasswordEncoder()
//////                .username("sai")
//////                .password("s@123")
//////                .roles("ADMIN")
//////                .build();
//////        		
//////		return new InMemoryUserDetailsManager(user1,user2); 
//////	}
////	
////}
//
//package com.medical.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
>>>>>>> a865f9c928f791d09cc5bfe218bc1dbfd5a09fa2
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
<<<<<<< HEAD
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
=======
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> a865f9c928f791d09cc5bfe218bc1dbfd5a09fa2
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringConfig {
<<<<<<< HEAD
// 
//	private UserDetailsService userDetailsService;
//     
//	public SpringConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
// 
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//      http.csrf(customizer->customizer.disable());
//	  http.authorizeHttpRequests(request->request.anyRequest().authenticated());
//	  //http.formLogin(Customizer.withDefaults());
//	  http.httpBasic(Customizer.withDefaults());
//	  http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		return http.build();
//	}
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		 provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//         provider.setUserDetailsService(userDetailsService);
//		return provider;	
//	}
//	
//	
////	@Bean
////	public UserDetailsService userDetailsService(){
////        UserDetails user1=User.withDefaultPasswordEncoder()
////                             .username("dilip")
////                             .password("d@123")
////                             .roles("USER")
////                             .build();
////        UserDetails user2=User.withDefaultPasswordEncoder()
////                .username("sai")
////                .password("s@123")
////                .roles("ADMIN")
////                .build();
////        		
////		return new InMemoryUserDetailsManager(user1,user2); 
////	}
//	
//}

=======
//
//    private final UserDetailsService userDetailsService;
//
//    public SpringConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())  // Disable CSRF (for stateless sessions)
//            .authorizeHttpRequests(auth -> auth
//                // Allow unrestricted access to the admin controller endpoints
//                .requestMatchers("/admins/**").permitAll()
//                
//                // Permit access to other public endpoints
//                .requestMatchers("/public/**").permitAll()
//                
//                // Require authentication for any other request
//                .anyRequest().authenticated()
//            )
//            .httpBasic(Customizer.withDefaults())  // Basic authentication
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Stateless session
//
//        return http.build();
//    }
//
//    
//    
//    
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));  // Password encoder
//        provider.setUserDetailsService(userDetailsService);  // Custom user details service
//        return provider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }
//}


>>>>>>> a865f9c928f791d09cc5bfe218bc1dbfd5a09fa2
package com.medical.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
=======
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;
>>>>>>> a865f9c928f791d09cc5bfe218bc1dbfd5a09fa2

@Configuration
@EnableWebSecurity
public class SpringConfig {

<<<<<<< HEAD
    private final UserDetailsService userDetailsService;

    public SpringConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Disable CSRF (for stateless sessions)
            .authorizeHttpRequests(auth -> auth
                // Allow unrestricted access to the admin controller endpoints
                .requestMatchers("/admins/**").permitAll()
                
                // Permit access to other public endpoints
                .requestMatchers("/public/**").permitAll()
                
                // Require authentication for any other request
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())  // Basic authentication
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Stateless session
=======
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for APIs (adjust as necessary)
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll() // Allow all requests (adjust as necessary)
            );
>>>>>>> a865f9c928f791d09cc5bfe218bc1dbfd5a09fa2

        return http.build();
    }

    @Bean
<<<<<<< HEAD
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));  // Password encoder
        provider.setUserDetailsService(userDetailsService);  // Custom user details service
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
=======
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Allow all origins
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")); // Allow all HTTP methods
        configuration.setAllowedHeaders(List.of("*")); // Allow all headers
        configuration.setAllowCredentials(true); // Allow credentials

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply to all paths
        return source;
>>>>>>> a865f9c928f791d09cc5bfe218bc1dbfd5a09fa2
    }
}
