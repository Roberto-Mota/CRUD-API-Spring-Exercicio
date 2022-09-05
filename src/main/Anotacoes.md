### Três tipos de comunicação com o banco de dados:

#### Named Methods
Pros
- São compiladas somente uma vez, quando o persistence unit eh carregado
- Podem ser centralizadas em um só lugar
Cons
- Static methods

#### JPQL
##### Spring JPA: JPA @Query
Pros
- Queries são construídas automaticamente quando usando EntityManager
- Escritas em JPQL, facilmente portáveis
Cons
- Pode reduzir a readability do código (Java mix SQL)

JPQL (Java Persistence Query Language) é definida na especificação JPA para criar queries de forma similar ao SQL padrão. 

`@Query("SELECT p FROM Produto p WHERE p.nome = ?1 AND p.preco = ?2")`
`List<Produto> findAll(String nome, BigDecimal preco)`
Os pontos de interrogaçãoservem apenas para marcar os parametros em ordem -?-
No nome do Método posso colocar o que quiser e.g. findAll ou acharTodos

`@Query("SELECT p FROM Produto p WHERE p.nome = :nome AND p.preco = :age")`
`List<Produto> findByName(@Param("nome") String nome, @Param("preco") BigDecimal preco);`

A Query é compilada automaticamente pelo JPA, com o Entity Manager. Devemos usá-la quando os Named Methods ou a estrutura em si começarem a ficar complexos de compreender.

#### Native Queries
##### Spring JPA: JPA Native Query
Pros
- Quando as queries começam a ficar muito complexas, os JPA statements não são otimizados. NativeQueries são mais eficientes nesse caso.
- Database vendor-specific features
Cons
- Pela possibilidade de vendor-specific features, pode complicar a portabilidade.


Spring JPA oferece a opção de executar queries com SQL Native. Usado principalmente para utilizar funções SQL prioritárias do Banco de Dados escolhido.

`@Query(value = "SELECT * FROM Usuario WHERE id = userId AND ativo = 1", nativeQuery = true)`
`Usuario findByUserIdAndAtivo(long userId, boolean ativo);`





