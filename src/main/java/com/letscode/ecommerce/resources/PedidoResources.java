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
import com.letscode.ecommerce.services.PedidoService;

@RestController
public class PedidoResources {
    
    @Autowired
    PedidoService pedidoService;
    
    @RequestMapping(path = "/criarPedido", method = RequestMethod.POST)
    public ResponseEntity<String> criarPedido(@RequestBody PedidoDto pedidoDto) {
        boolean sucesso = pedidoService.novoPedido(pedidoDto);
        if (sucesso) {
            return new ResponseEntity("Pedido criado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Pedido criado do produto falhou!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/adicionarProduto/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adicionarProduto(@PathVariable Long id, Authentication authentication) {
        boolean sucesso = pedidoService.adicionarProduto(id);
        if (sucesso) {
            return new ResponseEntity("Produto adicionado ao pedido com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Houve uma falha na adição do produto no pedido!", HttpStatus.BAD_REQUEST);
        }
    }
}
