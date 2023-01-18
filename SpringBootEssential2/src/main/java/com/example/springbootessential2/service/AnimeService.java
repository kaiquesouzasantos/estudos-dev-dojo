package com.example.springbootessential2.service;

import com.example.springbootessential2.request.AnimeRequestBody;
import com.example.springbootessential2.request.AnimeRequestPut;
import com.example.springbootessential2.domain.AnimeModel;
import com.example.springbootessential2.exception.ExceptionGeneric;
import com.example.springbootessential2.mapper.AnimeMapper;
import com.example.springbootessential2.repository.AnimeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

@RequiredArgsConstructor
@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    // URL -> default
    // URL?size=<tamanho> -> limita por ordenacao
    // URL?page=<numero_pagina> -> pagina especifica
    // URL?page=<numero_pagina> -> pagina especifica
    // URL?sort=<atributo>,ASC/DESC -> ordenacao
    public Page<AnimeModel> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public List<AnimeModel> listAll(){
        return animeRepository.findAll();
    }

    public List<AnimeModel> findByName(String nome){
        return animeRepository.findByNome(nome);
    }

    public AnimeModel findById(long id){
        return animeRepository.findById(id)
                .orElseThrow(() -> new ExceptionGeneric("ANIME NO CONTENT", "ANIME NOT FOUND", HttpStatus.BAD_REQUEST.value()));
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public AnimeModel save(AnimeRequestBody animeRequestBody) {
        return animeRepository.save(new AnimeMapper<AnimeRequestBody>().toAnime(animeRequestBody));
    }

    public void delete(long id) {
        animeRepository.deleteById(id);
    }

    public void replace(AnimeRequestPut animeRequestPut){
        this.findById(animeRequestPut.getId());
        animeRepository.save(new AnimeMapper<AnimeRequestPut>().toAnime(animeRequestPut));
    }
}
