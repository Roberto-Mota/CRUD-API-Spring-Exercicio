package com.letscode.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import com.letscode.ecommerce.models.Produto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {

    public Pedido(String nomeCliente, String emailCliente) {
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.dateTime = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nomeCliente")
    private String nomeCliente;

    @Column(name = "emailCliente")
    private String emailCliente;

    // @Column(name = "listaProduto")
    // private List<Produto> listaProduto;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> listaProduto;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

}
