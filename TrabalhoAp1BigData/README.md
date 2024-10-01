# Gerenciamento de Clientes

### 1. **Criar um Cliente**
- **Método**: `POST`
- **URL**: `http://localhost:8080/api/clientes`
- **Título**: Criar Cliente
- **Exemplo de JSON**:
  
```json
{
  "nome": "João da Silva",
  "email": "joao@example.com",
  "cpf": "123.456.789-00",
  "dataNascimento": "1990-05-15",
  "telefone": "(11) 91234-5678",
  "enderecos": [
    {
      "rua": "Rua Exemplo",
      "numero": "123",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "estado": "SP",
      "cep": "01000-000"
    }
  ]
}
```

### 2. **Buscar todos os Clientes**
- **Método**: `GET`
- **URL**: `http://localhost:8080/api/clientes`
- **Título**: Listar Clientes
- **Exemplo de Resposta**:
  
```json
[
  {
    "id": 1,
    "nome": "João da Silva",
    "email": "joao@example.com",
    "cpf": "123.456.789-00",
    "dataNascimento": "1990-05-15",
    "telefone": "(11) 91234-5678",
    "enderecos": [
      {
        "id": 1,
        "rua": "Rua Exemplo",
        "numero": "123",
        "bairro": "Centro",
        "cidade": "São Paulo",
        "estado": "SP",
        "cep": "01000-000"
      }
    ]
  }
]
```

### 3. **Buscar Cliente por ID**
- **Método**: `GET`
- **URL**: `http://localhost:8080/api/clientes/{id}`
  - Substitua `{id}` pelo ID real do cliente que deseja buscar.
- **Título**: Buscar Cliente por ID
- **Exemplo de Resposta**:

```json
{
  "id": 1,
  "nome": "João da Silva",
  "email": "joao@example.com",
  "cpf": "123.456.789-00",
  "dataNascimento": "1990-05-15",
  "telefone": "(11) 91234-5678",
  "enderecos": [
    {
      "id": 1,
      "rua": "Rua Exemplo",
      "numero": "123",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "estado": "SP",
      "cep": "01000-000"
    }
  ]
}
```

### 4. **Atualizar um Cliente**
- **Método**: `PUT`
- **URL**: `http://localhost:8080/api/clientes/{id}`
  - Substitua `{id}` pelo ID do cliente que deseja atualizar.
- **Título**: Atualizar Cliente
- **Exemplo de JSON**:

```json
{
  "nome": "Maria Oliveira",
  "email": "maria@example.com",
  "cpf": "123.456.789-00",
  "dataNascimento": "1985-09-12",
  "telefone": "(11) 98765-4321",
  "enderecos": [
    {
      "rua": "Rua Nova",
      "numero": "456",
      "bairro": "Jardim",
      "cidade": "Campinas",
      "estado": "SP",
      "cep": "13050-000"
    }
  ]
}
```

### 5. **Excluir um Cliente**
- **Método**: `DELETE`
- **URL**: `http://localhost:8080/api/clientes/{id}`
  - Substitua `{id}` pelo ID do cliente que deseja deletar.
- **Título**: Excluir Cliente
- **Exemplo de Resposta**:
  - **HTTP Status**: `204 No Content` (não há conteúdo na resposta quando o cliente é excluído com sucesso)

---

### 6. **Criar um Endereço**
- **Método**: `POST`
- **URL**: `http://localhost:8080/api/enderecos/{idCliente}`
- **Título**: Criar Endereço
- **Exemplo de JSON**:

```json
{
  "rua": "Rua Nova",
  "numero": "456",
  "bairro": "Jardim",
  "cidade": "Campinas",
  "estado": "SP",
  "cep": "13050-000"
}
```

### 7. **Buscar todos os Endereços**
- **Método**: `GET`
- **URL**: `http://localhost:8080/api/enderecos`
- **Título**: Listar Endereços
- **Exemplo de Resposta**:

```json
[
  {
    "id": 1,
    "rua": "Rua Exemplo",
    "numero": "123",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "01000-000"
  }
]
```

### 8. **Buscar Endereço por ID**
- **Método**: `GET`
- **URL**: `http://localhost:8080/api/enderecos/{id}`
  - Substitua `{id}` pelo ID do endereço que deseja buscar.
- **Título**: Buscar Endereço por ID
- **Exemplo de Resposta**:

```json
{
  "id": 1,
  "rua": "Rua Exemplo",
  "numero": "123",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "estado": "SP",
  "cep": "01000-000"
}
```

### 9. **Atualizar um Endereço**
- **Método**: `PUT`
- **URL**: `http://localhost:8080/api/enderecos/{id}`
  - Substitua `{id}` pelo ID do endereço que deseja atualizar.
- **Título**: Atualizar Endereço
- **Exemplo de JSON**:

```json
{
  "rua": "Rua Alterada",
  "numero": "789",
  "bairro": "Novo Bairro",
  "cidade": "Sorocaba",
  "estado": "SP",
  "cep": "18040-000"
}
```

### 10. **Excluir um Endereço**
- **Método**: `DELETE`
- **URL**: `http://localhost:8080/api/enderecos/{id}`
  - Substitua `{id}` pelo ID do endereço que deseja deletar.
- **Título**: Excluir Endereço
- **Exemplo de Resposta**:
  - **HTTP Status**: `204 No Content`

---
