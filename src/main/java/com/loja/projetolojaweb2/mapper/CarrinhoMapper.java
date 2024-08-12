package com.loja.projetolojaweb2.mapper;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPostRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CarrinhoMapper {

    public static final CarrinhoMapper INSTANCE = Mappers.getMapper(CarrinhoMapper.class);

    public abstract Carrinho toCarrinho(CarrinhoPostRequest carrinhoPostRequest);

    public abstract Carrinho toCarrinho(CarrinhoPutRequest carrinhoPutRequest);

}
