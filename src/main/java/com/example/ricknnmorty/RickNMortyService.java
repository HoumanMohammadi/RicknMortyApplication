package com.example.ricknnmorty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
@Service
public class RickNMortyService {

    private final WebClient webClient;
    private final WebClient webClientStudent= WebClient.create("http://localhost:8080/api/");

    public RickNMortyService(
            @Value("${rickety.url}") String url
    ) {
        this.webClient=WebClient.create(url);
    }
//this.webClient=WebClient.create("https://rickandmortyapi.com/api/");
    List<RickNMortyCharacter> getRickNMortyCharacter(){
        ResponseEntity<RickNMortyResult> responseEntity=webClient.get()
                .uri("character")
                .retrieve()
                .toEntity(RickNMortyResult.class)
                .block();

        return Objects.requireNonNull(Objects.requireNonNull(responseEntity).getBody()).results();


    }

    Student addStudent(Student newStudent){
        ResponseEntity<Student> responseEntity=webClientStudent.post()
                .uri("students")
                .bodyValue(newStudent)
                .retrieve()
                .toEntity(Student.class)
                .block();

        return Objects.requireNonNull(Objects.requireNonNull(responseEntity).getBody());


    }
}
