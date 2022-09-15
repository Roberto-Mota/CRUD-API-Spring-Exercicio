package com.letscode.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.letscode.ecommerce.models.Produto;

public class PedidoDto {
    private long id;
    private String nomeCliente;
    private String emailCliente;
    // private List<Produto> listaProduto;
    //private LocalDateTime dateTime;

    public PedidoDto(String nomeCliente, String emailCliente) {
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    @Override
    public String toString() {
        return "PedidoDto [emailCliente=" + emailCliente + ", id=" + id + ", nomeCliente=" + nomeCliente + "]";
    }

    // public List<Produto> getListaProduto() {
    //     return listaProduto;
    // }

    // public void setListaProduto(List<Produto> listaProduto) {
    //     this.listaProduto = listaProduto;
    // }

    // public LocalDateTime getDateTime() {
    //     return dateTime;
    // }

    // public void setDateTime(LocalDateTime dateTime) {
    //     this.dateTime = dateTime;
    // }



}
