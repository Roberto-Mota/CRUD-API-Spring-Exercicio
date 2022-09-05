package com.letscode.ecommerce.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.ecommerce.models.Produto;

public interface ProdutoDao extends JpaRepository<Produto, Long>{
    // Aqui eu posso criar namedQueries -?-
    List<Produto> findAllByIdOrName(long id, String nome); //SLECT * FROM PRODUTO WHERE id = {id} OR nome = {nome}
}

