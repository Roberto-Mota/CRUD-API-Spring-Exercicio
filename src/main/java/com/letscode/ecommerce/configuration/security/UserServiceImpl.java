package com.letscode.ecommerce.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.letscode.ecommerce.dao.ClienteDao;
import com.letscode.ecommerce.models.Cliente;

import java.util.Objects;

//Implementacaoo da classe UserDetailsService do Spring Security framework, usada para retornar as informacoes
//de autenticacao e autorizacao. A interface soh tem um metodo (loadUserByUsername()), que nós implementamos para
//"alimentar" informacoes sobre o usuario para o Spring security API.

//Aqui que me conecto com o banco de dados
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    ClienteDao clienteDao;

    //Implementacao da mesma, utilizando o DAO da aplicacao (credenciais salvas no banco).
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cliente = clienteDao.findByEmail(email);

        if (Objects.isNull(cliente)) {
            throw new UsernameNotFoundException(email);
        }

        return new UserPrincipalDetails(cliente); //Retornando somente o que eh importante para a autenticacao.
    }

}
