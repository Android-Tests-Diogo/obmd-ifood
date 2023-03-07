# iFood Teste omdb Api (CI GitHub Actions)

> Neste app é possível fazer buscas de Filmes/Series e salvar favoritos.

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

- [x] Navegação entre features
- [x] Feature de detalhes

## 🚀 Frameworks utilizados

Retrofit - Chamadas Api Rest<br />
Okhttp3 - Interceptador de Http Request<br />
Room - Persistencia de dados<br />
LiveData - Dados Observáveis<br />
Kotlin Coroutines - Para operações assincronas<br />
Koin - Injeção de dependencia<br />
Mockk - Mock de dependencias<br />
Turbine - Testes unitários usando Flow<br />
Junit - Testes unitários<br />
Kotlin Serialization - Para Serialização/Deserialização de dados<br />
Glide - Carregador e cacheador de imagens<br />
Detekt - Analise Estática

## 🚀 Arquitetura

Clean + MVVM

## 😄 Importante<br>

Toda View se resume a ações/estados e por isso nesse projeto foi criado um template State e Action direcionar as ações/estados afim de serem testadas unitáriamente.

## 🚀 Projeto multi-modulo

include ':app' - Modulo principal<br />
include ':design-system:ui' - Contem componentes e resources comuns<br />
include ':features:home' - Feature de buscas/favoritos<br />
include ':features:moviedetails' - Em construção<br />
include ':core' - Camada de dados/dominio<br />
include ':common:android' - Contem todos os recursos necessários para facilitar os outro módulos no contexto de Android<br />

## 🚀 Testes unitários

- DataSources
- Repositórios
- ViewModels(Inclusive referencias de Resources)
- Mappers

## 😄 O visual está bem feio, não julguem :P obrigado pela oportunidade!

