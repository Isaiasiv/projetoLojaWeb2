package com.loja.projetolojaweb2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "endereco" )
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String cep;
    @NonNull
    private String rua;
    @NonNull
    private String setor;
    @NonNull
    private String uf;
    @NonNull
    private String cidade;

    private String complemento;

    @ManyToOne()
    @JsonIgnore
   @JoinColumn(name = "fk_pessoa_login")
    private Pessoa pessoa;

}
