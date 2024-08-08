# Planner API

Aplicação foi desenvolvida para ajudar na organização e planejamento de viagens, permitindo o gerenciamento de atividades, participantes e links importantes relacionados a uma viagem. 

## Funcionalidades

### 1. **Criação de Viagens**
   - Crie uma nova viagem especificando o destino, data de início e término.
   - Convide participantes através de e-mail.

### 2. **Gerenciamento de Participantes**
   - Adicione participantes a uma viagem específica.
   - Obtenha uma lista de todos os participantes de uma viagem.
   - Envie e-mails de confirmação para participantes quando a viagem for confirmada.

### 3. **Atividades**
   - Registre atividades para uma viagem, como passeios, eventos ou reuniões.
   - Obtenha uma lista de todas as atividades planejadas para uma viagem.

### 4. **Links Úteis**
   - Adicione links importantes relacionados à viagem, como reservas de hotéis, sites de eventos, etc.
   - Obtenha todos os links cadastrados para uma viagem.

### 5. **Confirmação de Viagens**
   - Confirme uma viagem para notificar todos os participantes e registrar a viagem como confirmada.

## Endpoints

- **POST /trips:** Cria uma nova viagem.
- **GET /trips/{id}:** Obtém detalhes de uma viagem específica.
- **PUT /trips/{id}:** Atualiza as informações de uma viagem.
- **GET /trips/{id}/confirm:** Confirma uma viagem.
- **POST /trips/{id}/invite:** Convida um participante para a viagem.
- **GET /trips/{id}/participants:** Obtém todos os participantes de uma viagem.
- **POST /trips/{id}/activities:** Adiciona uma nova atividade à viagem.
- **GET /trips/{id}/activities:** Obtém todas as atividades planejadas para uma viagem.
- **POST /trips/{id}/links:** Adiciona um link relacionado à viagem.
- **GET /trips/{id}/links:** Obtém todos os links cadastrados para uma viagem.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **H2 Database** (ou outro banco de dados de sua escolha)
- **Maven**

## TO DO de próximos passos

- Criar testes unitários.
- Criar testes de comportamento (BDD)

## Como Rodar a Aplicação

1. Clone este repositório.
2. Navegue até o diretório do projeto.
3. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.
4. Acesse a API através do endereço `http://localhost:8080`.
