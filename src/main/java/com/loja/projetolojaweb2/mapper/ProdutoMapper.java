package com.loja.projetolojaweb2.mapper;

import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPostRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {

    public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    public abstract Produto toProduto(ProdutoPostRequest produtoPostRequest);

    public abstract Produto toProduto(ProdutoPutRequest produtoPutRequest);
}
