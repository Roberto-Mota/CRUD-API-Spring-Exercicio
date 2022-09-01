package com.letscode.ecommerce.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.ecommerce.models.Produto;

// import com.letscode.ecommerce.models.Cliente;

public interface ProdutoDao extends JpaRepository<Produto, Long>{

}

