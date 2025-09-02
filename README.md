# 🏥 HealthTrack - Prontuário Médico

<p align="center">
<img width="1307" height="760" alt="image" src="https://github.com/user-attachments/assets/69ae6bea-24e9-41dd-9347-b156c266a7d5" />
</p>

<p align="center">
<img width="1412" height="564" alt="image" src="https://github.com/user-attachments/assets/5b67ec7b-16b4-4240-9b19-b8c06389c65b" />
</p>

<p align="center">
<img width="1426" height="627" alt="image" src="https://github.com/user-attachments/assets/1d8f1972-faa4-427a-b4a1-48b52aef26d8" />
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

Sistema de Prontuário Médico, permitindo o cadastro e a administração de **Pacientes**, **Médicos** e **Consultas**. Login e Autenticação com dois perfis: **MEDIC** e **ADMIN**. Este software segue caracteristicas dos principios **SOLID** e **Clean Architecture** e **DDD (Domain Driven Design)**, em processo de produção.

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
  - Página para o Usuário MÉDICO visualizar suas consultas, anotar e prescrever medicamentos para cada uma delas.
 
- **Funcionalidades Previstas:**

  - UPLOAD e DOWNLOAD de exames por PACIENTE.
 
    
## ⚡ Rodando o Projeto

**Pré-requisitos:** Docker e Docker Compose  

Suba os containers:  
`docker-compose up -d --build`

Acesse:  
- Frontend: [http://localhost](http://localhost)  


**Observações importantes:**  
- Um usuário **ADMIN** é criado automaticamente na primeira execução:  
  - Username: `admin`  
  - Senha: `admin123`  
- Ao registrar um médico, um usuário **MEDIC** é criado automaticamente:  
  - Username: CRM do médico  
  - Senha: 3 primeiros dígitos do CRM + 5 primeiros dígitos do celular

## 🤝 Contribuição

Contribuições são sempre bem-vindas! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b minha-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Faça um push para a branch (`git push origin minha-feature`)
5. Abra um Pull Request
