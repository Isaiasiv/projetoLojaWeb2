package com.loja.projetolojaweb2.dto.EnderecoToPessoaDto;

import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import lombok.Data;

@Data
public class EnderecoToPessoaPutRequest {
    private Long enderecoId;
    private String loginPessoa;
}
