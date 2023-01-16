package com.example.springbootessential2.controller;

import com.example.springbootessential2.request.AnimeRequestBody;
import com.example.springbootessential2.request.AnimeRequestPut;
import com.example.springbootessential2.domain.AnimeModel;
import com.example.springbootessential2.service.AnimeService;
import com.example.springbootessential2.util.DateUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/animes")
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping("/")
    public ResponseEntity<Page<AnimeModel>> list(Pageable pageable) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.status(HttpStatus.OK).body(animeService.listAll(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnimeModel>> listAll() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.status(HttpStatus.OK).body(animeService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeModel> findById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(animeService.findById(id));
    }

    @GetMapping("/find")
    // @RequestParam ou @RequestParam("<nomeParametro>") ou @RequestParam(name = "<nomeParametro>")
    public ResponseEntity<List<AnimeModel>> findById(@RequestParam(required = false) String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(animeService.findbyName(nome));
    }

    @PostMapping
    public ResponseEntity<AnimeModel> save(@RequestBody @Valid AnimeRequestBody anime){
        return ResponseEntity.status(HttpStatus.CREATED).body(animeService.save(anime));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        animeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid AnimeRequestPut anime){
        animeService.replace(anime);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
