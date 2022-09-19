Criar um sistema usando Quarkus ou Spring Boot para um site de compras. A aplicação deve ter as funcionalidades:

    Login (opcional)
    CRUD (Create, Read, Update, Delete) para produtos e pedidos.
    Documentação (gerado com Swagger)

Preferencialmente entregue em link para o github, mas se necessário, pode ser anexado.

TODO:
Criar uma forma de contexto onde o cliente possui um "carrinho" (pedido) e é possível adicionar produtos nesse pedido

Roteiro:
**Cadastrar Cliente**
 -Consegui cadastrar sem problemas-

**Logar Cliente**
[Não consegui logar cliente, erro "bcrypt.BCryptPasswordEncoder     : Empty encoded password"] -> (Solved: Problema com construtor)
[-Preciso checar a efetividade do Token-] -> (Tip: Lembrar de colocar o token no header)

**Criar Pedido**
[Preciso pegar o contexto do usuario pra criar pedido mas deu erro: java.lang.NullPointerException: Cannot invoke "Object.toString()" because "user" is null] -> (Alternative: passei no parâmetro de cada método o "Authentication authentication", meio XGH, TODO: ver uma forma mais padronizada de fazer autenticação)

**Adicionar Produto em Pedido**
[Quando uma requisição para adicionar um produto é feita, eu preciso encontrar o pedido e adicionar o produto a ele] - [Problema ao encontrar e salvar o produto] (Solved: Não estava relacionando o pedido ao cliente)
Fechar Pedido

**Aplicar Documentação Swagger**
[Problema ao carregar a URL do Swagger, está voltando blank page com a div carregada]