## Voting Session System - Documentação da API 

A api foi desenvolvida com Spring Boot, Java 8 e banco de dados PostgreSQL e disponibilizada de forma on-line na plataforma Heroku.
Por ter sido hospedada na versão free, pode ser que a primeira requisição demore um pouco para ser realizada, uma vez que esta versão
coloca em estado de "sleeping" após 30 minutos de inatividade.

Para acessar o repositório do projeto <a href="https://github.com/kevinzamperetti/voting-session-system" target="_blank">clique aqui</a>.

Os endpoints da API foram disponibilizados no site do Postman, 
<a href="https://documenter.getpostman.com/view/10875867/SzmfYcuB?version=latest" target="_blank">clique aqui</a> para acessá-los.

A API possui endpoints para consulta dos associados, pautas, sessões de votação, associados que votaram
em uma sessão específica e contabilização dos votos por pauta.

Foi considerado que os associados já estariam cadastrados na base de dados.
Para consultá-los, basta chamar o endpoint abaixo que irá retornar a lista dos associados.

- https://voting-session-system.herokuapp.com/api/v1/associated

### Endpoints disponíveis na aplicação

**Cadastrar uma pauta:**
- POST: https://voting-session-system.herokuapp.com/api/v1/voting_subject

**Body:**
```
{
	"subject": "Pauta 1"
}
```

**Listagem das pautas existentes:**
- GET: https://voting-session-system.herokuapp.com/api/v1/voting_subject

**Cadastrar uma sessão de votação:**
- POST: https://voting-session-system.herokuapp.com/api/v1/voting_session

**Obs.:**
- Informar no objeto “votingSubject” o id da pauta a qual a sessão pertencerá.
- Se não informado o atributo “closingDate”, a duração da sessão será de 1 minuto.

**Body:**
```
{
	"name": "Seção da Pauta 4",
	"closingDate": "2020-05-09T14:00:00",
	"votingSubject" : {
		"id": 4
	}
}
```

**Listagem das sessões de votação existentes:**
- GET: https://voting-session-system.herokuapp.com/api/v1/voting_session

**Realizar votação em uma sessão de votação:**
- POST: https://voting-session-system.herokuapp.com/api/v1/associated_vote

**Obs.:**
- Informar o id do associado, id da sessão de votação e seu voto (YES ou NO).

**Body:**
```
{
	"idAssociated": 1,
	"idVotingSession": 1,
	"vote": "NO"
}
```

**Validações:**
- Este endpoint possui integração com uma API externa que verifica se o CPF do associado está habilitado ou não para votação.
- Caso não esteja será retornada um erro com a seguinte mensagem **“Document Number of Associated unable to vote.”**
- Outra validação é que somente pode ser realizada uma votação enquanto a sessão estiver em aberto, se ao tentar votar a sessão já
estiver fechada, será gerada a seguinte mensagem de erro **“Associated Vote denied Voting Session closed.”**
- O associado só poderá realizar uma votação por sessão. Ao tentar votar por mais de uma vez, será gerada a seguinte
mensagem de erro: **“Associated Vote already taken for this Voting Session”**

**Listagem dos votos realizados em uma sessão de votação:**
- GET: https://voting-session-system.herokuapp.com/api/v1/associated_vote/voting_session/{id}

**Obs.:**
- Substituir “{id}” pelo id sessão de votação.

**Contabilização de votos por pauta:**
- GET: https://voting-session-system.herokuapp.com/api/v1/voting_subject/{id}/votes

**Obs.:**
- Substituir “{id}” pelo id da pauta de votaçãoL.

### Versionamento da API

Como opção de versionamento da API foi escolhido o modelo de versionamento no path, onde é informada a versão da API logo após a URL base.
Neste projeto foi definido o modelo **“/api/{versão}”**.
Foi configurado no “application.yaml” do projeto a URL base **“/api”** e nos endpoints a versão **“/v1”**,
pois desta forma se um endpoint necessitar ser liberado em outra versão, sem que cause impacto para os clientes
que já utilizam, basta criá-lo com esta nova versão .

**Exemplo:**
- ../api/v1/voting_subject
- ../api/v2/voting_subject



