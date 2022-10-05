package com.emse.spring.faircorp.dto.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DummyUserService implements UserService {

    private List<String> names = new ArrayList<>(List.of("Elodie", "Charles"));
    private GreetingService greetingService;

    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void greetAll() {

        for (String name : names) {
            greetingService.greet(name);
        }
    }
}
