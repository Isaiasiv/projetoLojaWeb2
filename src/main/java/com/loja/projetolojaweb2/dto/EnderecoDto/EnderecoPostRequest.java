package com.loja.projetolojaweb2.dto.EnderecoDto;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class EnderecoPostRequest {

    private String cep;
    private String rua;
    private String setor;
    private String uf;
    private String cidade;
    private String complemento;
    private PessoaPostRequest pessoa;

}
