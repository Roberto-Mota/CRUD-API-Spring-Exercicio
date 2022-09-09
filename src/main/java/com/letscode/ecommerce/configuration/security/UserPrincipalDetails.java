package com.letscode.ecommerce.configuration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.letscode.ecommerce.models.Cliente;

import java.util.Collection;
import java.util.List;

//Classe que vai retornar somente o que eh necessario para autenticacao, ao inves de todo o cliente, implementando
//UserDetails do Spring.
//Diz, em comparação com o meu banco de dados, o que é o username e a senha.
public class UserPrincipalDetails implements UserDetails {

    final Cliente cliente; //Quando eu quero pegar uma senha e o user (no caso o email), vou pegar nessa classe, no caso Cliente.

    public UserPrincipalDetails(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_".concat(cliente.getPerfil().name()))); //Como eu vou conseguir pegar a role -?-
    }

    @Override
    public String getPassword() {
        return cliente.getSenha();
    }

    @Override
    public String getUsername() {
        return cliente.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return cliente.getEmail();
    }
}
