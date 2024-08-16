package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.EnderecoToPessoaDto.EnderecoToPessoaPutRequest;
import com.loja.projetolojaweb2.dto.loginRequest.LoginRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import com.loja.projetolojaweb2.service.PessoaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/pessoas")
@Log4j2
@RequiredArgsConstructor

public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping(path="/{login}")
    public Pessoa findById(@PathVariable String login) {
        return pessoaService.encontrarPorIdOuLancarExcecao(login);
    }

    @PostMapping()
    public ResponseEntity<Pessoa> save(@RequestBody PessoaPostRequest pessoaPostRequest) {
    return new ResponseEntity<>(pessoaService.salvar(pessoaPostRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Pessoa>> list() {
        return ResponseEntity.ok(pessoaService.listAll());
    }

    @PutMapping()
    public ResponseEntity<Pessoa> replace(@RequestBody PessoaPutRequest pessoaPutRequest) {
        pessoaService.atualizar(pessoaPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/addEndereco")
    public ResponseEntity<Endereco> addEnderecoToPessoa(@RequestBody EnderecoToPessoaPutRequest enderecoToPessoaPutRequest) {
        Endereco endereco = pessoaService.addEnderecoToPessoa(enderecoToPessoaPutRequest);
        return ResponseEntity.ok(endereco);
    }

    @DeleteMapping(path = "/{login}")
    public ResponseEntity<Pessoa> delete(@PathVariable String login) {
    pessoaService.delete(login);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            pessoaService.fazerLogout(request);
            return "Logout realizado com sucesso!";
        }catch (Exception e) {
            return "Erro ao realizar o logout: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {

        try {
            Pessoa pessoa = pessoaService.fazerLogin(loginRequest.getLoginPessoa(),loginRequest.getSenha());
            if(pessoa != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("Usuario logado", pessoa);
                return "Login Realizado com sucesso :)";
            }else{
                return "Credenciais invalidas :(";
            }
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login ou senha incorretos :(");
        }

    }

    @PutMapping("/recuperarSenha")
    public ResponseEntity<String> recuperarSenha(@RequestBody PessoaPutRequest pessoaPutRequest,
                                                 HttpServletRequest request) {
           //HttpSession session = request.getSession(false);
           //if(session == null || session.getAttribute("Usuario logado") == null) {
              //  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não está logado :(");
            //}

            try {
                //Verifica se usuario esta logado
                pessoaService.verificarLoginOuLancarExcecao(request);


                pessoaService.recuperarSenha(pessoaPutRequest);
                return ResponseEntity.ok("Senha atualizada com sucesso");

            }catch (ResponseStatusException e) {
                return ResponseEntity.status(e.getStatusCode().value()).body(e.getReason());
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a senha" + e.getMessage());

            }
    }


}
