package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Avaliacao;
import com.loja.projetolojaweb2.Repository.AvaliacaoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.AvaliacaoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService implements AvaliacaoServiceInterface {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Override
    public void add(Avaliacao avaliacao) {
        avaliacaoRepository.save(avaliacao);
    }

    @Override
    public void edit(Avaliacao avaliacao) {
        avaliacaoRepository.save(avaliacao);
    }

    @Override
    public void delete(Avaliacao avaliacao) {
        avaliacaoRepository.delete(avaliacao);
    }

    @Override
    public Avaliacao getAvaliacao(int id) {
        return avaliacaoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Avaliacao> getAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    @Override
    public void escreveComentario() {
        // Implementação específica aqui
    }

    public List<Avaliacao> findByAvaliacao(int estrela) {
        return avaliacaoRepository.findByEstrela(estrela);
    }
}
