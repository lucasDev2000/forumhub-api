# FórumHub API

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de tópicos de um fórum.

Este projeto foi desenvolvido como parte do **Challenge Back-End da Alura + Oracle ONE**.

---

# Tecnologias utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Validation
- Flyway Migration
- MySQL
- Docker
- Maven
- Spring Security

---

# Arquitetura do projeto

backend

controller  
TopicoController

domain  
Topico  
TopicoRepository

dto  
DadosCadastroTopico  
DadosAtualizacaoTopico

infra/security  
SecurityConfig

resources  
application.properties  
db/migration  
V1__create_table_topicos.sql

---

# Banco de dados

Tabela principal: **topicos**

| Campo | Tipo |
|------|------|
| id | Long |
| titulo | String |
| mensagem | Text |
| data_criacao | LocalDateTime |
| estado | String |
| autor | String |
| curso | String |

---

# Como executar o projeto

## Subir o banco de dados com Docker

Na raiz do projeto execute:

docker compose up -d

---

## Executar a API

Entre na pasta backend:

cd backend

Execute:

./mvnw spring-boot:run

A API estará disponível em:

http://localhost:8080

---

# Endpoints da API

## Criar tópico

POST /topicos

Exemplo de requisição:

{
"titulo": "Duvida Java",
"mensagem": "Como funciona Spring Boot?",
"autor": "Lucas",
"curso": "Spring Boot"
}

---

## Listar tópicos

GET /topicos

Com paginação:

GET /topicos?page=0&size=10

Filtro opcional:

GET /topicos?curso=Spring Boot

ou

GET /topicos?curso=Spring Boot&ano=2026

---

## Detalhar tópico

GET /topicos/{id}

Exemplo:

GET /topicos/1

---

## Atualizar tópico

PUT /topicos/{id}

Exemplo:

{
"titulo": "Nova dúvida",
"mensagem": "Como funciona REST no Spring?",
"autor": "Lucas",
"curso": "Spring Boot"
}

---

## Excluir tópico

DELETE /topicos/{id}

Resposta esperada:

204 No Content

---

# Teste rápido com curl

Criar tópico:

curl -i -X POST http://localhost:8080/topicos \
-H "Content-Type: application/json" \
-d '{"titulo":"Duvida","mensagem":"Como faço POST?","autor":"Lucas","curso":"Spring Boot"}'

Listar tópicos:

curl http://localhost:8080/topicos

Detalhar tópico:

curl http://localhost:8080/topicos/1

Atualizar tópico:

curl -i -X PUT http://localhost:8080/topicos/1 \
-H "Content-Type: application/json" \
-d '{"titulo":"Nova dúvida","mensagem":"Teste update","autor":"Lucas","curso":"Spring Boot"}'

Excluir tópico:

curl -i -X DELETE http://localhost:8080/topicos/1

---

# Funcionalidades implementadas

- Cadastro de tópicos
- Listagem de tópicos
- Paginação
- Filtro por curso e ano
- Detalhamento de tópico
- Atualização de tópico
- Exclusão de tópico
- Validação de dados
- Bloqueio de tópicos duplicados
- Migrations com Flyway
- Persistência com Spring Data JPA
- Banco MySQL com Docker

---

# Autor

Lucas

Projeto desenvolvido para estudos no programa **Oracle Next Education + Alura**.