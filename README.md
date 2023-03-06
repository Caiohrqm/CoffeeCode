# CoffeeCode

Uma API para um menu de uma cafeteria.

## Endpoints

- Cardápio
    - [Cadastrar item](#cadastrar-item)
    - [Atualizar item](#atualizar-item)
    - [Apagar item](#apagar-item)
    - [Listar tudo](#listar-tudo-mostrar-cardápio)
- Categorias
    - [Cadastrar](#cadastrar-categoria)
    - [Atualizar](#atualizar-categoria)
    - [Apagar](#apagar-categoria)
    - [Listar todas](#listar-todas)
- Pedidos
    - Cadastrar
    - Atualizar
    - Apagar
    - Listar todas
    - Mostrar detalhes

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

`PUT` /item/{item_id}

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

`DELETE` /item/{item_id}

**Códigos de resposta**

Código| Descrição
-|-
200| excluído com sucesso
401| sem permissão
404| id informado não existe

---

### Listar tudo (Mostrar Cardápio)

`GET` /menu

**Campos da resposta**

Campo| Tipo| Obrigatório| Descrição
-|-|:-:|-
id| inteiro| sim| código do item
categoria_id| inteiro| não| código de uma categoria previamente cadastrada
nome| texto| não| nome do item
descricao| texto| não| descrição detalhada do item
preco| decimal| não| preço de venda do item
ativo| booleano| não| se o item está ou não à venda no momento

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
            "preco": 5.6,
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

`PUT` /categoria/{categoria_id}

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

`DELETE` /categoria/{categoria_id}

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
id| inteiro| sim| código do item
categoria_id| inteiro| não| código de uma categoria previamente cadastrada
nome| texto| não| nome do item
descricao| texto| não| descrição detalhada do item
preco| decimal| não| preço de venda do item
ativo| booleano| não| se o item está ou não à venda no momento

**Exemplo de corpo de resposta**

```json
{
    "itens": [
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