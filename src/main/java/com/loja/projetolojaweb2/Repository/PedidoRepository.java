package com.loja.projetolojaweb2.repository;

import com.loja.projetolojaweb2.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {


}
