package com.letscode.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.ecommerce.models.Cliente;

import java.util.List;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByIdOrEmail(long id, String email); //SELECT * FROM CLIENTE WHERE id = {id} OR email = {email}
    Cliente findByEmail(String email);
}