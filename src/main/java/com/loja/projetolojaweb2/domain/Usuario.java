package com.loja.projetolojaweb2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Pessoa {


    private List<String> favoritos;

    private static final long serialVersionUID = 1L;



}
