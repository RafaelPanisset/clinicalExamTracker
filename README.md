# Projeto Java 8 com Tomcat e PostgreSQL

Este é um projeto Java 8 que utiliza o Tomcat como servidor de aplicações e PostgreSQL como banco de dados. O banco de dados será criado automaticamente quando você iniciar o projeto. Este guia explica como configurar e rodar o projeto usando Docker para o PostgreSQL e NetBeans como IDE.

## Requisitos

- JDK 8
- Tomcat
- Docker
- NetBeans
- Maven

## Instalação

### 1. Clonar o Repositório

Clone este repositório para sua máquina local:

```bash
git clone https://github.com/RafaelPanisset/clinicalExamTracker.git
cd clinicalExamTracker
```

### 2. Configuração do PostgreSQL com Docker
Você pode usar Docker para rodar uma instância do PostgreSQL. Execute o seguinte comando para iniciar um container PostgreSQL e mapear a porta 5432:
```bash
docker run --name postgres -e POSTGRES_PASSWORD=123 -p 5433:5432 -d postgres
```
Neste comando:
- `--name postgres-container` define o nome do container.
- `-e POSTGRES_PASSWORD=123` define a senha do usuário `postgres`.
- `-p 5433:5432` mapeia a porta do PostgreSQL no container para a porta 5433 na sua máquina local.
- `-d postgres` usa a imagem oficial do PostgreSQL e roda o container em segundo plano.

### 3. Configuração do Projeto no NetBeans

#### Abrir o NetBeans e importar o projeto:

1. No NetBeans, vá para `File` > `Open Project`.
2. Navegue até o diretório do projeto e selecione-o.

#### Configurar o Tomcat:

1. Vá para `Services` no NetBeans.
2. Clique com o botão direito em `Servers` e escolha `Add Server`.
3. Selecione `Apache Tomcat` e clique em `Next`.
4. Configure o caminho do Tomcat e clique em `Finish`.

#### Configurar o Projeto:

1. Clique com o botão direito no projeto e selecione `Properties`.
2. Vá para a aba `Run` e escolha o servidor Tomcat que você configurou.

#### Iniciar o Projeto:
1. Clique com o botão direito no projeto e selecione `Run`.
2. O NetBeans compilará e implantará o projeto no Tomcat.

### 4. Configuração do Projeto no NetBeans
Quando você iniciar o projeto pela primeira vez, ele criará automaticamente o banco e as tabelas no PostgreSQL.

### 5. Configuração do Projeto no NetBeans
Depois que o projeto for iniciado, você pode acessá-lo em:
http://localhost:8080/medilabsistemas
