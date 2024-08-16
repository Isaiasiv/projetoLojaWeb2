package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.EnderecoToPessoaDto.EnderecoToPessoaPutRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import com.loja.projetolojaweb2.mapper.PessoaMapper;
import com.loja.projetolojaweb2.repository.CarrinhoRepository;
import com.loja.projetolojaweb2.repository.EnderecoRepository;
import com.loja.projetolojaweb2.repository.PessoaRepository;
import com.loja.projetolojaweb2.service.serviceInterface.PessoaServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class PessoaService implements PessoaServiceInterface {

    private final PessoaRepository pessoaRepository;

    private final EnderecoRepository enderecoRepository;

    private final CarrinhoRepository carrinhoRepository;

    @Override
    public List<Pessoa> encontrarTodos() {
        return List.of();
    }

    @Override
    public Pessoa encontrarPorIdOuLancarExcecao(String login) {
        return pessoaRepository.findById(login)
            .orElseThrow(() ->new ResponseStatusException(BAD_REQUEST,"Pessoa não encontrada"));
    }

    @Override
    public void atualizar(PessoaPutRequest pessoaPutRequest) {

        Pessoa pessoaSalva = encontrarPorIdOuLancarExcecao(pessoaPutRequest.getLogin());
        Pessoa pessoa = PessoaMapper.INSTANCE.toPessoa(pessoaPutRequest);
        pessoa.setLogin(pessoaSalva.getLogin());

        //if(carrinhoOptional.isPresent()) {
           // pessoa.setCarrinho(carrinhoOptional.get());
       // }
        pessoaRepository.save(pessoa);
    }


    @Override
    public Pessoa salvar(PessoaPostRequest pessoaPostRequest) {
        return pessoaRepository.save(PessoaMapper.INSTANCE.toPessoa(pessoaPostRequest));
    }

    @Override
    public void delete(String login) {
        pessoaRepository.delete(encontrarPorIdOuLancarExcecao(login));
    }

//Melhorar esse metodo
    @Override
    public Pessoa fazerLogin(String usuario, String senha) {
        Pessoa pessoa = encontrarPorIdOuLancarExcecao(usuario);

        if(pessoa.getLogin().equals(usuario) && pessoa.getSenha().equals(senha)) {
            System.out.println("DEU CERTO!!!!");
            return pessoa;
        }
    throw new ResponseStatusException(UNAUTHORIZED,"Login ou senha incorretos");
    }

    @Override
    public void fazerLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }

    @Override
    public Pessoa recuperarSenha(PessoaPutRequest pessoaPutRequest) {
        Pessoa pessoaSalva = encontrarPorIdOuLancarExcecao(pessoaPutRequest.getLogin());

        pessoaSalva.setSenha(pessoaPutRequest.getSenha());
        pessoaRepository.save(pessoaSalva);
        return pessoaSalva;
    }

    @Override
    public Pessoa configurarConta() {
        return null;
    }
    @Override
    public List<Pessoa> list(){
    return null;
    }

    public List<Pessoa> listAll() {
      return  pessoaRepository.findAll();
    }

    @Override
    public Endereco addEnderecoToPessoa(EnderecoToPessoaPutRequest enderecoToPessoaPutRequest){
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(enderecoToPessoaPutRequest.getLoginPessoa());
       Optional<Endereco> enderecoOptional = enderecoRepository.findById(enderecoToPessoaPutRequest.getEnderecoId());

        if (pessoaOptional.isPresent() && enderecoOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            Endereco endereco = enderecoOptional.get();
            pessoa.addEndereco(endereco);
            return enderecoRepository.save(endereco);
        }

        throw new RuntimeException("Pessoa ou endereço não encontrado");

    }

    @Override
    public void verificarLoginOuLancarExcecao(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("Usuario logado") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED ,"Usuário não está logado :(");
        }
    }

}
