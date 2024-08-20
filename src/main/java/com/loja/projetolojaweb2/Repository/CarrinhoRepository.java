package com.loja.projetolojaweb2.repository;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Optional<Carrinho> findByPessoa(Pessoa pessoa);
}
