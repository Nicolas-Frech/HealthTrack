# 🏥 HealthTrack - Prontuário Médico

<p align="center">
<img width="1307" height="760" alt="image" src="https://github.com/user-attachments/assets/69ae6bea-24e9-41dd-9347-b156c266a7d5" />
</p>

<p align="center">
  <img width="1375" height="899" alt="image" src="https://github.com/user-attachments/assets/491514f2-f469-4ff8-963b-5d0c0d064f1d" />
</p>

<p align="center">
  <img width="1382" height="690" alt="image" src="https://github.com/user-attachments/assets/bf9d4a49-de65-43a9-b8e7-5032b2fc2321" />
</p>

<p align="center">
  <img width="1408" height="772" alt="image" src="https://github.com/user-attachments/assets/79eb03c1-e538-44e5-b2d7-ed76c758f4a0" />
</p>

## 📌 Sobre o Projeto

Sistema de Prontuário Médico, permitindo o cadastro e a administração de **Pacientes** e **Médicos**. Este software segue caracteristicas dos principios **SOLID** e **Clean Architecture** e **DDD (Domain Driven Design)**, em processo de produção.

A aplicação é composta por:

- **Frontend:** HTML/CSS/JAVASCRIPT e Bootstrap 5
- **Backend:** Java com Spring Boot 3
- **Banco de Dados:** MySQL
- **Containerização:** Docker e Docker Compose

## 🚀 Tecnologias Utilizadas

Este software foi desenvolvido com as seguintes tecnologias:

- **Frontend:**

  - HTML/CSS/JAVASCRIPT
  - Bootstrap 5

- **Backend:**

  - Java
  - Spring Boot 3
  - Maven
  - JPA
  - Hibernate

- **Banco de Dados:**

  - MySQL

- **Outras Ferramentas:**

  - Git e GitHub
  - Imsomnia
  - JWT
  - Spring Security

## 🔧 Status do Projeto

O projeto ainda está em desenvolvimento.

- **Funcionalidades Atuais:**

  - Gerenciamento de Pacientes (CRUD)
  - Gerenciamento de Médicos (CRUD)
  - Gerenciamento de Consultas (CRUD, Sistema de anotações/prescrições da consulta, validações)
  - Autenticação de Usuários (ADMIN e MEDIC)
  - Sempre que o ADMIN registrar um MÉDICO, automaticamente cria um Usuário (MEDIC) com username = CRM do Médico e senha = 3 primeiros digitos do CRM + 5 primeiros digitos do Celular.

- **Funcionalidades Planejadas:**

  - Página para o Usuário MÉDICO visualizar suas consultas, anotar e prescrever medicamentos para cada uma delas.


## 🤝 Contribuição

Contribuições são sempre bem-vindas! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b minha-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Faça um push para a branch (`git push origin minha-feature`)
5. Abra um Pull Request
