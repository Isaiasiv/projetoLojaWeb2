package com.loja.projetolojaweb2.repository;

import com.loja.projetolojaweb2.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,String> {

}
