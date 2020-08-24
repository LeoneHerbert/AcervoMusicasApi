<h1 align="center">
  Acervo Música API
</h1>

<h4 align="center">🎵 API para Playlist</h4>
<p align="center">  
  <img alt="Repository size" src="https://img.shields.io/github/repo-size/raelvieira/AcervoMusicasApi">
  
  <a href="https://github.com/raelvieira/AcervoMusicasApi/commits/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/raelvieira/AcervoMusicasApi">
  </a>
  
  <a href="https://github.com/raelvieira/AcervoMusicasApi/issues">
    <img alt="Repository issues" src="https://img.shields.io/github/issues/raelvieira/AcervoMusicasApi">
  </a>
</p>

<p align="center">
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-como-executar">Como excutar</a>&nbsp;&nbsp;&nbsp;
</p>

## 👨‍💻 Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:
* [Java](https://www.java.com/pt_BR/download/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Flyway](https://flywaydb.org/)
* [MySQL](https://www.mysql.com/)

## 💻 Projeto

O projeto trata-se de uma API para a criação de Playlists e cadastro de músicas. O funcionamento se dá da seguinte forma: O usuário deve ter um registro e a partir disso ele poderá salvar o nome de suas músicas junto com sua URL criar sua playlist e com suas músicas favoritas nela :)

<p>
  Como forma de facilitar os testes e conhecimento da API, foi disponibilizada uma documentação pelo Swagger através da rota <strong>/swagger-ui.html</strong> para acessá-la, basta executar o projeto!
  Além disso, também foi disponibilizada uma rota para o monitoramento da API, para acessar utilize a rota <strong>/actuator</strong>
  
  O projeto também conta com alguns testes unitários e de integração, basta executar as classes de testes que eles começaram a rodar ;)
</p>

## 🤔 Como executar

Bom, você pode executar esse projeto de duas formas, a primeira é pela execução padrão que será descrita mais abaixo e a segunda é através do docker. Eu separei um outro repositório, contendo o mesmo projeto, mas para que eu pudesse colocar lá todas as configurações que foram feitas com o docker, segue o link abaixo:

https://github.com/raelvieira/AcervoMusicasApiDocker

Nesse repositório, também foi feita uma integração com o Travis CI junto com o dockerhub (lá você encontra a tag de build com sucesso do projeto), se você decidir pegar o projeto por lá a única coisa que você vai precisar fazer basicamente é navegar até a pasta que estão os arquivos e executar o seguinte comando:

<strong>docker-compose up -d --build</strong>

a porta que está sendo exposta para a API é a 8080.

Preste atenção, em alguns ambientes, o docker impede que você acesse através da url localhost (um exemplo disso é algumas versões do windows), então, em alguns casos você vai ter que acessar pelo endereço <strong>192.168.99.100</strong>, que será o substituto do localhost, que foi o meu caso, mas verifique isso caso você não consiga ter acesso!

também você pode querer executar só pelas imagens que estão no dockerhub, os respectivos repositórios são:
- para a API : https://hub.docker.com/r/raelvieira/rest-api-acervo
- para o MYSQL : https://hub.docker.com/r/raelvieira/mysql-rest-api-acervo

<h4 align="center">Executando da maneira tradicional</h4>

Para realizar a execução do projeto é bem simples. Este projeto possui integração com o banco de dados MySQL, portanto, é importante que você verifique se todas as configurações de usuário e senha para acessar o banco estejam corretas, você pode fazer isso através do arquivo de propriedades que fica no resources do projeto, como mostra a imagem:

![image](https://user-images.githubusercontent.com/45599504/90998736-316add00-e59b-11ea-8148-63fec6df6ed9.png)

Após isso, não é necessário criar um banco de dados com o nome que está no projeto. Apenas certifique-se de que as configurações de acesso ao banco de dados estão corretas, isso inclui URL para o banco, Porta (se for necessário), Usuário e Senha (não se esqueça de alterar as do Flyway também). Após se certificar, o projeto irá se encarregar de criar o banco e montar seu esquema, pois, como já mencionado, o projeto já conta com uma tecnologia de migração de banco de dados o Flyway.

Após ter feito essa pequena verificação de configuração, você pode dá um start no projeto, para isso, se você estiver em uma IDE, basta executar a classe <strong>AcervoDeMusicasApiApplication</strong> na raiz do projeto! Se você ainda não tiver baixado as dependências, assim que você executar, provavelmente o projeto se encarregarar de baixar. Pode demorar um pouco para executar, pois, ele deverá baixar as dependências e realizar a migração do banco.


<hr>

by Israel Vieira 👋 [Entre em contato pelo Linkedin!](https://www.linkedin.com/in/israelvieiraa/)
