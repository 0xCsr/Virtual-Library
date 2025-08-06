# 📚 Biblioteca Virtual

Aplicação Spring Boot para cadastro de livros, usuários e o relacionamento entre usuários e livros, com status de leitura.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- BCrypt
- PostgreSQL
- Swagger (Springdoc OpenAPI)
- Maven
- Docker & Docker Compose
- Git
- Postman

## ⚙️ Funcionalidades

- Cadastro de usuários com senha criptografada pelo BCrypt
- Cadastro de livros
- Associação de livros a usuários com status (ex: "lido", "lendo", "quero ler")
- Listagem de livros por usuário

## 🧱 Boas Práticas Adotadas
- 🌐 **Tratamento Global de Exceções:** com `@RestControllerAdvice` e `@ExceptionHandler`, todas as exceções são capturadas e retornadas com mensagens amigáveis e status apropriados.
- 🧾 **Classe `ErrorResponse`:** cara padronizar o formato das mensagens de erro enviadas ao cliente.
- 🧾 **DTOs (Data Transfer Objects):** separados das entidades, usados para trafegar apenas os dados necessários nas requisições e respostas.
- 🔒 **Segurança:** senhas criptografadas com BCrypt e autenticação com Spring Security.
- 📦 **Camadas bem definidas:** Controller, Service, Repository, Model, DTO's & Exceptions + Config.
- 📄 **Documentação com Swagger:** facilita o teste e visualização da API.

## 🛠️ Como Rodar o Projeto

### ✅ Pré-requisitos

- Docker e Docker Compose instalados

### 🐳 Rodando com Docker

```bash
docker-compose up --build
