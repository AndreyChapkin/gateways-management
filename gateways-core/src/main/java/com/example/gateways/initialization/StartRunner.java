package com.example.gateways.initialization;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartRunner implements CommandLineRunner {
    private Initializer initializer;

    @Override
    public void run(String... args) throws Exception {
        initializer.initialize();
    }
}