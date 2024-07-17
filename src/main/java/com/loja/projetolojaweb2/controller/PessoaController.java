package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.service.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pessoas")
@Log4j2
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping("teste")
    public List<Pessoa> list(){

        return pessoaService.list();
    }
}
