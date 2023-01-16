package com.example.springbootessential2.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AnimeRequestPut {
    @Min(0)
    private Long id;

    @NotEmpty
    @NotNull
    private String nome;
}
