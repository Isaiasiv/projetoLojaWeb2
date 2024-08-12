package com.loja.projetolojaweb2.repository;

import com.loja.projetolojaweb2.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {

}
