package com.letscode.ecommerce.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letscode.ecommerce.configuration.security.UserPrincipalDetails;
import com.letscode.ecommerce.services.TempoService;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.Autowired;

@RestController
public class HelloEndpoints {

    // @Bean
    // private TempoService tempoService;

    @Autowired
    private TempoService tempoService;
    // Caso eu coloque uma interface em autowired, o spring não tenta criar uma
    // interface, mas sim uma classe de implementação que implementa a classe
    /*
     * Exemplos de Endpoints bem básicos
     */

    // @Autowired
    // Authentication authentication;
    // TODO: alguma forma de injeção de dependência do Authentication (Suspeito precisar fazer uma classe Custom)

    Logger log = LoggerFactory.getLogger(HelloEndpoints.class); // Objetos Logger não são construidos por mim, preciso
                                                                // apenas passar a classe para um factory

    @PreAuthorize("hasAnyRole('CLIENTE')")
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello(Principal principal) {
        // Aparentemente o principal tem menos possibilidades de manipulação em comparação ao Authentication
        System.out.println(principal.getName());
        System.out.println(principal.toString());
        log.error("Não consegui");
        log.info("Informação aqui");
        // log.debug("Debug aqui");
        log.warn("Essa caveira significa prerigo!");
        return new ResponseEntity<String>("Hello world!", HttpStatus.OK);
    }

    @RequestMapping(path = "/hello/{nome}", method = RequestMethod.GET)
    public ResponseEntity<String> helloNome(@PathVariable String nome, Authentication authentication) {

        // public ResponseEntity<String> helloNome(@PathVariable String nome,
        // HttpServletRequest request) {

        System.out.println("Principal do Auth: " +
                authentication.getPrincipal().toString()); // Devolve o user (No caso, o email)
        
        System.out.println("getName do Auth: " +
                authentication.getName()); // Devolve o user (No caso, o email)

        // System.out.println("Principal do Credentials: " +
        // authentication.getCredentials().toString()); // Devolve Null

        System.out.println("Authorities do Auth: " +
                authentication.getAuthorities().toString()); // Devolve as ROLES

        // System.out.println("Details do Auth: " +
        // authentication.getDetails().toString()); (Devolve Null)

        System.out.println("isAuthenticated do Auth: " +
                authentication.isAuthenticated());

                

        /*
         * Abstração impossibilitada:
         * 
         * UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         * System.out.println(userDetails.toString());
         * 
         * Exemplo Direto da request:
         * 
         * public ResponseEntity<String> helloNome(@PathVariable String nome,
         * Authentication authentication) {
         * 
         * System.out.println("UserPrincipal direto da request: " +
         * request.getUserPrincipal());
         * 
         * Principal principal = request.getUserPrincipal();
         * 
         * System.out.println(principal.getName());
         * 
         * Através do AuthenticationPrincipal -?- (Devolve NullPointer)
         * @AuthenticationPrincipal UserPrincipalDetails user) {
         */

        return new ResponseEntity<String>("Olá, " + nome, HttpStatus.OK);
    }

    @RequestMapping(path = "/hello/{nome}/horario", method = RequestMethod.GET)
    public ResponseEntity<String> helloNomeHorario(@PathVariable String nome) {
        return new ResponseEntity<String>("Olá, " + nome + " " + tempoService.testa(), HttpStatus.OK);
    }

    // @RequestMapping(path="/teste", method = RequestMethod.GET)
    // public ResponseEntity teste() {

    // return new ResponseEntity(testeService.testa(), HttpStatus.OK);
    // }
}
