# 🚀 Event Microservice
Este projeto é um **Microserviço de Gestão de Eventos** desenvolvido com **Spring Boot 3** e **Java**. Ele é responsável por criar eventos, gerenciar inscrições de participantes e interagir com um serviço externo (via Feign Client) para o envio de e-mails de confirmação. Feito com base na live da [@Fernanda-Kipper](https://github.com/fernanda-kipper)

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java
* **Framework:** Spring Boot 3
* **Banco de Dados:** H2 Database (em memória, para desenvolvimento)
* **ORM:** Spring Data JPA / Hibernate
* **Comunicação entre Serviços:** Spring Cloud OpenFeign
* **Utilitários:** Lombok
* **Build Tool:** Maven (Presumido)

## 📁 Estrutura da Aplicação (Visão Geral)O serviço expõe endpoints REST para operações CRUD em eventos e um endpoint dedicado para registro de participantes, que contém a lógica de negócio principal.

| Camada | Classes Chave | Responsabilidade |
| --- | --- | --- |
| **Domain/Entity** | `Event`, `Subscription` | Modelagem dos dados persistentes no H2. |
| **Repository** | `EventRepository`, `SubscriptionRepository` | Acesso e manipulação dos dados no banco. |
| **Service** | `EventService` | Lógica de negócio, validação de capacidade e integração Feign. |
| **Controller** | `EventController` | Recebe as requisições HTTP e retorna as respostas. |
| **Feign** | `EmailServiceClient` | Cliente HTTP para o Microserviço de E-mail. |
| **Exceptions** | `EventFullException`, `EventNotFoundException` | Tratamento de erros específicos da aplicação. |

## ⚙️ Configuração e Execução###Pré-requisitos* Java 17 ou superior
* Maven

### 1. Clonar e Compilar```bash
# Se você tiver um repositório git
git clone github.com/jotamath/ms-events-api.git
cd event-microservice

# Compilar o projeto
mvn clean install

```

###2. Executar a Aplicação```bash
# Executar o JAR gerado
java -jar target/event-microservice-0.0.1-SNAPSHOT.jar 
# (Ou use a opção Run As Spring Boot App no seu IDE: Eclipse/VS Code)

```

O serviço será iniciado na porta padrão do Spring Boot (geralmente `8080`).

## 📡 Endpoints da APIA base da API é `http://localhost:8080/events`.

| Método | Endpoint | Descrição | Corpo da Requisição | Resposta Esperada |
| --- | --- | --- | --- | --- |
| **POST** | `/events` | Cria um novo evento. | `EventRequestDTO` (title, description, maxParticipants, etc.) | Retorna o `Event` criado com o `ID` gerado. |
| **GET** | `/events` | Lista todos os eventos. | N/A | Lista de `Event`. |
| **POST** | `/events/{id}/register` | Registra um participante em um evento. | `{ "participantEmail": "email@teste.com" }` | 200 OK (Em caso de sucesso). |

### Exceções Tratadas| Status | Exceção | Descrição |
| --- | --- | --- |
| **404 Not Found** | `EventNotFoundException` | ID do evento não encontrado no banco. |
| **400 Bad Request** | `EventFullException` | O evento atingiu o limite de participantes. |
