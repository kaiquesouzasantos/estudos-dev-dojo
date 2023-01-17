package com.example.springbootessential2.util;

import com.example.springbootessential2.request.AnimeRequestPut;

public class AnimePutRequestBody {
    public static AnimeRequestPut createAnimePutRequestBody() {
        return new AnimeRequestPut(AnimeCreator.createAnimeValid().getId(), AnimeCreator.createAnimeValid().getNome());
    }
}
