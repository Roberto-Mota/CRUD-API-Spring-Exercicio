package com.letscode.ecommerce.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.ecommerce.models.Cliente;

// import com.letscode.ecommerce.models.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Long>{

}

