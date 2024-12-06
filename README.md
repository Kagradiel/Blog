# API para Blog  

Uma API para um blog contendo seções de temas, postagens e usuários autenticados.  

## Funcionalidades  

- CRUD completo para temas, postagens e usuários.  
- Autenticação e autorização configuradas para maior segurança.  
- Integração com Swagger para documentação interativa.  

## Stack utilizada  

**Back-end:** Java, Spring Boot  
**Database:** MySQL  

## Rodando localmente  

Clone o projeto  

```bash  
git clone https://github.com/Kagradiel/Blogpessoal.git  
```

Entre no diretório do projeto
```bash  
cd Blogpessoal  
```

Instale as dependências
```bash  
mvn clean install  
```

### Configure o banco de dados
 1. Certifique-se de que o MySQL está em execução.

 2. Crie um banco de dados chamado blogdb.

 3. Atualize as configurações do banco no arquivo application.properties:

 ```properties  
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb  
spring.datasource.username=seu_usuario  
spring.datasource.password=sua_senha   
```

### Inicie o servidor

```bash  
mvn spring-boot:run   
```

## Documentação da API
#### Documentação interativa com Swagger
Acesse a documentação interativa no navegador:

```bash  
http://localhost:8080/swagger-ui/index.html
```
