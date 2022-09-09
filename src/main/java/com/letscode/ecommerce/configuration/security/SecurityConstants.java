package com.letscode.ecommerce.configuration.security;

public class SecurityConstants {

    public static final String SECRET = "4453fd5e8408dc24655669d0a37ef72e"; //base para gerar os JWT tokens. Importante manter esse secret seguro (não colocar no Github em um projeto real, por exemplo)
    public static final long EXPIRATION_TIME = 1800_000; // 30 mins //Não é obrigatório, pode ter como entregar outra autorização (dando mais um token pra ele)
    public static final String TOKEN_PREFIX = "Bearer "; //Inicio do JWT (ex: Bearer 0832nkds...)
    public static final String HEADER_STRING = "Authorization"; //Header que o token sera adicionado/lido.

}
