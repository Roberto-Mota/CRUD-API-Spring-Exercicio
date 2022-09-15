package com.letscode.ecommerce.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.ecommerce.dto.PedidoDto;
import com.letscode.ecommerce.services.PedidoService;

@RestController
public class PedidoResources {
    
    @Autowired
    PedidoService pedidoService;
    
    @RequestMapping(path = "/criarPedido", method = RequestMethod.POST)
    public ResponseEntity<String> criarProduto(@RequestBody PedidoDto pedidoDto) {
        boolean sucesso = pedidoService.novoPedido(pedidoDto);
        if (sucesso) {
            return new ResponseEntity("Pedido criado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Pedido criado do produto falhou!", HttpStatus.BAD_REQUEST);
        }
    }
}
