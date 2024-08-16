package com.loja.projetolojaweb2.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.management.ConstructorParameters;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "4200")
public class Usuario extends Pessoa {



    @Nullable
    private List<String> favoritos;



    private static final long serialVersionUID = 1L;

    //No super o numero 1 é referente ao tipoConta.
//    public Usuario(String login, String senha, Long cpf, String nome, String telefone, String email, String dataNascimento, int tipoConta, List<String> favoritos) {
//        super(login, senha, cpf, nome, telefone, email, dataNascimento,tipoConta);
//       this.favoritos = favoritos;
    //}

}
