# CoffeeCode

Bem-vindo à documentação da API do CoffeeCode, um sistema de cardápio para uma cafeteria. Este sistema foi criado para ajudar a gerenciar o cardápio e os pedidos de uma cafeteria de forma eficiente e simplificada. Com o CoffeeCode, os clientes podem visualizar facilmente os itens disponíveis no cardápio e fazer pedidos de acordo com suas preferências. Além disso, os funcionários podem gerenciar os pedidos, atualizar o cardápio sempre que necessário. Nesta documentação, você encontrará informações detalhadas sobre as rotas disponíveis, os parâmetros necessários e as respostas retornadas pela API.

## Contribuidores

-  [Caio Henrique Martins](https://github.com/Caiohrqm)

- [Gabriella Vieira](https://github.com/vieiragab)

## Endpoints

- Menu (Cardápio)
    - [Adicionar Item](#adicionar-item)
    - [Atualizar Item](#atualizar-item)
    - [Apagar Item](#apagar-item)
    - [Listar Itens](#listar-itens)
    - [Mostrar Item](#mostrar-item)
- Pedidos
    - [Registrar](#registrar-pedido)
    - [Atualizar](#atualizar-pedido)
    - [Apagar](#apagar-pedido)
    - [Listar Todos](#listar-pedidos)
    - [Mostrar](#mostrar-pedido)

---

### Adicionar Item

`POST` /menu

**Campos da requisição**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
categoria| texto| sim| categoria do item
nome| texto| sim| nome do item
descricao| texto| não| breve descrição do item
preco| decimal| sim| preço de venda do item
ativo| booleano| sim| está à venda no momento; **padrão:** *true*

**Corpo de requisição**

```json
{
    "categoria": "Bebidas",
    "nome": "Expresso",
    "descricao": "Café preto",
    "preco": 3.50,
    "ativo": true
}
```

**Exemplo de resposta**

```json
{
    "id": 1,
    "categoria": "Bebidas",
    "nome": "Expresso",
    "descricao": "Café preto",
    "preco": 3.50,
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

### Atualizar item

`PUT` /menu/{id}

**Parâmetros de caminho**

id - código do item a ser atualizado

**Corpo de requisição**

```json
{
    "categoria": "Bebidas",
    "nome": "Cappuccino",
    "descricao": "Café com leite e chantilly",
    "preco": 5.00,
    "ativo": true
}
```

**Exemplo de resposta**

```json
{
    "id": 2,
    "categoria": "Bebidas",
    "nome": "Cappuccino",
    "descricao": "Café com leite e chantilly",
    "preco": 5.00,
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

### Apagar item

`DELETE` /menu/{id}

**Parâmetros de caminho**

id - código do item a ser apagado

**Códigos de resposta**

Código| Descrição
-|-
200| excluído com sucesso
401| sem permissão
404| id informado não existe

---

### Listar Itens

`GET` /menu

**Exemplo de resposta**

```json
[
    {
        "id": 1,
        "categoria": "Bebidas",
        "nome": "Expresso",
        "descricao": "Café preto",
        "preco": 3.50,
        "ativo": true
    },
    {
        "id": 2,
        "categoria": "Bebidas",
        "nome": "Cappuccino",
        "descricao": "Café com leite e chantilly",
        "preco": 5.50,
        "ativo": true
    },
    {
        "id": 3,
        "categoria": "Salgados",
        "nome": "Coxinha",
        "descricao": "Recheada com frango e catupiry",
        "preco": 6.50,
        "ativo": true
    }
]
```

**Códigos de resposta**

Código| Descrição
-|-
200| retornado com sucesso
404| página não encontrada

---

### Mostrar Item

`GET` /menu/{id}

**Parâmetros de caminho**

id - código do item a ser consultado

**Exemplo de resposta**

```json
{
    "id": 1,
    "categoria": "Bebidas",
    "nome": "Expresso",
    "descricao": "Café preto",
    "preco": 3.50,
    "ativo": true
}
```

**Códigos de resposta**

Código| Descrição
-|-
200| retornado com sucesso
404| id informado não existe

---

### Registrar Pedido

`POST` /pedido

**Campos da requisição**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
itens| lista[objeto]| sim| objeto contendo o id, preço e quantidade de cada item
total| decimal| sim| valor total do pedido
data| data| sim| data e hora que o pedido foi registrado
senha| inteiro| sim| código de espera do pedido; contagem zera no começo de cada dia
nome| texto| não| nome do cliente
entregue| booleano| sim| pedido já foi entregue; **padrão:** *false*

**Corpo de requisição**

```json
{
    "itens": [
        {
            "id": 1,
            "preco": 3.50,
            "quantidade": 1
        },
        {
            "id": 3,
            "preco": 6.50,
            "quantidade": 1
        }
    ],
    "total": 10.00,
    "data": "2023-03-07 09:52:33",
    "senha": 001,
    "nome": "João",
    "entregue": false
}
```

**Exemplo de resposta**

```json
{
    "id": 1,
    "itens": [
        {
            "id": 1,
            "preco": 3.50,
            "quantidade": 1
        },
        {
            "id": 3,
            "preco": 6.50,
            "quantidade": 1
        }
    ],
    "total": 10.00,
    "data": "2023-03-07 09:52:33",
    "senha": 001,
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

**Parâmetros de caminho**

id - código do pedido a ser atualizado

**Corpo de requisição**

```json
{
    "itens": [
        {
            "id": 1,
            "preco": 3.50,
            "quantidade": 1
        },
        {
            "id": 3,
            "preco": 6.50,
            "quantidade": 1
        }
    ],
    "total": 10.00,
    "data": "2023-03-07 09:52:33",
    "senha": 001,
    "nome": "João",
    "entregue": true
}
```

**Exemplo de resposta**

```json
{
    "id": 1,
    "itens": [
        {
            "id": 1,
            "preco": 3.50,
            "quantidade": 1
        },
        {
            "id": 3,
            "preco": 6.50,
            "quantidade": 1
        }
    ],
    "total": 10.00,
    "data": "2023-03-07 09:52:33",
    "senha": 001,
    "nome": "João",
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

**Parâmetros de caminho**

id - código do pedido a ser excluído

**Códigos de resposta**

Código| Descrição
-|-
200| excluído com sucesso
401| sem permissão
404| id informado não existe

---

### Listar Pedidos

`GET` /pedidos

**Exemplo de resposta**

```json
[
    {
        "id": 1,
        "itens": [
            {
                "id": 1,
                "preco": 3.50,
                "quantidade": 1
            },
            {
                "id": 3,
                "preco": 6.50,
                "quantidade": 1
            }
        ],
        "total": 10.00,
        "data": "2023-03-07 09:52:33",
        "senha": 001,
        "nome": "João",
        "entregue": true
    },
    {
        "id": 2,
        "itens": [
            {
                "id": 3,
                "preco": 6.50,
                "quantidade": 2
            }
        ],
        "total": 13.00,
        "data": "2023-03-07 10:08:46",
        "senha": 002,
        "nome": "Maria",
        "entregue": false
    }
]
```

**Códigos de resposta**

Código| Descrição
-|-
200| retornado com sucesso
404| página não encontrada

---

### Mostrar Pedido

`GET` /pedido/{id}

**Parâmetros de caminho**

id - código do pedido a ser consultado

**Exemplo de resposta**

```json
{
    "id": 2,
    "itens": [
        {
            "id": 3,
            "preco": 6.50,
            "quantidade": 2
        }
    ],
    "total": 13.00,
    "data": "2023-03-07 10:08:46",
    "senha": 002,
    "nome": "Maria",
    "entregue": false
}
```

**Códigos de resposta**

Código| Descrição
-|-
200| retornado com sucesso
404| id informado não existe
