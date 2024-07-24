package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Avaliacao;
import com.loja.projetolojaweb2.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/estrela/{estrela}")
    public List<Avaliacao> avaliacao(@PathVariable("estrela") int estrela) {
        return avaliacaoService.findByAvaliacao(estrela);
    }
}
