package com.example.ricknnmorty;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RickNMortyResult(
//        @JsonProperty("result")
        List<RickNMortyCharacter> results
) {
}
