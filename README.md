# movie-rankings-android

### Bibliotecas usadas:
- Requests: https://square.github.io/retrofit/
- Lazy loading de imagens: https://square.github.io/picasso/
- Blur de imagens: https://github.com/jrvansuita/GaussianBlur
- AndroidX Lifecycle (ViewModel e LiveData)

### Escolhas
- Foi utilizada uma arquitetura MVVM. 
- Dado que a estrutura do app é em maior parte apenas reprodução de conteúdo da API, data binding é empregado junto com os modelos recebidos do servidor.
- A pesquisa na tela principal espera 3 segundos de inatividade antes de fazer uma requisição nova para a API
- Layout inspirado em https://www.behance.net/gallery/20644723/Foundd-Redesign-The-best-way-to-find-new-movies
