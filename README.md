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

# Requisitos

[Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

[Maven](https://maven.apache.org/)

[Spring Tools 4 for Eclipse](https://spring.io/tools) 

[IntelliJ](https://www.jetbrains.com/pt-br/idea/)

[Eclipse - Java Code Coverage](https://www.eclemma.org/)

# Instalação OS X & Linux:

**Java 11 - SDKMAN:**

```sh
[Instalação SDKMAN](https://sdkman.io/install)
sdk i java 11.0.2-open
```

**Maven**

```sh
[Instalação Maven](https://maven.apache.org/install.html)
```

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
- Como o projeto implementa o uso de openapi, é gerado um front para testes no endpoint via swagger ui, que é acessado por : http://localhost:8080/swagger-ui,html
- Ou via curl, postman ou qualquer cliente que faça consumo de api REST, como por exemplo o postman. 

```sh
curl -X POST "http://localhost:8080/validate/password" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"password\":\"AbTp9!fok\"}"
```

O request deverá ser realizado com o verbo POST na url http://localhost:8080/validate/password, com o seguinte request body:
```
{ "password": "AbTp9!fok"  }
```

Sendo informado o valor da senha no atributo password, para ser validado, quando ocorrer sucesso irá retornar httpStatus 200 e response body será:
```
{ "valid": true }
```

Quando a senha for inválida será retornado , httpStatus 400 e o seguinte response body:
```
{ "valid": false }
```

