# Ukázka použití GraphQL a Spring Bootu

GRAPHIQL: http://localhost:8080/graphiql?path=/graphql

![](graphiql.png)

### Mutation

```graphql
mutation {
    createTodo(todo: {title: "asdf", completed: false}) {
        id
        title
        completed
    }
}
```

```graphql
mutation {
    createUser(user: {email: "asdf@asdf.cz"}) {
        id
        email
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

```graphql
query {
    getUsers {
        id,
        email
    }
}
```

