# Aluguel de Carros - API Spring Boot

Este projeto é uma API REST para gerenciar o cadastro de pessoas e operações de trasnferencia de carros.

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL 
- Jakarta Bean Validation
- Maven

## 📦 Funcionalidades principais

- Listagem de pessoas
- Cadastro de novos carros e novas pessoas
- Tratamento global de erros com `@ControllerAdvice`
- Respostas padronizadas para requisições inválidas
- Transferencia de carros entre as pessoas cadastradas préviamente

## ▶️ Como executar

Requisitos 
- java 17+
- maven
- PostgreSql configurado

1. Clone o repositório:
```bash
git clone https://github.com/LuisFerMoura/TransferenciaCarros.git;
```

2. Entre na pasta do projeto e execute
```bash
./mvnw spring-boot:run
```

## 📡 3. endpoints principais

3.1 /pessoas
POST - Cadastra nova pessoa
GET - Lista todas as pessoas cadastradas
GET /pessoas/{id} – Busca pessoa por ID

3.2 /transferencias
POST - Realiza uma nova transferencia passando id do carro + id do novo dono


