package com.letscode.ecommerce.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class TempoService {
    
    public String testa() {
        //LocalDateTime horas = new LocalDateTime.now();
        return LocalDateTime.now().toString();
     }
}
