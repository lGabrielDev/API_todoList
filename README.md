<!-- title -->
<h1 align="center">
    <span>Todo List API</span>
</h1>

<!-- badges -->
<div align="left">
    <img src="https://img.shields.io/badge/license-MIT-yellow" alt="badge icon"></img>
    <img src="https://img.shields.io/badge/version-1.0-green" alt="badge icon"></img>
    <img src="https://img.shields.io/badge/repo size-118 MB-orange" alt="badge icon"></img>
</div>

<!-- languages -->
<div align="right">
    <span><em>language</em></span>
    <!-- BR -->
    <a href="#">
        <img src="https://cdn.icon-icons.com/icons2/83/PNG/512/brazil_15818.png" alt="brazil flag icon" width="20x" align="center">
    </a>
    <!-- US -->
    <a href="./src/main/resources/static/readme_en_us.md">
        <img src="https://cdn.icon-icons.com/icons2/3665/PNG/512/usa_flag_united_states_america_icon_228698.png" alt="america flag icon" width="20px" align="center">
    </a>
</div>

<!-- About -->
## <img src="https://cdn2.iconfinder.com/data/icons/flat-pack-1/64/Computer-512.png" alt="todo list image icon" width="40px" align="center"> Sobre o Projeto
Essa é uma todolist criada para manipular tarefas diárias. O Objetivo aqui é praticar as operações CRUD, usando como plano de fundo uma lista de tarefas.

### Como a API funciona?

1. O usuário vai criar uma conta.
2. O usuário vai criar uma categoria de tarefas.
3. O usuário vai criar suas tarefas e gerenciá-las.

<br>

