package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Avaliacao;

import java.util.List;

public interface AvaliacaoServiceInterface {

    public void add(Avaliacao avaliacao);
    public void edit(Avaliacao avaliacao);
    public void delete(Avaliacao avaliacao);
    public Avaliacao getAvaliacao(int id);
    public List<Avaliacao> getAvaliacoes();
    public void escreveComentario();
}
