package com.example.springbootessential2.repository;

import com.example.springbootessential2.domain.AnimeModel;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
/*
    @DataJpaTest ->
    - altera o banco de dados(para o que possui o scope test no pom), deve ser em do tipo memory(h2)
    - permite a injeção do repository
    - adota as caracteristicas de construção/relacionamentos de entidades no contexto do teste
*/
@DisplayName("Tests for Repository")
class AnimeRepositoryTest {
    @Autowired private AnimeRepository animeRepository;

    @Test
    public void save_PersistentAnime_WhenSuccessful(){
        AnimeModel animeModel = createAnime();
        AnimeModel saveAnime = this.animeRepository.save(animeModel);

        assertEquals(
                animeModel.getNome(), saveAnime.getNome()
        );
    }

    @Test
    public void save_UpdateAnime_WhenSuccessful(){
        AnimeModel animeModel = createAnime();
        AnimeModel saveAnime = this.animeRepository.save(animeModel);

        saveAnime.setNome("test update");
        AnimeModel updateAnime = this.animeRepository.save(saveAnime);

        assertAll(
                () -> assertEquals(saveAnime.getId(), updateAnime.getId()),
                () -> assertEquals(saveAnime.getNome(), updateAnime.getNome())
        );
    }

    @Test
    public void delete_RemoveAnime_WhenSuccessful(){
        AnimeModel animeModel = createAnime();
        AnimeModel saveAnime = this.animeRepository.save(animeModel);

        this.animeRepository.delete(saveAnime);

        Optional<AnimeModel> animeExist = this.animeRepository.findById(saveAnime.getId());

        assertNull(animeExist.orElse(null));
    }

    @Test
    public void finByNome_ReturnsListOfAnime_WhenSuccessful(){
        AnimeModel animeModel = createAnime();
        AnimeModel saveAnime = this.animeRepository.save(animeModel);

        List<AnimeModel> animes = this.animeRepository.findByNome(saveAnime.getNome());

        assertFalse(animes.isEmpty());
    }

    @Test
    public void save_ThrowsConstraintValidationException_NameIsEmpty(){
        AnimeModel animeModel = new AnimeModel("");

        assertThrows(
                ConstraintViolationException.class, () -> this.animeRepository.save(animeModel)
        );
    }

    private AnimeModel createAnime(){
        return new AnimeModel("teste");
    }
}