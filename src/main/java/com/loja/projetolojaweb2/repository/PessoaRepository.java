package com.loja.projetolojaweb2.repository;

import com.loja.projetolojaweb2.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,String> {

}
