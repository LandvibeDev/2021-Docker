package kr.or.connect.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "kr.or.connect.dao",  "kr.or.connect.service"})
@Import({ DBConfig.class })
public class ApplicationConfig {

}