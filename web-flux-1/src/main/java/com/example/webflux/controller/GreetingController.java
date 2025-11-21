package com.example.webflux.controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello, Spring WebFlux!");
    }

    @GetMapping("/numbers")
    public Flux<Integer> numbers() {
        return Flux.range(1, 5);
    }

    @GetMapping("/hello/{name}")
    public Mono<String> helloName(@PathVariable String name) {
        return Mono.just("Hello, " + name + "!");
    }
}
