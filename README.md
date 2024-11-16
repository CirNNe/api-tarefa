<h1 align="center">API Tarefas</h1>

<p align="center">Programa para gerenciamento de tarefas</p>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

![Version](https://img.shields.io/badge/version-v1.0.0-blue)

Indice
=================
* [Sobre](#-sobre)
* [Versões](#-versões)
* [Tecnologias Utilizadas](#-tecnologias-utilizadas)
* [Metodologias adotadas](#-metodologias-adotadas)
* [Pré-requisitos](#-pré-requisitos)
* [Como Executar o Projeto](#-como-executar-o-projeto)
* [Autor](#-autor)

## 💬&nbsp; Sobre

O projeto **API Tarefas** consiste em uma API RESTful que permite a gestão de pessoas e tarefas. As principais funcionalidades incluem salvar, atualizar, deletar e listar pessoas e tarefas, alocar uma pessoa a uma tarefa, e listar departamentos com suas respectivas tarefas e as pessoas alocadas a elas. A API facilita o gerenciamento de recursos e atribuições dentro de uma estrutura organizacional.

---

## 🔖&nbsp; Versões

![Version](https://img.shields.io/badge/version-v1.0.0-blue)
- Definida a versão base do sistema

---

## 🚀 Tecnologias utilizadas

O projeto foi desenvolvido utilizando as seguintes tecnologias:

- Java v21
- Spring (Spring Boot, Spring Web, Spring Data JPA, Spring MVC, SpringDoc OpenAPI 3)
- Maven
- PostgreSQL
- JUnit5 e Mockito
- Postman
- IntelliJ IDE

---

## 👍 Metodologias adotadas

- SOLID, DRY
- API RESTful
- Consultas com SpringData JPA
- Injeção de dependências
- Tratamento de erros
- Geração automática do documento Swagger com a OpenAPI 3

---

## 🗂 Pré-requisitos

Se deseja baixar o projeto para utiliza-lo, você vai precisar ter instalado em sua máquina a seguinte ferramenta:
[Git](https://git-scm.com) para fazer o clone do repositório para sua máquina local.
Além disto deve ter instalado na sua máquina o [Java](https://www.oracle.com/br/java/technologies/downloads/#java21) na versão 21 e o [PostgreSQL](https://www.postgresql.org/download/). Por fim, é bom ter um editor para trabalhar com o código como [ItelliJ IDEA](https://www.jetbrains.com/pt-br/idea/)

## 🎲 Como executar o projeto

```bash
# Depois de instalar as tecnologias necesárias:

# Clone este repositório
git clone https://github.com/CirNNe/api-tarefas.git
# Ou se preferir baixe o arquivo .zip

# Após abrir o projeto e a IDE buildar e baixar as dependências necessárias,
# Crie um banco de dados Postgresql local com os dados de acesso contidos no arquivo
application.yml
# As tabelas e colunas serão criadas automaticamente pelo projeto
# Com o projeto rodando e o banco criado e ativo, faça as requisições aos endpoints que desejar
# A lista de endpoints se encontram no endereço:
http://localhost:8080/swagger-ui/index.html#/
```

## 👨‍💻 Autor

<a href="https://github.com/CirNNe">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/98779843?s=400&u=0acf3d526d374b620501ea180d5c81c3ff998c42&v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Higor Cirne</b></sub></a> <a href="https://github.com/CirNNe" title="GitHub"></a>

👋🏽 Entre em contato!

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/higorcirne/)
[![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white)](https://www.instagram.com/higordev_/)
