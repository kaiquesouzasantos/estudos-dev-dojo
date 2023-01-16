package com.example.springbootessential2.repository;

import com.example.springbootessential2.domain.AnimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<AnimeModel, Long> {
    List<AnimeModel> findByNome(String nome);
}
