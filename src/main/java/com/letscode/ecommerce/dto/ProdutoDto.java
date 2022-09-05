package com.letscode.ecommerce.dto;

import java.math.BigDecimal;

public class ProdutoDto {

    private long id;
    private String nome;
    private BigDecimal preco;
    private String descricao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ProdutoDto(String nome, BigDecimal preco, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }
    
}
