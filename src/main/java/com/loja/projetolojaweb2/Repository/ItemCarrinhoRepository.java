package com.loja.projetolojaweb2.Repository;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.ItemCarrinho;
import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.carrinhoDto.ItemCarrinhoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho,Long > {

   // ItemCarrinho findByCarrinhoAndProduto(Carrinho carrinho, Produto produto);
}
