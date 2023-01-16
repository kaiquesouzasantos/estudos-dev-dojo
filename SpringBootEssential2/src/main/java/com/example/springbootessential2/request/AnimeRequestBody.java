package com.example.springbootessential2.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnimeRequestBody {
    @NotBlank
    private String nome;
}
