package com.letscode.ecommerce.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.ecommerce.models.Produto;
import com.letscode.ecommerce.services.ProdutoService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
// 

// a) Criem um novo produto no banco
// b) Retorne um produto de acordo com o seu Id
// c) Delete um produto pelo ID 
// d) Atualizem o produto

@RestController
public class ProdutoResources {

    @Autowired
    ProdutoService produtoService;

    @RequestMapping(path = "/criarProduto", method = RequestMethod.POST)
    public ResponseEntity<String> criarProduto() {
        // ProdutoService.criar()
        return new ResponseEntity<String>("Hello world!", HttpStatus.OK);
    }

    @RequestMapping(path = "/lerProduto", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produto>> lerProduto() {

        List<Produto> produtoList = produtoService.listarTodosProdutos();
        if (produtoList.isEmpty()) {
            // Jogar que a lista esta vazia
            return ResponseEntity.ok(produtoList); // Mudar o retorno
        } else {
            return ResponseEntity.ok(produtoList);
        }
    }

    @RequestMapping(path = "/atualizarProduto", method = RequestMethod.PUT)
    public ResponseEntity<String> atualizarProduto(@PathVariable Produto produto) {

        boolean sucesso = produtoService.atualizarProduto(produto);
        if (sucesso) {
            return new ResponseEntity("Produto deletado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Remocao do cliente falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/deletarProduto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarProduto(@PathVariable long id) {

        boolean sucesso = produtoService.removerProduto(id);
        if (sucesso) {
            return new ResponseEntity("Produto deletado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Remocao do cliente falhou!", HttpStatus.BAD_REQUEST);
        }
    }
}

// package br.letscode.endpoints;

// import br.letscode.dto.ClienteDto;
// import br.letscode.models.Cliente;
// import br.letscode.services.ClienteService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// @RestController
// public class ClienteEndpoints {
// @Autowired
// ClienteService clienteService;

// @RequestMapping(path="/cliente", method = RequestMethod.GET, produces =
// APPLICATION_JSON_VALUE)
// public ResponseEntity<List<Cliente>> getAllCients() {
// List<Cliente> clienteList = clienteService.listarTodosClientes();

// return ResponseEntity.ok(clienteList);
// }

// @RequestMapping(path="/cliente", method = RequestMethod.POST)
// public ResponseEntity novoCliente(@RequestBody ClienteDto cliente) {
// boolean sucesso = clienteService.novoCliente(cliente);

// if(sucesso) {
// return new ResponseEntity("Cliente criado com sucesso!", HttpStatus.CREATED);
// }
// else {
// return new ResponseEntity("Criacao do cliente falhou!",
// HttpStatus.BAD_REQUEST);
// }
// }

// @RequestMapping(path="/cliente", method = RequestMethod.PUT)
// public ResponseEntity atualizarCliente(@RequestBody Cliente cliente) {
// boolean sucesso = clienteService.atualizarCliente(cliente);

// if(sucesso) {
// return new ResponseEntity("Cliente atualizado com sucesso!",
// HttpStatus.CREATED);
// }
// else {
// return new ResponseEntity("Atualizacao do cliente falhou!",
// HttpStatus.BAD_REQUEST);
// }
// }

// @RequestMapping(path="/cliente/{id}", method = RequestMethod.DELETE)
// public ResponseEntity removerCliente(@PathVariable long id) {
// boolean sucesso = clienteService.removerCliente(id);

// if(sucesso) {
// return new ResponseEntity("Cliente deletado com sucesso!", HttpStatus.OK);
// }
// else {
// return new ResponseEntity("Remocao do cliente falhou!",
// HttpStatus.BAD_REQUEST);
// }
// }
// }
