package com.letscode.ecommerce.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.ecommerce.services.TempoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.Autowired;

@RestController
public class HelloEndpoints {

    // @Bean
    // private TempoService tempoService;

    @Autowired
    private TempoService tempoService;
    // Caso eu coloque uma interface em autowired, o spring não tenta criar uma interface, mas sim uma classe de implementação que implementa a classe
    /*
     * Exemplos de Endpoints bem básicos
     */

     Logger log = LoggerFactory.getLogger(HelloEndpoints.class); //Objetos Logger não são construidos por mim, preciso apenas passar a classe para um factory

    @RequestMapping(path="/hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        log.error("Não consegui");
        log.info("Informação aqui");
        //log.debug("Debug aqui");
        log.warn("Essa caveira significa prerigo!");
        return new ResponseEntity<String>("Hello world!", HttpStatus.OK);
    }
    @RequestMapping(path="/hello/{nome}", method = RequestMethod.GET)
    public ResponseEntity<String> helloNome(@PathVariable String nome) {
        return new ResponseEntity<String>("Olá, " + nome, HttpStatus.OK);
    }
    @RequestMapping(path="/hello/{nome}/horario", method = RequestMethod.GET)
    public ResponseEntity<String> helloNomeHorario(@PathVariable String nome) {
        return new ResponseEntity<String>("Olá, "+ nome + " " + tempoService.testa(), HttpStatus.OK);
    }

    // @RequestMapping(path="/teste", method = RequestMethod.GET)
    // public ResponseEntity teste() {

    //     return new ResponseEntity(testeService.testa(), HttpStatus.OK);
    // }
}
