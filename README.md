[![codecov](https://codecov.io/gh/DiegoSouzaDev/UserCRUD/branch/master/graph/badge.svg)](https://codecov.io/gh/DiegoSouzaDev/UserCRUD)

# UserCRUD

CRUD implementado com Java 8 e SpringBoot, JUnit para testes e Maven para gerenciar a build.



## Desafio
Implementar o Back-end que seria utilizado pelo processo de cadastro de clientes de uma aplicação.

É necessário que sejam implementados os métodos de listagem, exclusão, inclusão e alteração levando em conta a tela anexa.

"imagem aqui"

Deverá implementar a divisão de camadas Controller e Service, disponibilizando chamada rest para o front-end. 
Não será necessário implementar persistência, podendo fazer algum mock dos dados. 


**Requisitos do desafio:**
* Deverá implementar em `JAVA` ou `Grails`.
* Na disponibilização do desafio, deverá conter os procedimento para rodar e executar o código.


## Teste e Execução da aplicação.

### EXECUTANDO OS TESTES
Para que os testes sejam executados, basta acessar a pasta raiz do projeto, abrir o `CMD` e executar o comando `mvn package`.
Feito isso, o maven baixará as dependencias e executará os testes.

### INICIANDO A APLICAÇÃO:

Para  inicia a aplicação, é necessário ir até o diretório raiz do projeto, abrir o `CMD` e executar o comando `mvn spring-boot:run`. 
Com isso a aplicação será inicializada utilizando a porta `8080`.


## API

**ENDPOINTS**
```
GET http://localhost:8080/user/find
```
```
GET http://localhost:8080/user/find/{param}
```
```
POST http://localhost:8080/user/add
```
```
PUT http://localhost:8080/user/update
```
```
DELETE http://localhost:8080/user/delete/{param}
```



**REQUISIÇÕES**

Método | Chamada | Descrição
------------ | ------------- | -------------
GET | `/find` | Retorna todos os usuários cadastrados.
GET | `/find/{params}` | Retorna um unico usuário identificando-o pelo seu ID atraves do `{param}`.
POST | `/add` | Adiciona um usuário, enviado no formato `JSON` no `Body` da requisição e retorna o id que o representa na base.
PUT | `/update` | Atualiza um usuário, enviado no formato `JSON` no `Body` da requisição e retorna o `JSON` do objeto atualizado.
DELETE | `/delete/{param}` | Remove o usuário identificado na chamada através do `{param}`.

**Exemplo de JSON**



**RESPOSTAS**
Código | Resposta
------------ | -------------
`200` | ID do usuário.
`200` | JSON de um usuário.
`200` | JSON de uma lista de usuários.
`400` | 400 Bad Request: 
`400` | 400 Bad Request: Username already in use by a user.
`400` | 400 Bad Request: Cannot update. The user no longer exist in the database
