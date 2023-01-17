package com.example.springbootessential2.util;

import com.example.springbootessential2.domain.AnimeModel;

public class AnimeCreator {
    public static AnimeModel createAnimeToBeSaved(){
        return new AnimeModel("teste");
    }

    public static AnimeModel createAnimeValid(){
        return new AnimeModel(1L, "teste");
    }
}
