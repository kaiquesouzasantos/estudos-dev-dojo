package com.example.springbootessential2.controller;

import com.example.springbootessential2.domain.AnimeModel;
import com.example.springbootessential2.request.AnimeRequestBody;
import com.example.springbootessential2.service.AnimeService;
import com.example.springbootessential2.util.AnimeCreator;
import com.example.springbootessential2.util.AnimePostRequestBodyCreator;
import com.example.springbootessential2.util.AnimePutRequestBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnimeControllerTest {
    @InjectMocks // -> utiliza-se quando deseja testar a classe em si
    private AnimeController animeController;
    @Mock // -> utiliza-se quando deseja o alterar o comportamento da classe/objeto contida no componente
    private AnimeService animeService;

    @BeforeEach
    public void setUp(){
        /*
            ArgumentMatchers.any[...]() ->
                any() -> qualquer objeto diferente de um Wrapper, esperado de forma opcional
                any(classe.class) -> any com arguemento obrigatorio
                any[wrapper] -> anyString(), anyLong(), anyShort() ...
        */

        BDDMockito
                .when(animeService.listAll(ArgumentMatchers.any())) // quando chamar/executar ... independendo de argumentos
                .thenReturn(new PageImpl<>(List.of(AnimeCreator.createAnimeValid()))); // retorne  ...

        BDDMockito
                .when(animeService.listAll())
                .thenReturn(List.of(AnimeCreator.createAnimeValid()));

        BDDMockito
                .when(animeService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(AnimeCreator.createAnimeValid());

        BDDMockito
                .when(animeService.findbyName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createAnimeValid()));

        BDDMockito
                .when(animeService.save(ArgumentMatchers.any(AnimeRequestBody.class)))
                .thenReturn(AnimeCreator.createAnimeValid());

        BDDMockito
                .doNothing()
                .when(animeService)
                .replace(ArgumentMatchers.any());

        BDDMockito
                .doNothing()
                .when(animeService)
                .delete(ArgumentMatchers.anyLong());
    }

    @Test
    public void list_ReturnslistOfAnimeInsidePageObject_WhenSuccessful(){
        String animeNameExpected = AnimeCreator.createAnimeValid().getNome();
        Page<AnimeModel> animePage = animeController.list(null).getBody();

        assertAll(
                () -> assertNotNull(animePage),
                () -> assertEquals(animeNameExpected, animePage.toList().get(0).getNome())
        );
    }

    @Test
    public void listAll_ReturnslistOfAnime_WhenSuccessful(){
        String animeNameExpected = AnimeCreator.createAnimeValid().getNome();
        List<AnimeModel> animeList = animeController.listAll().getBody();

        assertAll(
                () -> assertNotNull(animeList),
                () -> assertTrue(animeList.size() > 0),
                () -> assertEquals(animeNameExpected, animeList.get(0).getNome())
        );
    }

    @Test
    public void findById_ReturnsAnime_WhenSuccessfull(){
        Long idExpceted = AnimeCreator.createAnimeValid().getId();
        AnimeModel anime = animeController.findById(1L).getBody();

        assertAll(
                () -> assertNotNull(anime),
                () -> assertEquals(idExpceted, anime.getId())
        );
    }

    @Test
    public void findByName_ReturnsListAnime_WhenSuccessfull(){
        String nameExpected = AnimeCreator.createAnimeValid().getNome();
        List<AnimeModel> listAnime = animeController.findByName(nameExpected).getBody();

        assertAll(
                () -> assertNotNull(listAnime),
                () -> assertTrue(listAnime.size() > 0),
                () -> assertEquals(nameExpected, listAnime.get(0).getNome())
        );
    }

    @Test
    public void save_ReturnsAnime_WhenSuccessful(){
        AnimeModel animeExpceted = AnimeCreator.createAnimeValid();
        AnimeModel anime = animeController.save(AnimePostRequestBodyCreator.createAnimePostRequestBody()).getBody();

        assertAll(
                () -> assertNotNull(anime),
                () -> assertEquals(animeExpceted.getNome(), anime.getNome())
        );
    }

    @Test
    public void replace_UpdatesAnime_WhenSuccessful(){
        HttpStatusCode status = animeController.replace(AnimePutRequestBody.createAnimePutRequestBody()).getStatusCode();
        assertEquals(HttpStatus.NO_CONTENT, status);
    }

    @Test
    public void delete_RemoveAnime_WhenSuccessful(){
        HttpStatusCode status = animeController.delete(1L).getStatusCode();
        assertEquals(HttpStatus.NO_CONTENT, status);
    }
}