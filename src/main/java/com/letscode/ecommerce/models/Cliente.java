package com.letscode.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "email")
    private String email;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "senha")
    private String senha;

   @OneToOne(cascade = CascadeType.ALL)
   //@Column(name = "pedido")
    private Pedido pedido;
    
    @Enumerated(EnumType.STRING)
    private PerfilEnum perfil;



    public Cliente(String nome, String sobrenome, String email, String sexo, String cpf, String senha, PerfilEnum perfil) {
        if (nome.equalsIgnoreCase("Admin")) { // Regra simples apenas para testar o Admin
            perfil = PerfilEnum.ADMIN;
        }
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.sexo = sexo;
        this.cpf = cpf;
        this.perfil = perfil;
        this.senha = senha;
    }



    @Override
    public String toString() {
        return "Cliente [cpf=" + cpf + ", email=" + email + ", id=" + id + ", nome=" + nome + ", pedido=" + pedido
                + ", perfil=" + perfil + ", senha=" + senha + ", sexo=" + sexo + ", sobrenome=" + sobrenome + "]";
    }


    
    
}
