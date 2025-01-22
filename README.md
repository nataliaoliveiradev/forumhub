# <h1 align="center">ForumHub - Gerenciando Fóruns com Java e Spring Boot</h1>

<p align="center">
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/JPA-Hibernate-blue?style=for-the-badge&logo=hibernate&logoColor=white"/>
<img src="https://img.shields.io/badge/Alura-004D61?style=for-the-badge&logo=alura&logoColor=white"/>
</p>

---

## <h2 align="center">Descrição do Projeto</h2>

O **ForumHub** é um projeto desenvolvido para gerenciar tópicos e respostas em fóruns, utilizando as melhores práticas de desenvolvimento backend com Java e Spring Boot. O sistema implementa operações de CRUD para tópicos e respostas, além de autenticação e autorização baseadas em tokens JWT.

---

## <h2 align="center">Índice</h2>

* [Descrição do Projeto](#descrição-do-projeto)
* [Funcionalidades](#funcionalidades)
* [Objetivos do Projeto](#objetivos-do-projeto)
* [Tecnologias Utilizadas](#tecnologias-utilizadas)
* [Pré-requisitos](#pré-requisitos)
* [Como Executar o Projeto](#como-executar-o-projeto)
* [Autor](#autor)
* [Contato](#contato)
* [Licença](#licença)

---

## <h2 align="center">Funcionalidades</h2>

🔨 Funcionalidades do projeto:

- CRUD para tópicos de fórum.
- CRUD para respostas associadas a tópicos.
- Sistema de autenticação e autorização com tokens JWT.
- Paginação e ordenação de tópicos e respostas.
- Validação de dados de entrada para maior segurança e robustez.

---

## <h2 align="center">Objetivos do Projeto</h2>

📚 Este projeto foi desenvolvido para:
- Aplicar os conceitos de desenvolvimento backend em Java.
- Trabalhar com autenticação e autorização utilizando Spring Security.
- Consumir, armazenar e gerenciar dados com JPA e Hibernate.
- Aprender a validar dados de entrada com Jakarta Validation.
- Praticar a criação de APIs RESTful seguindo boas práticas.

---

## <h2 align="center">Tecnologias Utilizadas</h2>

- **Linguagem de Programação:**  
![Java](https://img.shields.io/badge/java-000.svg?style=for-the-badge&logo=openjdk&logoColor=%23ED8B00)

- **Frameworks:**  
![Spring Boot](https://img.shields.io/badge/springboot-000.svg?style=for-the-badge&logo=springboot&logoColor=%236DB33F)

- **Ferramentas:**  
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-000?style=for-the-badge&logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-000?style=for-the-badge&logo=jsonwebtokens&logoColor=orange)

---

## <h2 align="center">Pré-requisitos</h2>

Antes de começar, certifique-se de ter os seguintes itens instalados no seu ambiente:

- **Java JDK** 11 ou superior.  
- **Maven** configurado para gerenciamento de dependências.  
- **PostgreSQL** ou outro banco de dados relacional configurado.  
- IDE como **IntelliJ IDEA** ou **Eclipse**.

---

## <h2 align="center">Como Executar o Projeto</h2>

### 📁 Acesso ao projeto

Clone o repositório com o comando:

```bash
git clone https://github.com/seuusuario/forumhub.git
```

### 🛠️ Configuração e Execução

1. Configure o arquivo `application.properties` com as credenciais do seu banco de dados:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/forumhub
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

2. Execute o comando Maven para compilar o projeto:
   ```bash
   mvn clean install
   ```

3. Inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a API na URL:
   ```
   http://localhost:8080
   ```

---

## <h2 align="center">Autor</h2>

<div align="center">

| [<img src="https://avatars.githubusercontent.com/u/12345678?v=4" width=115><br><sub>Seu Nome</sub>](https://github.com/seuusuario) |
| :---: |
| **Desenvolvedor Backend, entusiasta de Java e apaixonado por tecnologia.** |

</div>

---

## <h2 align="center">Contato</h2>

[![LinkedIn](https://img.shields.io/badge/LinkedIn-000?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/seulinkedin/)  
[![GitHub](https://img.shields.io/badge/GitHub-000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/seuusuario)  
[![E-mail](https://img.shields.io/badge/-Email-000?style=for-the-badge&logo=microsoft-outlook&logoColor=white)](mailto:seuemail@exemplo.com)

---

## <h2 align="center">Licença</h2>

<p align="center">
Este projeto está licenciado sob a licença MIT. Para mais detalhes, consulte o arquivo LICENSE.
</p>

---
```
