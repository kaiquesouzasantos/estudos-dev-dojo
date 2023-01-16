package com.example.springbootessential2.client;

import com.example.springbootessential2.domain.AnimeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class SpringClient {
    public static void main(String[] args) {
        requisicaoSimples();
    }

    private static void requisicaoSimples(){
        ResponseEntity<AnimeModel> entity = new RestTemplate()
                // get/post For Entity/Object
                // getForEntity/Object(URI, <class_cast>.class)
                .getForEntity("http://localhost:8080/animes/3", AnimeModel.class);
        log.info(entity.toString());

        // [ou]

        AnimeModel anime = new RestTemplate()
                .getForObject("http://localhost:8080/animes/3", AnimeModel.class);
        log.info(anime.toString());

        // [ou]

        AnimeModel[] listAnime = new RestTemplate()
                .getForObject("http://localhost:8080/animes/all", AnimeModel[].class);
        log.info(anime.toString());

        // [ou]

        ResponseEntity<List<AnimeModel>> exchange = new RestTemplate()
                .exchange(
                        "http://localhost:8080/animes/all",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {}
                );
        log.info(String.valueOf(exchange.getBody()));
    }

    private static void requisicaoComParametros(){
        // os parametros passados na url devem ser argumentados sequencialmente
        ResponseEntity<AnimeModel> entity = new RestTemplate()
                .getForEntity("http://localhost:8080/animes/{id}", AnimeModel.class, 2);
        log.info(entity.toString());
    }

    private static void postObject(){
        AnimeModel kingdom = new AnimeModel();
        kingdom.setNome("kingdom");

        AnimeModel responseAnime = new RestTemplate().postForObject(
                "http://localhost:8080/animes",
                kingdom,
                AnimeModel.class
        );

        log.info(responseAnime.toString());

        // [ou]

        // possibilita o envio do corpo http completo(header, body)
        ResponseEntity<AnimeModel> responseAnimeExchenge = new RestTemplate().exchange(
                "http://localhost:8080/animes",
                HttpMethod.POST,
                new HttpEntity<>(kingdom, createJsonHeader()),
                AnimeModel.class
        );

        log.info(responseAnimeExchenge.toString());
    }

    private static void putObject(){
        AnimeModel kingdom = new AnimeModel();
        kingdom.setNome("kingdom");

        new RestTemplate().put(
                "http://localhost:8080/animes",
                kingdom
        );

        // [ou]

        ResponseEntity<Void> responseAnimeExchenge = new RestTemplate().exchange(
                "http://localhost:8080/animes",
                HttpMethod.PUT,
                new HttpEntity<>(kingdom, createJsonHeader()),
                Void.class
        );

        log.info(responseAnimeExchenge.toString());
    }

    private static void deleteObject(){
        AnimeModel kingdom = new AnimeModel();
        kingdom.setNome("kingdom");

        new RestTemplate().delete(
                "http://localhost:8080/animes/{id}", kingdom, 3
        );

        // [ou]

        ResponseEntity<Void> responseAnimeExchenge = new RestTemplate().exchange(
                "http://localhost:8080/animes/{id}",
                HttpMethod.DELETE, null, Void.class, 3
        );

        log.info(responseAnimeExchenge.toString());
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
