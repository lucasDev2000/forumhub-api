# ForumHub API

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de tópicos de um fórum.
O projeto implementa autenticação segura utilizando **Spring Security e JWT**, além de persistência em banco de dados **MySQL** com **Flyway** para controle de migrations.

---

# Tecnologias utilizadas

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (Auth0 Java JWT)
* MySQL
* Flyway
* Maven
* Docker

---

# Arquitetura do projeto

```
src/main/java/br/com/forumhub/forumhub

controller
├── AuthController
└── TopicoController

domain
├── topico
│   ├── Topico
│   └── TopicoRepository
└── usuario
    ├── Usuario
    ├── UsuarioRepository
    └── dto
        └── DadosLogin

infra/security
├── SecurityConfig
├── SecurityFilter
├── TokenService
└── AuthenticationService
```

---

# Banco de dados

O projeto utiliza **MySQL**.

As migrations são controladas pelo **Flyway**.

```
resources/db.migration

V1_create_table_topicos.sql
V2_create_table_usuarios.sql
```

---

# Rodando o projeto

### 1 Clonar repositório

```bash
git clone https://github.com/seuusuario/forumhub-api.git
cd forumhub-api
```

---

### 2 Subir banco com Docker

```bash
docker run -d \
--name forumhub-mysql \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=forumhub \
-p 3306:3306 \
mysql:8
```

---

### 3 Rodar aplicação

```bash
./mvnw spring-boot:run
```

A API ficará disponível em:

```
http://localhost:8080
```

---

# Autenticação JWT

A autenticação da API é feita utilizando **JWT (JSON Web Token)**.

### Endpoint de login

```
POST /login
```

Body:

```json
{
 "login": "lucas",
 "senha": "123456"
}
```

Resposta:

```
TOKEN JWT
```

---

# Usando o token

Após obter o token, ele deve ser enviado no header das próximas requisições:

```
Authorization: Bearer SEU_TOKEN
```

Exemplo:

```bash
curl http://localhost:8080/topicos \
-H "Authorization: Bearer SEU_TOKEN"
```

---

# Endpoints da API

### Criar tópico

```
POST /topicos
```

---

### Listar tópicos

```
GET /topicos
```

---

### Atualizar tópico

```
PUT /topicos/{id}
```

---

### Deletar tópico

```
DELETE /topicos/{id}
```

---

# Testando a API

Os testes podem ser feitos com ferramentas de API:

* Postman
* Insomnia
* Curl

---

# Segurança

A API implementa:

* Spring Security
* Autenticação via JWT
* API stateless
* Filtro de autenticação por token
* Proteção de endpoints

---

# Autor

Projeto desenvolvido para o desafio **Oracle Next Education + Alura**.

Lucas
