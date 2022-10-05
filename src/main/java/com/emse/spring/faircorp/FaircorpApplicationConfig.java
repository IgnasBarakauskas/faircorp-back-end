package com.emse.spring.faircorp;

import com.emse.spring.faircorp.dto.hello.ConsoleGreetingService;
import com.emse.spring.faircorp.dto.hello.GreetingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaircorpApplicationConfig {
    @Bean
    public CommandLineRunner greetingCommandLine() {
        GreetingService greetingService = new ConsoleGreetingService();
        return args -> {
            greetingService.greet("Spring");
        };
    }
}
