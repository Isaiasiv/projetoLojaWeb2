package com.loja.projetolojaweb2.mapper;

import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPostRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class FuncionarioMapper {
    public static final FuncionarioMapper INSTANCE = Mappers.getMapper(FuncionarioMapper.class);

    public abstract Funcionario toFuncionario(FuncionarioPostRequest funcionarioPostRequest);

    public abstract Funcionario toFuncionario(FuncionarioPutRequest funcionarioPutRequest);
}
