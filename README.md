# Server Onboarding - Challenge Vivo 2025

## 📋 Descrição
API REST desenvolvida em Spring Boot para gerenciamento de tarefas e treinamentos no processo de onboarding da Vivo. O sistema permite criar, consultar, atualizar e excluir tarefas e treinamentos, além de fornecer relatórios gerais.

## 🛠️ Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **Spring Validation**
- **Oracle Database 19.3**
- **HikariCP** (Pool de conexões)
- **Maven** (Gerenciamento de dependências)

## ⚙️ Configuração do Ambiente

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6+
- Oracle Database 19.3
- Variáveis de ambiente configuradas:
  - `DB_ORCL`: URL de conexão do Oracle
  - `DB_ORCL_USER`: Usuário do banco
  - `DB_ORCL_PASSWORD`: Senha do banco

### Instalação e Execução
```bash
# Clone o repositório
git clone <url-do-repositorio>

# Entre no diretório
cd server_onboarding

# Execute a aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📁 Estrutura do Projeto
```
src/main/java/onboarding/vivo/demo/
├── controller/          # Controladores REST
│   ├── TarefaController.java
│   └── TreinamentoController.java
├── service/            # Lógica de negócio
│   ├── TarefaService.java
│   └── TreinamentoService.java
├── repository/         # Camada de acesso a dados
│   ├── TarefaRepository.java
│   └── TreinamentoRepository.java
├── model/             # Entidades JPA
│   ├── Tarefa.java
│   └── Treinamento.java
├── exception/         # Tratamento de exceções
│   ├── ResourceNotFoundException.java
│   └── RestExceptionHandler.java
└── ServerOnboardingApplication.java  # Classe principal
```

## 🚀 Endpoints da API

### 📝 Tarefas (`/dashboard/tarefas`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/get-tarefa` | Lista todas as tarefas |
| GET | `/get-tarefa/{id}` | Busca tarefa por ID |
| POST | `/create-tarefa` | Cria nova tarefa |
| PUT | `/update-tarefa/{id}` | Atualiza tarefa existente |
| DELETE | `/delete-tarefa/{id}` | Remove tarefa |

#### Exemplo de JSON para Tarefa:
```json
{
  "descricao": "Nova tarefa",
  "dataInicio": "06/06/2025",
  "dataFim": "10/06/2025",
  "idFuncionario": 1
}
```

#### Exemplo de Requisição - Criar Tarefa:
```bash
curl -X POST http://localhost:8080/dashboard/tarefas/create-tarefa \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Nova tarefa",
    "dataInicio": "06/06/2025",
    "dataFim": "10/06/2025",
    "idFuncionario": 1
  }'
```

### 🎓 Treinamentos (`/dashboard/treinamentos`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/get-treinamento` | Lista todos os treinamentos |
| GET | `/get-treinamento/{id}` | Busca treinamento por ID |
| POST | `/create-treinamento` | Cria novo treinamento |
| PUT | `/update-treinamento/{id}` | Atualiza treinamento existente |
| DELETE | `/delete-treinamento/{id}` | Remove treinamento |

#### Exemplo de JSON para Treinamento:
```json
{
  "descricao": "Treinamento Spring Boot",
  "dataInicio": "01/10/2025",
  "dataFim": "05/10/2025",
  "idFuncionario": 1
}
```

## 📊 Pool de Conexões (HikariCP)
- **Timeout de conexão**: 60 segundos
- **Idle timeout**: 10 minutos
- **Max lifetime**: 30 minutos
- **Pool máximo**: 10 conexões
- **Pool mínimo**: 5 conexões
- **Query de teste**: `SELECT 1 FROM DUAL`

## 📝 Formato de Datas
As datas devem ser enviadas no formato `dd/MM/yyyy` (ex: `06/06/2025`).

**Exemplos válidos:**
- `"01/01/2025"`
- `"25/12/2025"`
- `"06/06/2025"`

## 🔍 Logs e Monitoramento

### Configuração de Logs
```properties
# Logging
logging.level.com.zaxxer.hikari=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## 🏗️ Arquitetura da Aplicação

### Padrão MVC (Model-View-Controller)
- **Controller**: Camada de apresentação (REST endpoints)
- **Service**: Camada de lógica de negócio
- **Repository**: Camada de acesso a dados (JPA)
- **Model**: Entidades de domínio

### Injeção de Dependências
- Utiliza constructor injection (recomendado pelo Spring)
- `@Autowired` implícito nos construtores únicos
- Configuração via `@Service`, `@Repository`, `@RestController`

### Validação
- Bean Validation com `@Valid`
- `@NotBlank` para campos obrigatórios
- `@NotNull` para campos não nulos

## 🚀 Deploy e Produção

### Variáveis de Ambiente Obrigatórias
```bash
DB_ORCL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
DB_ORCL_USER=seu_usuario
DB_ORCL_PASSWORD=sua_senha
```

### Build para Produção
```bash
# Limpar e compilar
./mvnw clean compile

# Executar testes
./mvnw test

# Gerar JAR
./mvnw package

# Executar JAR
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## 📚 Dependências Maven

### Principais Dependências
```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Oracle Database Driver -->
    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc8</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Test Dependencies -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 📄 Licença
Este projeto foi desenvolvido para o Challenge Vivo 2025.
