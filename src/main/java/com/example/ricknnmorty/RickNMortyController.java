package com.example.ricknnmorty;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/RickMorty")
@RequiredArgsConstructor
public class RickNMortyController {

    private final RickNMortyService service;

    @GetMapping
    List<RickNMortyCharacter> getRickMortyCharacter(){
        return service.getRickNMortyCharacter();
    }
}
