<h1 align="center">passcheck</h1>

<p align="center">
 <b>Aplicação backend que faz validação de senha</b></br>
  <span>Leandro Silva Yamaniha </sub>
</p>

# Resumo

Será exposto um endpoint que validará a senha de acordo com as seguintes definições:
- Nove ou mais caracteres
- Ao menos 1 dígito
- Ao menos 1 letra minúscula
- Ao menos 1 letra maiúscula
- Ao menos 1 caractere especial
- Considere como especial os seguintes caracteres: !@#$%^&*()-+
- Não possuir caracteres repetidos dentro do conjunto
Nota: Espaços em branco não devem ser considerados como caracteres válidos.

Para validar a senha foi usado expressão regular (Regex).
A senha a ser validada será informada no body da requisição do endpoint, verbo POST, http://localhost:8080/validate/password, ,cujo o payload será:

```
{
  "password": "senha"
}
```
Quando a senha for vâlida será retornado o httpStatus 200, com o seguinte body:
```
{ "valid": true }
```

Quando a senha for inválida será retornado , httpStatus 400 e o seguinte response body:
```
{ "valid": false }
```

Se for gerado o body invâlido será retornado httpStatus 400, com o atributo errors.
Exemplo:
```
curl -X POST \
  http://localhost:8080/validate/password \
  -H 'accept: */*' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 2a95c68a-221f-aa2f-b0ed-1cd93f326f55'
```

Response Body com HttpStatus 400 :
```
{
    "errors": {
        "message": "Malformed request body"
    }
}
```

Se for informado senha null. 
Exemplo:
````
curl -X POST \
  http://localhost:8080/validate/password \
  -H 'accept: */*' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: f2e6ef07-2398-f213-4610-7cb297cbd075' \
  -d '{
	"password":null
}'
````

O response body serâ:
````
{
    "errors": {
        "password": "não deve ser nulo"
    }
}
````

# Requisitos

[Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

[Maven](https://maven.apache.org/)

[Spring Tools 4 for Eclipse](https://spring.io/tools) 

[IntelliJ](https://www.jetbrains.com/pt-br/idea/)

[Eclipse - Java Code Coverage](https://www.eclemma.org/)

# Instalação OS X & Linux:

**Java 11 - SDKMAN:**

[Instalação SDKMAN](https://sdkman.io/install)
```sh
sdk i java 11.0.2-open
```

**Maven**

[Instalação Maven](https://maven.apache.org/install.html)


# Execução e Compilação

**Compilação e geração do artefato jar**

Acessar a pasta raiz do projeto:

```sh
sdk use java 11.0.2-open
mvn clean package
```

**Execução**

```sh
java -jar target/passcheck-0.0.1-SNAPSHOT.jar
```

**Testes do enpoint**

Podem ser realizados os testes de 2 formas:
- Como o projeto implementa o uso de openapi, é gerado um front para testes no endpoint via swagger ui, que é acessado por : http://localhost:8080/swagger-ui.html
- Ou via curl, postman ou qualquer cliente que faça consumo de api REST, como por exemplo o postman. 

```sh
curl -X POST "http://localhost:8080/validate/password" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"password\":\"AbTp9!fok\"}"
```

# Outras informações

Foi incluido no projeto : 
- check style
- jacoo para medir cobertura, trava de cobertura minima de 90% e relatório de coverage
- apache pmd para analise de código
- foi utilizado sonarqube, através da imagem docker, acessado via : docker-compose up -d. Neste caso é necessário ter o docker instalado.


