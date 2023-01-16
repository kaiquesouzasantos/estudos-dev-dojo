package com.example.springbootessential2.mapper;

import com.example.springbootessential2.domain.AnimeModel;
import org.springframework.beans.BeanUtils;

public class AnimeMapper<T> {
    public AnimeModel toAnime(T animeEntrada){
        AnimeModel animeSaida = new AnimeModel();
        BeanUtils.copyProperties(animeEntrada, animeSaida);
        return animeSaida;
    }
}