Vídeo do projeto: [<img src="./src/main/resources/static/img/readme/youtube_icon.png" alt="todo list image icon" width="40px" align="center">](https://www.youtube.com/watch?v=IGdVJ5rxb6o)


<hr>
<br>

<!-- Technologies -->
## <img src="https://cdn4.iconfinder.com/data/icons/general-office/91/General_Office_48-256.png" alt="todo list image icon" width="40px" align="center"> Tecnologias

- Java 17
- Spring Boot 3.3.4
- Spring Data JPA
- Spring Web
- OpenAPI (Swagger)
- PostgreSQL
- Junit, Mockito and AssertJ
- Docker 27.0.3
- Spring Security

<hr>
<br>

<!-- Functionalities -->
## <img src="https://cdn2.iconfinder.com/data/icons/75-market-research-wildberry-vol-1/256/Guideline-256.png" alt="todo list image icon" width="40px" align="center"> Funcionalidades

- [x] Relacionamento entre Entidades
- [x] Autenticação (Basic Auth)
- [x] Create, Read, Update e Delete tasks
- [x] Validação de attributes inputados
- [x] Lancamento de exceções customizadas
- [x] Testes unitários, trabalhando com AssertJ e Mockito
- [x] Documentação no Swagger

<br>
<hr>

<!-- Diagram -->
### <img src="https://cdn3.iconfinder.com/data/icons/web-design-development-flat-colors/48/flow_chart-512.png" alt ="image icon" width="40px" align="center"> Diagrama de relacionamento entre Entidades

A aplicação trabalha com 4 entidades:

- User
- Role (*ADMIN*, *REGULAR_USER*)
- Category
- Tasks

![entities relationship diagram](./src/main/resources/static/img/readme/entities_relationship_diagram.png)

<hr>
<br>

<!-- Authentication -->
### <img src="https://cdn3.iconfinder.com/data/icons/business-startup-57/64/289-512.png" alt ="image icon" width="40px" align="center"> Authentication (Basic Auth)
Quando falamos sobre autenticação básica, estamos falando sobre trabalhar com **usuarios** e **cargos/autoridades**.

Todas as rotas estão restritas e necessitam de autorização para serem acessadas. Além disso, o usuário precisa ter um cargo específico para conseguir acessá-las.

Nossa autenticação básica vai trabalhar com 2 tipos de cargos:

- **REGULAR_USER** -> Como o nome sugere, é um usuário normal/regular. Por padrão, toda vez que um usuário é criado, esse novo usuário recebe o cargo de *REGULAR_USER*.

<br>

- **ADMIN** -> Cargo especial que permite o usuário acessar rotas 'sensíveis'.

    Ao criar um novo usuário, automaticamente é criado também um usuario chamado "admin", com a senha "123". Esse usuario 'admin' vai ser responsável em dar cargos de *admin* para usuários normais. 

<br>

Trabalhando com usuários e cargos, nós garantimos que usuários consigam manipular apenas suas próprias tarefas. Não sendo permitido acessar informações de tarefas de outros usuários.

<br>

#### <img src="https://cdn4.iconfinder.com/data/icons/small-n-flat/24/image-256.png" alt ="image icon" width="40px" align="center"> Imagens de exemplo:

**403 - Forbidden**

![forbidden image example](./src/main/resources/static/img/readme/forbidden_example.png)

<br>

**401 - Unauthorized**
![unauthorized image example](./src/main/resources/static/img/readme/unauthorized_example.png)

<br>

**406 - Not Acceptable**
![information from other person image example](./src/main/resources/static/img/readme/task_from_another_person_example.png)

<hr>
<br>

## <img src="https://cdn1.iconfinder.com/data/icons/internet-45/64/http-link-internet-domain-1024.png" alt ="image icon" width="40px" align="center"> Endpoints

### Person Controller

| Método Http | URI                                                               | Descrição                                   | Cargo necessário |
| :---:       | :---                                                              |  :---                                       |  :---:           | 
| POST        | `http://localhost:8080/v1/api/person`                             | Criar usuário                               | permissao total  |
| PUT         | `http://localhost:8080/v1/api/person/give-admin-permission/{id}`  | Dar permissao de admin para um usuario      | ADMIN            |
| GET         | `http://localhost:8080/v1/api/person`                             | Listar todos os usuarios                    | ADMIN            |

<br>

### Category Controller
| Método Http    | URI                                           | Descrição                         | Cargo necessário |
| :---:          | :---                                          |  :---                             |  :---:           | 
| POST           | `http://localhost:8080/v1/api/category`       | Criar categoria                   | REGULAR_USER     |
| GET            | `http://localhost:8080/v1/api/category`       | Listar todas as categorias        | REGULAR_USER     |
| GET            | `http://localhost:8080/v1/api/category/{id}`  | Ler uma categoria específica      | REGULAR_USER     |
| PUT            | `http://localhost:8080/v1/api/category/{id}`  | Atualizar categoria               | REGULAR_USER     |
| DELETE         | `http://localhost:8080/v1/api/category/{id}`  | Deletar categoria                 | REGULAR_USER     |

<br>

### Task Controller
| Método Http    | URI                                                | Descrição                       | Cargo necessário |
| :---:          | :---                                               |  :---                           |  :---:           | 
| POST           | `http://localhost:8080/v1/api/task`                | Criar tarefa                    | REGULAR_USER     |
| GET            | `http://localhost:8080/v1/api/task`                | Listar todas as tarefas         | REGULAR_USER     |
| PUT            | `http://localhost:8080/v1/api/task/{id}`           | Atualizar tarefa                | REGULAR_USER     |
| DELETE         | `http://localhost:8080/v1/api/task/{id}`           | Deletar tarefa                  | REGULAR_USER     |

<br>

⚠️ Como voce pode perceber acima, a única rota que você consegue acessar sem precisar de autenticação é a rota para criar um novo usuário. Para acessar qualquer outra rota, você precisa estar autenticado. Caso contrário, você irá receber o erro 401 - Unauthorized.

<hr>
<br>

<!-- Validations -->
## <img src="https://cdn4.iconfinder.com/data/icons/rating-validation-3/128/validation_stamp_approval_approve_check-512.png" alt ="image icon" width="40px" align="center"> Validações
Para evitar qualquer problema, todos os atributos foram validados.

As validações implementadas foram:
### Validações de input da entidade (Person)

-  **CREATE**
    - 'username':
        1. username não pode ser nulo
		2. username não pode ter espaços em branco
		3. username deve possuir entre 5 e 20 caracteres
		4. username deve ser único

    <br>

    - 'password':
        1. password não pode ser nulo
		2. password deve possuir entre 8 e 20 caracteres
		3. password não pode ter espaços em branco
		4. password deve possuir pelo menos 1 letra MAIÚSCULA
		5. password deve possuir pelo menos 1 número
		6. password deve ter pelo menos 2 caraceteres especiais ('!', '@', '#' '=', etc...)
<br>

### Validações de input da entidade (Category)
- **CREATE**
    - 'name':
        1. name não pode ser nulo
        2. name deve possuir entre 1 e 20 caracteres
        3. name deve ser único

<br>

- **UPDATE**
    - 'name':
        1. name deve possuir entre 1 e 20 caracteres
        2. name deve ser único

<br>

### Validações de input da entidade (Task)
- **CREATE**
    - 'title':
        1. title não pode ser nulo
        2. title deve possuir entre 1 e 50 caracteres
        3. title deve ser único

    <br>

    - 'description':
        1. description não pode ser nulo e deve possuir entre 0 e 100 caracteres

    <br>

    - 'priority':
        1. priority não pode ser nulo
		2. priority o número deve ser entre 1 e 3
		3. priority não pode ser uma String

    <br>

     - 'category_id':
        1. category_id não pode ser nulo
        2. category_id não pode ser uma String
        3. category_id não pode ser de outro usuário

	<br>

- **UPDATE**
    - 'title':
        1. title deve possuir entre 1 e 50 caracteres
        2. title deve ser único

    <br>

    - 'description' attribute
        1. description não pode ser nulo e deve possuir entre 0 e 100 caracteres

    <br>

    - 'priority'
        1. priority o número deve ser entre 1 e 3
	    2. priority não pode ser uma String

    <br>
    
    - 'category_id' attribute
        1. category_id não pode ser uma String
        2. category_id não pode ser de outro usuário
<hr>
<br>

<!-- Custom Exception Handler -->
## <img src="https://cdn4.iconfinder.com/data/icons/common-app-symbols-round-colored/1024/caveat_proviso_disclaimer_exception_app_round_colored-512.png" alt ="image icon" width="40px" align="center"> Lancamento de exceções customizadas

Todas as exceções foram personalizadas para um melhor entendimento do usuário

**Example 1**
![information from other person image example](./src/main/resources/static/img/readme/username_wrong_example.png)	
	
<br>

**Example 2**
![information from other person image example](./src/main/resources/static/img/readme/password_wrong_example.png)	

<hr>
<br>

## Documentação no Swagger

![information from other person image example](./src/main/resources/static/img/readme/swagger_documentation.png)	

<br>

Para cada operação é mostrado todas as respostas possíveis
![information from other person image example](./src/main/resources/static/img/readme/swagger_possible_responses.png)	

<hr>
<br>

<!-- Build and run -->
## <img src="https://cdn3.iconfinder.com/data/icons/start-up-4/44/rocket-256.png" alt="todo list image icon" width="40px" align="center"> Rodando a aplicação

### Requisitos
- [git](https://git-scm.com/downloads)
- [Docker](https://docs.docker.com/desktop/wsl/)

<br>

### Passo a passo

1. Clone esse repositório
    ```bash
    git clone https://github.com/lGabrielDev/API_todoList.git
    ```
<br>

2. Vá ao diretorio

    ```bash
    cd API_todoList
    ```

<br>

3. Atribua os valores nas variáveis de ambiente:

    <img alt="environment variables image example" src="./src/main/resources/static/img/readme/preencher_envs.png" width="350px">

<br>

4.  Suba os containers

    ```bash
     mvn clean package -DskipTests;  # Compila o código Java e gera o arquivo .jar
     docker pull openjdk:17-ea-10-jdk;  # Baixa a imagem base do OpenJDK 17.
     docker compose up --build; # Constrói as imagens Docker e sobe os containers
    ```

<br>

5. Acesse o swagger: `http://localhost:8080/swagger.html`

<br>

6. Crie um usuário

7. Crie uma categoria

8. Crie suas tarefas 

<br>

<!-- <div>
    <span>Se ainda tiver dúvidas, assista o video tutorial aqui</span>
    <a href="https://www.youtube.com/watch?v=IGdVJ5rxb6o&ab_channel=lGabrielDev">
        <img src="https://cdn1.iconfinder.com/data/icons/logotypes/32/youtube-256.png" alt="video icon" width="30px" align="center">
    </a>
</div> -->

<hr>
<br>

<!-- Credits -->
<h2>
    <img src="https://cdn4.iconfinder.com/data/icons/thank-you/256/Artboard_4_copy-512.png" alt="thumbs up icon image" width="40px" align="center">
    <span>Créditos</span>
</h2>

<p>As imagens usadas nesse projeto foram retiradas dos seguintes sites:</p>

- [storyset](https://storyset.com/)
- [shields.io](https://shields.io/)
- [icon-icons](https://icon-icons.com/)
- [iconfinder](https://www.iconfinder.com/)

<span>Thanks!</span>

<hr>
<br>


<!-- License -->
## <img src="https://cdn4.iconfinder.com/data/icons/jetflat-2-multimedia-vol-3/60/0042_049_license_agreement_file_document_paper_page_sheet-512.png" alt="todo list image icon" width="40px" align="center"> Licença --> MIT

O projeto está sob a licença do [MIT](LICENSE.txt).

<hr>
<br>

<!-- Author -->
## <img src="https://cdn1.iconfinder.com/data/icons/office-work-3/200/copywriting-512.png" alt="todo list image icon" width="40px" align="center"> Autor

<br>

<div align="center">
    <img src="./src/main/resources/static/img/readme/profile_circular.jpeg" alt="profile avatar" width="150px">
    <p> <a href="https://github.com/lGabrielDev">Gabriel Freitas</a> 😎 </p>
</div>
