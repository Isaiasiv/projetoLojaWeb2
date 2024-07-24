package com.loja.projetolojaweb2.repository;

import com.loja.projetolojaweb2.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,String> {

}
