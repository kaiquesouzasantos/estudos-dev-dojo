package com.example.springbootessential2.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeRequestPut {
    @Min(0)
    private Long id;

    @NotEmpty
    @NotNull
    private String nome;
}
