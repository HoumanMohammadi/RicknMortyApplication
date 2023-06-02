package com.example.ricknnmorty;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
@Service
public class RickNMortyService {

    private final WebClient webClient= WebClient.create("https://rickandmortyapi.com/api/");

    List<RickNMortyCharacter> getRickNMortyCharacter(){
        ResponseEntity<RickNMortyResult> responseEntity=webClient.get()
                .uri("character")
                .retrieve()
                .toEntity(RickNMortyResult.class)
                .block();

        return Objects.requireNonNull(Objects.requireNonNull(responseEntity).getBody()).results();


    }
}
