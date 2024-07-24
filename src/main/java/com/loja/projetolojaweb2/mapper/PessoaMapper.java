package com.loja.projetolojaweb2.mapper;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PessoaMapper {

public static final PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

public abstract Pessoa toPessoa(PessoaPostRequest pessoaPostRequest);

public abstract Pessoa toPessoa(PessoaPutRequest pessoaPutRequest);
}
