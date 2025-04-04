//package com.medical.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
////@Configuration
////public class WebConfig implements WebMvcConfigurer {
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                .allowedOrigins("http://localhost:5000") // Allow only requests from this origin
////                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Allow specific HTTP methods
////                .allowedHeaders("*") // Allow all headers
////                .allowCredentials(true); // Enable credentials (cookies, authorization headers, etc.)
////    }
////}
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:5000","http://192.168.0.3:5000","http://localhost:80","http://62.72.29.190","http://www.haelanhomeopathy.com","http://62.72.29.190:80") // Frontend URL
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowCredentials(true)
//                .allowedHeaders("*");
//
//    }
//}
