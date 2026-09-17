# DeviceX

Sistema de gerenciamento para assistência técnica de computadores e dispositivos eletrônicos.

O DeviceX foi desenvolvido como um projeto full stack com foco em organização de atendimentos, clientes, dispositivos e ordens de serviço, utilizando uma arquitetura baseada em API REST.

---

## 📌 Sobre o projeto

O DeviceX tem como objetivo centralizar o gerenciamento de uma assistência técnica, permitindo controlar informações de clientes, equipamentos e ordens de serviço.

A aplicação foi desenvolvida pensando em uma estrutura que possa evoluir com novas funcionalidades ao longo do projeto.

### Principais funcionalidades atuais

- Cadastro e gerenciamento de clientes
- Cadastro e gerenciamento de dispositivos
- Criação e gerenciamento de ordens de serviço
- Controle de status das ordens de serviço
- Histórico de alterações de status
- Autenticação de usuários
- Autorização baseada em perfil
- Perfis de acesso ADMIN e TÉCNICO
- Validação de dados
- Tratamento global de exceções
- API REST
- Persistência de dados em PostgreSQL
- Integração com banco de dados hospedado
- Configuração por variáveis de ambiente

---

## 🛠️ Tecnologias utilizadas

### Backend

- Java 21
- Spring Boot 4.1.1
- Spring Data JPA
- Spring Security
- JWT
- Hibernate
- Bean Validation
- Maven

### Banco de dados

- PostgreSQL
- Supabase

### Infraestrutura

- Docker
- Render

### Controle de versão

- Git
- GitHub

---

## 🏗️ Arquitetura

O backend foi estruturado seguindo uma separação de responsabilidades entre as principais camadas da aplicação.

```text
src/main/java/com/devicex/api/

├── config/
│   ├── SecurityConfig.java
│   └── DataInitializer.java
│
├── controller/
│   ├── AuthController.java
│   ├── ClienteController.java
│   ├── DispositivoController.java
│   ├── OrdemServicoController.java
│   └── UsuarioController.java
│
├── dto/
│   ├── ClienteResponseDTO.java
│   ├── DispositivoResponseDTO.java
│   ├── HistoricoStatusResponseDTO.java
│   ├── LoginRequestDTO.java
│   ├── LoginResponseDTO.java
│   ├── OrdemServicoResponseDTO.java
│   └── UsuarioResponseDTO.java
│
├── exception/
│   ├── ClienteNotFoundException.java
│   ├── DispositivoNotFoundException.java
│   ├── GlobalExceptionHandler.java
│   ├── OrdemServicoNotFoundException.java
│   └── RegraNegocioException.java
│
├── model/
│   ├── Cliente.java
│   ├── Dispositivo.java
│   ├── HistoricoStatus.java
│   ├── OrdemServico.java
│   ├── Perfil.java
│   └── Usuario.java
│
├── repository/
│   ├── ClienteRepository.java
│   ├── DispositivoRepository.java
│   ├── HistoricoStatusRepository.java
│   ├── OrdemServicoRepository.java
│   └── UsuarioRepository.java
│
├── security/
│   ├── CustomUserDetailsService.java
│   └── JwtService.java
│
└── service/
    ├── ClienteService.java
    ├── DispositivoService.java
    ├── OrdemServicoService.java
    └── UsuarioService.java
