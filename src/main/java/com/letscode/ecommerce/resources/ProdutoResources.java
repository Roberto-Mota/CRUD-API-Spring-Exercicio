package com.letscode.ecommerce.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.ecommerce.dto.ProdutoDto;
import com.letscode.ecommerce.models.Produto;
import com.letscode.ecommerce.services.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

// Adicione Spring Security ao projeto atual
// Adicione suporte a role ADMIN (exemplo até agora contém somente CLIENTE)
// Para os endpoints Produtos (feitos no último exercício sobre JPA):
// a) /GET produtos endpoint deve estar disponível a todos (até mesmo sem login)
// b) /DELETE produtos pode somente ser acessado por usuários ADMIN


@RestController
public class ProdutoResources {

    @Autowired
    ProdutoService produtoService;

    @Operation(description = "Esse metodo cria um produto.")
    @RequestMapping(path = "/criarProduto", method = RequestMethod.POST)
    public ResponseEntity<String> criarProduto(@RequestBody ProdutoDto produtoDto) {
        boolean sucesso = produtoService.novoProduto(produtoDto);
        if (sucesso) {
            return new ResponseEntity("Produto cadastrado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Cadastro do produto falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(description = "Esse metodo lista todos os produtos.")
    @RequestMapping(path = "/lerProdutos", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produto>> lerProdutos() {

        List<Produto> produtoList = produtoService.listarTodosProdutos();
        if (produtoList.isEmpty()) {
            // TODO: Jogar que a lista esta vazia
            // return ResponseEntity.ok(produtoList); // Mudar o retorno
        } 
        return ResponseEntity.ok(produtoList);
    }

    @Operation(description = "Esse metodo devolve um produto específico.")
    @RequestMapping(path = "/lerProduto/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> lerProdutoPorId(@PathVariable long id) {
        Produto produto = produtoService.lerProdutoPorId(id);
        if (produto == null) {
            // TODO: Jogar que a lista esta vazia
            // return ResponseEntity.ok(produtoList); // Mudar o retorno
        } 
        return ResponseEntity.ok(produto);
    }

    @Operation(description = "Esse metodo atualiza um produto específico.")
    @RequestMapping(path = "/atualizarProduto", method = RequestMethod.PUT)
    public ResponseEntity<String> atualizarProduto(@RequestBody Produto produto) {
        boolean sucesso = produtoService.atualizarProduto(produto);
        if (sucesso) {
            return new ResponseEntity("Produto atualizado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Atualização do produto falhou!", HttpStatus.BAD_REQUEST);
        }
    }
    
    @Operation(description = "Esse metodo deleta um produto específico.")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(path = "/deletarProduto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletarProduto(@PathVariable long id) {
        boolean sucesso = produtoService.removerProduto(id);

        if (sucesso) {
            return new ResponseEntity("Produto deletado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Remocao do produto falhou!", HttpStatus.BAD_REQUEST);
        }
    }
}

