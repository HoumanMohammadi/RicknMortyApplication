package com.example.ricknnmorty;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    Student addNewStudent(@RequestBody Student student){
        return service.addStudent(student);
    }
}
