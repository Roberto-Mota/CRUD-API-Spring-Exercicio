package com.letscode.ecommerce.dto;

import java.util.List;

import com.letscode.ecommerce.dao.ProdutoDao;
import com.letscode.ecommerce.models.Pedido;
import com.letscode.ecommerce.models.Produto;

public class ClienteDto {

    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String sexo;
    private String cpf;
    private String senha;
    private Pedido pedido;

    public ClienteDto(long id, String nome, String sobrenome, String email, String sexo, String cpf, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.sexo = sexo;
        this.cpf = cpf;
        this.senha = senha;

    }

    public ClienteDto() {
    }

    public long getId() {
        return id;
    }

    

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        System.out.println("getSenha executado: " + this.senha);
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "ClienteDto [cpf=" + cpf + ", email=" + email + ", id=" + id + ", nome=" + nome + ", senha=" + senha
                + ", sexo=" + sexo + ", sobrenome=" + sobrenome + "]";
    }

}
