# Grupo
    
Breno Marino\
Felipe Cavalcante\
Rafael Libanio\
Sheldon Gomes

# Documentação Completa 

A documentação completa da aplicação está disponível na pasta /lojas_center/docs/Java Persistence.pdf

# Resumo

Foi criada uma aplicação Java contendo APIs para uma loja de e-commerce chamada Lojas Center.


Exemplo de utilização:

http://localhost:8080/lojas-center/swagger-ui/

A documentação completa da API está disponível no link em JSON:

http://localhost:8080/lojas-center/v2/api-docs

Você pode importá-la no seu software preferido... Ex. Postman ;) 



# Estrutura

 - config\
 Configuração dos elementos do Sistema, tais como, web app, JPA, Redis, Swagger.
    -  AppConfig: ;
    -  JPAConfig: Configuração do Spring Data JPA;
    -  RedisConfig: Configuração do Redis;
    -  SwaggerConfig: Configuração do Swagger;
    -  WebAppInitializer: ;
 - controller\
 Controle das interações externas via API
    - ClienteController: Interações com os cadastros de clientes;
    - PedidoController: Interações com os pedidos;
    - ProdutoController: Interações com os produtoss;
 - entity\
 Mapeamento das tabelas do banco de dados
    - Cliente;
    - Endereco;
    - Pedido;
    - Produto;
 - exceptions\
 Tratamento dos erros do Sistema
    - HandlerExceptions: ;
    - NotCreatedPedidoException: ;
    - NotFoundPedidoException: ;
 - main\
 Início da aplicação
   - SpringMain: Classe Main; 
 - model\
 Classes de transição entre os elementos do sistema
    ControleEstoqueProduto;
    PedidoForm;
    Response;
 - repository\
 Interações com o banco de dados
    - ClienteRepository;
    - EnderecoRepository;
    - PedidoRepository;
    - ProdutoRepository;
 - service\
 Lógicas de negócio
    - ClienteService: Serviços relacionados ao cadastro de clientes;
    - EnderecoService: Serviços relacionados ao endereço dos clientes;
    - IClienteService;
    - IEnderecoService;
    - PedidoService: Serviços relacionados aos pedidos dos clientes;
    - ProdutoService: Serviços relacionados ao cadastro de produtos;
 - resources\
 Armazena as propriedades de configuração do banco de dados MySQL, Redis e do servidor web da API
    - application.properties: Propriedades de configuração da API;
    - database.properties: Propriedades de configuração do MySQL;
    - redis.properties: Propriedades de configuração do Redis;