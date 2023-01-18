package com.example.springbootessential2.service;

import com.example.springbootessential2.domain.AnimeModel;
import com.example.springbootessential2.exception.ExceptionGeneric;
import com.example.springbootessential2.repository.AnimeRepository;
import com.example.springbootessential2.util.AnimeCreator;
import com.example.springbootessential2.util.AnimePostRequestBodyCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnimeServiceTest {
    @InjectMocks private AnimeService animeService;
    @Mock private AnimeRepository animeRepository;

    @BeforeEach
    public void setUp(){
        BDDMockito
                .when(animeRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(AnimeCreator.createAnimeValid())));

        BDDMockito
                .when(animeRepository.findAll())
                .thenReturn(List.of(AnimeCreator.createAnimeValid()));

        BDDMockito
                .when(animeRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(AnimeCreator.createAnimeValid()));

        BDDMockito
                .when(animeRepository.findByNome(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createAnimeValid()));

        BDDMockito
                .when(animeRepository.save(ArgumentMatchers.any(AnimeModel.class)))
                .thenReturn(AnimeCreator.createAnimeValid());

        BDDMockito
                .doNothing()
                .when(animeRepository)
                .delete(ArgumentMatchers.any(AnimeModel.class));
    }

    @Test
    public void listAll_ReturnslistOfAnimeInsidePageObject_WhenSuccessful(){
        String animeNameExpected = AnimeCreator.createAnimeValid().getNome();
        Page<AnimeModel> animePage = animeService.listAll(PageRequest.of(1,1));

        assertAll(
                () -> assertNotNull(animePage),
                () -> assertEquals(animeNameExpected, animePage.toList().get(0).getNome())
        );
    }

    @Test
    public void listAll_ReturnslistOfAnime_WhenSuccessful(){
        String animeNameExpected = AnimeCreator.createAnimeValid().getNome();
        List<AnimeModel> animeList = animeService.listAll();

        assertAll(
                () -> assertNotNull(animeList),
                () -> assertTrue(animeList.size() > 0),
                () -> assertEquals(animeNameExpected, animeList.get(0).getNome())
        );
    }

    @Test
    public void findById_ReturnsAnime_WhenSuccessfull(){
        Long idExpceted = AnimeCreator.createAnimeValid().getId();
        AnimeModel anime = animeService.findById(1L);

        assertAll(
                () -> assertNotNull(anime),
                () -> assertEquals(idExpceted, anime.getId())
        );
    }

    @Test
    public void findById_ReturnsException_WhenAnimeIsNotFound(){
        BDDMockito
                .when(animeRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(
                ExceptionGeneric.class, () -> animeService.findById(1L)
        );
    }

    @Test
    public void findByName_ReturnsListAnime_WhenSuccessfull(){
        String nameExpected = AnimeCreator.createAnimeValid().getNome();
        List<AnimeModel> listAnime = animeService.findByName(nameExpected);

        assertAll(
                () -> assertNotNull(listAnime),
                () -> assertTrue(listAnime.size() > 0),
                () -> assertEquals(nameExpected, listAnime.get(0).getNome())
        );
    }

    @Test
    public void save_ReturnsAnime_WhenSuccessful(){
        AnimeModel animeExpceted = AnimeCreator.createAnimeValid();
        AnimeModel anime = animeService.save(AnimePostRequestBodyCreator.createAnimePostRequestBody());

        assertAll(
                () -> assertNotNull(anime),
                () -> assertEquals(animeExpceted.getNome(), anime.getNome())
        );
    }

    @Test
    public void replace_UpdatesAnime_WhenSuccessful(){
        // assertDoesNotThrow(Exception.class, () -> animeService.replace(AnimePutRequestBody.createAnimePutRequestBody()));
    }

    @Test
    public void delete_RemoveAnime_WhenSuccessful(){
        // assertDoesNotThrow(Exception.class, () -> animeService.delete(1L));
    }
}