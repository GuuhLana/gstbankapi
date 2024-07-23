# GTechSystemBank USJT🚀
Este projeto consiste no desenvolvimento de uma aplicação similar a um sistema bancário, permitindo realizar operações bancárias básicas. Também foi implementado uma visualização de extratos e um barramento de eventos que estará monitorando todas as operações realizadas na API. Este projeto faz parte de um trabalho acadêmico para o curso de Sistemas de Informação, especificamente na UC de Sistemas Distribuídos e Mobile. O objetivo é demonstrar a aplicação de conceitos aprendidos durante o curso, tais como desenvolvimento de APIs REST, persistência de dados, e monitoramento de eventos em sistemas distribuídos.

## Funcionalidades: ⭐
    -Criação de conta
    -Exclusão de conta
    -Consulta de saldo
    -Consulta de extrato
    -Depósitos
    -Transferência entre contas

## Premissas: ⚙️
    -Spring Boot
    -Banco de dados PostgreSQL
    -REST API
    -Disponibilização do código em Git
    -Barramento de eventos

## Como executar? 🔨

Para executar a aplicação, siga os passos abaixo:

1. Clone o repositório Git:

        git clone https://github.com/GuuhLana/hdsystembank

2. Configure uma base de dados PostgreSQL conforme as especificações no arquivo application.properties localizado no projeto:

application.properties exemplo:

    #Banco de dados POSTGRES
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.url=jdbc:postgresql://localhost:5432/hdsysbank
    spring.datasource.username=postgres
    spring.datasource.password=1234321
    spring.jpa.hibernate.ddl-auto=update

3. Certifique-se de ter todas as dependências necessárias instaladas e configuradas corretamente. Para isso, você pode utilizar o Maven para gerenciar as dependências do projeto.

4. Execute a aplicação via IDE, ou se preferir via linha de comando:

       sh
       mvn spring-boot:run

5. Para facilitar as simulações, existe um arquivo SQL no projeto que adiciona alguns valores de titulares de contas já existentes no banco de dados. Execute este script para popular o banco de dados com valores iniciais. O arquivo está localizado em src/main/resources/scripts_sql/01_INSERT_DEFAULT_USERS.sql.

    Se preferir, conteúdo do arquivo abaixo:
    
        INSERT INTO tb_conta(agencia, numero, saldo, titular) VALUES ('01', '01', '1000', 'Luna Pereira Silva');
        INSERT INTO tb_conta(agencia, numero, saldo, titular) VALUES ('01', '02', '1000', 'Sandro de Sousa Flores');
        INSERT INTO tb_conta(agencia, numero, saldo, titular) VALUES ('02', '01', '1000', 'Eduarda Pereira Lins');
        INSERT INTO tb_conta(agencia, numero, saldo, titular) VALUES ('02', '02', '1000', 'Gustavo de Lana Rocha');
        INSERT INTO tb_conta(agencia, numero, saldo, titular) VALUES ('03', '01', '1000', 'Ruan Mendes do Nascimento');
        INSERT INTO tb_conta(agencia, numero, saldo, titular) VALUES ('03', '02', '1000', 'Hiago Lima Junior');

## Hora dos testes? 🛠️
Para testar as requisições, recomendamos utilizar o POSTMAN ou qualquer outra ferramenta similar de teste de APIs.

## Referência 📖
- [O que é API?](https://www.redhat.com/pt-br/topics/api/what-are-application-programming-interfaces)
- [Diferenças entre APIs REST e RESTful](https://www.dio.me/articles/entendendo-as-diferencas-entre-apis-rest-e-restful)
- [Criando uma API Rest](https://www.treinaweb.com.br/blog/criando-uma-api-rest-com-o-spring-boot/)
- [Construindo uma API RESTful com Java e Spring Framework](https://mari-azevedo.medium.com/construindo-uma-api-restful-com-java-e-spring-framework-46b74371d107)
- [Persistencia com Spring](https://www.baeldung.com/persistence-with-spring-series)
- [Spring Boot com Postgres](https://hackernoon.com/using-postgres-effectively-in-spring-boot-applications)
