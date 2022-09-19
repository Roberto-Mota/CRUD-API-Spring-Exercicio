package com.letscode.ecommerce.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.ecommerce.dto.PedidoDto;
import com.letscode.ecommerce.dto.ProdutoDto;
import com.letscode.ecommerce.models.Pedido;
import com.letscode.ecommerce.services.PedidoService;

@RestController
public class PedidoResources {
    
    @Autowired
    PedidoService pedidoService;
    
    @RequestMapping(path = "/lerPedido", method = RequestMethod.GET)
    public ResponseEntity<String> lerPedido() {
        try {
            Pedido pedido = pedidoService.findPedido();
            return new ResponseEntity(pedido.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Leitura do pedido falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/criarPedido", method = RequestMethod.POST)
    public ResponseEntity<String> criarPedido() {
        boolean sucesso = pedidoService.novoPedido();
        if (sucesso) {
            return new ResponseEntity("Pedido criado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Pedido criado do produto falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/adicionarProduto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adicionarProduto(@PathVariable Long id  /*, Authentication authentication)*/)  {
        boolean sucesso = pedidoService.adicionarProduto(id);
        if (sucesso) {
            return new ResponseEntity("Produto adicionado ao pedido com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Houve uma falha na adição do produto no pedido!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/fecharPedido", method = RequestMethod.PUT)
    public ResponseEntity<String> fecharPedido(/*@PathVariable Long id , Authentication authentication)*/) {
        boolean sucesso = pedidoService.fecharPedido();
        if (sucesso) {
            return new ResponseEntity("Pedido fechado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Houve uma falha no fechamento do pedido!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/removerPedido/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removerPedido(@PathVariable Long id /*, Authentication authentication)*/) {
        boolean sucesso = pedidoService.removerPedido(id);
        if (sucesso) {
            return new ResponseEntity("Pedido removido com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Houve uma falha na remoção do pedido!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/removerProduto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> removerProduto(@PathVariable Long id /*, Authentication authentication)*/) {
        boolean sucesso = pedidoService.removerProduto(id);
        if (sucesso) {
            return new ResponseEntity("Produto removido com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Houve uma falha na remoção do produto!", HttpStatus.BAD_REQUEST);
        }
    }
}
