# Netshoes Web Scraper

O Netshoes Web Scraper é uma aplicação Java que permite a extração do título, preço, descrição e imagem de produtos da Netshoes.

## Como funciona?

Para extrair as informações de um produto, o Netshoes Web Scraper realiza as seguintes etapas:

1. Faz o download da página do produto, usando a biblioteca [Apache HttpComponents](https://hc.apache.org/index.html);
2. Extrai as informações do produto (título, preço, descrição e imagem) no HTML retornado a partir de seletores CSS, usando a biblioteca [JSoup](https://jsoup.org);
3. Retorna as informações extraídas.

A interação com o usuário é feita através de uma interface web, desenvolvida em Java, que usa a plataforma [Spring Boot](https://spring.io/projects/spring-boot) e foi feita com o framework [Vaadin](https://www.vaadin.com).

## Como executar?

Para executar a aplicação, é preciso ter o [Java 21](https://www.oracle.com/java/technologies/downloads/) instalado na sua máquina.

### Executando a release mais recente (recomendado)

A forma mais simples de obter o Netshoes Web Scraper é baixando a release mais recente disponível [aqui](https://github.com). 

Após baixar o arquivo `.jar`, basta executá-lo com o comando `java -jar netshoeswebscraper-1.0.jar`.

### Executando a partir do código fonte

Para executar o Netshoes Web Scraper a partir do código fonte, é preciso clonar o repositório para sua máquina local usando o [Git](https://git-scm.com). Para isso, execute o comando `git clone https://github.com/vinxav/netshoes-web-scraper.git` no terminal.

Após clonar o repositório e entrar na raiz do repositório, basta executar o comando `./mvnw` (no Linux ou macOS) ou `mvnw` (no Windows). O Maven irá baixar as dependências do projeto e executar a aplicação.

## Como usar?

Depois de executar o Netshoes Web Scraper, acesse o endereço `http://localhost:8080/` no navegador. A página inicial da aplicação será exibida.

Para extrair as informações de um produto, basta informar a URL do produto no campo de texto e clicar no botão "Extrair". O título, preço, imagem e descrição do produto serão exibidos na página.

## Exemplos

*Nota: os exemplos abaixo foram obtidos em 04/10/2023. Os resultados podem ser diferentes dependendo da data em que o Netshoes Web Scraper for executado.*
