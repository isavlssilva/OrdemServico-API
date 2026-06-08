# 🔧 OS API — Sistema de Ordem de Serviço

API REST desenvolvida para gerenciamento de **Ordens de Serviço (OS)** de uma assistência técnica de equipamentos de informática.

O projeto foi construído utilizando **Spring Boot**, **Spring Data JPA** e **MySQL**, seguindo o padrão de **Arquitetura em Camadas**, promovendo organização, manutenção e escalabilidade do código.

---

## 📋 Funcionalidades

* Cadastro de clientes
* Cadastro de ordens de serviço
* Atualização de status da OS
* Cadastro de comentários em ordens de serviço
* Consulta de ordens por cliente
* Consulta de ordens com e sem comentários
* Documentação automática via Swagger/OpenAPI

---

## 🚀 Tecnologias Utilizadas

| Tecnologia          | Finalidade                    |
| ------------------- | ----------------------------- |
| Java 17             | Linguagem principal           |
| Spring Boot         | Framework Backend             |
| Spring Data JPA     | Persistência de dados         |
| MySQL               | Banco de dados                |
| Jakarta Persistence | Mapeamento ORM                |
| Swagger / OpenAPI   | Documentação da API           |
| Maven               | Gerenciamento de dependências |

---

## 🏗️ Arquitetura

O sistema segue uma arquitetura em camadas:

```text
Cliente HTTP
(Postman | Swagger)

        ↓

Controller
@RestController
Recebe requisições HTTP

        ↓

Service
@Service
Regras de negócio

        ↓

Repository
@Repository
Acesso aos dados

        ↓

MySQL
Banco de Dados
```

---

## 🗄️ Modelo de Dados

```text
CLIENTE
├── id
├── nome
├── email
└── telefone

        1:N

ORDEM_SERVICO
├── id
├── cliente_id
├── descricao
├── preco
├── status
├── data_abertura
└── data_finalizacao

        1:N

COMENTARIOS
├── id
├── descricao
├── data_postagem
└── ordem_servico_id
```

### Status da Ordem de Serviço

```text
ABERTA
FINALIZADA
CANCELADA
```
enum

---

## 📌 Regras de Negócio

* Toda OS é criada automaticamente com status **ABERTA**.
* A data de abertura é registrada automaticamente.
* O status pode ser alterado apenas de:

  * ABERTA → FINALIZADA
  * ABERTA → CANCELADA
* Ao excluir uma OS, seus comentários são removidos automaticamente (**CascadeType.ALL**).
* Cada comentário recebe sua data de postagem automaticamente.

---

## 🌐 Endpoints

### Ordens de Serviço

| Método | Endpoint                  | Descrição          |
| ------ | ------------------------- | ------------------ |
| GET    | `/ordem-de-servico`       | Listar todas as OS |
| GET    | `/ordem-de-servico/{id}`  | Buscar OS por ID   |
| POST   | `/ordem-de-servico`       | Criar nova OS      |
| PUT    | `/ordem-de-servico/{id}`  | Atualizar OS       |
| DELETE | `/ordem-de-servico/{id}`  | Excluir OS         |
| PATCH  | `/ordem-de-servico/{id}`  | Atualizar status   |

---

### Consultas por Cliente

| Método | Endpoint                                      |
| ------ | --------------------------------------------- |
| GET    | `/ordem-de-servico/cliente/{id}/abertas`      |
| GET    | `/ordem-de-servico/cliente/{id}/finalizadas`  |

---

### Consultas por Comentários

| Método | Endpoint                                         |
| ------ | ------------------------------------------------ |
| GET    | `/ordem-de-servico/com-comentarios`              |
| GET    | `/ordem-de-servico/sem-comentarios`              |
| GET    | `/ordem-de-servico/abertas/com-comentarios`      |
| GET    | `/ordem-de-servico/abertas/sem-comentarios`      |
| GET    | `/ordem-de-servico/finalizadas/com-comentarios`  |
| GET    | `/ordem-de-servico/finalizadas/sem-comentarios`  |

---

## ⚙️ Configuração do Banco de Dados

### Criar Banco

### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/osapi?createDatabaseIfNotExist=true&serverTimezone=UTC

spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.show-sql=true
```

---


## ▶️ Executando o Projeto

### Pré-requisitos

* Java 17+
* Maven
* MySQL


## 📚 Documentação da API

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui.html
```

---

## 📂 Estrutura do Projeto

```text
src/main/java

├── api
│   └── controller
│       ├── ClienteController
│       ├── OrdemServicoController
│       └── ComentariosController

├── domain
│   ├── model
│   │   ├── Cliente
│   │   ├── OrdemServico
│   │   ├── Comentarios
│   │   └── StatusOrdemServico
│   │
│   ├── repository
│   │   ├── ClienteRepository
│   │   ├── OrdemServicoRepository
│   │   └── ComentariosRepository
│   │
│   ├── service
│   │   ├── ClienteService
│   │   └── OrdemServicoService
│   │
│   └── dto
│       └── AtualizaStatusDTO
```

---

## ⚠️ Problema Comum

### UnsatisfiedDependencyException

```text
No property 'comentarios' found
```

Verificar:

1. `Comentarios.java` possui `@ManyToOne`
2. `OrdemServico.java` possui `@OneToMany(mappedBy = "ordemServico")`
3. Existe a coluna `ordem_servico_id` no banco


## 👨‍💻 Autoria

Projeto desenvolvido como atividade prática da disciplina de **Backend com Spring Boot**.

**Professor KGe**

Feito por: Isabelle Vitoria Lima da Silva
