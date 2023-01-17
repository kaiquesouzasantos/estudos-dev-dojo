package com.example.springbootessential2.util;

import com.example.springbootessential2.request.AnimeRequestBody;

public class AnimePostRequestBodyCreator {
    public static AnimeRequestBody createAnimePostRequestBody(){
        return new AnimeRequestBody(AnimeCreator.createAnimeToBeSaved().getNome());
    }
}
