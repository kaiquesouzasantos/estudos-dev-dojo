package com.example.springbootessential2.domain;

// import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animes")
public class AnimeModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonProperty("") -> quando o atributo JSON é diferente do nome da variavel
    @Column(nullable = false) @NotBlank
    private String nome;

    public AnimeModel(String nome) {
        this.nome = nome;
    }
}
