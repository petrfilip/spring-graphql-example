# Ukázka použití GraphQL a Spring Bootu


![](graphiql.png)

- Běží na Javě 17. 
- Využito _snapshot_ a _milestone_ repozitářů. 
- Pro ukládání data je využita H2 databáze. 
- Potřeba Lombok rozšíření 

**!!! Při vytváření TODO je userId hard-coded na hodnotu `2`.**


### Poznámky k vývoji
Schémata ukladat do adresáře `resources/graphql`

#### základní typy
- type - definuje návratové typy
- input - používá se jako vstup do requestů
- enum 

#### GraphQL Anotace
- @QueryMapping - (@Argument)
- @SchemaMapping - resolving schémat
- @BatchMapping - resolving schémat pro N+1 dotazy

## Ukázky requestů

GRAPHIQL: http://localhost:8080/graphiql?path=/graphql

### Mutation
```graphql
mutation {
    createUser(user: {email: "email@seznam.cz"}) {
        id
        email
    }
}
```

```graphql
mutation {
    createTodo(todo: {title: "My new todo item", completed: false}) {
        id
        title
        completed
    }
}
```


### Query

```graphql
query {
    getTodosByUserId(userId: 2, pageInfo: {offset: 2, limit: 5}) {
        id
        title
        completed
        createdBy {
            id
            email
        }
    }
}
```
Get all users
```graphql
query {
    getUsers {
        id,
        email
    }
}
```
Recursive query
```graphql
query {
    getTodosByUserId(
        userId: 2
        pageInfo: {offset: 2, limit: 5}
    ) {
        id
        title
        completed
        createdBy {
            id
            email
            todoList {
                id
                title
                createdBy {
                    id
                    email
                    todoList {
                        id
                        title
                        createdBy {
                            id
                            email
                            todoList {
                                title,
                                createdBy {
                                    email
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
```

----
- [ ] Vylepšit přidávání todo itemů (problém s uživateli)
- [ ] vylepšit vstupní parametry
- [x] Vytvořit typové resolvery 
- [x] Vyřešit N+1 problém
- [ ] Vyřešit securitu
- [ ] Přidat vlastní datové typy
- [ ] Validace vstupních parametrů
- [ ] Pořešit stránkování
- [ ] Vyzkoušet Subscription
- [ ] Vytvořit základní GUI
- [ ] vyčistit kód
