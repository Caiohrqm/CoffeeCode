# CoffeeCode

Uma API para um menu de uma cafeteria.

## Endpoints

- Itens
    - [Cadastrar](#cadastrar-item)
    - [Atualizar](#atualizar-item)
    - [Apagar](#apagar-item)
    - [Listar](#listar-itens)
- Categorias
    - [Cadastrar](#cadastrar-categoria)
    - [Atualizar](#atualizar-categoria)
    - [Apagar](#apagar-categoria)
    - [Listar](#listar-categorias)
- Pedidos
    - [Registrar](#registrar-pedido)
    - [Atualizar](#atualizar-pedido)
    - [Apagar](#apagar-pedido)
    - [Listar](#listar-pedidos)
    - [Detalhar](#detalhar-pedido)

---

### Cadastrar Item

`POST` /item

**Campos da requisição**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
id| inteiro| sim| código do item
categoria_id| inteiro| sim| código de uma categoria previamente cadastrada
nome| texto| sim| nome do item
descricao| texto| não| descrição detalhada do item
preco| decimal| sim| preço de venda do item
ativo| booleano| sim| se o item está ou não à venda no momento

**Exemplo de corpo de requisição**

```json
{
    "id": 1,
    "categoria_id": 1,
    "nome": "Expresso",
    "descricao": "Café preto",
    "preco": 4,
    "ativo": true
}
```

**Códigos de resposta**

Código| Descrição
-|-
201| item cadastrado com sucesso
401| sem permissão
400| os campos enviados são inválidos

---

### Atualizar Item

`PUT` /item/{id}

**Campos da requisição**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
categoria_id| inteiro| não| código de uma categoria previamente cadastrada
nome| texto| não| nome do item
descricao| texto| não| descrição detalhada do item
preco| decimal| não| preço de venda do item
ativo| booleano| não| se o item está ou não à venda no momento

**Exemplo de corpo de requisição**

```json
{
    "categoria_id": 1,
    "nome": "Expresso",
    "descricao": "Café preto",
    "preco": 4.2,
    "ativo": true
}
```

**Códigos de resposta**

Código| Descrição
-|-
200| atualizado com sucesso
401| sem permissão
404| id informado não existe

---

### Apagar Item

`DELETE` /item/{id}

**Códigos de resposta**

Código| Descrição
-|-
200| excluído com sucesso
401| sem permissão
404| id informado não existe

---

### Listar Itens

`GET` /item

**Campos da resposta**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
id| inteiro| sim| código do item
categoria_id| inteiro| não| código de uma categoria previamente cadastrada
nome| texto| não| nome do item
descricao| texto| não| descrição detalhada do item
preco| decimal| não| preço de venda do item
ativo| booleano| não| se o item está à venda no momento, se padrão é true

**Exemplo de corpo de resposta**

```json
{
    "menu": [
        {
            "id": 1,
            "categoria_id": 1,
            "nome": "Expresso",
            "descricao": "Café preto",
            "preco": 4.2,
            "ativo": true
        },
        {
            "id": 2,
            "categoria_id": 1,
            "nome": "Cappuccino",
            "descricao": "Café com leite e chantilly",
            "preco": 4.8,
            "ativo": true
        },
        {
            "id": 3,
            "categoria_id": 1,
            "nome": "Mocha",
            "descricao": "Café com chocolate, leite e chantilly",
            "preco": 5.6,
            "ativo": true
        },
        {
            "id": 6,
            "categoria_id": 2,
            "nome": "Coxinha",
            "descricao": "Recheada com frango e catupiry",
            "preco": 5.8,
            "ativo": true
        }
    ]
}
```

**Códigos de resposta**

Código| Descrição
-|-
200| retornado com sucesso
404| página não encontrada

---

### Cadastrar Categoria

`POST` /categoria

**Campos da requisição**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
id| inteiro| sim| código da categoria
nome| texto| sim| nome da categoria

**Exemplo de corpo de requisição**

```json
{
    "id": 1,
    "nome": "Bebidas"
}
```

**Códigos de resposta**

Código| Descrição
-|-
201| cadastrado com sucesso
401| sem permissão
400| os campos enviados são inválidos

---

### Atualizar Categoria

`PUT` /categoria/{id}

**Campos da requisição**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
id| inteiro| sim| código da categoria
nome| texto| sim| nome da categoria

**Exemplo de corpo de requisição**

```json
{
    "id": 2,
    "nome": "Salgados"
}
```

**Códigos de resposta**

Código| Descrição
-|-
200| atualizado com sucesso
401| sem permissão
404| id informado não existe

---

### Apagar Categoria

`DELETE` /categoria/{id}

**Códigos de resposta**

Código| Descrição
-|-
200| excluído com sucesso
401| sem permissão
404| id informado não existe

---

### Listar Categorias

`GET` /categorias

**Campos da resposta**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
id| inteiro| sim| código da categoria
nome| texto| sim| nome da categoria

**Exemplo de corpo de resposta**

```json
{
    "categorais": [
        {
            "id": 1,
            "nome": "Bebidas"
        },
        {
            "id": 2,
            "nome": "Salgados"
        },
        {
            "id": 3,
            "nome": "Doces"
        }
    ]
}
```

**Códigos de resposta**

Código| Descrição
-|-
200| retornado com sucesso
404| página não encontrada

---

### Registrar Pedido

`POST` /pedido

**Campos da requisição**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
id| inteiro| sim| código interno do pedido
itens| lista[objeto]| sim| objeto contendo o id, preço e quantidade de cada item
total| decimal| sim| valor total do pedido
data| data| sim| data e hora que o pedido foi registrado
senha| inteiro| sim| código externo do pedido, zera no começo de cada dia
nome| texto| não| nome do cliente
entregue| booleano| sim| se o pedido já foi entregue, seu padrão é falso

**Exemplo de corpo de requisição**

```json
{
    "id": 1,
    "itens": [
        {
            "id": 1,
            "preco": 4.2,
            "quantidade": 1
        },
        {
            "id": 6,
            "preco": 5.8,
            "quantidade": 1
        }
    ],
    "total": 10.2,
    "data": "2023-03-06 16:28:53",
    "senha": "001",
    "nome": "João",
    "entregue": false
}
```

**Códigos de resposta**

Código| Descrição
-|-
201| cadastrado com sucesso
400| os campos enviados são inválidos

---

### Atualizar Pedido

`PUT` /pedido/{id}

**Campos da requisição**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
id| inteiro| sim| código interno do pedido
entregue| booleano| sim| se o pedido já foi entregue

**Exemplo de corpo de requisição**

```json
{
    "id": 1,
    "entregue": true
}
```

**Códigos de resposta**

Código| Descrição
-|-
200| atualizado com sucesso
401| sem permissão
404| id informado não existe

---

### Apagar Pedido

`DELETE` /pedido/{id}

**Códigos de resposta**

Código| Descrição
-|-
200| excluído com sucesso
401| sem permissão
404| id informado não existe

---

### Listar Pedidos

`GET` /pedidos

**Campos da resposta**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
id| inteiro| sim| código interno do pedido
itens| lista[objeto]| sim| objeto contendo o id, preço e quantidade de cada item
total| decimal| sim| valor total do pedido
data| data| sim| data e hora que o pedido foi registrado
senha| inteiro| sim| código externo do pedido, zera no começo de cada dia
nome| texto| não| nome do cliente

**Exemplo de corpo de requisição**

```json
{
    "pedidos": [
        {
            "id": 1,
            "itens": [
                {
                    "id": 1,
                    "preco": 4.2,
                    "quantidade": 1
                },
                {
                    "id": 6,
                    "preco": 5.8,
                    "quantidade": 1
                }
            ],
            "total": 10,
            "senha": "001",
            "nome": "João"
        },
        {
            "id": 2,
            "itens": [
                {
                    "id": 3,
                    "preco": 5.6,
                    "quantidade": 1
                }
            ],
            "total": 5.6,
            "senha": "002",
            "nome": "Maria"
        }
    ]
}
```

**Códigos de resposta**

Código| Descrição
-|-
200| retornado com sucesso
404| página não encontrada

---

### Detalhar Pedido

`GET` /pedido/{id}

**Campos da resposta**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
id| inteiro| sim| código interno do pedido
itens| lista[objeto]| sim| objeto contendo o id, preço e quantidade de cada item
total| decimal| sim| valor total do pedido
data| data| sim| data e hora que o pedido foi registrado
senha| inteiro| sim| código externo do pedido, zera no começo de cada dia
nome| texto| não| nome do cliente
entregue| booleano| sim| se o pedido já foi entregue, seu padrão é falso

**Exemplo de corpo de requisição**

```json
{
    "id": 1,
    "itens": [
        {
            "id": 1,
            "preco": 4.2,
            "quantidade": 1
        },
        {
            "id": 6,
            "preco": 5.8,
            "quantidade": 1
        }
    ],
    "total": 10,
    "data": "2023-03-06 16:28:53",
    "senha": "001",
    "nome": "João",
    "entregue": true
}
```

**Códigos de resposta**

Código| Descrição
-|-
200| retornado com sucesso
404| página não encontrada